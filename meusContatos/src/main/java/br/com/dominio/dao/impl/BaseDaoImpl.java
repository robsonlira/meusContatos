package br.com.dominio.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
//import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.dominio.dao.BaseDao;
import br.com.dominio.util.Reflections;


public class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager entityManager;
	
    private Class<T> persistentClass;

    private Class<T> getPersistentClass() {

    	if (this.persistentClass == null) {
			this.persistentClass = Reflections.getGenericTypeArgument(this.getClass(), 0);
		}
    	    	
        return this.persistentClass;
    }

 
    protected EntityManager getEntityManager() {
        return entityManager;
    }

        
	protected CriteriaBuilder getCriteriaBuilder() {
		return getEntityManager().getCriteriaBuilder();
	}

	protected CriteriaQuery<T> createCriteriaQuery() {
		return getCriteriaBuilder().createQuery(getPersistentClass());
	}

	protected Query createQuery(final String ql) {
		return getEntityManager().createQuery(ql);
	}
	

	/*
	@Transactional(readOnly = false )
    public T save(T entity) {
    	try {
    	    getEntityManager().persist(entity);
            return (T) entity;			
		} catch (Exception ex) {
			getEntityManager().getTransaction().rollback();
			throw new Error("Ocorreu um erro durante a atualiza��o.", ex);
		} finally {
			
		}
    	
    }
    */
    
    public T save(T entity) {
   	    getEntityManager().persist(entity);
        return (T) entity;			
    	
    }

    public T update(T entity) {
         getEntityManager().merge(entity);
         return (T) entity;
    }
        
    
    public void delete(T entity) {
        if (!getEntityManager().contains(entity)) {
            entity = getEntityManager().merge(entity);            
        }
        try {
            getEntityManager().remove(entity);			
		} catch (Exception ex) {
			getEntityManager().getTransaction().rollback();
			throw new Error("Ocorreu um erro durante a exclus�o.", ex);
		} finally {
			
		}
    }
 
   /*
    public T update(T entity) {
    	try {
            getEntityManager().merge(entity);
            return (T) entity;
		} catch (Exception ex) {
			getEntityManager().getTransaction().rollback();
			throw new Error("Ocorreu um erro durante a atualiza��o.", ex);
		} finally {
			
		}
    }
*/
    /**
     *  findall(String query)
     *  
     * return getEntityManager().
     * createQuery(query).getResultList();
     */
    @SuppressWarnings("unchecked")
    public List<T> findall() {
    	
		final String jpql = "select this from " + getPersistentClass().getSimpleName() + " this";
		
		final Query query = getEntityManager().createQuery(jpql);
    	
		List<T> lista = query.getResultList();
		return lista;
    	
    }
        
    
    public T findByID(ID Id) {
        T entity = (T) getEntityManager().
                find(getPersistentClass(), Id);
        return entity;
    }

    
	/**
	 * Retorna o numero de objetos persistidos na classe.
	 * 
	 * @return o numero de linhas contadas
	 */
	public int countAll() {
		final Query query = getEntityManager().createQuery(
				"select count(this) from " + persistentClass.getSimpleName() + " this");
		//return (Long) query.getSingleResult();
		return countByCriteria();
	}

	protected int countByCriteria() {
		//final CriteriaBuilder builder = getCriteriaBuilder();
		//final CriteriaQuery<T> query = builder.createQuery(getPersistentClass());
		//final Root<T> entity = query.from(getPersistentClass());
		
		final Query query = getEntityManager().createQuery(
				"select count(this) from " + persistentClass.getSimpleName() + " this");
		
		return (Integer) query.getSingleResult();
		
	}
	
	
	/**
	 * Retrieves a list of entities based on a single example instance of it.
	 * <p>
	 * See below a sample of its usage:
	 * 
	 * <pre>
	 * Employee example = new Employee();
	 * example.setId(12345);
	 * return (List&lt;Employee&gt;) findByExample(example);
	 * </pre>
	 * 
	 * @param example
	 *            an entity example
	 * @return a list of entities
	 */
	protected List<T> findByExample(final T example) {
		final CriteriaQuery<T> criteria = createCriteriaByExample(example);
		return getEntityManager().createQuery(criteria).getResultList();
	}
	
	
	/**
	 * Support method which will be used for construction of criteria-based queries.
	 * 
	 * @param example
	 *            an example of the given entity
	 * @return an instance of {@code CriteriaQuery}
	 */
	private CriteriaQuery<T> createCriteriaByExample(final T example) {

		final CriteriaBuilder builder = getCriteriaBuilder();
		final CriteriaQuery<T> query = builder.createQuery(getPersistentClass());
		final Root<T> entity = query.from(getPersistentClass());

		final List<Predicate> predicates = new ArrayList<Predicate>();
		final Field[] fields = example.getClass().getDeclaredFields();

		for (Field field : fields) {

			if (!field.isAnnotationPresent(Column.class) && !field.isAnnotationPresent(Basic.class)
					&& !field.isAnnotationPresent(Enumerated.class)) {
				continue;
			}

			Object value = null;

			try {
				field.setAccessible(true);
				value = field.get(example);
			} catch (IllegalArgumentException e) {
				continue;
			} catch (IllegalAccessException e) {
				continue;
			}

			if (value == null) {
				continue;
			}

			final Predicate pred = builder.equal(entity.get(field.getName()), value);
			predicates.add(pred);
		}
		return query.where(predicates.toArray(new Predicate[0])).select(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQuery(String queryName, Object... params) {
		
		Query query = getEntityManager().createNamedQuery(queryName);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		final List<T> result = (List<T>) query.getResultList();
				
		return result;
	}
	
	@Override
	public List<T> findByNamedQueryAndNamedParams(final String queryName,
			final Map<String, ? extends Object> params) {
	
		Query query = getEntityManager().createNamedQuery(queryName);		
		
		for (final Map.Entry<String, ? extends Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}

		final List<T> result = (List<T>) query.getResultList();
		return result;
				
	}
	
    @SuppressWarnings("unchecked")
	public T findOneResult(String queryNamed, Map<String, Object> parameters) {
    	
    	T result = null;

    	Query query = getEntityManager().createNamedQuery(queryNamed);    	
    	// Method that will populate parameters if they are passed not null and empty
		if (parameters != null && !parameters.isEmpty()) {
			populateQueryParameters(query, parameters);
		}

		result = (T) query.getSingleResult();
		
    	return result;

    }
	
	private void populateQueryParameters(Query query, Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
	
	
	
    
}

package br.com.dominio.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface BaseDao<T, ID extends Serializable>  {

	
	/**
	 * Persiste uma entidade
	 * 
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract T save(T entity);

	
	@SuppressWarnings("unchecked")
	public abstract T findOneResult(String namedQuery, Map<String, Object> parameters);

	/**
	 * Apaga uma entidade
	 * @param entity
	 */
	public abstract void delete(T entity);
	
	/**
	 * Atualiza uma entidade
	 * @param entity
	 */
	public abstract T update(T entity);
	
	/**
	 * retorna um List com todos os dados
	 * @param String query
	 */	
	
	public abstract List<T> findall();

	
	/**
	 * Retorna uma entidade pelo seu ID
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract T findByID(ID Id);
	
	
	  /**
     * Count all entities
     *
     * @return the number of entities
     */
	@SuppressWarnings("unchecked")
    public abstract int countAll();
	
	 /**
     * Find using a named query
     *
     * @param queryName the name of the query
     * @param params the query parameters
     *
     * @return the list of entities
     */
    public abstract List<T> findByNamedQuery(
        final String queryName,
        Object... params
    );


    /**
     * Find using a named query
     *
     * @param queryName the name of the query
     * @param params the query parameters
     *
     * @return the list of entities
     */
    public abstract List<T> findByNamedQueryAndNamedParams(
        final String queryName,
        final Map<String, ?extends Object> params
    );

}
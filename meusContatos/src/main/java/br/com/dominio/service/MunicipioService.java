package br.com.dominio.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.com.dominio.dao.MunicipioDao;
import br.com.dominio.modelo.Estado;
import br.com.dominio.modelo.Municipio;
import br.com.dominio.util.Transacional;

public class MunicipioService implements Serializable {

    private static final long serialVersionUID = 1L;
	    
	@Inject
	private MunicipioDao dao ; 	
	
	@Transacional
	public void save(Municipio entidade) {
		dao.save(entidade);		
	}

	@Transacional
	public void delete(Municipio entidade) {
		dao.delete(entidade);		
	}

	@Transacional
	public void update(Municipio entidade) {
		dao.update(entidade);		
	}

	public List<Municipio> findAll() {
		//logger.info(String.format("Enviado novo cadastro em nome de %s", 
		  //      solicitacao.getNome()));
		return dao.findall();
	}

	public Municipio findByID(Integer Id) {		
		return dao.findByID(Id);
	}
		
	public List<Municipio> consultaMunicipios(Estado estado) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("estado", estado);			
		return dao.findByNamedQueryAndNamedParams("municipioPorEstado", params);
	}
	
	private boolean validateItem(Municipio municipio) {
		if (municipio.getEstado() == null || municipio.getEstado().getId() == null) {
			return false;
		}
		
		if (municipio.getDsMunicipio() == null ) {
			return false;
		}
		
		return true;
	}

	
	
	
}

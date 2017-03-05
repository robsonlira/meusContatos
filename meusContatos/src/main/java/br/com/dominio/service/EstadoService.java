package br.com.dominio.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.dominio.dao.EstadoDao;
import br.com.dominio.modelo.Estado;

public class EstadoService implements Serializable {

    private static final long serialVersionUID = 1L;
	    
	@Inject
	private EstadoDao dao ; 	
	

	public List<Estado> findAll() {
		return dao.findall();
	}

	public Estado findByID(Integer Id) {		
		return dao.findByID(Id);
	}
		
	
	
	
}

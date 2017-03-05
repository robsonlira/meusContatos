package br.com.dominio.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.dominio.dao.ContatoDao;
import br.com.dominio.modelo.Contato;
import br.com.dominio.util.Transacional;

public class ContatoService implements Serializable {

    private static final long serialVersionUID = 1L;
	
	@Inject
	private ContatoDao dao ; 	
	
	@Transacional
	public void save(Contato entidade) {
		dao.save(entidade);		
	}

	@Transacional
	public void delete(Contato entidade) {
		dao.delete(entidade);		
	}

	@Transacional
	public void update(Contato entidade) {
		dao.update(entidade);		
	}

	public List<Contato> findAll() {
		return dao.findall();
	}

	public Contato findByID(Integer Id) {		
		return dao.findByID(Id);
	}
		
	
	
	
}

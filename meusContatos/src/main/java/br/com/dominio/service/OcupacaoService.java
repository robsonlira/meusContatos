package br.com.dominio.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.dominio.dao.OcupacaoDao;
import br.com.dominio.modelo.Ocupacao;
import br.com.dominio.util.Transacional;

public class OcupacaoService implements Serializable {

    private static final long serialVersionUID = 1L;
	
	@Inject
	private OcupacaoDao dao ; 	
	
	@Transacional
	public void save(Ocupacao entidade) {
		dao.save(entidade);		
	}

	@Transacional
	public void delete(Ocupacao entidade) {
		dao.delete(entidade);		
	}

	@Transacional
	public void update(Ocupacao entidade) {
		dao.update(entidade);		
	}

	public List<Ocupacao> findAll() {
		return dao.findall();
	}

	public Ocupacao findByID(Integer Id) {		
		return dao.findByID(Id);
	}
		
	
	
	
}

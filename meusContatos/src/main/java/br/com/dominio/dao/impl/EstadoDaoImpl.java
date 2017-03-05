package br.com.dominio.dao.impl;

import javax.inject.Named;

import br.com.dominio.dao.EstadoDao;
import br.com.dominio.modelo.Estado;

@Named(value="estadoDao")
public class EstadoDaoImpl extends BaseDaoImpl<Estado, Integer> implements EstadoDao {

	private static final long serialVersionUID = 1L;
	
}

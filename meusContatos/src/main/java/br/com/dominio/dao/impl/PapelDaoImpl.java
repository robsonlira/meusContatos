package br.com.dominio.dao.impl;

import javax.inject.Named;

import br.com.dominio.dao.PapelDao;
import br.com.dominio.modelo.Papel;

@Named(value="papelDao")
public class PapelDaoImpl extends BaseDaoImpl<Papel, Integer> implements PapelDao {

	private static final long serialVersionUID = 1L;
	
}

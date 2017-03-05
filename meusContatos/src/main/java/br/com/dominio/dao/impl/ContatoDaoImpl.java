package br.com.dominio.dao.impl;

import javax.inject.Named;

import br.com.dominio.dao.ContatoDao;
import br.com.dominio.modelo.Contato;

@Named(value="contatoDao")
public class ContatoDaoImpl extends BaseDaoImpl<Contato, Integer> implements ContatoDao {

	private static final long serialVersionUID = 1L;

}

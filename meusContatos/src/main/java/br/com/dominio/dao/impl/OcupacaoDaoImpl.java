package br.com.dominio.dao.impl;

import javax.inject.Named;

import br.com.dominio.dao.OcupacaoDao;
import br.com.dominio.modelo.Ocupacao;

@Named(value="ocupacaoDao")
public class OcupacaoDaoImpl extends BaseDaoImpl<Ocupacao, Integer> implements OcupacaoDao {

	private static final long serialVersionUID = 1L;
	
}

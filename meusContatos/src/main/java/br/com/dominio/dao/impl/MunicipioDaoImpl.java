package br.com.dominio.dao.impl;

import javax.inject.Named;

import br.com.dominio.dao.MunicipioDao;
import br.com.dominio.modelo.Municipio;

@Named(value="municipioDao")
public class MunicipioDaoImpl extends BaseDaoImpl<Municipio, Integer> implements MunicipioDao {
	
	private static final long serialVersionUID = 1L;

}

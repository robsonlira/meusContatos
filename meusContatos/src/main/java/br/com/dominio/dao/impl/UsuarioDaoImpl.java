package br.com.dominio.dao.impl;

import javax.inject.Named;

import br.com.dominio.dao.UsuarioDao;
import br.com.dominio.modelo.Usuario;

@Named(value="usuarioDao")
public class UsuarioDaoImpl extends BaseDaoImpl<Usuario, Integer> implements UsuarioDao {

	private static final long serialVersionUID = 1L;
	
}

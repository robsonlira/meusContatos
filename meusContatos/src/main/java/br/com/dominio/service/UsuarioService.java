package br.com.dominio.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.com.dominio.dao.UsuarioDao;
import br.com.dominio.modelo.Usuario;

public class UsuarioService implements Serializable {

    private static final long serialVersionUID = 1L;
	    
	@Inject
	private UsuarioDao dao ; 	
	

	public List<Usuario> findAll() {
		return dao.findall();
	}

	public Usuario findByID(Integer Id) {		
		return dao.findByID(Id);
	}
		
	public Usuario findByLogin(String login) {
		
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("login", login );
						
		return this.dao.findOneResult("Usuario.findByLogin", parameters);	
	}	
	
	
}

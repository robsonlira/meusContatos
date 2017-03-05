package br.com.dominio.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.dominio.modelo.Papel;
import br.com.dominio.modelo.Usuario;
import br.com.dominio.service.UsuarioService;
import br.com.dominio.util.cdi.CDIServiceLocator;

/*
 * Spring-security requires an implementation of UserDetailService. 
 */

public class UserDetailsServiceImpl implements UserDetailsService {


	@Override
	public UserDetails loadUserByUsername(String username)	throws UsernameNotFoundException{
		 
		try {
			UsuarioService usuarioService =  CDIServiceLocator.getBean(UsuarioService.class);
			Usuario usuario = usuarioService.findByLogin(username) ;
						
			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;			
			
			UsuarioSistema user = null;
			
			if (usuario != null) {
				user = new UsuarioSistema(usuario, getGrupos(usuario));				
			} 
								
			return user;
			
		} catch (NoResultException e) {
			throw new UsernameNotFoundException("No such user");
		}
	
	}
	
	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Papel p : usuario.getPapels()) {
			authorities.add(new SimpleGrantedAuthority(p.getPaPapel().toUpperCase()));
		}
		
		return authorities;
	}

		

}

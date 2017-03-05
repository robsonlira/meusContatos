package br.com.dominio.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dominio.util.FacesUtil;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private String password;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletRequest request;

	@Inject
	private HttpServletResponse response;
	
	public void preRender() {
		if ("true".equals(request.getParameter("erro"))) {
			FacesUtil.exibirMensagemErro("#loginErro");
		}
	}	
	
	public void login() throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);
		
		facesContext.responseComplete();
	}
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

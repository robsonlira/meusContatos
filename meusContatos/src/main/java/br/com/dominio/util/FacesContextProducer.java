package br.com.dominio.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;

@ApplicationScoped
public class FacesContextProducer {

	@Produces
	@RequestScoped
	public FacesContext facesContextProducer() {
		return FacesContext.getCurrentInstance();
	}

	@Produces
	@RequestScoped
	public RequestContext produceRequestContext() {
		return RequestContext.getCurrentInstance();
	}
	
		
	@Produces
	@RequestScoped
	public ExternalContext getExternalContext() {
		return facesContextProducer().getExternalContext();
	}
	
	@Produces
	@RequestScoped
	public HttpServletResponse getHttpServletResponse() {
		return ((HttpServletResponse) getExternalContext().getResponse());	
	}
		
	
	
	
	
}

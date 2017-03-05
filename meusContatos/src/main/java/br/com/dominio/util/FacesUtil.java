package br.com.dominio.util;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FacesUtil {

	private static FacesUtil instance;

	public static synchronized FacesUtil getInstance() {
		if (instance == null) {
			instance = new FacesUtil();
		}
		return instance;
	}

	private static ResourceBundle getBundle() {
		Locale locale = getContext().getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle("resources.Messages", locale);		
		//ResourceBundle rb = fc.getApplication().getResourceBundle(fc, "bundle");		
		return bundle;
	}
	

	public static String getMsg(String messageId) {
		String msg = "";
		Locale locale = getContext().getViewRoot().getLocale();
		ResourceBundle bundle = getBundle();
		// ResourceBundle.getBundle("resources.message", locale);
		try {
			msg = bundle.getString(messageId);
		} catch (Exception e) {
		}
		return msg;
	}

	public static void exibirMensagemSucesso(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_INFO, mensagem);
	}

	public static void exibirMensagemAlerta(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_WARN, mensagem);
	}

	public static void exibirMensagemErro(String mensagem) {
		String msg = mensagem;
		
		if (mensagem.startsWith("#")){
			msg = getMsg(mensagem.substring(1));
		}
		
		exibirMensagem(FacesMessage.SEVERITY_ERROR, msg.toString());
	}

	private static void exibirMensagem(FacesMessage.Severity severity, String mensagem) {
		FacesMessage facesMessage = new FacesMessage(severity, mensagem, mensagem);

		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
		int size = selectOne ? entities.size() + 1 : entities.size();
		SelectItem[] items = new SelectItem[size];
		int i = 0;
		if (selectOne) {
			items[0] = new SelectItem("", "---");
			i++;
		}
		for (Object x : entities) {
			items[i++] = new SelectItem(x, x.toString());
		}
		return items;
	}

	public static FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}
		
	public static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public static String getApplicationURI() {
		try {
			String uri = getExternalContext().getRequestScheme().concat("://")
					.concat(getExternalContext().getRequestServerName()).concat(":")
					.concat(String.valueOf(getExternalContext().getRequestServerPort()));
			// .concat(getServletRequest().getContextPath());
			return uri;
		} catch (Exception ex) {
		}
		return null;
	}

	public static String getApplicationPath() {
		try {
			URI uri = new URI(getExternalContext().getRequestScheme(), null,
					getExternalContext().getRequestServerName(), getExternalContext().getRequestServerPort(),
					getExternalContext().getRequestContextPath(), null, null);
			return uri.toASCIIString();
		} catch (Exception ex) {
		}
		return null;
	}

	/**
	 * Recupera o caminho físico no servidor de aplicações.
	 * 
	 * @return Caminho físico no servidor de aplicações
	 */
	public static String getRealPath() {
		ServletContext servletContext = (ServletContext) getExternalContext().getContext();
		return servletContext.getRealPath("/");
	}

}

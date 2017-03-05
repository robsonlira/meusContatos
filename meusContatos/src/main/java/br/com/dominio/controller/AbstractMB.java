package br.com.dominio.controller;

import java.io.Serializable;

import javax.enterprise.context.spi.Context;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;
//import org.slf4j.Logger;

import br.com.dominio.util.FacesUtil;

public abstract class AbstractMB implements Serializable {

   // @Inject
   // protected Logger logger;	
    
    @Inject
    private RequestContext requestContext;
		
	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";
			
	//public AbstractMB() {	
	//}

	protected void displayErrorMessageToUser(String message) {
		FacesUtil messageUtil = new FacesUtil();
		messageUtil.exibirMensagemErro(message);
	}
	
	protected void displayInfoMessageToUser(String message) {
		FacesUtil messageUtil = new FacesUtil();
		messageUtil.exibirMensagemSucesso(message);
	}	
	
	protected void displayWarnMessageToUser(String message) {
		FacesUtil messageUtil = new FacesUtil();
		messageUtil.exibirMensagemAlerta(message);
	}
	
	protected void closeDialog(){
		this.requestContext.addCallbackParam(KEEP_DIALOG_OPENED, false);
	}
	
	protected void keepDialogOpen(){
		this.requestContext.addCallbackParam(KEEP_DIALOG_OPENED, true);		
	}
	
	
	   /**
     * Atualiza um componente pelo seu id no contexto atual
     *
     * @param componentId o id do componente
     */
    protected void updateComponent(String componentId) {
        this.requestContext.update(componentId);
    }	
	
	
}

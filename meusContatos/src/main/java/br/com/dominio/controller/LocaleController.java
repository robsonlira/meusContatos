package br.com.dominio.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class LocaleController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Locale currentLocale = new Locale("pt", "BR");
	private String language = "pt";
	// private Locale currentLocale =
	// FacesContext.getCurrentInstance().getViewRoot().getLocale();

	public LocaleController() {
		super();
		currentLocale = new Locale("pt", "BR");
	}

	
	public void onLanguageChange(ValueChangeEvent vce){
		if (vce != null) {
			String l = vce.getNewValue().toString();
			setLanguage(l);
			
			changeLanguage(l);
			
		} else {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("pt", "BR"));			
		}
	}	
	
	public void changeLanguage(String language) {

		setLanguage(language);
		
		switch (language) {
		case "en":
			englishLocale();
			break;
		case "pt":
			portugueseLocale();
			break;
		case "fr":
			francaisLocale();
			break;
		default:
			portugueseLocale();
			break;
		}

	}

	public void englishLocale() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		currentLocale = Locale.US;
		viewRoot.setLocale(currentLocale);
	}

	public void portugueseLocale() {
		currentLocale = new Locale("pt", "BR");

		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("pt", "BR"));

	}

	public void francaisLocale() {
		currentLocale = new Locale("fr", "FR");
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("fr", "FR"));

	}

	public String getLanguagem() {
		return currentLocale.getLanguage();
	}

	public Locale getCurrentLocale() {
		return currentLocale;
	}

	public void setCurrentLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}

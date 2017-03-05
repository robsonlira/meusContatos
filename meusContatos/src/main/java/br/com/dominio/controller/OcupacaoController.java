package br.com.dominio.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dominio.modelo.Municipio;
import br.com.dominio.modelo.Ocupacao;
import br.com.dominio.service.OcupacaoService;

@Named(value = "ocupacaoC")
@ViewScoped
public class OcupacaoController extends AbstractMB {

	private int totalRegistros;
	private List<Ocupacao> elementos;
	private Ocupacao registroSelecionado;

	@Inject
	private OcupacaoService ocupacaoService;

	private Ocupacao ocupacao = new Ocupacao();

	public OcupacaoController() {
	}

	public void salvar() {
		try {
			ocupacaoService.save(ocupacao);
			closeDialog();
			displayInfoMessageToUser("Registro criado com sucesso!");
			load();
			preparaInclusao();

		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ocorreu um erro ao atualizar o registro. "
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Ocupacao> getElementos() {
		if (elementos == null) {
			load();
		}

		return elementos;
	}

	private void load() {
		elementos = ocupacaoService.findAll();
	}

	public void preparaInclusao() {
		this.ocupacao = new Ocupacao();
	}

	public Ocupacao getOcupacao() {

		if (ocupacao == null) {
			ocupacao = new Ocupacao();
		}

		return ocupacao;
	}

	public Ocupacao getRegistroSelecionado() {
		if (registroSelecionado == null) {
			registroSelecionado = new Ocupacao();
		}

		return registroSelecionado;
	}

	@FacesConverter(forClass = Ocupacao.class)
	public static class OcupacaoControllerConverter implements Converter {

		public Object getAsObject(FacesContext facesContext,
				UIComponent component, String value) {

			if (value != null) {
				return this.getAttributesFrom(component).get(value);
			}
			return null;

		}

		java.lang.Integer getKey(String value) {
			java.lang.Integer key;
			key = Integer.valueOf(value);
			return key;
		}

		String getStringKey(java.lang.Integer value) {
			StringBuffer sb = new StringBuffer();
			sb.append(value);
			return sb.toString();
		}

		public String getAsString(FacesContext facesContext,
				UIComponent component, Object object) {
			if (object == null) {
				return null;
			}
			if (object instanceof Ocupacao) {
				Ocupacao o = (Ocupacao) object;
				return getStringKey(o.getId());
			} else {
				throw new IllegalArgumentException("object " + object
						+ " is of type " + object.getClass().getName()
						+ "; expected type: "
						+ OcupacaoController.class.getName());
			}
		}

		protected Map<String, Object> getAttributesFrom(UIComponent component) {
			return component.getAttributes();
		}

	}

}

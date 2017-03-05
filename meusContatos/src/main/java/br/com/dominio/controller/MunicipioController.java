package br.com.dominio.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.dominio.misc.ex.InternalServiceError;
import br.com.dominio.modelo.Estado;
import br.com.dominio.modelo.Municipio;
import br.com.dominio.service.EstadoService;
import br.com.dominio.service.MunicipioService;

@Named(value = "municipioC")
@ViewScoped
public class MunicipioController extends AbstractMB {

	private static final long serialVersionUID = 1L;

	private int totalRegistros;
	private List<Municipio> elementos;
	private List<Estado> estados;
	private Municipio registroSelecionado;
	private Municipio municipio;

	@Inject
	private MunicipioService municipioService;

	@Inject
	private EstadoService estadoService;

	public MunicipioController() {
	}

	public void salvar() {
		System.out.println("salvar()");
		try {
			municipioService.save(municipio);
			closeDialog();
			displayInfoMessageToUser("Registro criado com sucesso!");
			load();
			preparaInclusao();
		} catch (InternalServiceError e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ocorreu um erro ao atualizar o registro. "
					+ e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ocorreu um erro ao atualizar o registro. "
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			this.municipioService.delete(municipio);
			closeDialog();
			displayInfoMessageToUser("Registro excluido com sucesso!");
			load();
			preparaInclusao();
		} catch (InternalServiceError e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ocorreu um erro ao tentar excluir o registro. "
					+ e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ocorreu um erro ao tentar excluir o registro. "
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public void selectRegistro(SelectEvent evt) {

		try {
			if (evt.getObject() != null) {
				this.registroSelecionado = (Municipio) evt.getObject();
			} else {
				this.registroSelecionado = null;
			}
		} catch (Exception e) {
			this.registroSelecionado = null;
		}
	}

	public List<Municipio> getElementos() {
		if (elementos == null) {
			load();
		}

		return elementos;
	}

	private void load() {
		elementos = municipioService.findAll();
	}

	public void preparaInclusao() {
		System.out.println("preparaInclusao()");
		this.municipio = new Municipio();				
	}

	public void preparaAlteracao() {
		this.municipio = getRegistroSelecionado();
	}

	public void unselect() {
		this.registroSelecionado = null;
	}

	public Municipio getMunicipio() {
		System.out.println("getMunicipio()");
		if (municipio == null) {
			municipio = new Municipio();
		}

		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		System.out.println("setMunicipio()");
		this.municipio = municipio;
	}

	public Municipio getRegistroSelecionado() {
		if (registroSelecionado == null) {
			registroSelecionado = new Municipio();
		}

		return registroSelecionado;
	}

	public void setRegistroSelecionado(Municipio registroSelecionado) {
		this.registroSelecionado = registroSelecionado;
	}

	public List<Estado> getEstados() {
		if (estados == null) {
			estados = estadoService.findAll();
		}
		return estados;
	}


}

package br.com.dominio.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import br.com.dominio.misc.ex.InternalServiceError;
import br.com.dominio.modelo.Contato;
import br.com.dominio.modelo.Estado;
import br.com.dominio.modelo.Municipio;
import br.com.dominio.modelo.Ocupacao;
import br.com.dominio.service.AddressFinder;
import br.com.dominio.service.ContatoService;
import br.com.dominio.service.EstadoService;
import br.com.dominio.service.MunicipioService;
import br.com.dominio.service.OcupacaoService;
import br.com.dominio.service.AddressFinder.Address;
import br.com.dominio.util.FacesUtil;
import br.com.dominio.util.Util;

@Named(value = "contatoC")
@ViewScoped
public class ContatoController extends AbstractMB {

	private int totalRegistros;
	private Estado estado;
	private List<Contato> elementos;
	private List<Estado> estados;
	private List<Municipio> municipios;
	private List<Ocupacao> ocupacoes;

	private Contato registroSelecionado;
	private Contato contato;

	private String image;
	private static final String EXTENSION = "jpg";

	@Inject
	private ContatoService contatoService;

	@Inject
	private EstadoService estadoService;

	@Inject
	private MunicipioService municipioService;

	@Inject
	private OcupacaoService ocupacaoService;
	
    @Inject
    private AddressFinder addressFinderService;	

	@PostConstruct
	public void init() {
		System.out.println("init()");
		this.estado = new Estado();
		this.estados = estadoService.findAll();
		this.estado = this.estados.get(1);
	}

	public ContatoController() {
		System.out.println("ContatoController()");

		// preparaInclusao();
	}

	public void salvar() {

		try {
			contatoService.update(contato);
			closeDialog();
			displayInfoMessageToUser("Registro criado com sucesso!");
			load();
			preparaInclusao();
		} catch (InternalServiceError e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ocorreu um erro ao atualizar o registro. " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ocorreu um erro ao atualizar o registro. " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			this.contatoService.delete(contato);
			closeDialog();
			displayInfoMessageToUser("Registro excluido com sucesso!");
			load();
			preparaInclusao();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ocorreu um erro ao tentar excluir o registro. " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void selectRegistro(SelectEvent evt) {
		//logger.debug("selectRegistro()");

		try {
			if (evt.getObject() != null) {
				this.registroSelecionado = (Contato) evt.getObject();
			} else {
				this.registroSelecionado = null;
			}
		} catch (Exception e) {
			this.registroSelecionado = null;
			//logger.error(e.getMessage(), e);
		}
	}

	public List<Contato> getElementos() {
		if (elementos == null) {
			load();
		}
		return elementos;
	}

	private void load() {
		elementos = contatoService.findAll();
	}

	public void preparaInclusao() {
		this.contato = new Contato();
		this.contato.setConSexo("M");
		this.estado = this.estados.get(1);
	}

	public void preparaAlteracao() {
		//logger.debug("preparaAlteracao()");
		this.contato = getRegistroSelecionado();

		if (this.contato.getMunicipio() != null) {
			this.estado = contato.getMunicipio().getEstado();
		} else {
			this.estado = this.estados.get(1);
		}
	}

	public void unselect() {
		this.registroSelecionado = null;
	}

	public Contato getContato() {
		System.out.println("getContato()");

		if (contato == null) {
			contato = new Contato();
		}
		return contato;
	}

	public Contato getRegistroSelecionado() {
		//logger.debug("getRegistroSelecionado()");
		if (registroSelecionado == null) {
			//logger.debug("getRegistroSelecionado() - novo contato()");
			registroSelecionado = new Contato();
		}
		//logger.debug("Contato: " + registroSelecionado.getConNome());
		return registroSelecionado;
	}

	public void setRegistroSelecionado(Contato registroSelecionado) {
		//logger.debug("setRegistroSelecionado()");
		this.registroSelecionado = registroSelecionado;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		if (estados == null) {
			estados = estadoService.findAll();
		}
		return estados;
	}

	public List<Municipio> getMunicipios() {

		if (this.getEstado() != null) {
			municipios = municipioService.consultaMunicipios(this.getEstado());
		} else {
			municipios = new ArrayList<>();
		}
		return municipios;
	}

	public List<Ocupacao> getOcupacoes() {

		if (ocupacoes == null) {
			ocupacoes = ocupacaoService.findAll();
		}
		return ocupacoes;
	}

	public void upload(FileUploadEvent event) {

		try {
			UploadedFile file = event.getFile();
			String nomeCaminhoArquivoString = file.getFileName();
			String[] quebrarCaminho = nomeCaminhoArquivoString.split("\\\\");
			String arquivo = quebrarCaminho[quebrarCaminho.length - 1];
			String ext[] = arquivo.split("\\.");
			String novoArquivo = Util.generateGUID() + "." + ext[1].toString();

			Path target = null;
			try {
				target = Util.criarArquivo("D:\\Lixo\\" + novoArquivo);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Files.copy(file.getInputstream(), target, StandardCopyOption.REPLACE_EXISTING);

			contato.setConArquivo(novoArquivo.toString());
			contato.setConFoto(event.getFile().getContents());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Chama o servico de busca dos enderecos e completa o endereco do caboclo
	 */
	public void doAddressFind() {

		try {
			final Address endereco = this.addressFinderService.findAddressByZipcode(this.contato.getConCep());

			this.contato.setConEndereco( Util.semAcento(endereco.getLogradouro()));
			this.contato.setConBairro( Util.semAcento(endereco.getBairro()));
			String codIbge = endereco.getIbge();
			String nomeCidade = Util.semAcento(endereco.getLocalidade());		
			String uf = endereco.getUf().toString().trim();
			// Procura o estado na lista e atualiza o objeto
			for (int x = 0; x < estados.size(); x++) {				
				if (estados.get(x).getSiglaEstado().equals(uf.toString())) {
            	   this.estado = estados.get(x);
            	   // Atualiza a lista de Municipios
            	   getMunicipios();
                } 
			}
			
			for (int x = 0; x < municipios.size(); x++) {				
				if (municipios.get(x).getCodIbge().equals(codIbge.toString())) {
            	   this.contato.setMunicipio(municipios.get(x)); 
                } 
			}
			
			// estado
			//this.contato.setProvince(endereco.getFullUfName());
			// cidade
			//this.contato.setCity(endereco.getLocalidade());
			this.updateComponent("formCadastro:endereco");
			this.updateComponent("formCadastro:bairro");
			this.updateComponent("formCadastro:panelUf");
			this.updateComponent("formCadastro:panelMunicipio");

		} catch (Exception ex) {
			//this.logger.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
	}

	@FacesConverter(forClass = Contato.class)
	public static class ContatoControllerConverter implements Converter {

		public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {

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

		public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
			if (object == null) {
				return null;
			}
			if (object instanceof Contato) {
				Contato o = (Contato) object;

				return getStringKey(o.getId());
			} else {
				throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName()
						+ "; expected type: " + ContatoController.class.getName());
			}
		}

		protected Map<String, Object> getAttributesFrom(UIComponent component) {
			return component.getAttributes();
		}

	}

}

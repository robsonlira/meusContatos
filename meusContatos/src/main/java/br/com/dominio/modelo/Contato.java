package br.com.dominio.modelo;


import java.awt.Image;
import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;


/**
 * The persistent class for the contato database table.
 * 
 */
@Entity
public class Contato implements BaseEntity, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="con_codigo")
	private Integer id;

	@NotEmpty
	@Column(name="con_nome", length=50)
	private String conNome;	

	@Column(name="con_email", length=50)
	private String conEmail;	
	
	@Column(name="con_endereco", length=60)
	private String conEndereco;

	@Column(name="con_bairro", length=30)
	private String conBairro;

	@Column(name="con_fone", length=20)
	private String conFone;

	@Column(name="con_cel", length=20)
	private String conCel;

	@Column(name="con_arquivo", length=120)
	private String conArquivo;
		
	@Column(name="con_foto")
	private byte[] conFoto;

    @Temporal( TemporalType.DATE)
	@Column(name="con_dtcad")
	private Date conDtcad;

	@Column(name="usr_codigo")
	private Integer usrCodigo;

	@Column(name="con_obs")
	private String conObs;

	@Column(name="con_sexo", length=1)
	private String conSexo;

	@Column(name="con_numero", length=10)
	private String conNumero;

	@Column(name="con_cep", length=12)
	private String conCep;	

	@Column(name="con_skype", length=30 )
	private String conSkype;	
	
	@Column(name="con_facebook", length=40)
	private String conFace;	
	
	@Column(name="con_whatsapp", length=20)
	private String conWhatsapp;	

	@Column(name="con_familia", length=1)
	private String conFamilia;		
	
	@Temporal( TemporalType.DATE)
	@Column(name="con_dtnasc")
	private Date conDtnasc;
	
	//bi-directional many-to-one association to Municipio
	//@ManyToOne(fetch=FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name="id_municipio")
	private Municipio municipio;		
	
	//bi-directional many-to-one association to Municipio
	//@ManyToOne(fetch=FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name="id_ocupacao")
	private Ocupacao ocupacao;		
	
	@Transient
	private Image foto;	
	
	public Contato() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConBairro() {
		return this.conBairro;
	}

	public void setConBairro(String conBairro) {
		this.conBairro = conBairro;
	}

	public String getConCel() {
		return this.conCel;
	}

	public void setConCel(String conCel) {
		this.conCel = conCel;
	}


	public Date getConDtcad() {
		return this.conDtcad;
	}

	public void setConDtcad(Date conDtcad) {
		this.conDtcad = conDtcad;
	}

	public String getConEmail() {
		return this.conEmail;
	}

	public void setConEmail(String conEmail) {
		this.conEmail = conEmail;
	}

	public String getConEndereco() {
		return this.conEndereco;
	}

	public void setConEndereco(String conEndereco) {
		this.conEndereco = conEndereco;
	}

	public String getConFone() {
		return this.conFone;
	}

	public void setConFone(String conFone) {
		this.conFone = conFone;
	}

	public byte[] getConFoto() {
		return this.conFoto;
	}

	public void setConFoto(byte[] conFoto) {
		this.conFoto = conFoto;
	}

	public String getConNome() {
		return this.conNome;
	}

	public void setConNome(String conNome) {
		this.conNome = conNome;
	}


	public Integer getUsrCodigo() {
		return this.usrCodigo;
	}

	public void setUsrCodigo(Integer usrCodigo) {
		this.usrCodigo = usrCodigo;
	}

	public String getConObs() {
		return conObs;
	}

	public void setConObs(String conObs) {
		this.conObs = conObs;
	}

	public Date getConDtnasc() {
		return conDtnasc;
	}

	public void setConDtnasc(Date conDtnasc) {
		this.conDtnasc = conDtnasc;
	}	
	
	
	public Image getFoto() {
		return foto;
	}

	public void setFoto(Image foto) {
		this.foto = foto;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Ocupacao getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(Ocupacao ocupacao) {
		this.ocupacao = ocupacao;
	}

	public String getConSexo() {
		return conSexo;
	}

	public void setConSexo(String conSexo) {
		this.conSexo = conSexo;
	}

	public String getConNumero() {
		return conNumero;
	}

	public void setConNumero(String conNumero) {
		this.conNumero = conNumero;
	}

	public String getConCep() {
		return conCep;
	}

	public void setConCep(String conCep) {
		this.conCep = conCep;
	}
	
	public String getConSkype() {
		return conSkype;
	}

	public void setConSkype(String conSkype) {
		this.conSkype = conSkype;
	}

	public String getConFace() {
		return conFace;
	}

	public void setConFace(String conFace) {
		this.conFace = conFace;
	}

	public String getConWhatsapp() {
		return conWhatsapp;
	}

	public void setConWhatsapp(String conWhatsapp) {
		this.conWhatsapp = conWhatsapp;
	}

	public String getConFamilia() {
		return conFamilia;
	}

	public void setConFamilia(String conFamilia) {
		this.conFamilia = conFamilia;
	}

	
	public String getConArquivo() {
		return conArquivo;
	}
	
	public void setConArquivo(String conArquivo) {
		this.conArquivo = conArquivo;
	}
	
	
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
    public String toString() {
        return String.format("%s[id=%d]", getClass().getSimpleName(), getId() );
    }
	

}
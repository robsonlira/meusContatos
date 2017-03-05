package br.com.dominio.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the cep database table.
 * 
 */
@Entity
@Table(name = "cep")
public class Cep implements BaseEntity, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="cep", length=8)
	private String cep;
	
	@Column(name="logradouro", length=120)
	private String logradouro;

	@Column(name="complemento", length=50)
	private String complemento;
	
	@Column(name="bairro", length=60)
	private String bairro;
	
	@Column(name="localidade", length=50)
	private String localidade;
		
	@Column(name="uf", length=2 )
	private String uf;

	@Column(name="ibge", length=10 )
	private String igbe;

	@Column(name="gia", length=50 )
	private String gia;

	@Temporal(TemporalType.DATE)
	@Column(name="datacad")
	private Date datacad;
	
    public Cep() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIgbe() {
		return igbe;
	}

	public void setIgbe(String igbe) {
		this.igbe = igbe;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}
	
	public Date getDatacad() {
		return datacad;
	}
	
	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
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
		Cep other = (Cep) obj;

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
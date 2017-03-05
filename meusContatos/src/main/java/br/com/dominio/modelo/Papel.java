package br.com.dominio.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Papel implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pa_id")
	private Integer id;

	@Column(name = "pa_descricao", length = 60)
	private String paDescricao;

	@Temporal(TemporalType.DATE)
	@Column(name = "pa_dtcad")
	private Date paDtcad;

	@Column(name = "pa_papel", length = 30)
	private String paPapel;

	// bi-directional many-to-many association to Usuario
	// @ManyToMany(mappedBy="papels")
	// private List<Usuario> usuarios;

	public Papel() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer Id) {
		this.id = Id;
	}

	public String getPaDescricao() {
		return this.paDescricao;
	}

	public void setPaDescricao(String paDescricao) {
		this.paDescricao = paDescricao;
	}

	public Date getPaDtcad() {
		return this.paDtcad;
	}

	public void setPaDtcad(Date paDtcad) {
		this.paDtcad = paDtcad;
	}

	public String getPaPapel() {
		return this.paPapel;
	}

	public void setPaPapel(String paPapel) {
		this.paPapel = paPapel;
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
		Papel other = (Papel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

}
package br.com.dominio.modelo;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the ocupacao database table.
 * 
 */
@Entity
public class Ocupacao implements BaseEntity, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    	
	@Column(name="id_ocupacao")
	private Integer id;

	@NotEmpty
	@Column(name="ds_ocupacao")
	private String dsOcupacao;

	//bi-directional many-to-one association to Contato
	//@OneToMany(mappedBy="ocupacao")
	//private List<Contato> contatos;	
	
	public Ocupacao() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDsOcupacao() {
		return this.dsOcupacao;
	}

	public void setDsOcupacao(String dsOcupacao) {
		this.dsOcupacao = dsOcupacao;
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
		Ocupacao other = (Ocupacao) obj;
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
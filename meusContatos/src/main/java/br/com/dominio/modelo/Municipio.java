package br.com.dominio.modelo;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the municipio database table.
 * 
 */
@Entity
@Table(name = "municipio")
@NamedQuery(
		name = "municipioPorEstado",
		query = "select m from Municipio m where m.estado = :estado order by m.dsMunicipio"
	)
public class Municipio implements BaseEntity, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
	@Column(name="id_municipio")
	private Integer id;

	@NotEmpty
	@Column(name="ds_municipio", length=60)
	private String dsMunicipio;

	@NotEmpty
	@Column(name="cod_ibge", length=10)
	private String codIbge;
	
	//bi-directional many-to-one association to Estado
	//@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="id_estado")
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;		
	
	//bi-directional many-to-one association to Municipio
	//@OneToMany(mappedBy="municipio")
	//private List<Contato> contatos;
		

    public Municipio() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDsMunicipio() {
		return this.dsMunicipio;
	}

	public void setDsMunicipio(String dsMunicipio) {
		this.dsMunicipio = dsMunicipio;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}	
    
	public String getCodIbge() {
		return codIbge;
	}

	public void setCodIbge(String codIbge) {
		this.codIbge = codIbge;
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
		Municipio other = (Municipio) obj;
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
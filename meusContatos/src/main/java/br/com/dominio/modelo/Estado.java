package br.com.dominio.modelo;

import java.io.Serializable;

import javax.persistence.*;



/**
 * The persistent class for the estado database table.
 * 
 */
@Entity
@Table(name = "estado")
public class Estado implements BaseEntity, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_estado")
	private Integer id;
		
	@Column(name="sigla_estado", length=2 )
	private String siglaEstado;

	@Column(name="ds_estado", length=50)
	private String dsEstado;

	//bi-directional many-to-one association to Municipio
	//@OneToMany(mappedBy="estado")
	//private List<Municipio> municipios;

    public Estado() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSiglaEstado() {
		return siglaEstado;
	}

	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}

	public String getDsEstado() {
		return this.dsEstado;
	}

	public void setDsEstado(String dsEstado) {
		this.dsEstado = dsEstado;
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
		Estado other = (Estado) obj;

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
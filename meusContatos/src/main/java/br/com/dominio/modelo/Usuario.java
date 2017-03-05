package br.com.dominio.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name="Usuario.findByLogin",
			query="SELECT u FROM Usuario u WHERE u.login = :login")
})
public class Usuario implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_codigo")
	private Integer id;

	@Column(name="usr_administrador", length = 1)
	private String usrAdministrador;

	@Column(name="usr_ativo")
	private Boolean usrAtivo;

	@Column(name="usr_dias_expiracao")
	private Integer usrDiasExpiracao;

	@Column(name="usr_email", length = 80)
	private String usrEmail;

	@Column(name="usr_foto")
	private byte[] usrFoto;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="usr_inicio_expiracao")
	private Date usrInicioExpiracao;

	@Column(nullable = false, name="usr_login", length = 20)
	private String login;

	@Column(name="usr_nome", length = 80)
	private String usrNome;

	@Column(nullable = false, name="usr_senha", length = 32)
	private String password;

	@Column(name="usr_tipo_expiracao", length = 1)
	private String usrTipoExpiracao;
	
	//bi-directional many-to-many association to Papel
    @ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="usuario_papel"
		, joinColumns={
			@JoinColumn(name="usr_codigo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="pa_id")
			}
		)
    
	private List<Papel> papels = new ArrayList<>();

    
    public Usuario() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsrAdministrador() {
		return this.usrAdministrador;
	}

	public void setUsrAdministrador(String usrAdministrador) {
		this.usrAdministrador = usrAdministrador;
	}

	public Boolean getUsrAtivo() {
		return this.usrAtivo;
	}

	public void setUsrAtivo(Boolean usrAtivo) {
		this.usrAtivo = usrAtivo;
	}

	public Integer getUsrDiasExpiracao() {
		return this.usrDiasExpiracao;
	}

	public void setUsrDiasExpiracao(Integer usrDiasExpiracao) {
		this.usrDiasExpiracao = usrDiasExpiracao;
	}

	public String getUsrEmail() {
		return this.usrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	public byte[] getUsrFoto() {
		return this.usrFoto;
	}

	public void setUsrFoto(byte[] usrFoto) {
		this.usrFoto = usrFoto;
	}

	public Date getUsrInicioExpiracao() {
		return this.usrInicioExpiracao;
	}

	public void setUsrInicioExpiracao(Date usrInicioExpiracao) {
		this.usrInicioExpiracao = usrInicioExpiracao;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getUsrNome() {
		return this.usrNome;
	}

	public void setUsrNome(String usrNome) {
		this.usrNome = usrNome;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsrTipoExpiracao() {
		return this.usrTipoExpiracao;
	}

	public void setUsrTipoExpiracao(String usrTipoExpiracao) {
		this.usrTipoExpiracao = usrTipoExpiracao;
	}

	public List<Papel> getPapels() {
		return this.papels;
	}

	public void setPapels(List<Papel> papels) {
		this.papels = papels;
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
		Usuario other = (Usuario) obj;
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
package personaltrainer.model;
// Generated 14-ago-2017 23:50:33 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Coach generated by hbm2java
 */
public class Coach implements java.io.Serializable {

	private Integer id;
	private Catalogo catalogo;
	private String nombre;
	private String pass;
	private String email;
	private String admin;
	private String avatar;
	private Date fechaRegistro;
	private Set<ClienteCoach> clienteCoaches = new HashSet<ClienteCoach>(0);
	private Set<Mensajes> mensajeses = new HashSet<Mensajes>(0);

	public Coach() {
	}

	public Coach(Catalogo catalogo, String nombre, String pass, String email, String admin, Date fechaRegistro) {
		this.catalogo = catalogo;
		this.nombre = nombre;
		this.pass = pass;
		this.email = email;
		this.admin = admin;
		this.fechaRegistro = fechaRegistro;
	}

	public Coach(Catalogo catalogo, String nombre, String pass, String email, String admin, String avatar,
			Date fechaRegistro, Set<ClienteCoach> clienteCoaches, Set<Mensajes> mensajeses) {
		this.catalogo = catalogo;
		this.nombre = nombre;
		this.pass = pass;
		this.email = email;
		this.admin = admin;
		this.avatar = avatar;
		this.fechaRegistro = fechaRegistro;
		this.clienteCoaches = clienteCoaches;
		this.mensajeses = mensajeses;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Catalogo getCatalogo() {
		return this.catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdmin() {
		return this.admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Set<ClienteCoach> getClienteCoaches() {
		return this.clienteCoaches;
	}

	public void setClienteCoaches(Set<ClienteCoach> clienteCoaches) {
		this.clienteCoaches = clienteCoaches;
	}

	public Set<Mensajes> getMensajeses() {
		return this.mensajeses;
	}

	public void setMensajeses(Set<Mensajes> mensajeses) {
		this.mensajeses = mensajeses;
	}

}
package personaltrainer.model;
// Generated 14-ago-2017 23:50:33 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cliente generated by hbm2java
 */
public class Cliente implements java.io.Serializable {

	private Integer id;
	private Catalogo catalogoByNivelFisico;
	private Catalogo catalogoBySexo;
	private Catalogo catalogoByObjetivo;
	private String nombre;
	private String apellidos;
	private String email;
	private String pass;
	private Integer edad;
	private Integer altura;
	private Byte peso;
	private Date fechaRegistro;
	private String baja;
	private String activo;
	private Set<ClienteCoach> clienteCoaches = new HashSet<ClienteCoach>(0);
	private Set<Mensajes> mensajeses = new HashSet<Mensajes>(0);
	private Set<PlanCliente> planClientes = new HashSet<PlanCliente>(0);

	public Cliente() {
	}

	public Cliente(Catalogo catalogoByNivelFisico, Catalogo catalogoBySexo, Catalogo catalogoByObjetivo, String nombre,
			String apellidos, String email, String pass, Date fechaRegistro, String baja) {
		this.catalogoByNivelFisico = catalogoByNivelFisico;
		this.catalogoBySexo = catalogoBySexo;
		this.catalogoByObjetivo = catalogoByObjetivo;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.pass = pass;
		this.fechaRegistro = fechaRegistro;
		this.baja = baja;
	}

	public Cliente(Catalogo catalogoByNivelFisico, Catalogo catalogoBySexo, Catalogo catalogoByObjetivo, String nombre,
			String apellidos, String email, String pass, Integer edad, Integer altura, Byte peso, Date fechaRegistro,
			String baja, Set<ClienteCoach> clienteCoaches, Set<Mensajes> mensajeses, Set<PlanCliente> planClientes) {
		this.catalogoByNivelFisico = catalogoByNivelFisico;
		this.catalogoBySexo = catalogoBySexo;
		this.catalogoByObjetivo = catalogoByObjetivo;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.pass = pass;
		this.edad = edad;
		this.altura = altura;
		this.peso = peso;
		this.fechaRegistro = fechaRegistro;
		this.baja = baja;
		this.clienteCoaches = clienteCoaches;
		this.mensajeses = mensajeses;
		this.planClientes = planClientes;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Catalogo getCatalogoByNivelFisico() {
		return this.catalogoByNivelFisico;
	}

	public void setCatalogoByNivelFisico(Catalogo catalogoByNivelFisico) {
		this.catalogoByNivelFisico = catalogoByNivelFisico;
	}

	public Catalogo getCatalogoBySexo() {
		return this.catalogoBySexo;
	}

	public void setCatalogoBySexo(Catalogo catalogoBySexo) {
		this.catalogoBySexo = catalogoBySexo;
	}

	public Catalogo getCatalogoByObjetivo() {
		return this.catalogoByObjetivo;
	}

	public void setCatalogoByObjetivo(Catalogo catalogoByObjetivo) {
		this.catalogoByObjetivo = catalogoByObjetivo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Integer getAltura() {
		return this.altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	public Byte getPeso() {
		return this.peso;
	}

	public void setPeso(Byte peso) {
		this.peso = peso;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getBaja() {
		return this.baja;
	}

	public void setBaja(String baja) {
		this.baja = baja;
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

	public Set<PlanCliente> getPlanClientes() {
		return this.planClientes;
	}

	public void setPlanClientes(Set<PlanCliente> planClientes) {
		this.planClientes = planClientes;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

}

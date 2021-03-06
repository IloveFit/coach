package personaltrainer.model;
// Generated 14-ago-2017 23:50:33 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Mensajes generated by hbm2java
 */
public class Mensajes implements java.io.Serializable {

	private static final long serialVersionUID = 2733504437578920227L;
	
	private Integer id;
	private Cliente cliente;
	private Coach coach;
	private Mensajes mensajes;
	private String asunto;
	private String mensaje;
	private Date fecha;
	private String leido;
	private int tipoMensaje;
	private Set<Mensajes> mensajeses = new HashSet<Mensajes>(0);

	public Mensajes() {
	}

	public Mensajes(Cliente cliente, Coach coach, String asunto, String mensaje, Date fecha, String leido) {
		this.cliente = cliente;
		this.coach = coach;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.fecha = fecha;
		this.leido = leido;
	}

	public Mensajes(Cliente cliente, Coach coach, Mensajes mensajes, String asunto, String mensaje, Date fecha,
			String leido, Set<Mensajes> mensajeses) {
		this.cliente = cliente;
		this.coach = coach;
		this.mensajes = mensajes;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.fecha = fecha;
		this.leido = leido;
		this.mensajeses = mensajeses;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Coach getCoach() {
		return this.coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public Mensajes getMensajes() {
		return this.mensajes;
	}

	public void setMensajes(Mensajes mensajes) {
		this.mensajes = mensajes;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getLeido() {
		return this.leido;
	}

	public void setLeido(String leido) {
		this.leido = leido;
	}

	public Set<Mensajes> getMensajeses() {
		return this.mensajeses;
	}

	public void setMensajeses(Set<Mensajes> mensajeses) {
		this.mensajeses = mensajeses;
	}

	public int getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(int tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

}

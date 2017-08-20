package personaltrainer.action.administrator;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import personaltrainer.dao.ClienteDao;
import personaltrainer.dao.ClienteDaoImpl;
import personaltrainer.dao.MensajeDao;
import personaltrainer.dao.MensajeDaoImpl;
import personaltrainer.model.Cliente;
import personaltrainer.model.Coach;
import personaltrainer.model.Mensajes;
import personaltrainer.util.Constantes;

public class MensajesAction extends ActionSupport implements Action, ModelDriven<Mensajes>, ServletContextAware, ServletRequestAware, SessionAware {

	private static final long serialVersionUID = 3815272144438393467L;

	public Mensajes mensaje;
	
	private HttpServletRequest servletRequest;
	
	private ServletContext ctx;
	
	private Map<String, Object> session;
	
	private int mensajesNoLeidos;
	
	private List<Cliente> listClientes;
	
	private Map<String, List<Mensajes>> mapaMensajes;
	
	public MensajesAction() {
		super();
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String refrescarCabecera() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		MensajeDao mensajeDao = new MensajeDaoImpl(sf);
		
		try {
			Coach coach = (Coach) session.get(Constantes.COACH_SESION);
			
			setMensajesNoLeidos(mensajeDao.mensajesNoLeidos(coach.getId()));
		} catch (Exception e) {
			e.printStackTrace();
			setMensajesNoLeidos(0);
		} 
		
		
		return SUCCESS;
	}
	
	public String inicio() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ClienteDao clienteDao = new ClienteDaoImpl(sf);
		
		try {
			
			setListClientes(clienteDao.listarCliente());
			
			//lista de mensajes del coach
			setMapaMensajes(ordenarMensajes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String alta() {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		MensajeDao mensajeDao = new MensajeDaoImpl(sf);
		ClienteDao clienteDao = new ClienteDaoImpl(sf);
		
		try {
			//fecha del mensaje
			getModel().setFecha(new Date());
			//marcado como no leido
			getMensaje().setLeido(Constantes.NO);
			mensajeDao.altaMensaje(getModel());
			setMensaje(new Mensajes());
			
			addActionMessage(getText("mensaje.alta.ok"));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("mensaje.alta.ko"));
			
			return ERROR;
		} finally {
			//lista de mensajes del coach
			setMapaMensajes(ordenarMensajes());
			//recargamos la lista de clientes
			setListClientes(clienteDao.listarCliente());
		}
		
		return SUCCESS;
	}
	
	public void validateAlta() {
		if (StringUtils.isEmpty(getModel().getAsunto())) {
			addFieldError("mensaje.asunto", getText("form.campo.obligatorio"));
		}
		if (StringUtils.isEmpty(getModel().getMensaje())) {
			addFieldError("mensaje.mensaje", getText("form.campo.obligatorio"));
		}
		
		//recargamos la lista de clientes
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ClienteDao clienteDao = new ClienteDaoImpl(sf);
		setListClientes(clienteDao.listarCliente());
		//lista de mensajes del coach
		setMapaMensajes(ordenarMensajes());
	}
	
	public String leer () {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		MensajeDao mensajeDao = new MensajeDaoImpl(sf);
		
		try {
			//marcado como leido
			mensajeDao.leerMensaje(getModel().getId());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		} 
		return SUCCESS;
	}
	
	public Map<String, List<Mensajes>> ordenarMensajes () {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		MensajeDao mensajeDao = new MensajeDaoImpl(sf);
		String pattern = "dd MMM yyyy";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    Map<String, List<Mensajes>> mapaMensajes = new LinkedHashMap<String, List<Mensajes>>();
		
		try {
			Coach coach = (Coach) session.get(Constantes.COACH_SESION);
			
			//recuperamos los mensajes del coach
			List<Mensajes> listaMensajes = mensajeDao.listarMensajesCoach(coach.getId());
			
			for (Mensajes mensaje : listaMensajes) {
				String fechaMensaje = format.format(mensaje.getFecha());
				if (mapaMensajes.containsKey(fechaMensaje)) {
					mapaMensajes.get(fechaMensaje).add(mensaje);
				} else {
					mapaMensajes.put(fechaMensaje, new ArrayList<Mensajes>());
					mapaMensajes.get(fechaMensaje).add(mensaje);
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		
		return mapaMensajes;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
	}
	@Override
	public Mensajes getModel() {
		return mensaje;
	}

	public int getMensajesNoLeidos() {
		return mensajesNoLeidos;
	}

	public void setMensajesNoLeidos(int mensajesNoLeidos) {
		this.mensajesNoLeidos = mensajesNoLeidos;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

	public List<Cliente> getListClientes() {
		return listClientes;
	}

	public void setListClientes(List<Cliente> listClientes) {
		this.listClientes = listClientes;
	}

	public Mensajes getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensajes mensaje) {
		this.mensaje = mensaje;
	}

	public Map<String, List<Mensajes>> getMapaMensajes() {
		return mapaMensajes;
	}

	public void setMapaMensajes(Map<String, List<Mensajes>> mapaMensajes) {
		this.mapaMensajes = mapaMensajes;
	}
	
		
}
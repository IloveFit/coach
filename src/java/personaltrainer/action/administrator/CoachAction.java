package personaltrainer.action.administrator;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import personaltrainer.dao.CatalogoDao;
import personaltrainer.dao.CatalogoDaoImpl;
import personaltrainer.dao.ClienteDao;
import personaltrainer.dao.ClienteDaoImpl;
import personaltrainer.dao.CoachDao;
import personaltrainer.dao.CoachDaoImpl;
import personaltrainer.dao.MensajeDao;
import personaltrainer.dao.MensajeDaoImpl;
import personaltrainer.dao.PlanDao;
import personaltrainer.dao.PlanDaoImpl;
import personaltrainer.model.Catalogo;
import personaltrainer.model.Cliente;
import personaltrainer.model.Coach;
import personaltrainer.model.Mensajes;
import personaltrainer.model.Plan;
import personaltrainer.util.Constantes;
import personaltrainer.util.EncriptarPass;


public class CoachAction extends ActionSupport implements Action, ModelDriven<Coach>, ServletContextAware, ServletRequestAware {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3366280364881131900L;
	
	private ServletContext ctx;
	
	private Coach coach;
	
	private List<Catalogo> tipoCoach;
	private List<Coach> listadoCoach;
	
	private File avatar;
	private String avatarContentType;
	private String avatarFileName;
	
	private HttpServletRequest servletRequest;
	
	private boolean admin;
	
	private int numeroClientes;
	private int numeroMensajes;
	
	private String nuevaPass;
	private String repiteNuevaPass;
	
	private String tabSelected;
	
	/**
	 * parametros zona superior
	 */
	private int numeroClientesTop;
	private int numeroPlanesTop;
	private int numeroMensajesTop;
	
	
	public CoachAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * Login de la parte de administracion
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		try {
			SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
			CoachDao coachDAO = new CoachDaoImpl(sf);
			CatalogoDao catalogoDao = new CatalogoDaoImpl(sf);
			
			Coach coach = coachDAO.getCoachByEmail(getModel().getEmail());
	
			if (coach != null) {
				String pass = getModel().getPass();
				if (!coach.getPass().equals(EncriptarPass.encriptarPassword(pass))) {
					addActionError(getText("login.pass.error"));
					return ERROR;
				} else {
					Map<String, Object> session = (Map<String, Object>) ActionContext.getContext().get("session");
					session.put(Constantes.COACH_SESION, coach);
					session.put(Constantes.COMBO_TIPO_COACH, catalogoDao.listarCatalogo(Constantes.TIP_COACH));
					session.put(Constantes.COMBO_TIPO_EJERCICIO, catalogoDao.listarCatalogo(Constantes.TIP_EJERCICIO));
					session.put(Constantes.COMBO_NIVEL, catalogoDao.listarCatalogo(Constantes.TIP_NIVEL_FISICO));
					session.put(Constantes.COMBO_OBJETIVO, catalogoDao.listarCatalogo(Constantes.TIP_OBJETIVO));
					session.put(Constantes.COMBO_TIP_MAQUINA, catalogoDao.listarCatalogo(Constantes.TIP_MAQUINA));
				}
			} else {
				addActionError(getText("login.email.error"));
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			addActionError(getText("login.error"));
			return ERROR;
		}
	}
	
	/**
	 * 
	 */
	public void validateLogin() {
		if (StringUtils.isEmpty(getModel().getEmail())) {
			addFieldError("coach.email", getText("login.mail.obligatorio"));
		}
		if (StringUtils.isEmpty(getModel().getPass())) {
			addFieldError("coach.pass", getText("login.pass.obligatorio"));
		}
	}
	
	public String registro() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		try {
			CoachDao coachDao = new CoachDaoImpl(sf);
			setListadoCoach(coachDao.listarCoach());
			
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return SUCCESS;
	}
	
	/**
	 * Alta de un nuevo coach
	 * @return
	 * @throws Exception
	 */
	public String alta() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		CoachDao coachDao = new CoachDaoImpl(sf);
		
		try {			
			
			//guardamos el avatar en el servidor
			if (StringUtils.isEmpty(getAvatarFileName())) {
				coach.setAvatar(Constantes.AVATAR_DEFAULT);
			} else {
				String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
				filePath += Constantes.RUTA_AVATAR;
				System.out.println("Server path:" + filePath);
				File fileToCreate = new File(filePath, getAvatarFileName());
	
				FileUtils.copyFile(getAvatar(), fileToCreate);
				
				//incluimos el nombre del avatar
				coach.setAvatar(getAvatarFileName());
			}
			
			Coach coach = getCoach();
			//vemos si es administrador
			if (isAdmin()) {
				coach.setAdmin(Constantes.SI);
			} else {
				coach.setAdmin(Constantes.NO);
			}
			//encriptamos la pass
			coach.setPass(EncriptarPass.encriptarPassword(coach.getPass()));
			//fecha de registro
			coach.setFechaRegistro(new Date());
			coachDao.altaCoach(coach);
			
			addActionMessage(getText("coach.alta.ok"));
			
			//limpiamos el formulario
			setCoach(new Coach());
			
			//recargamos la lista de coach
			setListadoCoach(coachDao.listarCoach());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("coach.alta.ko"));
			
			//recargamos la lista de coach
			setListadoCoach(coachDao.listarCoach());

			return ERROR;
		} 
		
		return SUCCESS;
	}
	
	/**
	 * 
	 */
	public void validateAlta() {
		if (StringUtils.isEmpty(getModel().getNombre())) {
			addFieldError("coach.nombre", getText("coach.alta.nombre.obligatorio"));
		}
		if (StringUtils.isEmpty(getModel().getEmail())) {
			addFieldError("coach.email", getText("login.mail.obligatorio"));
		}
		if (StringUtils.isEmpty(getModel().getPass())) {
			addFieldError("coach.pass", getText("login.pass.obligatorio"));
		} else if (getModel().getPass().length() < 4 || getModel().getPass().length() > 12) {
			addFieldError("coach.pass", getText("coach.pass.length"));
		}
		if (StringUtils.isEmpty(getRepiteNuevaPass())) {
			addFieldError("repiteNuevaPass", getText("form.campo.obligatorio"));
		} else if (getRepiteNuevaPass().length() < 4 || getRepiteNuevaPass().length() > 12) {
			addFieldError("repiteNuevaPass", getText("coach.pass.length"));
		}
		
		if (!getModel().getPass().equals(getRepiteNuevaPass())) {
			addFieldError("repiteNuevaPass", getText("coach.modificar.pass.iguales"));
		}
		
		//recargamos la lista de coach
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		CoachDao coachDao = new CoachDaoImpl(sf);
		setListadoCoach(coachDao.listarCoach());
	}
	
	/**
	 * Eliminar un coach de BBDD
	 * @return
	 */
	public String eliminar() {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		CoachDao coachDao = new CoachDaoImpl(sf);
		
		try {
			coachDao.eliminarCoach(getModel().getId());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("coach.eliminar.ko"));
			
			//recargamos la lista de coach
			setListadoCoach(coachDao.listarCoach());

			return ERROR;
		} 
		addActionMessage(getText("coach.eliminar.ok"));
		//recargamos la lista de coach
		setListadoCoach(coachDao.listarCoach());
		
		return SUCCESS;
	}
	
	/**
	 * Modificar un coach
	 * @return
	 * @throws Exception
	 */
	public String modificar() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		CoachDao coachDao = new CoachDaoImpl(sf);
		
		try {			
			Coach coach = coachDao.getCoach(getModel().getId());
			
			if (StringUtils.isNotEmpty(getAvatarFileName())) {
				//guardamos el avatar en el servidor
				String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
				filePath += Constantes.RUTA_AVATAR;
				File fileToCreate = new File(filePath, getAvatarFileName());

				FileUtils.copyFile(getAvatar(), fileToCreate);
				
				coach.setAvatar(getAvatarFileName());
			}
			
			//vemos si es administrador
			if (isAdmin()) {
				coach.setAdmin(Constantes.SI);
			} else {
				coach.setAdmin(Constantes.NO);
			}
			coach.setNombre(getModel().getNombre());
			coach.setEmail(getModel().getEmail());
			coach.setCatalogo(getModel().getCatalogo());
			
			coachDao.modificarCoach(coach);
			
			addActionMessage(getText("coach.modificar.ok"));
			
			//limpiamos el formulario
			setCoach(new Coach());
			
			//recargamos la lista de coach
			setListadoCoach(coachDao.listarCoach());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("coach.modificar.ko"));
			
			//recargamos la lista de coach
			setListadoCoach(coachDao.listarCoach());

			return ERROR;
		} 
		
		return SUCCESS;
	}
	
	/**
	 * 
	 */
	public void validateModificar() {
		if (StringUtils.isEmpty(getModel().getNombre())) {
			addFieldError("coach.nombre", getText("coach.alta.nombre.obligatorio"));
		}
		if (StringUtils.isEmpty(getModel().getEmail())) {
			addFieldError("coach.email", getText("login.mail.obligatorio"));
		}
		
		//recargamos la lista de coach
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		CoachDao coachDao = new CoachDaoImpl(sf);
		setListadoCoach(coachDao.listarCoach());
	}
	
	public String logout() {
		Map<String, Object> session = (Map<String, Object>) ActionContext.getContext().get("session");
		session.remove(Constantes.COACH_SESION);
		
		return SUCCESS;
	}
	
	/**
	 * Pantalla del perfil del coach
	 * 
	 * @return
	 * @throws Exception
	 */
	public String perfil() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		try {
			CoachDao coachDao = new CoachDaoImpl(sf);
			setListadoCoach(coachDao.listarCoach());
			
			Coach coach = (Coach) servletRequest.getSession().getAttribute(Constantes.COACH_SESION);
			List<Integer> datosCoach = coachDao.datosPerfilCoach(coach.getId());
			
			//numero de clientes del coach
			setNumeroClientes(datosCoach.get(0));
			//numero de mensajes del coach
			setNumeroMensajes(datosCoach.get(1));

		} catch (Exception e) {
			e.printStackTrace();
			
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * Pantalla modificacion datos personales del coach
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modificarDatos() throws Exception {
		//marcamos la pestaña 1
		setTabSelected("1");
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		try {
			CoachDao coachDao = new CoachDaoImpl(sf);
			setListadoCoach(coachDao.listarCoach());
			
			Coach coach = coachDao.getCoach(getModel().getId());
			coach.setNombre(getModel().getNombre());
			coach.setEmail(getModel().getEmail());
			coach.setCatalogo(getModel().getCatalogo());
			
			coachDao.modificarCoach(coach);
			
			//actualizamos los datos de sesion
			servletRequest.getSession().setAttribute(Constantes.COACH_SESION, coachDao.getCoach(getModel().getId()));
			
			addActionMessage(getText("coach.modificar.ok"));

		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("coach.modificar.ko"));
			
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public void validateModificarDatos() {
		//marcamos la pestaña 1
		setTabSelected("1");
		validateModificar();
	}
	
	/**
	 * Pantalla modificacion datos personales del coach
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modificarPassword() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		//marcamos la pestaña 1
		setTabSelected("2");
		try {
			CoachDao coachDao = new CoachDaoImpl(sf);
			setListadoCoach(coachDao.listarCoach());
			
			String passEncriptada = EncriptarPass.encriptarPassword(getNuevaPass());
			
			Coach coach = coachDao.getCoach(getModel().getId());
			coach.setPass(passEncriptada);
			
			coachDao.modificarCoach(coach);
			
			//actualizamos los datos de sesion
			servletRequest.getSession().setAttribute(Constantes.COACH_SESION, coachDao.getCoach(getModel().getId()));
			
			addActionMessage(getText("coach.modificar.pass.ok"));

		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("coach.modificar.pass.ko"));
			
			return ERROR;
		} finally {
			getModel().setPass("");
			setNuevaPass("");
			setRepiteNuevaPass("");
		}
		
		return SUCCESS;
	}
	
	public void validateModificarPassword() {
		//marcamos la pestaña 2
		setTabSelected("2");
		
		if (StringUtils.isEmpty(getModel().getPass())) {
			addFieldError("coach.pass", getText("form.campo.obligatorio"));
		} else {
			Coach coach = (Coach) servletRequest.getSession().getAttribute(Constantes.COACH_SESION);	
			if (!coach.getPass().equals(EncriptarPass.encriptarPassword(getModel().getPass()))) {
				addFieldError("coach.pass", getText("caoch.modificar.pass.incorrecta"));
			}
		}
		
		if (StringUtils.isEmpty(getNuevaPass())) {
			addFieldError("nuevaPass", getText("form.campo.obligatorio"));
		} else if (getNuevaPass().length() < 4 || getNuevaPass().length() > 12) {
			addFieldError("nuevaPass", getText("coach.pass.length"));
		}
		if (StringUtils.isEmpty(getRepiteNuevaPass())) {
			addFieldError("repiteNuevaPass", getText("form.campo.obligatorio"));
		} else if (getRepiteNuevaPass().length() < 4 || getRepiteNuevaPass().length() > 12) {
			addFieldError("repiteNuevaPass", getText("coach.pass.length"));
		}
		if (!getNuevaPass().equals(getRepiteNuevaPass())) {
			addFieldError("nuevaPass", getText("coach.modificar.pass.iguales"));
		}
	}
	
	public String modificarAvatar () {
		//marcamos la pestaña 2
		setTabSelected("2");
		
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		CoachDao coachDao = new CoachDaoImpl(sf);
		
		try {
			Coach coach = coachDao.getCoach(getModel().getId());
			
			//si no selecciona ninguna imagen ponemos la de por defecto
			if (StringUtils.isEmpty(getAvatarFileName())) {
				coach.setAvatar(Constantes.AVATAR_DEFAULT);
			} else {
				//guardamos el avatar en el servidor
				String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
				filePath += Constantes.RUTA_AVATAR;
				System.out.println("Server path:" + filePath);
				File fileToCreate = new File(filePath, getAvatarFileName());

				FileUtils.copyFile(getAvatar(), fileToCreate);
				
				//incluimos el nombre del avatar
				coach.setAvatar(getAvatarFileName());
			}
			
			coachDao.modificarCoach(coach);
			
			//actualizamos los datos de sesion
			servletRequest.getSession().setAttribute(Constantes.COACH_SESION, coachDao.getCoach(getModel().getId()));
			
			addActionMessage(getText("coach.modificar.ok"));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("coach.modificar.ko"));
			
			return ERROR;
		}	
		
		return SUCCESS;
	}
	
	public String top() {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		
		try {
			//ponemos en sesion los datos de la capa top
			//numero de clientes
			ClienteDao clienteDao = new ClienteDaoImpl(sf);
			List<Cliente> listaClientes = clienteDao.listarCliente();
			int numClientes = listaClientes.size();
			setNumeroClientesTop(numClientes);
			
			//numero de planes
			PlanDao planDao = new PlanDaoImpl(sf);
			List<Plan> listaPlanes = planDao.listarPlan();
			int numPlanes = listaPlanes.size();
			setNumeroPlanesTop(numPlanes);
			
			//numero de mensajes de tipo cliente
			MensajeDao mensajeDao = new MensajeDaoImpl(sf);
			List<Mensajes> listaMensajes = mensajeDao.listarMensajesTipoCliente();
			int numMensajes = listaMensajes.size();
			setNumeroMensajes(numMensajes);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	@Override
	public Coach getModel() {
		return coach;
	}
	
	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
	}
	
	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public List<Catalogo> getTipoCoach() {
		return tipoCoach;
	}

	public void setTipoCoach(List<Catalogo> tipoCoach) {
		this.tipoCoach = tipoCoach;
	}

	public File getAvatar() {
		return avatar;
	}

	public void setAvatar(File avatar) {
		this.avatar = avatar;
	}

	public String getAvatarContentType() {
		return avatarContentType;
	}

	public void setAvatarContentType(String avatarContentType) {
		this.avatarContentType = avatarContentType;
	}

	public String getAvatarFileName() {
		return avatarFileName;
	}

	public void setAvatarFileName(String avatarFileName) {
		this.avatarFileName = avatarFileName;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<Coach> getListadoCoach() {
		return listadoCoach;
	}

	public void setListadoCoach(List<Coach> listadoCoach) {
		this.listadoCoach = listadoCoach;
	}

	public int getNumeroClientes() {
		return numeroClientes;
	}

	public void setNumeroClientes(int numeroClientes) {
		this.numeroClientes = numeroClientes;
	}

	public int getNumeroMensajes() {
		return numeroMensajes;
	}

	public void setNumeroMensajes(int numeroMensajes) {
		this.numeroMensajes = numeroMensajes;
	}

	public String getNuevaPass() {
		return nuevaPass;
	}

	public void setNuevaPass(String nuevaPass) {
		this.nuevaPass = nuevaPass;
	}

	public String getRepiteNuevaPass() {
		return repiteNuevaPass;
	}

	public void setRepiteNuevaPass(String repiteNuevaPass) {
		this.repiteNuevaPass = repiteNuevaPass;
	}

	public String getTabSelected() {
		return tabSelected;
	}

	public void setTabSelected(String tabSelected) {
		this.tabSelected = tabSelected;
	}

	public int getNumeroClientesTop() {
		return numeroClientesTop;
	}

	public void setNumeroClientesTop(int numeroClientesTop) {
		this.numeroClientesTop = numeroClientesTop;
	}

	public int getNumeroPlanesTop() {
		return numeroPlanesTop;
	}

	public void setNumeroPlanesTop(int numeroPlanesTop) {
		this.numeroPlanesTop = numeroPlanesTop;
	}

	public int getNumeroMensajesTop() {
		return numeroMensajesTop;
	}

	public void setNumeroMensajesTop(int numeroMensajesTop) {
		this.numeroMensajesTop = numeroMensajesTop;
	}
	
}
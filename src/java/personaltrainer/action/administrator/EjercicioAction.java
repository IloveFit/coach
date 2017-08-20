package personaltrainer.action.administrator;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.apache.struts2.views.freemarker.tags.SetModel;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import personaltrainer.dao.CatalogoDao;
import personaltrainer.dao.CatalogoDaoImpl;
import personaltrainer.dao.CoachDao;
import personaltrainer.dao.CoachDaoImpl;
import personaltrainer.dao.EjercicioDao;
import personaltrainer.dao.EjercicioDaoImpl;
import personaltrainer.dao.RutinaEjercicioDao;
import personaltrainer.dao.RutinaEjercicioDaoImpl;
import personaltrainer.model.Catalogo;
import personaltrainer.model.Coach;
import personaltrainer.model.Ejercicio;
import personaltrainer.model.RutinaEjercicio;
import personaltrainer.util.Constantes;
import personaltrainer.util.EncriptarPass;


public class EjercicioAction extends ActionSupport implements Action, ModelDriven<Ejercicio>, ServletContextAware, ServletRequestAware {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3366280364881131900L;
	
	private ServletContext ctx;
	
	private Ejercicio ejercicio;
	
	private List<Ejercicio> tipoEjercicio;
	private List<Ejercicio> listadoEjercicio;
	
	private File video;
	private String videoContentType;
	private String videoFileName;
	
	private HttpServletRequest servletRequest;
	
	public EjercicioAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String registro() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		try {
			EjercicioDao ejercicioDao = new EjercicioDaoImpl(sf);
			setListadoEjercicio(ejercicioDao.listarEjercicio());
			
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return SUCCESS;
	}
	
	/**
	 * Alta de un nuevo ejercicio
	 * @return
	 * @throws Exception
	 */
	public String alta() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		EjercicioDao ejercicioDao = new EjercicioDaoImpl(sf);
		
		try {			
			
			//guardamos el video en el servidor
			String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
			filePath += Constantes.RUTA_VIDEOS;
			System.out.println("Server path:" + filePath);
			File fileToCreate = new File(filePath, getVideoFileName());

			FileUtils.copyFile(getVideo(), fileToCreate);
			
			Ejercicio ejercicio= getModel();
			ejercicio.setVideo(getVideoFileName());
			//introducimos la descripcion corta
			ejercicio.setDescripcionCorta(getModel().getDescripcion().substring(0, 199));
			//marcamos el ejercicio como activo
			ejercicio.setActivo(Constantes.SI);
		
			ejercicioDao.altaEjercicio(ejercicio);
			
			addActionMessage(getText("ejercicio.alta.ok"));
			
			//limpiamos el formulario
			setEjercicio(new Ejercicio());
			
			//recargamos la lista de ejercicios
			setListadoEjercicio(ejercicioDao.listarEjercicio());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("ejercicio.alta.ko"));
			
			//recargamos la lista de ejercicios
			setListadoEjercicio(ejercicioDao.listarEjercicio());

			return ERROR;
		} 
		
		return SUCCESS;
	}
	
	/**
	 * 
	 */
	public void validateAlta() {
		if (StringUtils.isEmpty(getModel().getNombre())) {
			addFieldError("ejercicio.nombre", getText("coach.alta.nombre.obligatorio"));
		}  else if(getModel().getNombre().length() > Constantes.NOM_EJER_LENGTH) {
			addFieldError("ejercicio.nombre", getText("ejercicio.nombre.length"));
		}
		if (StringUtils.isEmpty(getModel().getDescripcion())) {
			addFieldError("ejercicio.descripcion", getText("ejercicio.descripcion.obligatorio"));
		} else if(getModel().getDescripcion().length() > Constantes.DESC_EJER_LENGTH) {
			addFieldError("ejercicio.descripcion", getText("ejercicio.descripcion.length"));
		}
		if (StringUtils.isEmpty(getVideoFileName())) {
			addFieldError("video", getText("ejercicio.descripcion.length"));
		}
				
		//recargamos la lista de coach
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		EjercicioDao ejercicioDao = new EjercicioDaoImpl(sf);
		setListadoEjercicio(ejercicioDao.listarEjercicio());
	}
	
	/**
	 * Eliminar un coach de BBDD
	 * @return
	 */
	public String eliminar() {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		EjercicioDao ejercicioDao = new EjercicioDaoImpl(sf);
		
		try {
			ejercicioDao.eliminarEjercicio(getModel().getId());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("ejercicio.eliminar.ko"));
			
			//recargamos la lista de ejercicios
			setListadoEjercicio(ejercicioDao.listarEjercicio());

			return ERROR;
		} 
		addActionMessage(getText("ejercicio.eliminar.ok"));
		//recargamos la lista de ejercicios
		setListadoEjercicio(ejercicioDao.listarEjercicio());
		
		return SUCCESS;
	}
	
	public void validateEliminar () {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		RutinaEjercicioDao rutinaEjercicioDao = new RutinaEjercicioDaoImpl(sf);
		EjercicioDao ejercicioDao = new EjercicioDaoImpl(sf);
		
		//comprobamos si el ejercicio esta incluido en alguna rutina
		List<RutinaEjercicio> rutinaEjercicios = rutinaEjercicioDao.listarRutinaEjercicioByEjercicio(getModel().getId());
		
		if (!rutinaEjercicios.isEmpty()) {
			addActionError(getText("ejercicio.eliminar.restriccion"));
		}
		
		//recargamos la lista de ejercicios
		setListadoEjercicio(ejercicioDao.listarEjercicio());
	}
	
	/**
	 * Modificar un coach
	 * @return
	 * @throws Exception
	 */
	public String modificar() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		EjercicioDao ejercicioDao = new EjercicioDaoImpl(sf);
		
		try {			
			Ejercicio ejercicio = ejercicioDao.getEjercicio(getModel().getId());
			
			
			ejercicio.setNombre(getModel().getNombre());
			ejercicio.setDescripcion(getModel().getDescripcion());
			ejercicio.setCatalogo(getModel().getCatalogo());
			ejercicio.setDescripcionCorta(getModel().getDescripcion().substring(0, 199));
			
			if (StringUtils.isNotEmpty(getVideoFileName())) {
				//guardamos el video en el servidor
				String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
				filePath += Constantes.RUTA_VIDEOS;
				System.out.println("Server path:" + filePath);
				File fileToCreate = new File(filePath, getVideoFileName());

				FileUtils.copyFile(getVideo(), fileToCreate);
				
				ejercicio.setVideo(getVideoFileName());
			}
			
			ejercicioDao.modificarEjercicio(ejercicio);
			
			addActionMessage(getText("ejercicio.modificar.ok"));
			
			//limpiamos el formulario
			setEjercicio(new Ejercicio());
			
			//recargamos la lista de ejercicios
			setListadoEjercicio(ejercicioDao.listarEjercicio());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("ejercicio.modificar.ko"));
			
			//recargamos la lista de ejercicios
			setListadoEjercicio(ejercicioDao.listarEjercicio());

			return ERROR;
		} 
		
		return SUCCESS;
	}
	
	/**
	 * 
	 */
	public void validateModificar() {
		if (StringUtils.isEmpty(getModel().getNombre())) {
			addFieldError("ejercicio.nombre", getText("coach.alta.nombre.obligatorio"));
		}
		if (StringUtils.isEmpty(getModel().getDescripcion())) {
			addFieldError("ejercicio.descripcion", getText("ejercicio.descripcion.obligatorio"));
		} else if(getModel().getDescripcion().length() > Constantes.DESC_EJER_LENGTH) {
			addFieldError("ejercicio.descripcion", getText("ejercicio.descripcion.length"));
		}
		
		//recargamos la lista de ejercicios
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		EjercicioDao ejercicioDao = new EjercicioDaoImpl(sf);
		setListadoEjercicio(ejercicioDao.listarEjercicio());
	}
	
	public String getrutina() {
		return SUCCESS;
	}
	
	@Override
	public Ejercicio getModel() {
		return ejercicio;
	}
	
	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
	}
	
	public Ejercicio getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(Ejercicio ejercicio) {
		this.ejercicio = ejercicio;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public List<Ejercicio> getTipoEjercicio() {
		return tipoEjercicio;
	}

	public void setTipoEjercicio(List<Ejercicio> tipoEjercicio) {
		this.tipoEjercicio = tipoEjercicio;
	}

	public List<Ejercicio> getListadoEjercicio() {
		return listadoEjercicio;
	}

	public void setListadoEjercicio(List<Ejercicio> listadoEjercicio) {
		this.listadoEjercicio = listadoEjercicio;
	}

	public File getVideo() {
		return video;
	}

	public void setVideo(File video) {
		this.video = video;
	}

	public String getVideoContentType() {
		return videoContentType;
	}

	public void setVideoContentType(String videoContentType) {
		this.videoContentType = videoContentType;
	}

	public String getVideoFileName() {
		return videoFileName;
	}

	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}
	
}
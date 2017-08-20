package personaltrainer.action.administrator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import personaltrainer.dao.EjercicioDao;
import personaltrainer.dao.EjercicioDaoImpl;
import personaltrainer.dao.RutinaDao;
import personaltrainer.dao.RutinaDaoImpl;
import personaltrainer.model.Ejercicio;
import personaltrainer.model.Rutina;
import personaltrainer.model.RutinaEjercicio;


public class RutinaAction extends ActionSupport implements Action, ModelDriven<Rutina>, ServletContextAware, ServletRequestAware {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3366280364881131900L;
	
	private ServletContext ctx;
	
	private Rutina rutina;
	
	private List<Rutina> listadoRutina;
	
	private List<RutinaEjercicio> listRutinaEjercicio;
	
	private List<Ejercicio> listaEjercicios;
	
	private List<Integer> listaDuracion;
	
	private List<Integer> listaIdsEjercicios;
	
	private HttpServletRequest servletRequest;
	
	public RutinaAction() {
		super();
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String registro() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		try {
			RutinaDao rutinaDao = new RutinaDaoImpl(sf);
			setListadoRutina(rutinaDao.listarRutina());
			
			EjercicioDao ejercicioDao = new EjercicioDaoImpl(sf);
			setListaEjercicios(ejercicioDao.listarEjercicio());

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return SUCCESS;
	}
	
	/**
	 * Alta de una nueva rutina
	 * @return
	 * @throws Exception
	 */
	public String alta() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		RutinaDao rutinaDao = new RutinaDaoImpl(sf);
		EjercicioDao ejercicioDao = new EjercicioDaoImpl(sf);
		
		try {			
			
			Rutina rutina= getModel();
			
			//creamos los objetos RutinaEjercicio
			for (int i = 0; i < listaIdsEjercicios.size(); i++) {
				RutinaEjercicio rutinaEjercicio = new RutinaEjercicio();
				Ejercicio ejercicio = new Ejercicio();
				
				ejercicio.setId(listaIdsEjercicios.get(i));
				rutinaEjercicio.setEjercicio(ejercicio);
				rutinaEjercicio.setDuracionRepet(listaDuracion.get(i));
				rutinaEjercicio.setRutina(rutina);
				
				rutina.getRutinaEjercicios().add(rutinaEjercicio);
			}
		
			rutinaDao.altaRutina(rutina);
			
			addActionMessage(getText("rutina.alta.ok"));
			
			//limpiamos el formulario
			setRutina(new Rutina());
			setListaDuracion(null);
			setListaEjercicios(null);
			setListaIdsEjercicios(null);
			
			//recargamos la lista de rutinas y de ejercicios
			setListadoRutina(rutinaDao.listarRutina());
			setListaEjercicios(ejercicioDao.listarEjercicio());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("rutina.alta.ko"));
			
			//recargamos la lista de rutinas y de ejercicios
			setListadoRutina(rutinaDao.listarRutina());
			setListaEjercicios(ejercicioDao.listarEjercicio());

			return ERROR;
		} 
		
		return SUCCESS;
	}
	
	/**
	 * 
	 */
	public void validateAlta() {
		if (StringUtils.isEmpty(getModel().getNombre())) {
			addFieldError("rutina.nombre", getText("coach.alta.nombre.obligatorio"));
		}
		
		//recorremos las listas para ver que no hay nada vacio
		for (int i = 0; i < getListaDuracion().size(); i++) {
			if (getListaDuracion().get(i) == null) {
				addFieldError("listaDuracion[" + i + "]", getText("rutina.alta.duracion.obligatorio"));
			}
		}
				
		//recargamos la lista de coach
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		RutinaDao rutinaDao = new RutinaDaoImpl(sf);
		EjercicioDao ejercicioDao = new EjercicioDaoImpl(sf);
		setListadoRutina(rutinaDao.listarRutina());
		setListaEjercicios(ejercicioDao.listarEjercicio());
	}
	
	/**
	 * Eliminar una rutina de BBDD
	 * @return
	 */
	public String eliminar() {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		RutinaDao rutinaDao = new RutinaDaoImpl(sf);
		EjercicioDao ejercicioDao = new EjercicioDaoImpl(sf);
		
		try {
			rutinaDao.eliminarRutina(getModel().getId());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("rutina.eliminar.ko"));
			
			//recargamos la lista de rutinas y ejercicios
			setListadoRutina(rutinaDao.listarRutina());
			setListaEjercicios(ejercicioDao.listarEjercicio());

			return ERROR;
		} 
		addActionMessage(getText("rutina.eliminar.ok"));
		//recargamos la lista de rutinas y ejercicios
		setListadoRutina(rutinaDao.listarRutina());
		setListaEjercicios(ejercicioDao.listarEjercicio());
		
		return SUCCESS;
	}
	
	/**
	 * Modificar una rutina
	 * @return
	 * @throws Exception
	 */
	public String modificar() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		RutinaDao rutinaDao = new RutinaDaoImpl(sf);
		
		try {			
			Rutina rutina = rutinaDao.getRutina(getModel().getId());
			
			
			rutina.setNombre(getModel().getNombre());
			rutina.getRutinaEjercicios().clear();
			//creamos los objetos RutinaEjercicio
			for (int i = 0; i < listaIdsEjercicios.size(); i++) {
				RutinaEjercicio rutinaEjercicio = new RutinaEjercicio();
				Ejercicio ejercicio = new Ejercicio();
				
				ejercicio.setId(listaIdsEjercicios.get(i));
				rutinaEjercicio.setEjercicio(ejercicio);
				rutinaEjercicio.setDuracionRepet(listaDuracion.get(i));
				rutinaEjercicio.setRutina(rutina);
				
				rutina.getRutinaEjercicios().add(rutinaEjercicio);
			}
			
			rutinaDao.modificarRutina(rutina);
			
			addActionMessage(getText("rutina.modificar.ok"));
			
			//limpiamos el formulario
			setRutina(new Rutina());
			
			//recargamos la lista de rutinas
			setListadoRutina(rutinaDao.listarRutina());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("rutina.modificar.ko"));
			
			//recargamos la lista de rutinas
			setListadoRutina(rutinaDao.listarRutina());

			return ERROR;
		} 
		
		//limpiamos los daots
		setRutina(new Rutina());
		getListaIdsEjercicios().clear();
		getListaDuracion().clear();
		
		return SUCCESS;
	}
	
	/**
	 * 
	 */
	public void validateModificar() {
		if (StringUtils.isEmpty(getModel().getNombre())) {
			addFieldError("ejercicio.nombre", getText("coach.alta.nombre.obligatorio"));
		}
		
		//recorremos las listas para ver que no hay nada vacio
		for (int i = 0; i < getListaDuracion().size(); i++) {
			if (getListaDuracion().get(i) == null) {
				addFieldError("listaDuracion[" + i + "]", getText("rutina.alta.duracion.obligatorio"));
			}
		}
		
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		RutinaDao rutinaDao = new RutinaDaoImpl(sf);
		EjercicioDao ejercicioDao = new EjercicioDaoImpl(sf);
		setListadoRutina(rutinaDao.listarRutina());
		setListaEjercicios(ejercicioDao.listarEjercicio());
	}
	
	public String detallerutina () {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		try {
			RutinaDao rutinaDao = new RutinaDaoImpl(sf);
			setRutina(rutinaDao.getRutina(getModel().getId()));
			
			EjercicioDao ejercicioDao = new EjercicioDaoImpl(sf);
			setListaEjercicios(ejercicioDao.listarEjercicio());
			
			List<RutinaEjercicio> rutinaEjercicios = new ArrayList<RutinaEjercicio>(getModel().getRutinaEjercicios());			
			
			setListRutinaEjercicio(rutinaEjercicios);

		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
		
		return SUCCESS;
	}
	
	@Override
	public Rutina getModel() {
		return rutina;
	}
	
	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
	}
	
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public Rutina getRutina() {
		return rutina;
	}

	public void setRutina(Rutina rutina) {
		this.rutina = rutina;
	}

	public List<Rutina> getListadoRutina() {
		return listadoRutina;
	}

	public void setListadoRutina(List<Rutina> listadoRutina) {
		this.listadoRutina = listadoRutina;
	}

	public List<Ejercicio> getListaEjercicios() {
		return listaEjercicios;
	}

	public void setListaEjercicios(List<Ejercicio> listaEjercicios) {
		this.listaEjercicios = listaEjercicios;
	}

	public List<Integer> getListaDuracion() {
		return listaDuracion;
	}

	public void setListaDuracion(List<Integer> listaDuracion) {
		this.listaDuracion = listaDuracion;
	}

	public List<Integer> getListaIdsEjercicios() {
		return listaIdsEjercicios;
	}

	public void setListaIdsEjercicios(List<Integer> listaIdsEjercicios) {
		this.listaIdsEjercicios = listaIdsEjercicios;
	}

	public List<RutinaEjercicio> getListRutinaEjercicio() {
		return listRutinaEjercicio;
	}

	public void setListRutinaEjercicio(List<RutinaEjercicio> listRutinaEjercicio) {
		this.listRutinaEjercicio = listRutinaEjercicio;
	}
	
}
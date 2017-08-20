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

import personaltrainer.dao.PlanDao;
import personaltrainer.dao.PlanDaoImpl;
import personaltrainer.dao.RutinaDao;
import personaltrainer.dao.RutinaDaoImpl;
import personaltrainer.model.Plan;
import personaltrainer.model.PlanRutina;
import personaltrainer.model.Rutina;
import personaltrainer.util.Constantes;


public class PlanAction extends ActionSupport implements Action, ModelDriven<Plan>, ServletContextAware, ServletRequestAware {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3366280364881131900L;
	
	private ServletContext ctx;
	
	private Plan plan;
	
	private List<Plan> listadoPlan;
	
	private List<PlanRutina> listPlanRutina;
	
	private List<Rutina> listaRutinas;
	
	private List<Integer> listaIdsRutinas;
	
	private HttpServletRequest servletRequest;
	
	public PlanAction() {
		super();
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String registro() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		try {
			PlanDao planDao = new PlanDaoImpl(sf);
			setListadoPlan(planDao.listarPlan());
			
			RutinaDao rutinaDao = new RutinaDaoImpl(sf);
			setListaRutinas(rutinaDao.listarRutina());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return SUCCESS;
	}
	
	/**
	 * Alta de un nuevo plan
	 * @return
	 * @throws Exception
	 */
	public String alta() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		PlanDao planDao = new PlanDaoImpl(sf);
		RutinaDao rutinaDao = new RutinaDaoImpl(sf);
		
		try {			
			
			Plan plan= getModel();
			
			//creamos los objetos RutinaEjercicio
			for (int i = 0; i < listaIdsRutinas.size(); i++) {
				PlanRutina planRutina = new PlanRutina();
				Rutina rutina = new Rutina();
				
				rutina.setId(listaIdsRutinas.get(i));
				planRutina.setRutina(rutina);
				planRutina.setPlan(plan);
				
				plan.getPlanRutinas().add(planRutina);
			}
			//lo marcamos como activo
			plan.setActivo(Constantes.SI);
			
			planDao.altaPlan(plan);
			
			addActionMessage(getText("plan.alta.ok"));
			
			//limpiamos el formulario
			setPlan(new Plan());
			setListaIdsRutinas(null);
			setListaRutinas(null);
			
			//recargamos la lista de planes y de rutinas
			setListadoPlan(planDao.listarPlan());
			setListaRutinas(rutinaDao.listarRutina());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("plan.alta.ko"));
			
			//recargamos la lista de rutinas y de ejercicios
			setListaRutinas(rutinaDao.listarRutina());
			setListadoPlan(planDao.listarPlan());

			return ERROR;
		} 
		
		return SUCCESS;
	}
	
	/**
	 * 
	 */
	public void validateAlta() {
		if (StringUtils.isEmpty(getModel().getNombre())) {
			addFieldError("plan.nombre", getText("coach.alta.nombre.obligatorio"));
		}  else if(getModel().getNombre().length() > Constantes.NOM_EJER_LENGTH) {
			addFieldError("plan.nombre", getText("plan.nombre.length"));
		}
		if (StringUtils.isEmpty(getModel().getDescripcion())) {
			addFieldError("plan.descripcion", getText("plan.descripcion.obligatorio"));
		} else if(getModel().getDescripcion().length() > Constantes.DESC_EJER_LENGTH) {
			addFieldError("plan.descripcion", getText("plan.descripcion.length"));
		}
		
		//recargamos la lista de planes
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		PlanDao planDao = new PlanDaoImpl(sf);
		RutinaDao rutinaDao = new RutinaDaoImpl(sf);
		setListaRutinas(rutinaDao.listarRutina());
		setListadoPlan(planDao.listarPlan());
	}
	
	/**
	 * Eliminar un plan de BBDD
	 * @return
	 */
	public String eliminar() {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		RutinaDao rutinaDao = new RutinaDaoImpl(sf);
		PlanDao planDao = new PlanDaoImpl(sf);
		
		try {
			planDao.eliminarPlan(getModel().getId());
			
			addActionMessage(getText("plan.eliminar.ok"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("plan.eliminar.ko"));
			
			//recargamos la lista de planes y rutinas
			setListaRutinas(rutinaDao.listarRutina());
			setListadoPlan(planDao.listarPlan());

			return ERROR;
		} finally {
			//recargamos la lista de rutinas y ejercicios
			setListaRutinas(rutinaDao.listarRutina());
			setListadoPlan(planDao.listarPlan());	
		}
	}
	
	/**
	 * Modificar un plan
	 * @return
	 * @throws Exception
	 */
	public String modificar() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		PlanDao planDao = new PlanDaoImpl(sf);
		
		try {			
			Plan plan = planDao.getPlan(getModel().getId());
			
			
			plan.setNombre(getModel().getNombre());
			plan.getPlanRutinas().clear();
			//creamos los objetos RutinaEjercicio
			for (int i = 0; i < listaIdsRutinas.size(); i++) {
				PlanRutina planRutina = new PlanRutina();
				Rutina rutina = new Rutina();
				
				rutina.setId(listaIdsRutinas.get(i));
				planRutina.setRutina(rutina);
				planRutina.setPlan(plan);
				
				plan.getPlanRutinas().add(planRutina);
			}
			
			planDao.modificarPlan(plan);
			
			addActionMessage(getText("plan.modificar.ok"));
			
			//limpiamos el formulario
			setPlan(new Plan());
			
			//recargamos la lista de planes
			setListadoPlan(planDao.listarPlan());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("plan.modificar.ko"));
			
			//recargamos la lista de planes
			setListadoPlan(planDao.listarPlan());

			return ERROR;
		} 
		
		//limpiamos los daots
		setPlan(new Plan());
		getListaIdsRutinas().clear();
		
		return SUCCESS;
	}
	
	/**
	 * 
	 */
	public void validateModificar() {
		if (StringUtils.isEmpty(getModel().getNombre())) {
			addFieldError("ejercicio.nombre", getText("coach.alta.nombre.obligatorio"));
		}
		
		//recargamos la lista de planes
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		PlanDao planDao = new PlanDaoImpl(sf);
		RutinaDao rutinaDao = new RutinaDaoImpl(sf);
		setListaRutinas(rutinaDao.listarRutina());
		setListadoPlan(planDao.listarPlan());
	}
	
	public String detallePlan () {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		try {
			PlanDao planDao = new PlanDaoImpl(sf);
			setPlan(planDao.getPlan(getModel().getId()));
			
			RutinaDao rutinaDao = new RutinaDaoImpl(sf);
			setListaRutinas(rutinaDao.listarRutina());
			
			List<PlanRutina> planRutinas = new ArrayList<PlanRutina>(getModel().getPlanRutinas());		
			
			setListPlanRutina(planRutinas);

		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
		
		return SUCCESS;
	}
	
	@Override
	public Plan getModel() {
		return plan;
	}
	
	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
	}
	
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
	public List<Plan> getListadoPlan() {
		return listadoPlan;
	}

	public void setListadoPlan(List<Plan> listadoPlan) {
		this.listadoPlan = listadoPlan;
	}

	public List<Integer> getListaIdsRutinas() {
		return listaIdsRutinas;
	}

	public void setListaIdsRutinas(List<Integer> listaIdsRutinas) {
		this.listaIdsRutinas = listaIdsRutinas;
	}

	public List<Rutina> getListaRutinas() {
		return listaRutinas;
	}

	public void setListaRutinas(List<Rutina> listaRutinas) {
		this.listaRutinas = listaRutinas;
	}

	public List<PlanRutina> getListPlanRutina() {
		return listPlanRutina;
	}

	public void setListPlanRutina(List<PlanRutina> listPlanRutina) {
		this.listPlanRutina = listPlanRutina;
	}
	
}
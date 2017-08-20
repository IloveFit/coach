package personaltrainer.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import personaltrainer.model.Plan;
import personaltrainer.model.PlanRutina;
import personaltrainer.model.Rutina;
import personaltrainer.model.RutinaEjercicio;

public class PlanDaoImpl implements PlanDao {
	
	private SessionFactory sf;
	
	public PlanDaoImpl(SessionFactory sf){
		this.sf = sf;
	}

	public Plan altaPlan (Plan plan) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			session.persist(plan);
						
			tx.commit();
			session.close();
			
			return plan;
		} catch (PersistenceException pe) {
			if (pe.getCause().getCause().getMessage().contains("Duplicate entry")) {
				throw new ConstraintViolationException(pe.getCause().getCause().getMessage(), null, null);
			}
			System.out.println(pe.getStackTrace());
			return null;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
		
	}
	
	/**
	 * 
	 */
	public Plan modificarPlan (Plan plan) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Query query = session.createQuery("delete PlanRutina where plan.id = " + plan.getId());
			query.executeUpdate();
			
			session.update(plan);
			
			tx.commit();
			session.close();
			
			return plan;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
		
	}
	
	/**
	 * Elimina un {@link Plan} a partir de un id
	 * 
	 * @param id
	 */
	public void eliminarPlan (Integer id) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Plan plan = (Plan) session.get(Plan.class, id);
			session.delete(plan);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw e;
		}
	}
	
	/**
	 * Devuelve un {@link Plan} a partir de su id
	 * 
	 * @param id
	 * @return
	 */
	public Plan getPlan (Integer id) {
		Plan plan = null;
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			plan = (Plan) session.get(Plan.class, id);
			
			List<PlanRutina> rutinas = new ArrayList<>(plan.getPlanRutinas());
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return plan;
	}
	
	public List<Plan> listarPlan () {
		List<Plan> lstPlan = new ArrayList<Plan>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			lstPlan = (ArrayList<Plan>)session.createNativeQuery("SELECT * FROM Plan", Plan.class).list();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return lstPlan;
	}
	
	/**
	 * 
	 * @param idPlan
	 * @return
	 */
	public List<PlanRutina> listarPlanRutina (Integer idPlan) {
		List<PlanRutina> lstPlanRutina = new ArrayList<PlanRutina>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			NativeQuery<PlanRutina> nativeQuery = session.createNativeQuery("SELECT * FROM plan_rutina WHERE id_plan =:idPlan", PlanRutina.class); 
			nativeQuery.setParameter("idPlan", idPlan);
			lstPlanRutina = (ArrayList<PlanRutina>)nativeQuery.list();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw e;
		}
		
		return lstPlanRutina;
	}
}
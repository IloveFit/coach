package personaltrainer.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.NativeQuery;

import personaltrainer.model.PlanRutina;
import personaltrainer.model.RutinaEjercicio;

public class PlanRutinaDaoImpl implements PlanRutinaDao {
	
	private SessionFactory sf;
	
	public PlanRutinaDaoImpl(SessionFactory sf){
		this.sf = sf;
	}

	public PlanRutina altaPlanRutina (PlanRutina planRutina) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			session.save(planRutina);
			
			tx.commit();
			session.close();
			
			return planRutina;
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
	public PlanRutina modificarPlanRutina (PlanRutina planRutina) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			session.update(planRutina);
			
			tx.commit();
			session.close();
			
			return planRutina;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
		
	}
	
	/**
	 * Elimina un {@link PlanRutina} a partir de un id
	 * 
	 * @param id
	 */
	public void eliminarPlanRutina (Integer id) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			PlanRutina planRutina = (PlanRutina) session.get(PlanRutina.class, id);
			session.delete(planRutina);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	/**
	 * Devuelve un {@link PlanRutina} a partir de su id
	 * 
	 * @param id
	 * @return
	 */
	public PlanRutina getPlanRutina (Integer id) {
		PlanRutina planRutina = null;
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			planRutina = (PlanRutina) session.get(PlanRutina.class, id);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return planRutina;
	}
	
	public List<PlanRutina> listarPlanRutinaByRutina (Integer idRutina) {
		List<PlanRutina> lsLtPlanRutina = new ArrayList<PlanRutina>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			NativeQuery<PlanRutina> nativeQuery = session.createNativeQuery("SELECT * FROM plan_rutina WHERE id_rutina =:idRutina", PlanRutina.class); 
			nativeQuery.setParameter("idRutina", idRutina);
			lsLtPlanRutina = (ArrayList<PlanRutina>)nativeQuery.getResultList();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return lsLtPlanRutina;
	}
}
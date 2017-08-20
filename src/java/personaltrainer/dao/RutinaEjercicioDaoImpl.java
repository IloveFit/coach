package personaltrainer.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.NativeQuery;

import personaltrainer.model.RutinaEjercicio;

public class RutinaEjercicioDaoImpl implements RutinaEjercicioDao {
	
	private SessionFactory sf;
	
	public RutinaEjercicioDaoImpl(SessionFactory sf){
		this.sf = sf;
	}

	public RutinaEjercicio altaRutinaEjercicio (RutinaEjercicio rutinaEjercicio) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			session.save(rutinaEjercicio);
			
			tx.commit();
			session.close();
			
			return rutinaEjercicio;
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
	public RutinaEjercicio modificarRutinaEjercicio (RutinaEjercicio rutinaEjercicio) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			session.update(rutinaEjercicio);
			
			tx.commit();
			session.close();
			
			return rutinaEjercicio;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
		
	}
	
	/**
	 * Elimina un {@link RutinaEjercicio} a partir de un {@link RutinaEjercicioId}
	 * 
	 * @param id
	 */
	public void eliminarRutinaEjercicio (Integer id) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			RutinaEjercicio rutinaEjercicio = (RutinaEjercicio) session.get(RutinaEjercicio.class, id);
			session.delete(rutinaEjercicio);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	/**
	 * Devuelve un {@link RutinaEjercicio} a partir de su id
	 * 
	 * @param id
	 * @return
	 */
	public RutinaEjercicio getRutinaEjercicio (Integer id) {
		RutinaEjercicio rutinaEjercicio = null;
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			rutinaEjercicio = (RutinaEjercicio) session.get(RutinaEjercicio.class, id);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return rutinaEjercicio;
	}
	
	public List<RutinaEjercicio> listarRutinaEjercicioByEjercicio (Integer idEjercicio) {
		List<RutinaEjercicio> lsLtRutinaEjercicio = new ArrayList<RutinaEjercicio>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			NativeQuery<RutinaEjercicio> nativeQuery = session.createNativeQuery("SELECT * FROM rutina_ejercicio WHERE id_ejercicio =:idEjercicio", RutinaEjercicio.class); 
			nativeQuery.setParameter("idEjercicio", idEjercicio);
			lsLtRutinaEjercicio = (ArrayList<RutinaEjercicio>)nativeQuery.getResultList();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return lsLtRutinaEjercicio;
	}
}
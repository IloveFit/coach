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

import personaltrainer.model.Ejercicio;
import personaltrainer.model.Rutina;
import personaltrainer.model.RutinaEjercicio;
import personaltrainer.util.Constantes;

public class RutinaDaoImpl implements RutinaDao {
	
	private SessionFactory sf;
	
	public RutinaDaoImpl(SessionFactory sf){
		this.sf = sf;
	}

	public Rutina altaRutina (Rutina rutina) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			session.persist(rutina);	
			
			tx.commit();
			session.close();
			
			return rutina;
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
	public Rutina modificarRutina (Rutina rutina) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Query query = session.createQuery("delete RutinaEjercicio where rutina.id = " + rutina.getId());
			query.executeUpdate();
			
			session.update(rutina);
			
			tx.commit();
			session.close();
			
			return rutina;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
		
	}
	
	/**
	 * Elimina un {@link Rutina} a partir de un id
	 * 
	 * @param id
	 */
	public void eliminarRutina (Integer id) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Rutina rutina = (Rutina) session.get(Rutina.class, id);
			session.delete(rutina);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw e;
		}
	}
	
	/**
	 * Devuelve un {@link Rutina} a partir de su id
	 * 
	 * @param id
	 * @return
	 */
	public Rutina getRutina (Integer id) {
		Rutina rutina = null;
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			rutina = (Rutina) session.get(Rutina.class, id);
			
			List<RutinaEjercicio> ejercicios = new ArrayList<>(rutina.getRutinaEjercicios());
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return rutina;
	}
	
	public List<Rutina> listarRutina () {
		List<Rutina> lsLtRutina = new ArrayList<Rutina>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			lsLtRutina = (ArrayList<Rutina>)session.createNativeQuery("SELECT * FROM Rutina", Rutina.class).list();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return lsLtRutina;
	}
	
	/**
	 * 
	 * @param idRutina
	 * @return
	 */
	public List<RutinaEjercicio> listarRutinaEjercicio (Integer idRutina) {
		List<RutinaEjercicio> lsLtRutinaEjercicio = new ArrayList<RutinaEjercicio>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			NativeQuery<RutinaEjercicio> nativeQuery = session.createNativeQuery("SELECT * FROM rutina_ejercicio WHERE id_rutina =:idRutina", RutinaEjercicio.class); 
			nativeQuery.setParameter("idRutina", idRutina);
			lsLtRutinaEjercicio = (ArrayList<RutinaEjercicio>)nativeQuery.list();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw e;
		}
		
		return lsLtRutinaEjercicio;
	}
}
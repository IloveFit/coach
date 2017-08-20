package personaltrainer.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import personaltrainer.model.Ejercicio;

public class EjercicioDaoImpl implements EjercicioDao {
	
	private SessionFactory sf;
	
	public EjercicioDaoImpl(SessionFactory sf){
		this.sf = sf;
	}

	public Ejercicio altaEjercicio (Ejercicio ejercicio) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			session.save(ejercicio);
			
			tx.commit();
			session.close();
			
			return ejercicio;
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
	
	public Ejercicio modificarEjercicio (Ejercicio ejercicio) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			session.update(ejercicio);
			
			tx.commit();
			session.close();
			
			return ejercicio;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
		
	}
	
	/**
	 * Elimina un {@link Ejercicio} a partir de un id
	 * 
	 * @param email
	 */
	public void eliminarEjercicio (Integer id) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Ejercicio ejercicio = (Ejercicio) session.get(Ejercicio.class, id);
			session.delete(ejercicio);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw e;
		}
	}
	
	/**
	 * Devuelve un {@link Ejercicio} a partir de su id
	 * 
	 * @param email
	 * @return
	 */
	public Ejercicio getEjercicio (Integer id) {
		Ejercicio ejercicio = null;
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			ejercicio = (Ejercicio) session.get(Ejercicio.class, id);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return ejercicio;
	}
	
	public List<Ejercicio> listarEjercicio () {
		List<Ejercicio> lsLtEjercicio = new ArrayList<Ejercicio>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			lsLtEjercicio = (ArrayList<Ejercicio>)session.createNativeQuery("SELECT * FROM Ejercicio", Ejercicio.class).list();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return lsLtEjercicio;
	}
}
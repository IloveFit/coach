package personaltrainer.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import personaltrainer.model.Catalogo;
import personaltrainer.model.Cliente;
import personaltrainer.model.Coach;

public class CoachDaoImpl implements CoachDao {
	
	private SessionFactory sf;
	
	public CoachDaoImpl(SessionFactory sf){
		this.sf = sf;
	}

	@Override
	public Coach getCoachByCredentials(String coachId, String password) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query<Coach> query = session.createQuery("from Coach where id=:id and pwd=:pwd", Coach.class);
		query.setParameter("id", coachId); query.setParameter("pwd", password);
		Coach coach = query.uniqueResult();
		if(coach != null){
			System.out.println("User Retrieved from DB::"+coach);
		}
		tx.commit();session.close();
		return coach;
	}

	public Coach altaCoach (Coach coach) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			session.save(coach);
			
			tx.commit();
			session.close();
			
			return coach;
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
	
	public Coach modificarCoach (Coach coach) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			session.update(coach);
			
			tx.commit();
			session.close();
			
			return coach;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
		
	}
	
	/**
	 * Elimina un {@link Coach} a partir de un id
	 * 
	 * @param email
	 */
	public void eliminarCoach (Integer id) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Coach coach = (Coach) session.get(Coach.class, id);
			session.delete(coach);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	/**
	 * Devuelve un {@link Coach} a partir de su id
	 * 
	 * @param email
	 * @return
	 */
	public Coach getCoach (Integer id) {
		Coach coach = null;
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			coach = (Coach) session.get(Coach.class, id);
			Catalogo tipoCoach = coach.getCatalogo(); 
			tipoCoach.getNombre();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return coach;
	}
	
	/**
	 * Devuelve un {@link Coach} a partir de un email
	 * 
	 * @param email
	 * @return
	 */
	public Coach getCoachByEmail (String email) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query<Coach> query = session.createQuery("from Coach where email=:email", Coach.class);
		query.setParameter("email", email);
		Coach coach = query.uniqueResult();
		if(coach != null){
			System.out.println("User Retrieved from DB::"+coach);
			
			Catalogo tipoCoach = coach.getCatalogo(); 
			tipoCoach.getNombre();
		}
		tx.commit();session.close();
		return coach;
	}
	
	public List<Coach> listarCoach () {
		List<Coach> lsLtCoach = new ArrayList<Coach>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			lsLtCoach = (ArrayList<Coach>)session.createNativeQuery("SELECT * FROM Coach", Coach.class).list();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return lsLtCoach;
	}
	
	public List<Integer> datosPerfilCoach (Integer idCoach) {
		List<Integer> datosPerfilCoach = new ArrayList<Integer>();
		int clientes = 0;
		int mensajes = 0;
		Coach coach = null;
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			coach = (Coach) session.get(Coach.class, idCoach);
			
			clientes = coach.getClienteCoaches().size();
			mensajes = coach.getMensajeses().size();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		datosPerfilCoach.add(new Integer(clientes));
		datosPerfilCoach.add(new Integer(mensajes));
		
		return datosPerfilCoach;
	}
}
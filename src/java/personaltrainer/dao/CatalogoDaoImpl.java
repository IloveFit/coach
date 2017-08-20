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

public class CatalogoDaoImpl implements CatalogoDao {
	
	private SessionFactory sf;
	
	public CatalogoDaoImpl(SessionFactory sf){
		this.sf = sf;
	}

	public Catalogo altaCatalogo (Catalogo catalogo) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			session.save(catalogo);
			
			tx.commit();
			session.close();
			
			return catalogo;
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
	
	public Catalogo modificarCatalogo (Catalogo catalogo) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			session.update(catalogo);
			
			tx.commit();
			session.close();
			
			return catalogo;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
		
	}
	
	/**
	 * Elimina un {@link Catalogo} a partir de un email
	 * 
	 * @param email
	 */
	public void eliminarCatalogo (Integer id) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Catalogo catalogo = (Catalogo) session.get(Catalogo.class, id);
			session.delete(catalogo);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	/**
	 * Devuelve un {@link Catalogo} a partir de su id
	 * 
	 * @param email
	 * @return
	 */
	public Catalogo getCatalogo (Integer id) {
		Catalogo catalogo = null;
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			catalogo = (Catalogo) session.get(Catalogo.class, id);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return catalogo;
	}
	
	/**
	 * Devuelve una kista de {@link Catalogo} correspondientes a un tipo
	 * 
	 * @param email
	 * @return
	 */
	public List<Catalogo> listarCatalogo (String tipCatalogo) {
		List<Catalogo> lsLtCatalogo = new ArrayList<Catalogo>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Query<Catalogo> query = session.createQuery("from Catalogo where tipCatalogo=:tipCatalogo", Catalogo.class);
			query.setParameter("tipCatalogo", tipCatalogo);	
			
			lsLtCatalogo = query.getResultList();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		
		return lsLtCatalogo;
	}
	
	public List<Catalogo> listarCatalogo () {
		List<Catalogo> lsLtCatalogo = new ArrayList<Catalogo>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			lsLtCatalogo = (ArrayList<Catalogo>)session.createNativeQuery("SELECT * FROM Catalogo").list();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return lsLtCatalogo;
	}
}
package personaltrainer.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import personaltrainer.model.Cliente;

public class ClienteDaoImpl implements ClienteDao {
	
	private SessionFactory sf;
	
	public ClienteDaoImpl(SessionFactory sf){
		this.sf = sf;
	}

	/**
	 * Alta de un cliente en BBDD
	 * 
	 * @param cliente
	 * @return
	 */
	public Cliente altaCliente (Cliente cliente) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			session.save(cliente);
			
			tx.commit();
			session.close();
			
			return cliente;
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
	 * Modificar un cliente en BBDD
	 * 
	 * @param cliente
	 * @return
	 */
	public Cliente modificarCliente (Cliente cliente) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			session.update(cliente);
			
			tx.commit();
			session.close();
			
			return cliente;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
		
	}
	
	/**
	 * Elimina un {@link Cliente} a partir de un id
	 * 
	 * @param email
	 */
	public void eliminarCliente (Integer id) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Cliente cliente = (Cliente) session.get(Cliente.class, id);
			session.delete(cliente);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw e;
		}
	}
	
	/**
	 * Devuelve un {@link Cliente} a partir de su id
	 * 
	 * @param id
	 * @return
	 */
	public Cliente getCliente (Integer id) {
		Cliente cliente = null;
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			cliente = (Cliente) session.get(Cliente.class, id);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return cliente;
	}
	
	/**
	 * Devuelve un {@link Cliente} a partir de un email
	 * 
	 * @param email
	 * @return
	 */
	public Cliente getClienteByEmail (String email) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query<Cliente> query = session.createQuery("from Cliente where email=:email", Cliente.class);
		query.setParameter("email", email);
		Cliente cliente = query.uniqueResult();
		if(cliente != null){
			System.out.println("User Retrieved from DB::"+cliente);
			
		}
		tx.commit();session.close();
		return cliente;
	}
	
	public List<Cliente> listarCliente () {
		List<Cliente> lsLtCliente = new ArrayList<Cliente>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			lsLtCliente = (ArrayList<Cliente>)session.createNativeQuery("SELECT * FROM Cliente", Cliente.class).list();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return lsLtCliente;
	}
}
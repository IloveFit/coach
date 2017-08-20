package personaltrainer.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;

import org.eclipse.jdt.internal.compiler.classfmt.MethodInfoWithAnnotations;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import personaltrainer.model.Catalogo;
import personaltrainer.model.Cliente;
import personaltrainer.model.Coach;
import personaltrainer.model.Mensajes;
import personaltrainer.util.Constantes;

public class MensajeDaoImpl implements MensajeDao {
	
	private SessionFactory sf;
	
	public MensajeDaoImpl(SessionFactory sf){
		this.sf = sf;
	}

	public Mensajes altaMensaje (Mensajes mensaje) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			session.save(mensaje);
			
			tx.commit();
			session.close();
			
			return mensaje;
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
	 * Modificar un mensaje en bbdd
	 * 
	 * @param mensaje
	 * @return
	 */
	public Mensajes modificarMensaje (Mensajes mensaje) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			session.update(mensaje);
			
			tx.commit();
			session.close();
			
			return mensaje;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
		
	}
	
	/**
	 * Elimina un {@link Mensajes} a partir de un id
	 * 
	 * @param email
	 */
	public void eliminarMensaje (Integer id) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Mensajes mensaje = (Mensajes) session.get(Mensajes.class, id);
			session.delete(mensaje);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	public void leerMensaje (Integer id) {
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Query query = session.createQuery("update Mensajes set leido = '" + Constantes.SI + "' "
			        + "where id = " + id);
			query.executeUpdate();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw e;
		}
	}
	
	/**
	 * Devuelve un {@link Mensajes} a partir de su id
	 * 
	 * @param email
	 * @return
	 */
	public Mensajes getMensaje (Integer id) {
		Mensajes mensaje = null;
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			mensaje = (Mensajes) session.get(Mensajes.class, id);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return mensaje;
	}
	
	/**
	 * Devuelve una kista de {@link Mensajes} correspondientes a una conversacion
	 * 
	 * @param email
	 * @return
	 */
	public List<Mensajes> listarMensajesConv (Integer conversacion) {
		List<Mensajes> lstMensajes = new ArrayList<Mensajes>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Query<Mensajes> query = session.createQuery("from Mensajes where conversacion=:conversacion", Mensajes.class);
			query.setParameter("conversacion", conversacion);	
			
			lstMensajes = query.getResultList();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		
		return lstMensajes;
	}
	
	public List<Mensajes> listarMensajesCliente (Integer idCliente) {
		List<Mensajes> lstMensajes = new ArrayList<Mensajes>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Query<Mensajes> query = session.createQuery("from Mensajes where id_cliente=:idCliente", Mensajes.class);
			query.setParameter("id_cliente", idCliente);	
			
			lstMensajes = query.getResultList();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		
		return lstMensajes;
	}
	
	public List<Mensajes> listarMensajesTipoCliente () {
		List<Mensajes> lstMensajes = new ArrayList<Mensajes>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Query<Mensajes> query = session.createQuery("from Mensajes where tipoMensaje=:tipoMensaje", Mensajes.class);
			query.setParameter("tipoMensaje", 1);	
			
			lstMensajes = query.getResultList();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		
		return lstMensajes;
	}
	
	public List<Mensajes> listarMensajesCoach (Integer idCoach) {
		List<Mensajes> lstMensajes = new ArrayList<Mensajes>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Query<Mensajes> query = session.createQuery("from Mensajes where coach.id=:idCoach and mensajes.id is null order by fecha desc", Mensajes.class);
			query.setParameter("idCoach", idCoach);	
			
			lstMensajes = query.getResultList();
			
			//cargamos el cliente
			for (Mensajes mensaje : lstMensajes) {
				Cliente cliente = mensaje.getCliente();
				cliente.getNombre();
				cliente.getApellidos();
				
				Coach coach = mensaje.getCoach();
				coach.getNombre();
				
				List<Mensajes> mensajesHijos = new ArrayList<>(mensaje.getMensajeses());
				
				for (Mensajes mensajeHijo : mensajesHijos) {
					Cliente clienteHijo = mensajeHijo.getCliente();
					clienteHijo.getNombre();
					clienteHijo.getApellidos();
					
					Coach coachHijo = mensajeHijo.getCoach();
					coachHijo.getNombre();
				}
			}
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		
		return lstMensajes;
	}
	
	/**
	 * Devuelve todos los mensajes de la tabla
	 */
	public List<Mensajes> listarMensajes () {
		List<Mensajes> lstMensajes = new ArrayList<Mensajes>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			lstMensajes = (ArrayList<Mensajes>)session.createNativeQuery("SELECT * FROM Mensajes").list();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return lstMensajes;
	}
	
	public int mensajesNoLeidos (Integer idCoach) {
		List<Mensajes> lstMensajes = new ArrayList<Mensajes>();
		
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Query<Mensajes> query = session.createQuery("from Mensajes where coach.id=:idCoach and leido='N' and tipoMensaje = 1", Mensajes.class);
			query.setParameter("idCoach", idCoach);
			
			lstMensajes = query.getResultList();
			
			session.close();

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw e;
		}
		
		if (lstMensajes != null) {
			return lstMensajes.size();
		} else {
			return 0;
		}
		
		
	}
}
package personaltrainer.dao;

import java.util.List;

import personaltrainer.model.Mensajes;

public interface MensajeDao {

	Mensajes altaMensaje (Mensajes mensaje);
	
	void eliminarMensaje (Integer id);
	
	Mensajes modificarMensaje (Mensajes mensaje);
	
	Mensajes getMensaje (Integer id);
	
	List<Mensajes> listarMensajes ();
	
	List<Mensajes> listarMensajesConv (Integer conversacion);

	List<Mensajes> listarMensajesCliente (Integer cliente);
	
	List<Mensajes> listarMensajesCoach (Integer coach);
	
	void leerMensaje (Integer id);
	
	int mensajesNoLeidos (Integer idCoach);
	
	List<Mensajes> listarMensajesTipoCliente ();
}
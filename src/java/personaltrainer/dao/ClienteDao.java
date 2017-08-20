package personaltrainer.dao;

import java.util.List;

import personaltrainer.model.Cliente;

public interface ClienteDao {

	Cliente altaCliente (Cliente cliente);
	
	void eliminarCliente (Integer id);
	
	Cliente modificarCliente (Cliente cliente);
	
	Cliente getCliente (Integer id);
	
	List<Cliente> listarCliente ();
	
	Cliente getClienteByEmail (String email);
	
}
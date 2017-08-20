package personaltrainer.dao;

import java.util.List;

import personaltrainer.model.Ejercicio;

public interface EjercicioDao {

	Ejercicio altaEjercicio (Ejercicio ejercicio);
	
	void eliminarEjercicio (Integer id);
	
	Ejercicio modificarEjercicio (Ejercicio ejercicio);
	
	Ejercicio getEjercicio (Integer id);
	
	List<Ejercicio> listarEjercicio ();
	
}
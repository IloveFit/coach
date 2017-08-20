package personaltrainer.dao;

import java.util.List;

import personaltrainer.model.RutinaEjercicio;

public interface RutinaEjercicioDao {

	RutinaEjercicio altaRutinaEjercicio (RutinaEjercicio rutina);
	
	void eliminarRutinaEjercicio (Integer id);
	
	RutinaEjercicio modificarRutinaEjercicio (RutinaEjercicio rutina);
	
	RutinaEjercicio getRutinaEjercicio (Integer id);
	
	List<RutinaEjercicio> listarRutinaEjercicioByEjercicio (Integer idEjercicio);
	
}
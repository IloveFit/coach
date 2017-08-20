package personaltrainer.dao;

import java.util.List;

import personaltrainer.model.Rutina;
import personaltrainer.model.RutinaEjercicio;

public interface RutinaDao {

	Rutina altaRutina (Rutina rutina);
	
	void eliminarRutina (Integer id);
	
	Rutina modificarRutina (Rutina rutina);
	
	Rutina getRutina (Integer id);
	
	List<Rutina> listarRutina ();
	
	List<RutinaEjercicio> listarRutinaEjercicio (Integer idRutina);
}
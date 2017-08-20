package personaltrainer.dao;

import java.util.List;

import personaltrainer.model.PlanRutina;

public interface PlanRutinaDao {

	PlanRutina altaPlanRutina (PlanRutina planRutina);
	
	void eliminarPlanRutina (Integer id);
	
	PlanRutina modificarPlanRutina (PlanRutina id);
	
	PlanRutina getPlanRutina (Integer id);
	
	List<PlanRutina> listarPlanRutinaByRutina (Integer idRutina);
	
}
package personaltrainer.dao;

import java.util.List;

import personaltrainer.model.Plan;
import personaltrainer.model.PlanRutina;

public interface PlanDao {

	Plan altaPlan (Plan plan);
	
	void eliminarPlan (Integer id);
	
	Plan modificarPlan (Plan plan);
	
	Plan getPlan (Integer id);
	
	List<Plan> listarPlan ();
	
	List<PlanRutina> listarPlanRutina (Integer idPlan);
}
package personaltrainer.dao;

import java.util.List;

import personaltrainer.model.Coach;

public interface CoachDao {

	Coach getCoachByCredentials(String coachId, String password);
	
	Coach altaCoach (Coach coach);
	
	void eliminarCoach (Integer id);
	
	Coach modificarCoach (Coach coach);
	
	Coach getCoach (Integer id);
	
	List<Coach> listarCoach ();
	
	Coach getCoachByEmail (String email);
	
	List<Integer> datosPerfilCoach (Integer idCoach);
}
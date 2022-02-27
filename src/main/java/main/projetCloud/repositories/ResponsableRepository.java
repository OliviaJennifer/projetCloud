package main.projetCloud.repositories;

import main.projetCloud.domain.Responsable;

public interface ResponsableRepository {
	Integer create(Integer idregion, String email, String mdp);
	
	Responsable find(String email, String mdp);
	
	Responsable findById(Integer id);
}

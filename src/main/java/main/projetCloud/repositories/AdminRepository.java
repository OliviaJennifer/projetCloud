package main.projetCloud.repositories;

import main.projetCloud.domain.Admin;

public interface AdminRepository {
	
	Integer create(String email, String mdp);
	
	Admin find(String email, String mdp);
	
	Admin findById(Integer id);
	
}

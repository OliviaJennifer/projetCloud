package main.projetCloud.repositories;

import main.projetCloud.domain.User;
import main.projetCloud.exceptions.EtAuthException;

public interface UserRepository {

	Integer create(String nom, String prenom, String sexe, String dtn, String adresse, String email, String password)throws EtAuthException;
	
	User findByNamePassword(String name, String pawwsord) throws EtAuthException;
	
	Integer getCountByEmail(String email);
	
	User findById(Integer id);
	
	User findByName(String nom);
}

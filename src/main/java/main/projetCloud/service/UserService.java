package main.projetCloud.service;

import main.projetCloud.domain.User;
import main.projetCloud.exceptions.EtAuthException;

public interface UserService {

	User validateUser(String nom, String password) throws EtAuthException;
	
	User registerUser(String nom, String prenom, String sexe, String dtn, String adresse, String email, String password) throws EtAuthException;
}

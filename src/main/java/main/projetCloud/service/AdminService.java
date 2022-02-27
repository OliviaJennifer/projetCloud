package main.projetCloud.service;

import org.springframework.beans.factory.annotation.Autowired;

import main.projetCloud.domain.Admin;
import main.projetCloud.exceptions.EtAuthException;


public interface AdminService {
	
	Admin validateAdmin(String email, String mdp) throws EtAuthException;
	
	Admin registerAdmin(String email, String mdp) throws EtAuthException;
	
}

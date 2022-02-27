package main.projetCloud.service;

import main.projetCloud.domain.Responsable;
import main.projetCloud.exceptions.EtAuthException;

public interface ResponsableService {
	
	Responsable validateResponsable(String email, String mdp) throws EtAuthException;
	
	Responsable registerResponsable(Integer idregion, String email, String mdp) throws EtAuthException;
}

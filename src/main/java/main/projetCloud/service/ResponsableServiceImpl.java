package main.projetCloud.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.projetCloud.domain.Responsable;
import main.projetCloud.exceptions.EtAuthException;
import main.projetCloud.repositories.AdminRepository;
import main.projetCloud.repositories.ResponsableRepository;

@Service
@Transactional
public class ResponsableServiceImpl implements ResponsableService{
	
	@Autowired
	ResponsableRepository responsableRepository;
	
	@Override
	public Responsable validateResponsable(String email, String mdp) throws EtAuthException {
		if(email!= null)
		{
			return responsableRepository.find(email, mdp);
		}
		return null;
	}

	@Override
	public Responsable registerResponsable(Integer idregion,String email, String mdp) throws EtAuthException {
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		Pattern patternpwd = Pattern.compile("(é)");		
		char[] charact = {'ù','é','è','ç','à','â'};		
		char[] pwd = mdp.toCharArray();
		boolean matche = true;
		for(int i=0;i<pwd.length;i++)
		{			
			for(int e=0;e<charact.length;e++){
				System.out.println("Comparaison: "+pwd[i]+"/"+charact[e]);
				if(pwd[i]==charact[e])matche = false;
				
			}
		}		
		if(email!=null)email = email.toLowerCase();
		if(pwd.length<8)throw new EtAuthException("Trop court");
		if(!pattern.matcher(email).matches())throw new EtAuthException("Invalid email format");		
		if(matche!=true)throw new EtAuthException("Invalid password ");		
		Integer responsableId = responsableRepository.create(idregion, email, mdp);
		
		return responsableRepository.findById(responsableId);
	}

}

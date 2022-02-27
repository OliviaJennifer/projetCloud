package main.projetCloud.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.projetCloud.domain.User;
import main.projetCloud.exceptions.EtAuthException;
import main.projetCloud.repositories.UserRepository;

 	
@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public User validateUser(String nom, String password) throws EtAuthException {
		if(nom!= null)
		{
			return userRepository.findByNamePassword(nom, password);
		}
		return null;
	}
//Enregistrement
	@Override
	public User registerUser(String nom, String prenom, String sexe, String dtn, String adresse, String email,
			String password) throws EtAuthException {
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		Pattern patternpwd = Pattern.compile("(é)");
		
		char[] charact = {'ù','é','è','ç','à','â'};		
		char[] pwd = password.toCharArray();
		boolean matche = true;
		for(int i=0;i<pwd.length;i++)
		{			
			for(int e=0;e<charact.length;e++){
				System.out.println("Comparaison: "+pwd[i]+"/"+charact[e]);
				if(pwd[i]==charact[e])matche = false;
				
			}
		}
		
		if(email!=null)email = email.toLowerCase();
		if(!pattern.matcher(email).matches())throw new EtAuthException("Invalid email format");		
		if(matche!=true)throw new EtAuthException("Invalid password ");
		Integer count = userRepository.getCountByEmail(email);
		if(count>0)throw new EtAuthException("Email already in use");
		Integer userId = userRepository.create(nom, prenom, sexe, dtn, adresse, email, password);
		
		return userRepository.findById(userId);
							
	}

}

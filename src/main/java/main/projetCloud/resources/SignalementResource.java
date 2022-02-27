package main.projetCloud.resources;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.projetCloud.domain.Region;
import main.projetCloud.domain.Signalement;
import main.projetCloud.domain.TypeSignalement;
import main.projetCloud.domain.User;
import main.projetCloud.repositories.RegionRepository;
import main.projetCloud.repositories.SignalementRepository;
import main.projetCloud.repositories.TypeSignalementRepository;
import main.projetCloud.repositories.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/signalement")
public class SignalementResource {
	
	@Autowired
	SignalementRepository signalementRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	TypeSignalementRepository typeSignalementRepository; 
	
	@GetMapping("/allsignal")
	public List<Signalement> getSignalements(){
		List<Signalement> liste = signalementRepository.allSignalement();
		return liste;
	}

	@GetMapping("usersignal")
	public List<Signalement>getUserSignals(@RequestBody Map<String, Object> signaleMap){
		String usernom = (String)signaleMap.get("usernom");
		User user =userRepository.findByName(usernom);
		List<Signalement> liste = signalementRepository.userSignalements(user.getId());
		return liste;
	}
	
	@PostMapping("/insertsignal")
	public String createSignalement(@RequestBody Map<String, Object> signaleMap) { 
		String usernom = (String)signaleMap.get("usernom");
		String regionname = (String)signaleMap.get("regionname");
		String type =(String)signaleMap.get("type");
		Double longitude = Double.valueOf((String)signaleMap.get("longitude"));
		Double latitude = Double.valueOf((String)signaleMap.get("latitude"));
		String statu = (String)signaleMap.get("statu");
		String commentaire = (String)signaleMap.get("commentaire");
		String isAffecte = (String)signaleMap.get("isAffecte");

		System.out.println("UserNom : "+usernom);
		Signalement signalement = new Signalement();
		User user = userRepository.findByName(usernom);
		Region region = regionRepository.regionByNom(regionname);
		TypeSignalement typesign = typeSignalementRepository.typeSignalementBydesc(type);
		
		signalement.setIdUtilisateur(user.getId());
		signalement.setIdRegion(region.getId());
		signalement.setIdTypeSignalement(typesign.getId());		
		signalement.setLongitude(longitude);
		signalement.setLatitude(latitude);
		signalement.setStatut(statu);		
		signalement.setIsAffecte(isAffecte);
		signalementRepository.createSignalement(usernom, regionname, type, longitude, latitude, statu, commentaire, isAffecte);		
		return signalement.getCommentaire();
	}
}

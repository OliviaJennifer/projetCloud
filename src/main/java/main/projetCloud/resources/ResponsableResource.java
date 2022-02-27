package main.projetCloud.resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import main.projetCloud.Constants;
import main.projetCloud.domain.Admin;
import main.projetCloud.domain.Region;
import main.projetCloud.domain.Responsable;
import main.projetCloud.domain.Signalement;
import main.projetCloud.domain.TypeSignalement;
import main.projetCloud.repositories.RegionRepository;
import main.projetCloud.repositories.TypeSignalementRepository;
import main.projetCloud.service.ResponsableService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/responsable")
public class ResponsableResource {
	@Autowired
	ResponsableService responsableService;
	
	@Autowired
	TypeSignalementRepository typeSignalementRepository;

	@Autowired
	RegionRepository regionRepository;
	@GetMapping("/allRegion")
	public List<Region> getRegions(){
		List<Region> liste = regionRepository.allRegion();
		return liste;
	}
	
	@CrossOrigin(origins="*", allowedHeaders = "*") 
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> loginResponsable(@RequestBody Map<String, Object> responsableMap){		
		String email = (String) responsableMap.get("email");
		String mdp = (String) responsableMap.get("mdp");
		System.out.println("Login invoked :"+email+"// "+mdp);
		System.out.println("LOGIN");
		Responsable responsable = responsableService.validateResponsable(email, mdp);
		return new ResponseEntity(generateJWTToken(responsable), HttpStatus.OK);		
	}
	
	@GetMapping("/allTypeSignalement")
	public List<TypeSignalement> getSignalements(){
		List<TypeSignalement> liste = typeSignalementRepository.allType();
		return liste;
	}
	
	@CrossOrigin(origins="*", allowedHeaders = "*")
	@PostMapping("/register")
	public ResponseEntity<Map<String,String>> registerResponsable(@RequestBody Map<String, Object> responsableMap) {		
		Integer idregion = Integer.valueOf((String)responsableMap.get("idregion"));
		String email = (String) responsableMap.get("email");
		String mdp = (String) responsableMap.get("mdp");		
		System.out.println("RESPONSABLE : register : idregion "+idregion);
		System.out.println("RESPONSABLE : register : email "+email);
		System.out.println("RESPONSABLE : register : mdp "+mdp);
		Responsable responsable = responsableService.registerResponsable(idregion, email, mdp);
		return new ResponseEntity(generateJWTToken(responsable), HttpStatus.OK);		
	}
	
	private Map<String, String> generateJWTToken(Responsable responsable)
	{
		System.out.println("IDDD : "+responsable.getId());
		System.out.println("IDDD : "+responsable.getIdRegion());
		System.out.println("IDDD : "+responsable.getEmail());
		System.out.println("IDDD : "+responsable.getMdp());

		Map<String, String> map = new HashMap<>();
		if(responsable!=null) {
		long timestamp = System.currentTimeMillis();
		String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
				.setIssuedAt(new Date(timestamp))
				.setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
				.claim("userId", responsable.getId())
				.claim("idregion", responsable.getIdRegion())
				.claim("email", responsable.getEmail())
				.claim("mdp",responsable.getMdp())
				.compact();
		map.put("token",token);
		return map;
		}else {
			map.put("error", "erreur");
		}
		map.put("error", "erreur");
		return map;
	}
}

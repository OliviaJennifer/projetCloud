package main.projetCloud.resources;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import main.projetCloud.Constants;
import main.projetCloud.domain.Admin;
import main.projetCloud.domain.User;
import main.projetCloud.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/users")
public class UserResource {

	@Autowired
	UserService userService;
	
	@CrossOrigin(origins="*", allowedHeaders = "*") 
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap){
		String nom = (String) userMap.get("nom");
		String password = (String) userMap.get("password");
		User user = userService.validateUser(nom, password);
//		Map<String, String> map = new HashMap(); 
//		map.put("message", "loggedIn succefully");
		return new ResponseEntity(generateJWTToken(user), HttpStatus.OK);
	}
	@CrossOrigin(origins="*", allowedHeaders = "*") 
	@PostMapping("/register")
	public ResponseEntity<Map<String,String>> registerUser(@RequestBody Map<String, Object> userMap) {
		String nom = (String) userMap.get("nom");
		String prenom = (String) userMap.get("prenom");
		String sexe = (String) userMap.get("sexe");
		String dtn = (String)userMap.get("dtn");
		String adresse = (String) userMap.get("adresse");
		String email = (String) userMap.get("email");
		String password = (String) userMap.get("password");		
		User user = userService.registerUser(nom, prenom, sexe, dtn, adresse, email, password);
//		Map<String ,String>map=new HashMap<>();
//		map.put("message", "registered succefuly");
		return new ResponseEntity(generateJWTToken(user), HttpStatus.OK);
		
	}
	
	private Map<String, String> generateJWTToken(User user)
	{
		System.out.println("IDDD : "+user.getId());
		System.out.println("IDDD : "+user.getEmail());
		System.out.println("IDDD : "+user.getPassword());

		Map<String, String> map = new HashMap<>();
		if(user!=null) {
		long timestamp = System.currentTimeMillis();
		String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
				.setIssuedAt(new Date(timestamp))
				.setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
				.claim("userId", user.getId())
				.claim("email", user.getEmail())
				.claim("mdp",user.getPassword())
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

package main.projetCloud.resources;

import java.util.Date;
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
import main.projetCloud.service.AdminService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/admin")
public class AdminResource {

	@Autowired
	AdminService adminService;
	
	@CrossOrigin(origins="*", allowedHeaders = "*") 
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> loginAdmin(@RequestBody Map<String, Object> adminMap){		
		String email = (String) adminMap.get("email");
		String mdp = (String) adminMap.get("mdp");
		System.out.println("Login invoked :"+email+"// "+mdp);
		System.out.println("LOGIN");
		Admin admin = adminService.validateAdmin(email, mdp);
		return new ResponseEntity(generateJWTToken(admin), HttpStatus.OK);		
	}
	
	
	@CrossOrigin(origins="*", allowedHeaders = "*")
	@PostMapping("/register")
	public ResponseEntity<Map<String,String>> registerAdmin(@RequestBody Map<String, Object> adminMap) {
		
		String email = (String) adminMap.get("email");
		String mdp = (String) adminMap.get("mdp");		
		Admin admin = adminService.registerAdmin(email, mdp);
		return new ResponseEntity(generateJWTToken(admin), HttpStatus.OK);		
	}
	
	private Map<String, String> generateJWTToken(Admin admin)
	{
		System.out.println("IDDD : "+admin.getId());
		System.out.println("IDDD : "+admin.getEmail());
		System.out.println("IDDD : "+admin.getMdp());

		Map<String, String> map = new HashMap<>();
		if(admin!=null) {
		long timestamp = System.currentTimeMillis();
		String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
				.setIssuedAt(new Date(timestamp))
				.setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
				.claim("userId", admin.getId())
				.claim("email", admin.getEmail())
				.claim("mdp",admin.getMdp())
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

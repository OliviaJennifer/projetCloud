package main.projetCloud.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.web.bind.annotation.Request;

import main.projetCloud.domain.Signalement;

public interface SignalementRepository {
	//extends JpaRepository<Signalement,Integer>
//	String insertSignale(Integer id, Integer idRegion, Integer idTypeSignalement, Integer idUtilisateur, Double longitude,
//			Double latitude, String commentaire, Timestamp daty, String statut, String isAffecte);
	
	Integer createSignalement(String usernom,String regionname,String type,Double longitude,Double latitude,String statu,String commentaire,String isAffecte);
	
	List<Signalement> allSignalement();
	
	List<Signalement> userSignalements(Integer iduser);

}

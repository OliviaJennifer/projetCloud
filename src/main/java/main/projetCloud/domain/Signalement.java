package main.projetCloud.domain;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
//
//@Data
//@Entity
//@Table(name="signalement")
public class Signalement {
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	private Integer idRegion;	
	private Integer idTypeSignalement;	
	private Integer idUtilisateur;
	private Double longitude;
	private Double latitude;
	private String commentaire;
	private Date daty;
	private String statut;
	
//	@Column(name="isaffecte")
	private String isAffecte;
	
	public Signalement() {
	}
	public Signalement(Integer id, Integer idRegion, Integer idTypeSignalement, Integer idUtilisateur, Double longitude,
			Double latitude, String commentaire, Date daty, String statut, String isAffecte) {
		super();
		this.id = id;
		this.idRegion = idRegion;
		this.idTypeSignalement = idTypeSignalement;
		this.idUtilisateur = idUtilisateur;
		this.longitude = longitude;
		this.latitude = latitude;
		this.commentaire = commentaire;
		this.daty = daty;
		this.statut = statut;
		this.isAffecte = isAffecte;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}
	public Integer getIdTypeSignalement() {
		return idTypeSignalement;
	}
	public void setIdTypeSignalement(Integer idTypeSignalement) {
		this.idTypeSignalement = idTypeSignalement;
	}
	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Date getDaty() {
		return daty;
	}
	public void setDaty(Date daty) {
		this.daty = daty;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getIsAffecte() {
		return isAffecte;
	}
	public void setIsAffecte(String isAffecte) {
		this.isAffecte = isAffecte;
	}
	
	
}

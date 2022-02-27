package main.projetCloud.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

public class TypeSignalement {
	private Integer id;
	private String description;
	private String couleur;
	
	public TypeSignalement() {
		
	}
	public TypeSignalement(Integer id, String description, String couleur) {
		this.id = id;
		this.description = description;
		this.couleur = couleur;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	
}

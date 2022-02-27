package main.projetCloud.domain;

public class Region {
	private Integer id;
	private String nom;
	public Region() {
	}
	public Integer getId() {
		return id;
	}
	
	public Region(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}

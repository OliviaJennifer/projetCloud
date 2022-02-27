package main.projetCloud.domain;

public class User {
	private Integer id;
	private String nom;
	private String prenom;
	private String sexe;
	private String dtn;
	private String adresse;
	private String email;
	private String  password;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(Integer id, String nom, String prenom, String sexe, String dtn, String adresse, String email,
			String password) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.dtn = dtn;
		this.adresse = adresse;
		this.email = email;
		this.password = password;
	}
	public Integer getId() {
		return id;
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getDtn() {
		return dtn;
	}
	public void setDtn(String dtn) {
		this.dtn = dtn;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}

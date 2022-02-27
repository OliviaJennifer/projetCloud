package main.projetCloud.domain;

public class Responsable {
	private Integer id;
	private Integer idRegion;
	private String email;
	private String mdp;

	public Responsable() {
		
	}
	public Responsable(Integer id, Integer idRegion, String email, String mdp) {
		super();
		this.id = id;
		this.idRegion = idRegion;		
		this.email = email;
		this.mdp = mdp;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
}

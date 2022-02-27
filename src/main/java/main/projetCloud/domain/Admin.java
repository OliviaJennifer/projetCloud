package main.projetCloud.domain;
//Test change
public class Admin {
	private Integer id;
	private String email;
	private String mdp;	
	public Admin() {}

	public Admin(Integer id, String email, String mdp) {
		super();
		this.id = id;
		this.email = email;
		this.mdp = mdp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

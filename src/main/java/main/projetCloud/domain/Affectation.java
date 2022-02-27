package main.projetCloud.domain;

import java.sql.Date;

public class Affectation {
	private Integer id;
	private Integer idSignalement;
	private Integer idAdmin;
	private Integer idRegion;
	private Date datedeValidation;
	public Affectation() {}
	public Affectation(Integer id, Integer idSignalement, Integer idAdmin, Integer idRegion, Date datedeValidation) {
		super();
		this.id = id;
		this.idSignalement = idSignalement;
		this.idAdmin = idAdmin;
		this.idRegion = idRegion;
		this.datedeValidation = datedeValidation;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdSignalement() {
		return idSignalement;
	}
	public void setIdSignalement(Integer idSignalement) {
		this.idSignalement = idSignalement;
	}
	public Integer getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(Integer idAdmin) {
		this.idAdmin = idAdmin;
	}
	public Integer getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}
	public Date getDatedeValidation() {
		return datedeValidation;
	}
	public void setDatedeValidation(Date datedeValidation) {
		this.datedeValidation = datedeValidation;
	}
	
	
	
}

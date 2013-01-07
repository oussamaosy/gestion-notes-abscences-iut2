package model;

public class Matiere {

	private Integer id;
	private String nom;
	

	public Matiere() {
		super();
	}
	
	public Matiere(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
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
	
}

package model;

public class Absence {
	
	private int EtudiantId;
	private int matiere;
	private int nbHeures;
	
	public Absence() {
		super();
	}
	
	public Absence(int etudiantId, int matiere, int nbHeures) {
		super();
		this.EtudiantId = etudiantId;
		this.matiere = matiere;
		this.nbHeures = nbHeures;
	}

	public int getEtudiantId() {
		return EtudiantId;
	}
	public void setEtudiantId(int etudiantId) {
		EtudiantId = etudiantId;
	}
	public int getMatiere() {
		return matiere;
	}
	public void setMatiere(int matiere) {
		this.matiere = matiere;
	}
	public int getNbHeures() {
		return nbHeures;
	}
	public void setNbHeures(int nbHeures) {
		this.nbHeures = nbHeures;
	}
}

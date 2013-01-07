package model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestionFactory {

	/****************************************************Partie Etudiant***********************************************************/

	
	private static final List<Etudiant> listEtudiants = Arrays.asList(
			new Etudiant(0, "Francis", "Brunet-Manquat", 0),
			new Etudiant(1, "Philippe", "Martin", 0),
			new Etudiant(2, "Mario", "Cortes-Cornax", 0),
			new Etudiant(3, "Françoise", "Coat", 1),
			new Etudiant(4, "Laurent", "Bonnaud", 2),
			new Etudiant(5, "Sébastien", "Bourdon", 2),
			new Etudiant(6, "Mathieu", "Gatumel", 1)
			);

	// Retourne l'ensemble des etudiants
	public static List<Etudiant> getEtudiants() {
		return listEtudiants;
	}
	
	// Retourne l'ensemble des etudiants d'un groupe donné
	public static List<Etudiant> getEtudiantsParGroupe(int groupe) {
		ArrayList<Etudiant> listEtudiantsParGroupe = new ArrayList<Etudiant>();
		for (Etudiant etudiant : getEtudiants()) {
			if (etudiant.getGroupe()==(groupe)) {
				listEtudiantsParGroupe.add(etudiant);
			}
		}
		return listEtudiantsParGroupe;
	}
	
	// Retourne l'etudiant d'un id d'etudiant donné sinon retourne null
	public static Etudiant getEtudiantParId(int etuId) {
		for (Etudiant etu : getEtudiants()) {
			if (etu.getId()==etuId) {
				return etu;
			}
		}
		return null;
	}
	
	/****************************************************Partie Absence***********************************************************/
	
	private static final List<Absence> listAbsences = Arrays.asList(
			new Absence(0,5,2),
			new Absence(1,1,2),
			new Absence(0,2,3),
			new Absence(2,4,5),
			new Absence(2,4,4),
			new Absence(4,2,6),
			new Absence(5,1,1)
			);

	// Retourne l'ensemble des Absences
	public static List<Absence> getAbsences() {
		return listAbsences;
	}
	
	// Retourne l'ensemble des absences d'un id d'etudiant donné
	public static List<Absence> getAbsencesParEtudiant(int etuId) {
		ArrayList<Absence> listAbsencesParEtudiant = new ArrayList<Absence>();
		for (Absence absence : getAbsences()) {
			if (absence.getEtudiantId()==etuId) {
				listAbsencesParEtudiant.add(absence);
			}
		}
		return listAbsencesParEtudiant;
	}
		
	// Retourne l'ensemble des absences d'un groupe donné
		public static List<Absence> getAbsencesParGroupe(int groupe) {
			ArrayList<Absence> listAbsencesParGroupe = new ArrayList<Absence>();
			for (Absence absence : getAbsences()) {
				if (getEtudiantParId(absence.getEtudiantId()).getGroupe()==groupe) {
					listAbsencesParGroupe.add(absence);
				}
			}
			return listAbsencesParGroupe;
		}
		
		/****************************************************Partie Groupe***********************************************************/

		private static final List<Groupe> listGroupes = Arrays.asList(
				new Groupe(0,"MIAM"),
				new Groupe(1,"SIMO"),
				new Groupe(2,"MESSI")
				);
		
		// Retourne l'ensemble des Groupes
		public static List<Groupe> getGroupes() {
			return listGroupes;
		}
		
		/****************************************************Partie Matiere***********************************************************/

		private static final List<Matiere> listMatieres = Arrays.asList(
				new Matiere(0,"INF1"),
				new Matiere(1,"INF2"),
				new Matiere(2,"INF3"),
				new Matiere(3,"INF4"),
				new Matiere(4,"ENV1"),
				new Matiere(5,"ENV2"),
				new Matiere(6,"ENV3")
				);
		
		// Retourne l'ensemble des matieres
		public static List<Matiere> getMatieres() {
			return listMatieres;
		}
}

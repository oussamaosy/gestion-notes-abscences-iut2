

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestionFactory {

	private static final List<Etudiant> listEtudiants = Arrays.asList(
			new Etudiant(0, "Francis", "Brunet-Manquat", "MIAM"),
			new Etudiant(1, "Philippe", "Martin", "MIAM"),
			new Etudiant(2, "Mario", "Cortes-Cornax", "MIAM"),
			new Etudiant(3, "Françoise", "Coat", "3IS"),
			new Etudiant(4, "Laurent", "Bonnaud", "MESSI"),
			new Etudiant(5, "Sébastien", "Bourdon", "MESSI"),
			new Etudiant(6, "Mathieu", "Gatumel", "3IS")
			);

	// Retourne l'ensemble des etudiants
	public static List<Etudiant> getEtudiants() {
		return listEtudiants;
	}
	
	// Retourne l'ensemble des etudiants d'un groupe donné
	public static List<Etudiant> getEtudiantsParGroupe(String groupe) {
		ArrayList<Etudiant> listEtudiantsParGroupe = new ArrayList<Etudiant>();
		for (Etudiant etudiant : getEtudiants()) {
			if (etudiant.getGroupe().equals(groupe)) {
				listEtudiantsParGroupe.add(etudiant);
			}
		}
		return listEtudiantsParGroupe;
	}
}

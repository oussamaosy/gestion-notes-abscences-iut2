

public class TestAbsences {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Liste des étudiants :");
		for (Etudiant etu : GestionFactory.getEtudiants()) {
			System.out.println(etu.getPrenom() + " " + etu.getNom() + " " + etu.getGroupe() + " " + etu.getNbAbsences());
		}
		
		System.out.println("\nListe des étudiants MIAM:");
		for (Etudiant etu : GestionFactory.getEtudiantsParGroupe("MIAM")) {
			System.out.println(etu.getPrenom() + " " + etu.getNom() + " " + etu.getGroupe() + " " + etu.getNbAbsences());
		}
		
		System.out.println("\nListe des étudiants 3IS:");
		for (Etudiant etu : GestionFactory.getEtudiantsParGroupe("3IS")) {
			System.out.println(etu.getPrenom() + " " + etu.getNom() + " " + etu.getGroupe() + " " + etu.getNbAbsences());
		}
		
		System.out.println("\nListe des étudiants MESSI:");
		for (Etudiant etu : GestionFactory.getEtudiantsParGroupe("MESSI")) {
			System.out.println(etu.getPrenom() + " " + etu.getNom() + " " + etu.getGroupe() + " " + etu.getNbAbsences());
		}
	}

}

package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Absence;
import model.Etudiant;
import model.EtudiantPeer;
import model.Groupe;
import model.GroupePeer;
import model.Matiere;
import model.MatierePeer;




public class Absences{

	private Controleur controleur;

	public Absences(Controleur controleur) {
		// TODO Auto-generated constructor stub
		this.controleur=controleur;

	}

	public  void traiterActions(HttpServletRequest request, HttpServletResponse response, String action, String methode) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String act=action;
		if(act.indexOf("/")>0){
			//Répartition dans les controleurs
			act = act.substring(act.indexOf("/")+1);
		
			if (methode.equals("get") && act.equals("editer")) {
				home(request, response);
			
			} else if (methode.equals("get") && act.equals("supprimer")) {
				supprimer(request, response);
				
			} else if (methode.equals("get") && act.equals("creer")) {
				creer(request, response);
			
			} else if (methode.equals("get") && act.equals("editer")) {
				System.out.println("editer : "+controleur.getPathEditerAbsences());
				editer(request, response);
				
			}
		}else{
			home(request, response);	
		}
		System.out.println("action : "+act);

		
	
}
	
	//
	private  void home(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pathView",controleur.getPathAbsences());
		System.out.println(request.getParameter("choix"));
		// on récupère les données du post
		String choix = "groupe";//request.getParameter("choix");
		int choixMatiere = 0;//Integer.parseInt(request.getParameter("matiere"));
		
		List<Etudiant> listEtudiants;
		List<Groupe> listGroupes;
		List<Matiere> listMatiere;
		
		try {
			/*************Remplissage du formulaire***************/
			//Accès aux données
			listEtudiants = EtudiantPeer.doSelectAll();
			listGroupes = GroupePeer.doSelectAll();
			listMatiere = MatierePeer.doSelectAll();
			
			//Transferer les listes à la VUE
			request.setAttribute("etudiants",listEtudiants);
			request.setAttribute("groupes",listGroupes);
			request.setAttribute("matieres",listMatiere);

			/*************Remplissage du tableau******************/
	    	List<Absence> absences = new ArrayList<Absence>();
			if(choix=="etudiant"){
				int choixEtudiant = 1;//Integer.parseInt(request.getParameter("etudiant"));
				if(choixEtudiant!=0){
					if(choixMatiere!=0){
						absences = Absence.getAbsencesEtudiantForMatiere(choixEtudiant, choixMatiere);
					}else{
						absences = Absence.getAbsencesEtudiant(choixEtudiant);
					}
				}
			}else{
				int choixGroupe = 1;//Integer.parseInt(request.getParameter("groupe"));
				if(choixGroupe!=0){
					if(choixMatiere!=0){
						absences = Absence.getAbsencesGroupeForMatiere(choixGroupe, choixMatiere);
					}else{
						absences = Absence.getAbsencesGroupe(choixGroupe);
					}
				}
			}
			//Transferer les notes
			request.setAttribute("absences",absences);
			
			/*************Remplissage de la moyenne******************/
			
			int nbAbs = 0;
			int nbHeures = 0;
			for(Absence absence : absences){
				nbAbs = nbAbs + 1;
				nbHeures = nbHeures + absence.getNbheures();
			}
			
			//Transferer la moyenne des notes
			request.setAttribute("nbAbs",nbAbs);
			request.setAttribute("nbHeures",nbHeures);
			
			controleur.loadJSP(controleur.getPathMain(), request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//
	private  void supprimer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pathView",controleur.getPathSupprimerAbsences());
	}
	
	//
	private  void creer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pathView",controleur.getPathCreerAbsences());
	}
	private  void editer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pathView",controleur.getPathEditerAbsences());
	}
}
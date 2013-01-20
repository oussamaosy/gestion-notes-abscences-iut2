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
import model.Note;

public class Notes {

	private Controleur controleur;

	public Notes(Controleur controleur) {
		// TODO Auto-generated constructor stub
		this.controleur=controleur;

	}

	public  void traiterActions(HttpServletRequest request, HttpServletResponse response, String action, String methode) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String act=action;
		System.out.println("action : "+act);

		if(act.indexOf("/")>0){
			//Répartition dans les controleurs
			act = act.substring(act.indexOf("/")+1);
		
			if (methode.equals("get") && act.equals("editer")) {
				home(request, response);
			
			} else if (methode.equals("get") && act.equals("supprimer")) {
				supprimer(request, response);
				
			} else if (methode.equals("get") && act.equals("creer")) {
				creer(request, response);
			}else if (methode.equals("get") && act.equals("editer")) {
				editer(request, response);
			}
		}else{
			home(request, response);	
		}
		System.out.println("action : "+act);

	
}
	//
	private void home(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("pathView",controleur.getPathNotes());
		// on récupère les données du post
		String choix = "choix";
		if(request.getParameter("choix")!=null)
			choix = request.getParameter("choix");
		int choixMatiere = 0;
		if(request.getParameter("matiere")!=null)
			choixMatiere = Integer.parseInt(request.getParameter("matiere"));
		
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
	    	List<Note> notes = new ArrayList<Note>();
	    	int choixEtudiant = 0;
	    	int choixGroupe = 0;
			if(choix.equalsIgnoreCase("etudiant")){
				if(request.getParameter("etudiant")!=null)
					choixEtudiant = Integer.parseInt(request.getParameter("etudiant"));
				if(choixEtudiant!=0){
					if(choixMatiere!=0){
						notes = Note.getNotesEtudiantForMatiere(choixEtudiant, choixMatiere);
					}else{
						notes = Note.getNotesEtudiant(choixEtudiant);
					}
				}
			}else if(choix.equalsIgnoreCase("groupe")){
				if(request.getParameter("groupe")!=null)
					choixGroupe = Integer.parseInt(request.getParameter("groupe"));
				if(choixGroupe!=0){
					if(choixMatiere!=0){
						notes = Note.getNotesGroupeForMatiere(choixGroupe, choixMatiere);
					}else{
						notes = Note.getNotesGroupe(choixGroupe);
					}
				}
				System.out.println("numGroupe: "+choixGroupe);
			}
			//Transferer les notes
			request.setAttribute("notes",notes);
			
			/*************Remplissage de la moyenne******************/
			
			float avgNote = 0;
			if(!notes.isEmpty()){
				for(Note note : notes){
					avgNote = (float) (avgNote + note.getNote());
				}
				avgNote = avgNote/notes.size();
			}
			
			//Transferer la moyenne des notes
			request.setAttribute("avgNote",avgNote);
			request.setAttribute("choix",choix);
			request.setAttribute("choixMatiere",choixMatiere);
			request.setAttribute("choixEtudiant",choixEtudiant);
			request.setAttribute("choixGroupe",choixGroupe);
			
			controleur.loadJSP(controleur.getPathMain(), request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//
	private void supprimer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pathView",controleur.getPathSupprimerNotes());
	}
	
	//
	private void creer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pathView",controleur.getPathCreerNotes());
	}
	
	private  void editer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pathView",controleur.getPathEditerNotes());
	}
}
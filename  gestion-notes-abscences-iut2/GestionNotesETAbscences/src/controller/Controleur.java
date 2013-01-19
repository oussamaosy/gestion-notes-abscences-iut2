package controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Absence;
import model.BaseNote;
import model.Etudiant;
import model.EtudiantPeer;
import model.GestionFactory;
import model.Groupe;
import model.GroupePeer;
import model.Matiere;
import model.MatierePeer;
import model.Note;


@SuppressWarnings("serial")
public class Controleur extends HttpServlet {
	private String pathHome;
	private String pathMain;
	private String pathEditerAbsences;
	private String pathCreerAbsences;
	private String pathSupprimerAbsences;
	private String pathEditerNotes;
	private String pathCreerNotes;
	private String pathSupprimerNotes;
	// INIT
	public void init() throws ServletException {
		pathMain = getServletConfig().getInitParameter("pathMain");
		pathHome = getServletConfig().getInitParameter("pathHome");
		pathEditerAbsences = getServletConfig().getInitParameter("pathEditerAbsences");
		pathSupprimerAbsences = getServletConfig().getInitParameter("pathSupprimerAbsences");
		pathCreerAbsences = getServletConfig().getInitParameter("pathCreerAbsences");
		pathEditerNotes = getServletConfig().getInitParameter("pathEditerNotes");
		pathSupprimerNotes = getServletConfig().getInitParameter("pathSupprimerNotes");
		pathCreerNotes = getServletConfig().getInitParameter("pathCreerNotes");
	}
	
	// POST
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// on passe la main au GET
		doGet(request, response);
	}
	
	// GET
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {


		// On récupère la méthode d'envoi de la requête
		String methode = request.getMethod().toLowerCase();
		
		// On récupère l'action à exécuter
		String action = request.getPathInfo();
		if (action == null) {
			action = "/home";
		}
		
		// Exécution action
		if (methode.equals("get") && action.equals("/home")) {
			doHome(request, response);

		} else if (methode.equals("get") && action.equals("/editerAbsences")) {
			doEditerAbsences(request, response);
		
		} else if (methode.equals("get") && action.equals("/supprimerAbsences")) {
			doSupprimerAbsences(request, response);
			
		} else if (methode.equals("get") && action.equals("/creerAbsences")) {
			doCreerAbsences(request, response);
			
		} else if (methode.equals("get") && action.equals("/editerNotes")) {
			doEditerNotes(request, response);
			
		} else if (methode.equals("get") && action.equals("/supprimerNotes")) {
			doSupprimerNotes(request, response);
			
		} else if (methode.equals("get") && action.equals("/creerNotes")) {
			doCreerNotes(request, response);
			
		} else {
			// Autres cas
			doHome(request, response);
		}
	}

	private void doHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			request.setAttribute("pathView",pathHome);
			loadJSP(pathMain, request, response);
	}

	//
	private void doEditerAbsences(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pathView",pathEditerAbsences);
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
			
			loadJSP(pathMain, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//
	private void doSupprimerAbsences(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pathView",pathSupprimerAbsences);
	}
	
	//
	private void doCreerAbsences(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pathView",pathCreerAbsences);
	}
	
	//
	private void doEditerNotes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("pathView",pathEditerNotes);
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
	    	List<Note> notes = new ArrayList<Note>();
			if(choix=="etudiant"){
				int choixEtudiant = 1;//Integer.parseInt(request.getParameter("etudiant"));
				if(choixEtudiant!=0){
					if(choixMatiere!=0){
						notes = Note.getNotesEtudiantForMatiere(choixEtudiant, choixMatiere);
					}else{
						notes = Note.getNotesEtudiant(choixEtudiant);
					}
				}
			}else{
				int choixGroupe = 1;//Integer.parseInt(request.getParameter("groupe"));
				if(choixGroupe!=0){
					if(choixMatiere!=0){
						notes = Note.getNotesGroupeForMatiere(choixGroupe, choixMatiere);
					}else{
						notes = Note.getNotesGroupe(choixGroupe);
					}
				}
			}
			//Transferer les notes
			request.setAttribute("notes",notes);
			
			/*************Remplissage de la moyenne******************/
			
			float avgNote = 0;
			for(Note note : notes){
				avgNote = (float) (avgNote + note.getNote());
			}
			avgNote = avgNote/notes.size();
			//Transferer la moyenne des notes
			request.setAttribute("avgNote",avgNote);
			
			loadJSP(pathMain, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//
	private void doSupprimerNotes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pathView",pathSupprimerNotes);
	}
	
	//
	private void doCreerNotes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pathView",pathCreerNotes);
	}


	public void loadJSP(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
	}


}

package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Absence;
import model.AbsencePeer;
import model.Etudiant;
import model.EtudiantPeer;
import model.Groupe;
import model.GroupePeer;
import model.Matiere;
import model.MatierePeer;
import model.Note;
import model.NotePeer;

public class Notes {

	private Controleur controleur;
	private List<String> attributs ;
	public Notes(Controleur controleur) {
		// TODO Auto-generated constructor stub
		this.controleur=controleur;
		attributs= new ArrayList<String>();
		attributs.add("note");
		attributs.add("etudiantId");
		attributs.add("matiereId");
		attributs.add("id");

	}

	public  void traiterActions(HttpServletRequest request, HttpServletResponse response, String action, String methode) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String act=action;
		System.out.println("action : "+act);

		if(act.indexOf("/")>0){
			//Répartition dans les controleurs
			act = act.substring(act.indexOf("/")+1);
			if (methode.equals("get") && act.equals("editer")) {
				editer(request, response);
			
			} else if (methode.equals("get") && act.equals("supprimer")) {
				supprimer(request, response);
				
			} else if (methode.equals("get") && act.equals("ajouter")) {
				ajouter(request, response);
				
			} else if (methode.equals("post") && act.equals("creer")) {
				creer(request, response);
			
			} else if(methode.equals("post") && act.equals("modifier")) {
				modifier(request, response);
			
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
		//String choix = "choix";
		//if(request.getParameter("choix")!=null)
		//	choix = request.getParameter("choix");
		int choixMatiere = 0;
		if(request.getParameter("matiereId")!=null)
			choixMatiere = Integer.parseInt(request.getParameter("matiereId"));
		
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
			System.out.println(request.getParameter("etudiantId"));
			System.out.println(request.getParameter("groupeId"));
	    	if(request.getParameter("etudiantId")!=null && !request.getParameter("etudiantId").equals("0")){
					choixEtudiant = Integer.parseInt(request.getParameter("etudiantId"));
				if(choixEtudiant!=0){
					if(choixMatiere!=0){
						notes = Note.getNotesEtudiantForMatiere(choixEtudiant, choixMatiere);
					}else{
						notes = Note.getNotesEtudiant(choixEtudiant);
					}
				}
			}else if(request.getParameter("groupeId")!=null && !request.getParameter("groupeId").equals("0")){
					choixGroupe = Integer.parseInt(request.getParameter("groupeId"));
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
			//request.setAttribute("choix",choix);
			request.setAttribute("matiereId",choixMatiere);
			request.setAttribute("etudiantId",choixEtudiant);
			request.setAttribute("groupeId",choixGroupe);
			
			controleur.loadJSP(controleur.getPathMain(), request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//
	private void supprimer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("id");
		int etudiantId = 0;
		if (id!=null){
			try {
				Note note = NotePeer.retrieveByPK(Integer.parseInt(id));
				etudiantId=note.getEtudiantId();

				NotePeer.doDelete(note);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("/GestionNotesETAbscences/gestion/notes?etudiantId="+etudiantId);

	}
	
	//
	private void creer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pathView",controleur.getPathCreerNotes());
		try {
			List<String> attributsForm = attributs;
			attributsForm.remove("id");
			if(controleur.checkForm(attributsForm, request)){
				request.setAttribute("pathView",controleur.getPathEditerNotes());

				System.out.println("SAUVEGARDE: "+request.getParameter("etudiantId"));

				
				Note note =new Note();
				if(Float.parseFloat(request.getParameter("note"))>20){
					System.out.println("Formu");
					request.setAttribute("msgErreur","La note doit être comprise entre 0 et 20");
					controleur.reinitialiserForm(attributsForm, request);
					ajouter(request,response);
				}else{
					note.setNote(Float.parseFloat(request.getParameter("note")));
					note.setEtudiant(EtudiantPeer.retrieveByPK(Integer.parseInt(request.getParameter("etudiantId"))));
					note.setMatiere(MatierePeer.retrieveByPK(Integer.parseInt(request.getParameter("matiereId"))));
	
					note.save();
	
					System.out.println("SAUVEGARDE");
					System.out.println(note);
					response.sendRedirect("/GestionNotesETAbscences/gestion/notes?etudiantId="+Integer.parseInt(request.getParameter("etudiantId")));
				}
			}else{
				System.out.println("Formu");
				request.setAttribute("msgErreur","Des champs sont manquants");
				controleur.reinitialiserForm(attributsForm, request);
				ajouter(request,response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private  void editer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			
			//Accès aux données
			List<Etudiant> listEtudiants;
			List<Matiere> listMatieres;

			listEtudiants = EtudiantPeer.doSelectAll();
			listMatieres = MatierePeer.doSelectAll();
			
			Note note =NotePeer.retrieveByPK(Integer.parseInt(request.getParameter("id")));

			//Transferer les listes à la VUE
			request.setAttribute("etudiants",listEtudiants);
			
			request.setAttribute("pathView",controleur.getPathEditerNotes());
			request.setAttribute("noteEtu",note);
			request.setAttribute("titre","Editer une absence de "+note.getEtudiant().getPrenom()+" "+note.getEtudiant().getNom());
			request.setAttribute("actionForm","modifier");

			request.setAttribute("etudiants",listEtudiants);
			request.setAttribute("Matieres",listMatieres);
			request.setAttribute("isNew",false);

			System.out.println(note);
			controleur.loadJSP(controleur.getPathMain(), request, response);

		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private  void ajouter(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			
			//Accès aux données
			List<Etudiant> listEtudiants;
			List<Matiere> listMatieres;

			listEtudiants = EtudiantPeer.doSelectAll();
			listMatieres = MatierePeer.doSelectAll();
			

			//Transferer les listes à la VUE
			request.setAttribute("etudiants",listEtudiants);
			
			request.setAttribute("pathView",controleur.getPathEditerNotes());
			request.setAttribute("noteEtu",null);
			request.setAttribute("titre","Ajouter une note");
			request.setAttribute("actionForm","creer");

			request.setAttribute("etudiants",listEtudiants);
			request.setAttribute("Matieres",listMatieres);
			request.setAttribute("isNew",true);
			controleur.loadJSP(controleur.getPathMain(), request, response);

		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private  void modifier(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			if(controleur.checkForm(attributs, request)){
				request.setAttribute("pathView",controleur.getPathEditerNotes());

				System.out.println("SAUVEGARDE: "+request.getParameter("id"));

				
				Note note =NotePeer.retrieveByPK(Integer.parseInt(request.getParameter("id")));
				if(Float.parseFloat(request.getParameter("note"))>20){
					request.setAttribute("msgErreur","La note doit être comprise entre 0 et 20!");
					editer(request,response);
				}else{
					note.setNote(Float.parseFloat(request.getParameter("note")));
					note.setEtudiant(EtudiantPeer.retrieveByPK(Integer.parseInt(request.getParameter("etudiantId"))));
					note.setMatiere(MatierePeer.retrieveByPK(Integer.parseInt(request.getParameter("matiereId"))));
					
					note.save();
	
					System.out.println("SAUVEGARDE");
					System.out.println(note);
					response.sendRedirect("/GestionNotesETAbscences/gestion/notes?etudiantId="+Integer.parseInt(request.getParameter("etudiantId")));
				}
			}else{
				System.out.println("Formu");
				editer(request,response);
				request.setAttribute("msgErreur","Des champs sont manquants");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
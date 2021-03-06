package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.torque.NoRowsException;
import org.apache.torque.TooManyRowsException;
import org.apache.torque.TorqueException;

import model.Absence;
import model.AbsencePeer;
import model.Etudiant;
import model.EtudiantPeer;
import model.Groupe;
import model.GroupePeer;
import model.Matiere;
import model.MatierePeer;




public class Absences{

	private Controleur controleur;
	private List<String> attributs ;
	public Absences(Controleur controleur) {
		// TODO Auto-generated constructor stub
		this.controleur=controleur;
		attributs= new ArrayList<String>();
		attributs.add("nbheures");
		attributs.add("etudiantId");
		attributs.add("matiereId");
		attributs.add("date");
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
	private  void home(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pathView",controleur.getPathAbsences());
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
	    	List<Absence> absences = new ArrayList<Absence>();
	    	int choixEtudiant = 0;
	    	int choixGroupe = 0;


	    	if(request.getParameter("etudiantId")!=null && !request.getParameter("etudiantId").equals("0")){
					choixEtudiant = Integer.parseInt(request.getParameter("etudiantId"));
				if(choixEtudiant!=0){
					if(choixMatiere!=0){
						absences = AbsencePeer.getAbsencesEtudiantForMatiere(choixEtudiant, choixMatiere);
					}else{
						absences = AbsencePeer.getAbsencesEtudiant(choixEtudiant);
					}
				}
			}else if((request.getParameter("groupeId")!=null && !request.getParameter("groupeId").equals("0"))){
				System.out.println("passe");
					choixGroupe = Integer.parseInt(request.getParameter("groupeId"));
				if(choixGroupe!=0){
					if(choixMatiere!=0){
						absences = AbsencePeer.getAbsencesGroupeForMatiere(choixGroupe, choixMatiere);
					}else{
						absences = AbsencePeer.getAbsencesGroupe(choixGroupe);
					}
				}
			}
			//Transferer les notes
			request.setAttribute("absences",absences);
			
			/*************Remplissage de la moyenne******************/
			
			int nbAbs = 0;
			int nbHeures = 0;
			if(!absences.isEmpty()){
				for(Absence absence : absences){
					nbAbs = nbAbs + 1;
					nbHeures = nbHeures + absence.getNbheures();
				}
			}
			//Transferer la moyenne des notes
			request.setAttribute("nbAbs",nbAbs);
			request.setAttribute("nbHeures",nbHeures);
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
	private  void supprimer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("id");
		int etudiantId=0;
		if (id!=null){
			try {
				Absence abs =AbsencePeer.retrieveByPK(Integer.parseInt(id));
				etudiantId=abs.getEtudiantId();
				AbsencePeer.doDelete(abs);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("/GestionNotesETAbscences/gestion/absences?etudiantId="+etudiantId);

	}
	
	//
	private  void creer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pathView",controleur.getPathCreerAbsences());
		try {
			List<String> attributsForm = attributs;
			attributsForm.remove("id");
			if(controleur.checkForm(attributsForm, request)){
				request.setAttribute("pathView",controleur.getPathEditerAbsences());

				System.out.println("SAUVEGARDE: "+request.getParameter("etudiantId"));

				
				Absence abs =new Absence();
				abs.setNbheures(Integer.parseInt(request.getParameter("nbheures")));
				abs.setEtudiant(EtudiantPeer.retrieveByPK(Integer.parseInt(request.getParameter("etudiantId"))));
				abs.setMatiere(MatierePeer.retrieveByPK(Integer.parseInt(request.getParameter("matiereId"))));

				System.out.println(request.getParameter("date").substring(0, 10));
				System.out.println(request.getParameter("date").substring(11, 16));
				DateFormat formatter ;
				formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm"); 
				Date date;
				date = (Date)formatter.parse(request.getParameter("date"));
			
				abs.setDate(date);
				
				abs.save();

				System.out.println("SAUVEGARDE");
				System.out.println(abs);
				//controleur.getServletContext().getRequestDispatcher("/gestion/absences").forward(request, response);
				response.sendRedirect("/GestionNotesETAbscences/gestion/absences?etudiantId="+Integer.parseInt(request.getParameter("etudiantId")));
				//home(request,response);

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
	private  void modifier(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			if(controleur.checkForm(attributs, request)){
				request.setAttribute("pathView",controleur.getPathEditerAbsences());

				System.out.println("SAUVEGARDE: "+request.getParameter("id"));

				
				Absence abs =AbsencePeer.retrieveByPK(Integer.parseInt(request.getParameter("id")));
				abs.setNbheures(Integer.parseInt(request.getParameter("nbheures")));
				abs.setEtudiant(EtudiantPeer.retrieveByPK(Integer.parseInt(request.getParameter("etudiantId"))));
				abs.setMatiere(MatierePeer.retrieveByPK(Integer.parseInt(request.getParameter("matiereId"))));

				System.out.println(request.getParameter("date").substring(0, 10));
				System.out.println(request.getParameter("date").substring(11, 16));
				DateFormat formatter ;
				formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm"); 
				Date date;
				date = (Date)formatter.parse(request.getParameter("date"));
			
				abs.setDate(date);
				
				abs.save();

				System.out.println("SAUVEGARDE");
				System.out.println(abs);
				response.sendRedirect("/GestionNotesETAbscences/gestion/absences?etudiantId="+Integer.parseInt(request.getParameter("etudiantId")));

			}else{
				System.out.println("Formu");
				request.setAttribute("msgErreur","Des champs sont manquants");
				editer(request,response);

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
			
			Absence abs =AbsencePeer.retrieveByPK(Integer.parseInt(request.getParameter("id")));

			//Transferer les listes à la VUE
			request.setAttribute("etudiants",listEtudiants);
			
			request.setAttribute("pathView",controleur.getPathEditerAbsences());
			request.setAttribute("absence",abs);
			request.setAttribute("titre","Editer une absence de "+abs.getEtudiant().getPrenom()+" "+abs.getEtudiant().getNom());
			request.setAttribute("actionForm","modifier");

			request.setAttribute("etudiants",listEtudiants);
			request.setAttribute("Matieres",listMatieres);
			request.setAttribute("isNew",false);

			System.out.println(abs);
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
			
			request.setAttribute("pathView",controleur.getPathEditerAbsences());
			request.setAttribute("absence",null);
			request.setAttribute("titre","Ajouter une absence");
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
}
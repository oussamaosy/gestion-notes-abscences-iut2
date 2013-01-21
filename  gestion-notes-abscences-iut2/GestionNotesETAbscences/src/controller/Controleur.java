package controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import model.UtilisateurPeer;


@SuppressWarnings("serial")
public class Controleur extends HttpServlet {
	private String pathHome;
	private String pathMain;
	private String pathAbsences;
	private String pathCreerAbsences;
	private String pathSupprimerAbsences;
	private String pathNotes;
	private String pathCreerNotes;
	private String pathSupprimerNotes;
	private String pathEditerNotes;
	private String pathEditerAbsences;
	private String pathConnexion;
	private boolean connexion;
	
	// INIT
	public void init() throws ServletException {
		pathMain = getServletConfig().getInitParameter("pathMain");
		pathHome = getServletConfig().getInitParameter("pathHome");
		pathAbsences = getServletConfig().getInitParameter("pathAbsences");
		pathSupprimerAbsences = getServletConfig().getInitParameter("pathSupprimerAbsences");
		pathCreerAbsences = getServletConfig().getInitParameter("pathCreerAbsences");
		pathNotes = getServletConfig().getInitParameter("pathNotes");
		pathSupprimerNotes = getServletConfig().getInitParameter("pathSupprimerNotes");
		pathCreerNotes = getServletConfig().getInitParameter("pathCreerNotes");
		pathEditerNotes= getServletConfig().getInitParameter("pathEditerNotes");
		pathEditerAbsences= getServletConfig().getInitParameter("pathEditerAbsences");
		pathConnexion= getServletConfig().getInitParameter("pathConnexion");
	}
	
	public String getPathEditerNotes() {
		return pathEditerNotes;
	}

	public String getPathEditerAbsences() {
		return pathEditerAbsences;
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
		String actionPath= request.getPathInfo().substring(1);
		String controleurName = actionPath;
		System.out.println("Controleur: "+controleurName);

		if(controleurName.indexOf("/")>0){
			controleurName = controleurName.substring(0, controleurName.indexOf("/"));
		}
			System.out.println("indexOf: "+controleurName.indexOf("/"));
			
			//MENU :
			Hashtable<String,String> menu = new Hashtable<String,String>();
			menu.put("Accueil", "/GestionNotesETAbscences/gestion/home");
			menu.put("Notes", "/GestionNotesETAbscences/gestion/notes");
			menu.put("Absences", "/GestionNotesETAbscences/gestion/absences");
			request.setAttribute("menu",menu);

			System.out.println("Controleur: "+controleurName);
			//Répartition dans les controleurs

			if(controleurName.equals("absences")){
				request.setAttribute("rubrique","Absences");
				Absences controleurabsences = new Absences(this);
				controleurabsences.traiterActions(request,response,actionPath,methode);
				
			}else if(controleurName.equals("notes")){
				request.setAttribute("rubrique","Notes");
				Notes controleurNotes = new Notes(this);
				controleurNotes.traiterActions(request,response,actionPath,methode);

			}
			// Exécution action
			else if (controleurName == null) {
				controleurName = "home";
			}else {
				// Autres cas
				request.setAttribute("rubrique","Accueil");
				doHome(request, response);
			}
	}

	public String getPathMain() {
		return pathMain;
	}



	public String getPathAbsences() {
		return pathAbsences;
	}



	public String getPathCreerAbsences() {
		return pathCreerAbsences;
	}


	public String getPathSupprimerAbsences() {
		return pathSupprimerAbsences;
	}


	public String getPathNotes() {
		return pathNotes;
	}



	public String getPathCreerNotes() {
		return pathCreerNotes;
	}


	public String getPathSupprimerNotes() {
		return pathSupprimerNotes;
	}

	public String getPathConnexion() {
		return pathConnexion;
	}


	private void doHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			request.setAttribute("pathView",pathHome);
			loadJSP(pathMain, request, response);
	}

	
	private void doConnexion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
			loadJSP(pathConnexion, request, response);
			request.setAttribute("pathView",pathConnexion);
			connexion = authentification(request);
			if(connexion){
				HttpSession session = request.getSession(connexion);
			}
	}
	

	private boolean authentification(HttpServletRequest request) throws Exception {
		String login = request.getAttribute("login").toString();
		String password = request.getAttribute("password").toString();
		return UtilisateurPeer.verifConnexion(login, password);
	}
	

	public void loadJSP(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	public boolean checkForm(List<String> attributs,HttpServletRequest request){
		boolean ok=true;
		for (String  attrName: attributs){

			if (request.getParameter(attrName).isEmpty()){
				ok =false;
			}
		}
		return ok;
	}

	public void reinitialiserForm(List<String> attributs,
			HttpServletRequest request) {
		for (String  attrName: attributs){
			request.setAttribute(attrName,request.getParameter(attrName));
		}
		// TODO Auto-generated method stub
		
	}


}

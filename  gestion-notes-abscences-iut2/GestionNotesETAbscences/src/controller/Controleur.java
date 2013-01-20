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
	private String pathAbsences;
	private String pathCreerAbsences;
	private String pathSupprimerAbsences;
	private String pathNotes;
	private String pathCreerNotes;
	private String pathSupprimerNotes;
	private String pathEditerNotes;
	private String pathEditerAbsences;

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
		pathEditerNotes= getServletConfig().getInitParameter("pathEditerNotes");;
		pathEditerAbsences= getServletConfig().getInitParameter("pathEditerAbsences");;
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


			System.out.println("Controleur: "+controleurName);
			//Répartition dans les controleurs

			if(controleurName.equals("absences")){
				
				Absences controleurabsences = new Absences(this);
				controleurabsences.traiterActions(request,response,actionPath,methode);
				
			}else if(controleurName.equals("notes")){
				
				Notes controleurNotes = new Notes(this);
				controleurNotes.traiterActions(request,response,actionPath,methode);

			}
			// Exécution action
			else if (controleurName == null) {
				controleurName = "home";
			}
			else if(methode.equals("get") && controleurName.equals("home")) {
				doHome(request, response);
	
			
				
			} else {
				// Autres cas
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



	private void doHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			request.setAttribute("pathView",pathHome);
			loadJSP(pathMain, request, response);
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

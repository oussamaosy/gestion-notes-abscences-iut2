package controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Etudiant;
import model.GestionFactory;


@SuppressWarnings("serial")
public class Controleur extends HttpServlet {
	private String pathHome;
	private String pathMain;
	private String pathEditerAbsences;
	private String pathEditerNotes;
	// INIT
	public void init() throws ServletException {
		pathMain = getServletConfig().getInitParameter("pathMain");
		pathHome = getServletConfig().getInitParameter("pathHome");
		pathEditerAbsences = getServletConfig().getInitParameter("pathEditerAbsences");
		pathEditerNotes = getServletConfig().getInitParameter("pathEditerNotes");
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
			action = "/jeu";
			System.out.println("action == null");
		}
		System.out.println(action);
		
		// Exécution action
		if (methode.equals("get") && action.equals("/home")) {
			doHome(request, response);

		} else if (methode.equals("get") && action.equals("/editerAbsences")) {
			doEditerAbsences(request, response);
		
		} else if (methode.equals("get") && action.equals("/editerNotes")) {
			doEditerNotes(request, response);
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
		System.out.println("pathEditerAbsences: "+pathEditerAbsences);
		
		request.setAttribute("pathView",pathEditerAbsences);
		loadJSP(pathMain, request, response);
	}
	
	//
	private void doEditerNotes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Mettre l'objet jeu en attribut de requête
		//request.setAttribute("jeu", jeu);
		
		request.setAttribute("pathView",pathEditerNotes);
		List<Etudiant> listEtudiants = GestionFactory.getEtudiants();
		System.out.println(listEtudiants);
		Etudiant etu = new Etudiant(1, "prenom", "nom", "groupe");
		request.setAttribute("etudiant",etu);
		request.setAttribute("etudiants",listEtudiants);

		System.out.println(etu.getNom());
		loadJSP(pathMain, request, response);
		
	}


	public void loadJSP(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
	}


}

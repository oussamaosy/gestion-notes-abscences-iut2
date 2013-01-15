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
import model.EtudiantPeer;
import model.GestionFactory;
import model.Groupe;
import model.GroupePeer;
import model.Matiere;
import model.MatierePeer;


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
			action = "/home";
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
		// Mettre l'objet jeu en attribut de requête
		//request.setAttribute("jeu", jeu);
		System.out.println("pathEditerAbsences: "+pathEditerAbsences);
		request.setAttribute("pathView",pathEditerAbsences);
		request.setAttribute("etudiants",GestionFactory.getEtudiants());
		request.setAttribute("groupes",GestionFactory.getGroupes());
		request.setAttribute("matieres",GestionFactory.getMatieres());
		loadJSP(pathMain, request, response);
	}
	
	//
	private void doEditerNotes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Mettre l'objet jeu en attribut de requête
		//request.setAttribute("jeu", jeu);
		
		request.setAttribute("pathView",pathEditerNotes);
		List<Etudiant> listEtudiants;
		List<Groupe> listGroupes;
		List<Matiere> listMatiere;
		try {
			//Accès aux données
			listEtudiants = EtudiantPeer.doSelectAll();
			listGroupes = GroupePeer.doSelectAll();
			listMatiere = MatierePeer.doSelectAll();
			
			Etudiant etu = new Etudiant();
			etu.setNom("nom");
			etu.setPrenom("prenom");
			
			//Transferer un seul etudiant : exemple BEEN
			request.setAttribute("etudiant",etu);
			
			//Transferer les listes à la VUE
			request.setAttribute("etudiants",listEtudiants);
			request.setAttribute("groupes",listGroupes);
			request.setAttribute("matieres",listMatiere);


			System.out.println(etu.getNom());
			loadJSP(pathMain, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		
	}


	public void loadJSP(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
	}


}

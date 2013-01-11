package controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Notes extends Controleur{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String pathEditerNotes;
	
	public void init() throws ServletException {
		super.init();
		pathEditerNotes = getServletConfig().getInitParameter("pathEditerNotes");
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		super.doGet(request, response);
		
		if (methode.equals("get") && action.equals("/editer")) {
			doEditer(request, response);
		} 
	}	
	//
	private void doEditer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Mettre l'objet jeu en attribut de requête
		//request.setAttribute("jeu", jeu);		
		request.setAttribute("pathView",pathEditerNotes);
		request.setAttribute("etudiants",null);
		request.setAttribute("groupes",null);
		request.setAttribute("matieres",null);
		loadJSP(pathMain, request, response);
	}
	

}

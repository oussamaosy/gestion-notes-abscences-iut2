package controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.torque.NoRowsException;
import org.apache.torque.TooManyRowsException;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;

import model.Etudiant;
import model.EtudiantPeer;
import model.GestionFactory;
import model.Groupe;
import model.GroupePeer;

public class Absences extends Controleur{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String pathEditerAbsences;
	
	public void init() throws ServletException {
		super.init();
		pathEditerAbsences = getServletConfig().getInitParameter("pathEditerAbsences");
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		super.doGet(request, response);
		
		if (methode.equals("get") && action.equals("/editer")) {
					doEditer(request, response);
			
		} 
	}	
	private void doEditer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		System.out.println("PASSE :"+ pathEditerAbsences);

		// Mettre l'objet jeu en attribut de requête
		//request.setAttribute("jeu", jeu);
				
			List<Etudiant> listEtudiants;
			try {
				listEtudiants = EtudiantPeer.doSelectAll();
				List<Groupe> listGroupes= GroupePeer.doSelectAll();
				request.setAttribute("etudiants",listEtudiants);
				request.setAttribute("groupes",listGroupes);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
		
		




		request.setAttribute("pathView",pathEditerAbsences);
		
		System.out.println(pathEditerAbsences);

		loadJSP(pathMain, request, response);
		
	}
	

}

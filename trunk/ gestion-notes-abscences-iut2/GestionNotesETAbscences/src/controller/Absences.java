package controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			try {
				doEditer(request, response);
			} catch (TorqueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} 
	}	
	private void doEditer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, TorqueException {
		System.out.println("PASSE :"+ pathEditerAbsences);

		// Mettre l'objet jeu en attribut de requête
		//request.setAttribute("jeu", jeu);
		Criteria crit = new Criteria();
		Etudiant etu= EtudiantPeer.retrieveByPK(1);
System.out.println(etu);
		List<Etudiant> listEtudiants = EtudiantPeer.doSelect(crit);
		System.out.println(listEtudiants);
		List<Groupe> listGroupes= GroupePeer.doSelect(crit);


		etu.setNom("nom");
		etu.setPrenom("prenom");

		request.setAttribute("pathView",pathEditerAbsences);
		request.setAttribute("etudiant",etu);
		request.setAttribute("etudiants",listEtudiants);
		request.setAttribute("groupes",listGroupes);
		System.out.println(pathEditerAbsences);

		loadJSP(pathMain, request, response);
		
	}
	

}

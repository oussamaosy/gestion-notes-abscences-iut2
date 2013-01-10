package controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.torque.Torque;
import org.apache.torque.TorqueException;




@SuppressWarnings("serial")
public class Controleur extends HttpServlet {
	protected String pathMain;
	protected String pathEditerAbsences;
	protected String methode;
	protected String action;
	private static final String TORQUE_PROPS = new String("Torque.properties");

	// INIT
	public void init() throws ServletException {
		pathMain = getServletConfig().getInitParameter("pathMain");
		pathEditerAbsences = getServletConfig().getInitParameter("pathEditerAbsences");
		// Initialisation de la connection Torque
		System.out.println("passe");		
		
	
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
		methode = request.getMethod().toLowerCase();
		
		// On récupère l'action à exécuter
		action = request.getPathInfo();
		if (action == null) {
			action = "/jeu";
			System.out.println("action == null");
		}
		System.out.println(action);
		
		// Exécution action
		/*if (methode.equals("get") && action.equals("/home")) {
			doHome(request, response);

		} else if (methode.equals("get") && action.equals("/editerAbsences")) {
			doEditerAbsences(request, response);
		
		} else if (methode.equals("get") && action.equals("/editerNotes")) {
			doEditerNotes(request, response);
		} else {
			// Autres cas
			doHome(request, response);
		}*/
	}



	
	



	public void loadJSP(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
	}


}

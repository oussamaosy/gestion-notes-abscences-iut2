package controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Home extends Controleur{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String pathHome;
	
	public void init() throws ServletException {
		super.init();
		pathHome = getServletConfig().getInitParameter("pathHome");
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		super.doGet(request, response);
		
			doHome(request, response);
	}	
	//
	private void doHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			request.setAttribute("pathView",pathHome);
			System.out.println(pathHome);
			loadJSP(pathMain, request, response);
	}
	

}

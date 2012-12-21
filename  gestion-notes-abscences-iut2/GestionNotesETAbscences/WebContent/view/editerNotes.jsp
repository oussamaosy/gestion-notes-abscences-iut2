<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.List" %>
<%@ page import= "model.Etudiant" %>

<form action="" method="post">

 <jsp:useBean id="etudiant" class="model.Etudiant" scope="request"/>
 <% List<Etudiant> listEtudiant = (List<Etudiant>)request.getAttribute("etudiants"); 
	System.out.println("ETUDIANT "+listEtudiant);

 for(Etudiant etu : listEtudiant){%>
	 <%=etu+"<br/>" %>
 <%} %>
 
 Etudiant :<jsp:getProperty  name="etudiant" property="nom" />
 
 <br/>
NOTES <input type="text" />
</form>

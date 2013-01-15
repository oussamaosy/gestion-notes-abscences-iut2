<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= getServletContext().getInitParameter("title")%></title>
<%--  on récupère les paramètres d'initialisation de la servlet --%>
</head>

<body>
	<!-- Entete -->
	<h1>Application de suivi des notes et absences</h1>
	
	<!-- Menu -->
	<ul id="menu">
	<li>
	<a href="home">Home</a>
	</li>
	<li>
	<a href="editerAbsences">Absences</a>
	</li>
	<li><a href="editerNotes">Notes</a></li>
	</ul>
	
	<!-- Contenu -->
	<% String pathView = (String)request.getAttribute("pathView"); %>
	<jsp:include page="<%=pathView%>"/>
	
	<!-- Pied de page -->
	<p>Réalisé par Moez Askri et Colin Fournier,2012-2013, IUT2 de Grenoble</p>
</body>
</html>
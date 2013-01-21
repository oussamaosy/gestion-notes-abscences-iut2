<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.Hashtable"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= getServletContext().getInitParameter("title")%></title>
<%--  on récupère les paramètres d'initialisation de la servlet --%>
  <link rel="stylesheet" media="all" type="text/css" href="http://code.jquery.com/ui/1.10.0/themes/smoothness/jquery-ui.css">
 
 <link rel="stylesheet" href="/GestionNotesETAbscences/ressources/css/jquery-ui-timepicker-addon.css" type="text/css">
 <link rel="stylesheet" href="/GestionNotesETAbscences/ressources/css/main.css" type="text/css">
 <script type="text/javascript" src="/GestionNotesETAbscences/ressources/js/jquery-1.8.0.min.js"></script>
 <script type="text/javascript" src="/GestionNotesETAbscences/ressources/js/jquery-ui1.8.min.js"></script>
 <script  type="text/JavaScript" src="/GestionNotesETAbscences/ressources/js/jquery-ui-timepicker-addon.js"></script>
  <script  type="text/JavaScript" src="/GestionNotesETAbscences/ressources/js/jquery-ui-sliderAccess.js"></script>
 
</head>

<body>
	<!-- Entete -->
	<h1>Gestion des notes et absences</h1>
	<a class="deconnexion" href="/GestionNotesETAbscences/gestion/deconnexion">Déconnexion</a>
	
	<!-- Menu -->
	<ul id="menu">
	<% Hashtable<String,String> menu = (Hashtable<String,String>)request.getAttribute("menu");
	for(String key : menu.keySet()){%>
	<li>
	<a <%=request.getAttribute("rubrique").equals(key)?"class='selected'":""%> href="<%=menu.get(key)%>"><%=key %></a>
	</li>
	<% }%>
	</ul>
	
	<!-- Contenu -->
	<% String pathView = (String)request.getAttribute("pathView"); %>
	<div id="content">
	
	<jsp:include page="<%=pathView%>"/>
	</div>
	<!-- Pied de page -->
	<p>Réalisé par Moez Askri et Colin Fournier,2012-2013, IUT2 de Grenoble</p>
</body>
</html>
 <script  type="text/JavaScript">
 $(document).ready(function() {
	 $('#date').datetimepicker({
		 timeFormat: "hh:mm",
		 dateFormat: "yy-mm-dd"
	 });
	});
 
 </script>

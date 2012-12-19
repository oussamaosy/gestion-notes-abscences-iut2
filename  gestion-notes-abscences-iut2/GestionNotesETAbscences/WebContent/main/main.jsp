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
<% String pathView = (String)request.getAttribute("pathView"); %>
<jsp:include page="<%=pathView%>"/>
</body>
</html>
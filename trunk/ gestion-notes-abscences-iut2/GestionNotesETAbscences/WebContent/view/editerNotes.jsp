<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%--<%@ page import="model.Etudiant"%>
<%@ page import="model.Groupe"%>
<%@ page import="model.Matiere"%>--%>

<jsp:useBean id="noteEtu" class="model.Note" scope="request" /> 
<% 
List<model.Etudiant> listEtudiant = (List<model.Etudiant>) request.getAttribute("etudiants");
List<model.Matiere> listMatiere = (List<model.Matiere>) request.getAttribute("Matieres");
Boolean isNew= (Boolean)request.getAttribute("isNew");
String msgErreur="";
String note="0";
String etuId ="0";
String matId ="0";
if(!isNew){
	etuId=String.valueOf(noteEtu.getEtudiantId());
	matId=String.valueOf(noteEtu.getMatiereId());
	note=String.valueOf(noteEtu.getNote());
}

%>

<h2> <%= request.getAttribute("titre") %> </h2>
<% if(request.getAttribute("msgErreur")!=null){
	note =(String)request.getAttribute("note");
	etuId=(String)request.getAttribute("etudiantId");
	matId=(String)request.getAttribute("matiereId");
	msgErreur=(String)request.getAttribute("msgErreur");
	%>
	<strong class="msgErreur"><%=msgErreur %></strong>
	<%

} %>
<form method="post" action="<%=request.getAttribute("actionForm")%>">
	<fieldset>
		<legend> Ajouter une note </legend>
		<label for="etudiantId"> Sélectioner un étudiant: </label> <select
			name="etudiantId" id="etudiant">
			<option value=""></option>
			<%
				for (model.Etudiant etu : listEtudiant) { %>
					<option value="<%= etu.getId() %>" <%=String.valueOf(etu.getId()).equals(etuId)?"selected='selected'":""%>"><%=etu.getNom() + " " + etu.getPrenom()%></option>
			<%}%>
		</select><br> 
		<label for="note"> Note sur 20: </label>
		<input type="text" id="note" name="note" value="<%= noteEtu.getNote() %>">
		<br> <label for="matiereId">Filtrer par matière :</label> 
		<select
			name="matiereId" id="matiere">
			<option value=""></option>
			<% 
				for (model.Matiere mat : listMatiere) {%>
					<option value="<%= mat.getId() %>" <%=String.valueOf(mat.getId()).equals(matId)?"selected='selected'":""%>"><%=mat.getIntitule()%></option>
			<%}%>
		</select><br> <br> <input type="submit" value="Enregistrer">
	</fieldset>
	
	<input type="hidden" name="id" value='<jsp:getProperty name="noteEtu" property="id" />' />
</form>
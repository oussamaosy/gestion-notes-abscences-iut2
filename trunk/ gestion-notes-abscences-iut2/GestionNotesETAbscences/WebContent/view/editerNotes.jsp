<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%--<%@ page import="model.Etudiant"%>
<%@ page import="model.Groupe"%>
<%@ page import="model.Matiere"%>

<jsp:useBean id="etudiant" class="model.Etudiant" scope="request" /> --%>
			

<p>BASE DE DONNEE </p>
<form>
	<fieldset>
		<legend> Voir les notes </legend>
		<input type="radio" name="choix" value="etudiant" checked>Etudiant:
		<select name="etudiant" id="etudiant">
			<option value="">Aucune</option>

			<%
				List<model.Etudiant> listEtudiant = (List<model.Etudiant>) request.getAttribute("etudiants");
				for (model.Etudiant etu : listEtudiant) {%>
			<option value="<%= etu.getId() %>"><%=etu.getNom() + " " + etu.getPrenom()%></option>
			<%
				}
			%>			
		</select><br> <input type="radio" name="choix" value="groupe">Groupe:
		<select name="groupe" id="groupe">
			<option value="">Aucune</option>
			<%
				List<model.Groupe> listGroupes = (List<model.Groupe>) request.getAttribute("groupes");
			
				for (model.Groupe groupe : listGroupes) {
			%>
			<option value="<%= groupe.getId() %>"> <%= groupe.getIntitule() %></option>
			<%
				}
			%>		
		</select><br> <label for="matiere">Filtrer par matière :</label> <select
			name="matiere" id="matiere">
			<option value="">Toutes</option>
			<%
				List<model.Matiere> listMatiere = (List<model.Matiere>) request.getAttribute("matieres");
				for (model.Matiere matiere : listMatiere) {%>
			<option value="<%=matiere.getId()%>"><%=matiere.getIntitule()%></option>
			<%
				}
			%>
		</select><br> <br> <input type="submit" value="Filtrer">
	</fieldset>
</form>

<label for="avNotes">Moyenne des notes: </label>
<input type="text" readonly id="avNotes" name="avNotes" />
<br>

<table>
	<tr>
		<th>Etudiant</th>
		<th>Matiere</th>
		<th>Note</th>
		<th></th>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
</table>
<br>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Etudiant"%>
<%@ page import="model.Groupe"%>
<%@ page import="model.Matiere"%>
<jsp:useBean id="etudiant" class="model.Etudiant" scope="request" />

<form>
	<fieldset>
		<legend> Voir les absences </legend>
		<input type="radio" name="choix" value="etudiant" checked>Etudiant:
		<select name="etudiant" id="etudiant">
			<option value="">Aucune</option>
			<%
				List<Etudiant> listEtudiant = (List<Etudiant>) request.getAttribute("etudiants");
				for (Etudiant etu : listEtudiant) {
			%>
			<option value="<%=etu.getId()%>"><%=etu.getNom() + " " + etu.getPrenom()%></option>
			<%
				}
			%>
		</select><br> 
		<input type="radio" name="choix" value="groupe">Groupe:
		<select name="groupe" id="groupe">
			<option value="">Aucune</option>
			<%
				List<Groupe> listGroupe = (List<Groupe>)request.getAttribute("groupes");
				for (Groupe groupe : listGroupe) {
			%>
			<option value="<%=groupe.getId()%>"><%=groupe.getNom()%></option>
			<%
				}
			%>
		</select><br> 
		<label for="matiere">Filtrer par matière :</label> <select
			name="matiere" id="matiere">
			<option value="">Toutes</option>
			<%
				List<Matiere> listMatiere = (List<Matiere>)request.getAttribute("matieres");
				for (Matiere matiere : listMatiere) {
			%>
			<option value="<%=matiere.getId()%>"><%=matiere.getNom()%></option>
			<%
				}
			%>
		</select><br>
		<br> <input type="submit" value="Filtrer">
	</fieldset>
</form>

<label for="nbAbsence">Nombre total d'absences: </label>
<input type="text" readonly id="nbAbs" name="nbAbs" />
<br>

<table>
	<tr>
		<th>Etudiant</th>
		<th>Matiere</th>
		<th>NbHeure</th>
		<th></th>
	</tr>


	<%
		for (Etudiant etu : listEtudiant) {
	%>
	<tr>
		<td><%=etu.getNom() + etu.getPrenom()%></td>
		<td><%=etu%></td>
		<td><%=etu%></td>
		<td><%=etu%></td>
	</tr>
	<%
		}
	%>
</table>
<br>

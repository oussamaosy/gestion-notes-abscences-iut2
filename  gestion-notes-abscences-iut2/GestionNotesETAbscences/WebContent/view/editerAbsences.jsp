<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%--<%@ page import="model.Etudiant"%>
<%@ page import="model.Groupe"%>
<%@ page import="model.Matiere"%>
<jsp:useBean id="etudiant" class="model.Etudiant" scope="request" /> --%>

<form>
	<fieldset>
		<legend> Ajouter une absence </legend>
		<label for="etudiant"> Sélectioner un étudiant: </label> <select
			name="etudiant" id="etudiant">
			<option value="">Aucune</option>
			<%--<%
				List<Etudiant> listEtudiant = (List<Etudiant>) request
						.getAttribute("etudiants");
				for (Etudiant etu : listEtudiant) {
			<option value="<%=etu.getId()%>"><%=etu.getNom() + " " + etu.getPrenom()%></option>
			<%
				}
			%> --%>
		</select><br> <label for="nbheures"> Nombre d'heures: </label> <select
			name="nbheures" id="nbheures">
			<%
				for (int i = 1; i < 7; i++) {
			%>
			<option value="<%=i%>"><%=i + " heure(s)"%>
			</option>
			<%
				}
			%>
		</select><br> <label for="matiere">Filtrer par matière :</label> <select
			name="matiere" id="matiere">
			<option value="">Toutes</option>
			<%-- <%
				List<Matiere> listMatiere = (List<Matiere>) request
						.getAttribute("matieres");
				for (Matiere matiere : listMatiere) {
			<option value="<%=matiere.getId()%>"><%=matiere.getIntitule()%></option>
			<%
				}
			%>			--%>
			
		</select><br> <br> <input type="submit" value="Enregistrer">
	</fieldset>
</form>
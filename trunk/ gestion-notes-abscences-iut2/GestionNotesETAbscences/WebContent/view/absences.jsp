<%@page import="model.EtudiantPeer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%--<%@ page import="model.Etudiant"%>
<%@ page import="model.Groupe"%>
<%@ page import="model.Matiere"%>

<jsp:useBean id="etudiant" class="model.Etudiant" scope="request" /> --%>
			
<form method="post" action="absences">
	<fieldset>
		<legend> Voir les absences </legend>
		<input type="radio" name="choix" value="etudiant" checked>
		<label for="etudiant">Etudiant:</label>
		<select name="etudiant" id="etudiant">
			<option value="0">Aucune</option>

			<%
				List<model.Etudiant> listEtudiant = (List<model.Etudiant>) request.getAttribute("etudiants");
				for (model.Etudiant etu : listEtudiant) {%>
			<option value="<%= etu.getId() %>"><%=etu.getNom() + " " + etu.getPrenom()%></option>
			<%
				}
			%>			
		</select><br> <input type="radio" name="choix" value="groupe">
		<label for="groupe">Groupe:</label>
		<select name="groupe" id="groupe">
			<option value="0">Aucune</option>
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
			<option value="0">Toutes</option>
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

<label for="nbAbs">Nombre d'absences: </label>
<input type="text" readonly id="nbAbs" name="nbAbs" value="<%=request.getAttribute("nbAbs")%>"/>
<br>
<label for="nbHeures">Nombre d'heures: </label>
<input type="text" readonly id="nbHeures" name="nbHeures" value="<%=request.getAttribute("nbHeures")%>"/>
<br>

<%
		List<model.Absence> listAbsence = (List<model.Absence>) request.getAttribute("absences");
		if(!listAbsence.isEmpty()){
		
%>

<table>
	<tr>
		<th>Etudiant</th>
		<th>Matiere</th>
		<th>NbHeure</th>
		<th></th>
	</tr>
	<%
				for (model.Absence absence : listAbsence) {
					model.Etudiant etudiant = absence.getEtudiant();
					String nomEtu = etudiant.getPrenom()+" "+etudiant.getNom();
					model.Matiere matiere = absence.getMatiere();
					String nomMat = matiere.getIntitule();
					double nbAbs = (int)absence.getNbheures();
	%>
	<tr>
		<td><%=nomEtu%></td>
		<td><%=nomMat%></td>
		<td><%=nbAbs%></td>
		<td>
			<a href="/GestionNotesETAbscences/gestion/absences/editer?id=<%= absence.getId()%>">
				<img title="Modifier l'absence" alt="Modifier l'absence" 
					src="<%= getServletContext().getContextPath()%>/ressources/modifier.png"/>
			</a>
			<a href="/GestionNotesETAbscences/gestion/absences/supprimer?id=<%= absence.getId()%>">
				<img title="Supprimer l'absence" alt="Supprimer l'absence" 
					src="<%= getServletContext().getContextPath()%>/ressources/supprimer.png"/>
			</a>
		</td>
	</tr>
	
	<%
				}
			%>
	<tr>
		<td>
				<a href="/GestionNotesETAbscences/gestion/absences/ajouter">
					<img title="Ajouter une absence" alt="Ajouter une absence" 
						src="<%= getServletContext().getContextPath()%>/ressources/add.png"/> 
						Ajouter une note
				</a>
		</td>
	</tr>
</table>
<br>
<%
	}else{
%>
	<span>Aucune absence n'est disponible pour ce filtre.</span>
<%
	}
%>

<%@page import="model.EtudiantPeer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%--<%@ page import="model.Etudiant"%>
<%@ page import="model.Groupe"%>
<%@ page import="model.Matiere"%>

<jsp:useBean id="etudiant" class="model.Etudiant" scope="request" /> --%>
			
<form method="post" action="editerNotes">
	<fieldset>
		<legend> Voir les notes </legend>
		<input type="radio" name="choix" value="etudiant" checked>Etudiant:
		<select name="etudiant" id="etudiant">
			<option value="0">Aucune</option>

			<%
				List<model.Etudiant> listEtudiant = (List<model.Etudiant>) request.getAttribute("etudiants");
				for (model.Etudiant etu : listEtudiant) {%>
			<option value="<%= etu.getId() %>"><%=etu.getNom() + " " + etu.getPrenom()%></option>
			<%
				}
			%>			
		</select><br> <input type="radio" name="choix" value="groupe">Groupe:
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

<label for="avNotes">Moyenne des notes: </label>
<input type="text" readonly id="avgNotes" name="avgNotes" value="<%=request.getAttribute("avgNote")%>"/>
<br>

<%
		List<model.Note> listNote = (List<model.Note>) request.getAttribute("notes");
		if(!listNote.isEmpty()){
		
%>

<table>
	<tr>
		<th>Etudiant</th>
		<th>Matiere</th>
		<th>Note</th>
		<th></th>
	</tr>
	<%
				for (model.Note note : listNote) {
					model.Etudiant etudiant = note.getEtudiant();
					String nomEtu = etudiant.getPrenom()+" "+etudiant.getNom();
					model.Matiere matiere = note.getMatiere();
					String nomMat = matiere.getIntitule();
					double noteEtu = note.getNote();
	%>
	<tr>
		<td><%=nomEtu%></td>
		<td><%=nomMat%></td>
		<td><%=noteEtu%></td>
		<td>
			<a href="creerNotes">
				<img title="Modifier la note" alt="Modifier la note" 
					src="<%= getServletContext().getContextPath()%>/ressources/modifier.png"/>
			</a>
			<a href="supprimerNotes">
				<img title="Supprimer la note" alt="Supprimer la note" 
					src="<%= getServletContext().getContextPath()%>/ressources/supprimer.png"/>
			</a>
		</td>
	</tr>
	<%
				}
			%>
</table>
<br>
<%
	}else{
%>
	<span>Aucune note n'est disponible pour ce filtre.</span>
<%
	}
%>

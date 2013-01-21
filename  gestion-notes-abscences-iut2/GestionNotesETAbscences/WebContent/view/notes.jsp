<%@page import="model.EtudiantPeer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%--<%@ page import="model.Etudiant"%>
<%@ page import="model.Groupe"%>
<%@ page import="model.Matiere"%>

<jsp:useBean id="etudiant" class="model.Etudiant" scope="request" /> --%>
			
<form method="post" action="/GestionNotesETAbscences/gestion/notes">
	<fieldset>
		<legend> Voir les notes </legend>
		<label for="etudiant">Etudiant : </label>
		<select name="etudiantId" id="etudiant">
			<option value="0"></option>

			<%
				List<model.Etudiant> listEtudiant = (List<model.Etudiant>) request.getAttribute("etudiants");
				int etudiantId = 0;
				if(request.getAttribute("etudiantId")!=null){
					Object obj = request.getAttribute("etudiantId");
					etudiantId = ((Integer)obj).intValue();
				}
				for (model.Etudiant etu : listEtudiant) {
					if(etu.getId()==etudiantId){
			%>
						<option value="<%= etu.getId() %>" selected><%=etu.getNom() + " " + etu.getPrenom()%></option>	
					<%}else{ %>
						<option value="<%= etu.getId() %>"><%=etu.getNom() + " " + etu.getPrenom()%></option>
			<%
					}
				}
			%>			
		</select><br> 
		<label for="groupe">Groupe : </label>
		<select name="groupeId" id="groupe">
			<option value="0"></option>
			<%
				List<model.Groupe> listGroupes = (List<model.Groupe>) request.getAttribute("groupes");
				int groupeId = 0;
				if(request.getAttribute("groupeId")!=null){
					Object obj = request.getAttribute("groupeId");
					groupeId = ((Integer)obj).intValue();
				}
				for (model.Groupe groupe : listGroupes) {
					if(groupe.getId()==groupeId){
			%>
						<option value="<%= groupe.getId() %>" selected> <%= groupe.getIntitule() %></option>
					<%}else{ %>
						<option value="<%= groupe.getId() %>"> <%= groupe.getIntitule() %></option>
			<%
					}
				}
			%>		
		</select><br> <label for="matiere">Filtrer par matière :</label> <select
			name="matiereId" id="matiere">
			<option value="0">Toutes</option>
			<%
				List<model.Matiere> listMatiere = (List<model.Matiere>) request.getAttribute("matieres");
				int matiereId = 0;
				if(request.getAttribute("matiereId")!=null){
					Object obj = request.getAttribute("matiereId");
					matiereId = ((Integer)obj).intValue();
				}
				for (model.Matiere matiere : listMatiere) {
					if(matiere.getId()==matiereId){
				%>
						<option value="<%=matiere.getId()%>" selected><%=matiere.getIntitule()%></option>
					<%}else{%>
						<option value="<%=matiere.getId()%>"><%=matiere.getIntitule()%></option>
			<%
					}
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
	<tr class="tableEntete">
		<th>Etudiant</th>
		<th>Matiere</th>
		<th>Note</th>
		<th>Groupe</th>
		
		<td></td>
	</tr>
	<%
				for (model.Note note : listNote) {
					model.Etudiant etudiant = note.getEtudiant();
					String nomEtu = etudiant.getPrenom()+" "+etudiant.getNom();
					model.Matiere matiere = note.getMatiere();
					String nomMat = matiere.getIntitule();
					float noteEtu = (float)note.getNote();
	%>
	<tr>
		<th><%=nomEtu%></th>
		<td><%=nomMat%></td>
		<td><%=noteEtu%></td>
		<td><%=note.getEtudiant().getGroupe().getIntitule()%></td>
		
		<td>
			<a href="/GestionNotesETAbscences/gestion/notes/editer?id=<%= note.getId()%>">
				<img title="Modifier la note" alt="Modifier la note" 
					src="<%= getServletContext().getContextPath()%>/ressources/modifier.png"/>
			</a>
			<a href="/GestionNotesETAbscences/gestion/notes/supprimer?id=<%= note.getId()%>">
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
	<br/>
	
<%
	}
	
%>
	<a href="/GestionNotesETAbscences/gestion/notes/ajouter">
		<img title="Ajouter une note" alt="Ajouter une note" 
			src="<%= getServletContext().getContextPath()%>/ressources/add.png"/> 
		Ajouter une note
	</a>
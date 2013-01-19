<%@ page import="model.Matiere"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%--<%@ page import="model.Etudiant"%>
<%@ page import="model.Groupe"%>--%>
<%@ page import="model.Absence"%>

<jsp:useBean id="absence" class="model.Absence" scope="request" />

<h2> <%= request.getAttribute("titre") %> </h2>
<form>
	<table>
		<tbody>
		<tr>
			<th><label for="nbheures" >Durée (heures) : </label></th>
			<td><input id="nbheures" size="1" name="nbheures" type="text" value='<jsp:getProperty name="absence" property="nbheures" />' /></td>
		</tr>
		<tr>
			<th><label for="etudiantId" >Etudiant :</label></th>
			
			<td>
				<select name="etudiantId" id="etudiant">
				<option value="0">Aucune</option>
					<% List<model.Etudiant> listEtudiant = (List<model.Etudiant>) request.getAttribute("etudiants");
					int etuId =((model.Absence)request.getAttribute("absence")).getEtudiantId();
					for (model.Etudiant etu : listEtudiant) {%>
						<option value="<%= etu.getId() %>" <%=etu.getId()==etuId?"selected='selected'":""%>"><%=etu.getNom() + " " + etu.getPrenom()%></option>
					<%}%>			
				</select>
			</td>
		</tr>
		<tr>
			<th><label for="etudiantId" >Matiere :</label></th>
			
			<td>
				<select name="MatiereId" id="etudiant">
				<option value="0">Aucune</option>
					<% List<model.Matiere> listMatiere = (List<model.Matiere>) request.getAttribute("Matieres");
					int matId =((model.Absence)request.getAttribute("absence")).getMatiereId();
					for (model.Matiere mat : listMatiere) {%>
						<option value="<%= mat.getId() %>" <%=mat.getId()==matId?"selected='selected'":""%>"><%=mat.getIntitule()%></option>
					<%}%>			
				</select>
			</td>
		</tr>
		<tr>
			<th><label for="date" >Date (AAAA-MM-JJ HH:mm:ss) : </label></th>
			<td><input id="date" name="date" type="text" value='<jsp:getProperty name="absence" property="date" />' /></td>
		</tr>
		</tbody>
		<tfoot>
		<tr><td><input type="submit" value="Enregistrer"></td></tr>
		</tfoot>	
	</table>
			 
	
</form>
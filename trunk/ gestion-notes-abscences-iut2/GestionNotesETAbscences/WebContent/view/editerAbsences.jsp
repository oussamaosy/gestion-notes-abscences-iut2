<%@ page import="model.Matiere"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%--<%@ page import="model.Etudiant"%>
<%@ page import="model.Groupe"%>--%>
<%@ page import="model.Absence"%>

<jsp:useBean id="absence" class="model.Absence" scope="request" />
<% 
List<model.Etudiant> listEtudiant = (List<model.Etudiant>) request.getAttribute("etudiants");
List<model.Matiere> listMatiere = (List<model.Matiere>) request.getAttribute("Matieres");
Boolean isNew= (Boolean)request.getAttribute("isNew");
String msgErreur="";
String nbheures="0";
String etuId ="0";
String matId ="0";
String date="";
if(!isNew){
	etuId=String.valueOf(absence.getEtudiantId());
	matId=String.valueOf(absence.getMatiereId());
	date=absence.getDate().toString().substring(0, 16);
	nbheures=String.valueOf(absence.getNbheures());
}

%>

<h2> <%= request.getAttribute("titre") %> </h2>
<% if(request.getAttribute("msgErreur")!=null){
	nbheures =(String)request.getAttribute("nbheures");
	etuId=(String)request.getAttribute("etudiantId");
	matId=(String)request.getAttribute("matiereId");
	date=(String)request.getAttribute("date");
	msgErreur=(String)request.getAttribute("msgErreur");
	%>
	<strong class="msgErreur"><%=msgErreur %></strong>
	<%

} %>
<form method="post" action="<%=request.getAttribute("actionForm")%>">
	<table>
		<tbody>
		<tr>
			<th><label for="nbheures" >Durée (heures) : </label></th>
			<td><input id="nbheures" size="1" name="nbheures" type="text" value='<%= nbheures %>' /></td>
		</tr>
		<tr>
			<th><label for="etudiantId" >Etudiant :</label></th>
			
			<td>
				<select name="etudiantId" id="etudiant">
				<option value=""></option>
					<% 
					
					for (model.Etudiant etu : listEtudiant) { %>
						<option value="<%= etu.getId() %>" <%=String.valueOf(etu.getId()).equals(etuId)?"selected='selected'":""%>"><%=etu.getNom() + " " + etu.getPrenom()%></option>
					<% } %>			
				</select>
			</td>
		</tr>
		<tr>
			<th><label for="matiereId" >Matiere :</label></th>
			
			<td>
				<select name="matiereId" id="matiere">
				<option value=""></option>
					<% 
					
					for (model.Matiere mat : listMatiere) {%>
						<option value="<%= mat.getId() %>" <%=String.valueOf(mat.getId()).equals(matId)?"selected='selected'":""%>"><%=mat.getIntitule()%></option>
					<%}%>			
				</select>
			</td>
		</tr>
		<tr>
			<th><label for="date" >Date (AAAA-MM-JJ HH:mm:ss) : </label></th>
			
			<td><input id="date" name="date" type="text" value="<%= date %>" /></td>
		</tr>
		</tbody>
		<tfoot>
		<tr><td><input type="submit" value="Enregistrer"></td></tr>
		</tfoot>	
	</table>
			 
	<input type="hidden" name="id" value='<jsp:getProperty name="absence" property="id" />' />
</form>
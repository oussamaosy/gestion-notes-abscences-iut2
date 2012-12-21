<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<form>
	<fieldset> <legend> Voir les absences </legend>
			<input type="radio" name="choix" value="etudiant" checked>Etudiant:  
			<select name="etudiant" id="etudiant">
				<option value="">Aucune</option>
			</select><br>
			
			<input type="radio" name="choix" value="groupe">Groupe:  
			<select name="groupe" id="groupe">
				<option value="">Aucune</option>
			</select><br>

			<label for="matiere">Filtrer par matière :</label>
			<select name="matiere" id="matiere">
				<option value="">Aucune</option>
			</select><br>
			
			<label for="nbAbsence">Nombre total d'absences: </label>
			<input type="text" readonly id="nbAbs" name="nbAbs"/><br>
			<table>
				<tr>
					<th>Etudiant</th>
					<th>Matiere</th>
					<th>NbHeure</th>
					<th></th>
				</tr>
				
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				
			</table><br>
	</fieldset>
			
	
</form>

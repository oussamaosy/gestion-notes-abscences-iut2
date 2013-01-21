<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

 <link rel="stylesheet" href="/GestionNotesETAbscences/ressources/css/login.css" type="text/css">


<form action="doCconnexion" method="post">
	<div>
		<fieldset>
			<legend>Connexion à l'application</legend>
			<p>
				<label for="login">Login</label><input type="text" name="login"
					id="login" />
			</p>

			<p>
				<label for="password">Password</label><input type="text"
					name="password" id="password" />
			</p>

		</fieldset>
		<input type="submit" name="submit" value="connexion" />
	</div>
</form>


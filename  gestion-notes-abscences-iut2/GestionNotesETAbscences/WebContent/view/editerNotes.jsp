<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<form action="" method="post">

 <jsp:useBean id="etudiant" class="model.Etudiant" scope="request"/>
 Etudiant :<jsp:getProperty  name="etudiant" property="nom" />
 <br/>
NOTES <input type="text" />
</form>

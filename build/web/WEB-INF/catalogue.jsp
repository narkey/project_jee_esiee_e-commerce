<%-- 
    Document   : catalogue
    Created on : 21 mars 2014, 16:48:08
    Author     : Titou
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des articles disponibles</title>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<c:import url="../header.jsp"></c:import>    
    </head>
    <body>
        <div class="container container-fluid">
    <h1>Liste des articles disponibles</h1>
        <br/><br/><br/>
        <legend>Liste des articles disponibles</legend>
    <table class="table table-striped">
	<thead><tr>
        <th>Titre</th>
        <th>Auteur</th>
        <th>Description</th>
        <th>Prix</th>
        <th>Acheter ?</th>
     	</tr></thead>

	<tbody>     
	<c:forEach items="${list_book}" var="book">
		<tr>
			<td><c:out value="${book.titre}" /></td>
			<td><c:out value="${book.auteur}" /></td>
			<td><c:out value="${book.description}" /></td>
			<td><c:out value="${book.prix}" /></td>
                        <td><button type="submit" class="btn btn-primary" name="book_id" value="${book.id}">Ajouter au panier</td>
		</tr>
	</c:forEach>
	</tbody>
   </table>         
      
   <!--
   <blockquote class="blockquote-reverse">
    	<p><b>Merci pour ces informations. <br/> Votre problème sera résolu dans les 24H.</b></p>
    	<footer>L' &Eacute;quipe du Support Technique</footer>
    </blockquote> 
   -->
        </div>
    <!-- Le javascript
		================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>

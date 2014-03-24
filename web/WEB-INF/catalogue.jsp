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
        
    <form action="ControleurServlet" class="form-horizontal" method="post">     
	
    <table class="table table-striped">
	<thead><tr>
        <th><span class="glyphicon glyphicon-tag"></span>&nbsp;&nbsp;Titre</th>
        <th><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;Auteur</th>
        <th><span class="glyphicon glyphicon-picture"></span>&nbsp;&nbsp;Genre</th>
        <th><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Description</th>
        <th>Prix</th>
        <th><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;&nbsp;Acheter</th>
     	</tr></thead>

	<tbody>
	<c:forEach items="${list_book}" var="book">
		<tr>
			<td><c:out value="${book.titre}" /></td>
			<td><c:out value="${book.auteur}" /></td>
			<td><c:out value="${book.genre}" /></td>
			<td><c:out value="${book.description}" /></td>
			<td><c:out value="${book.prix}" />  <span class="glyphicon glyphicon-euro"></span></td>
			<td><form action="" method="post" class="form-inline"><input type="hidden" name="origin" value="catalogue"/>
			<input type="hidden" name="cat" value="<c:out value="${param['cat']}"></c:out>"/>
            <input type="hidden" name="book_id" value="<c:out value="${book.id}"></c:out>"/>
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;Ajouter au panier</button></form></td>
            
		</tr>
	</c:forEach>
	</tbody>
   </table>         
   </form>
 
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

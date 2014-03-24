<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>D&eacute;tails de la commande</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<c:import url="../header.jsp"></c:import>    
</head>

<body>
<div class="container container-fluid">
	<legend>D&eacute;tails de la commande</legend>
    <table class="table table-striped">
		<thead><tr>
        	<th><span class="glyphicon glyphicon-tag"></span>&nbsp;&nbsp;Titre</th>
        	<th><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;Auteur</th>
        	<th><span class="glyphicon glyphicon-picture"></span>&nbsp;&nbsp;Genre</th>
        	<th>Prix  <span class="glyphicon glyphicon-euro"></span></th>
        	<th>Quantit&eacute;</th>
     	</tr></thead>

		<tbody>     
			<c:forEach items="${listeDetails}" var="detail">
				<tr>
					<td><c:out value="${detail['titre']}" /></td>
					<td><c:out value="${detail['auteur']}" /></td>
					<td><c:out value="${detail['genre']}" /></td>
					<td><c:out value="${detail['prix']}" /></td>
					<td><c:out value="${detail['quantite']}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	</div>
<!-- Le javascript
		================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
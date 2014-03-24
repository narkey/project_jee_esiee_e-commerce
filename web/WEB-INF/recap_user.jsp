<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
		
	<legend>Mes commandes en cours</legend>
    <table class="table table-striped">
		<thead><tr>
        	<th><span class="glyphicon glyphicon-barcode"></span>&nbsp;&nbsp;Num&eacute;ro de commande</th>
        	<th><span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Adresse de livraison</th>
        	<th><span class="glyphicon glyphicon-calendar"></span>&nbsp;&nbsp;Date de la commande</th>
        	<th><span class="glyphicon glyphicon-euro"></span>&nbsp;&nbsp;Prix</th>
     	</tr></thead>

		<tbody>     
			<c:forEach items="${list_commands_user}" var="command">
				<tr>
					<td>#<c:out value="${command['id']}" /></td>
					<td><c:out value="${command['adresse']}" /></td>
					<td><c:out value="${command['date']}" /></td>
					<td><c:out value="${command['prix']}" /><span class="glyphicon glyphicon-euro"></span></td>
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
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
	<legend>Liste des clients</legend>
    <table class="table table-striped">
		<thead><tr>
        	<th>Nom</th>
        	<th>Pr&eacute;nom</th>
        	<th><span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Adresse</th>
        	<th>@ Mail</th>
     	</tr></thead>

		<tbody>     
			<c:forEach items="${list_customers}" var="customer">
				<tr>
					<td><c:out value="${customer.nom}" /></td>
					<td><c:out value="${customer.prenom}" /></td>
					<td><c:out value="${customer.adresse}" /></td>
					<td><c:out value="${customer.email}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<br/>
	
	<legend>Liste des commandes pass&eacute;es</legend>
    <table class="table table-striped">
		<thead><tr>
        	<th><span class="glyphicon glyphicon-barcode"></span>&nbsp;&nbsp;Num&eacute;ro de commande</th>
        	<th><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;Contact</th>
        	<th><span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Adresse de livraison</th>
        	<th><span class="glyphicon glyphicon-calendar"></span>&nbsp;&nbsp;Date de la commande</th>
        	<th><span class="glyphicon glyphicon-euro"></span>&nbsp;&nbsp;Prix</th>
        	<th><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;D&eacute;tails</th>
     	</tr></thead>

		<tbody>     
			<c:forEach items="${list_commands}" var="command">
				<tr>
					<td>#<c:out value="${command['id']}" /></td>
					<td><c:out value="${command['email']}" /></td>
					<td><c:out value="${command['adresse']}" /></td>
					<td><c:out value="${command['date']}" /></td>
					<td><c:out value="${command['prix']}" /><span class="glyphicon glyphicon-euro"></span></td>
					<td><a href="detailsCommande?idCmd=<c:out value="${command['id']}"/>">Plus de d&eacute;tails</a></td>
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
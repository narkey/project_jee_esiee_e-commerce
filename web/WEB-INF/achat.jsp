<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panier</title>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    	<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">        
		<c:import url="../header.jsp"></c:import>
        <script type="text/javascript">  
        var quantite = 1;
        
                
        function modifier(increment,prix, id) {
        	var prixTotal = document.getElementById('prixTotal').value;
  			 prixTotal=Number(prixTotal);         	
    			quantite+=increment;
                prixTotal+=Number(prix);
                
              if(quantite>=1){   
                document.getElementById('nb' + id).value=quantite;
                document.getElementById('prixTotal').value=prixTotal;
            }else
            {
                quantite=1;
            }
        }
        </script>		
    </head>
    
    <body>
		<div class="container container-fluid">
	<legend>Contenu du panier</legend>

        <c:if test="${ empty sessionScope.cart_book }">
            <p class="text-warning">Votre panier est vide ! Consultez notre catalogue ! </p>
        </c:if>
    <c:if test="${ !empty sessionScope.cart_book }">    
        <table class="table table-striped">
            <thead>
                <tr>
					<th><span class="glyphicon glyphicon-picture"></span>&nbsp;&nbsp;Article</th>
					<th>Genre</th>
					<th>Prix unitaire</th>
					<th>Quantit&eacute;</th>
					<th>Supprimer</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list_book}" var="book">
                <tr>
					<td>
					<p><strong><c:out value="${book.key.titre}"/></strong></p>
					<footer>Auteur - <cite title="<c:out value="${book.key.auteur}"/>"><c:out value="${book.key.auteur}"/></cite></footer>
					<footer><em><small>Description - <c:out value="${book.key.description}"/></small></em></footer>
					</td>
                    <td><c:out value="${book.key.genre}"/></td>
                    <td><c:out value="${book.key.prix}"/></td>
                    <!--<td><input type="text" name="quantity" value="<c:out value='${book.value}'/>"</td>-->
					<td>
					<div class="col-sm-2"><input disabled="disabled" class="form-control" type="text" id="nb<c:out value="${book.key.id}"/>" name="quantite" value="1"></div>
					<input class="btn btn-info" type="button" value="+" onClick="modifier(1,<c:out value="${book.key.prix}"/>,<c:out value="${book.key.id}"/>)">
					<input class="btn btn-danger" type="button" value="-" onClick="modifier(-1,-<c:out value="${book.key.prix}"/>,<c:out value="${book.key.id}"/>)">		
					</td>
					<td>
						<form action="" method="post" class="form-inline"><input type="hidden" name="origin" value="supp_livre"/>
						<input type="hidden" name="book_id" value="<c:out value="${book.key.id}"></c:out>"/>
						<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span>  Supprimer</button></form>
					</td>
                    <input type="hidden" name="book_id" value="${book.key.id}"/>
                </tr>
                </c:forEach>
            </tbody>
        </table>
           <legend>Prix total en <span class="glyphicon glyphicon-euro"></span></legend>
   <form class="form-inline" method="post" action="ControleurServlet">
    	<div class="col-sm-4"><input disabled="disabled" class="form-control input-lg" type="text" id="prixTotal" value="<c:out value="${ sessionScope.prixTotal}"/>" name="prixTotal"></div>
        <input type="hidden" name="origin" value="achat"/>
       <button type="submit" class="btn btn-warning" name="bouton" value="maj">Mettre à jour mon panier</button>
       <button type="submit" class="btn btn-success btn-lg" name="bouton" value="acheter"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;&nbsp;Acheter</button>
    </form>
    </c:if>
	</div>
    <script src="jquery/css/start/images/jquery-ui.min.js"></script>    
    <script src="jquery/js/jquery-ui.min.js"></script> 
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>


</html>

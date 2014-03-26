<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panier</title>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    	<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">        
		
        <script type="text/javascript">   
                
        function modifier(increment,prix, id) {
        	var prixTotal = document.getElementById('prixTotal').value;
  			 prixTotal=Number(prixTotal);     
                         var quantite = document.getElementById('nb'+id).value;
                         quantite=Number(quantite);
    			quantite+=increment;
                prixTotal+=Number(prix);
                
              if(quantite>=1){   
                document.getElementById('nb' + id).value=quantite;
                document.getElementById('prixTotal').value=prixTotal;
            }
        }
        </script>	
        <c:import url="../header.jsp"></c:import>
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
                       <td>
                           <div class="col-sm-2"><input disabled="disabled" class="form-control" type="text" id="nb<c:out value='${book.key.id}'/>" name="quantite<c:out value='${book.key.id}'/>" value="<c:out value='${book.value}'/>"/></div>
					<input class="btn btn-info" type="button" value="+" onClick="modifier(1,<c:out value="${book.key.prix}"/>,<c:out value="${book.key.id}"/>)">
					<input class="btn btn-danger" type="button" value="-" onClick="modifier(-1,-<c:out value="${book.key.prix}"/>,<c:out value="${book.key.id}"/>)">		
					</td>
					<td>
					<form action="ControleurServlet" method="post" class="form-inline">	
						<input type="hidden" name="book_id" value="<c:out value="${book.key.id}"></c:out>"/>
                                                 <input type="hidden" name="origin" value="supp_livre"/>
						<button type="submit" class="btn btn-danger" name="bouton" value="supp_livre"><span class="glyphicon glyphicon-minus"></span>  Supprimer</button>
                                        </form> 
					</td>
                   
                </tr>


                </c:forEach>
            </tbody>
        </table>
           <legend>Prix total en <span class="glyphicon glyphicon-euro"></span></legend>
<form action="ControleurServlet" method="post" class="form-inline">
    	<div class="col-sm-4"><input disabled="disabled" class="form-control input-lg" type="text" id="prixTotal" value="<c:out value="${cart_book.prix_total}"/>" name="prixTotal"></div>
        <input type="hidden" name="origin" value="achat"/>
       <!--<button type="submit" class="btn btn-warning" name="bouton" value="maj">Mettre à jour mon panier</button>-->
       <button type="submit" class="btn btn-success btn-lg" name="bouton" value="confirmation"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;&nbsp;Acheter</button>
    </form>
    </c:if>
	</div>
    <script src="jquery/css/start/images/jquery-ui.min.js"></script>    
    <script src="jquery/js/jquery-ui.min.js"></script> 
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>


</html>

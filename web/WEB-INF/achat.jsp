<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panier</title>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
        <c:import url="../header.jsp"></c:import>
    </head>
    <form method="post" action="ControleurServlet" class="form-horizontal" role="form">
    <body>
        <c:if test="${ empty sessionScope.cart_book }">
            <p class="text-warning">Votre panier est vide ! Consultez notre catalogue ! </p>
        </c:if>
    <c:if test="${ !empty sessionScope.cart_book }">    
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Article</th>
                    <th>Prix unitaire</th>
                    <th>Quantit&eacute;</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list_book}" var="book">
                <tr>
                    <td><c:out value="${book.key.titre}"/></td>
                    <td><c:out value="${book.key.prix}"/></td>
                    <td><input type="text" name="quantity" value="<c:out value='${book.value}'/>"</td>
                    <input type="hidden" name="book_id" value="${book.key.id}"/>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <input type="hidden" name="origin" value="achat"/>
       <button type="submit" class="btn btn-warning" name="bouton" value="maj">Mettre à jour mon panier</button>
       <button type="submit" class="btn btn-success btn-lg" name="bouton" value="acheter"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;&nbsp;Acheter</button>
    </form>
    </c:if>
    <script src="jquery/css/start/images/jquery-ui.min.js"></script>    
    <script src="jquery/js/jquery-ui.min.js"></script> 
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>


</html>

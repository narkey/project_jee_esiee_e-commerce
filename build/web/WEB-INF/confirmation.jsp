
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation</title>
         <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
<c:import url="../header.jsp"></c:import>    
</head>
    
    
    
    <body>
        <div class="container container-fluid">
       <c:if test="${ empty sessionScope.cart_book }">
           <p class="text-warning">Merci d'avoir commandé ! Vous pouvez revenir à la page d'<a href="index.jsp">accueil</a> pour commander de nouveaux livres !</p>
        </c:if>
    <h1>Confirmation de la demande</h1>
        <br/><br/><br/>
         <%-- Vérification de la présence d'un objet utilisateur en session --%>
   	 <c:if test="${ !empty sessionScope.sessionUtilisateur }">
        <legend>Nom de l'utilisateur : <c:out value="${sessionScope.sessionUtilisateur.nom}"/></legend>
         <!--<legend>Nom de l'utilisateur : <c:out value="${user.nom}"/></legend>-->
         <legend>adresse : <c:out value="${sessionScope.sessionUtilisateur.adresse}"/></legend>
         <!--<legend>Adresse : <c:out value="${user.adresse}"/></legend>-->
         <br/><br/><br/>
        </c:if>

    <legend>Liste d'achat</legend>     
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
                    <td><c:out value='${book.value}'/></td>
                    <input type="hidden" name="book_id" value="${book.key.id}"/>
                </tr>
                </c:forEach>
            </tbody>
        </table>               
        <legend for="text">Prix total :</legend>
        <c:out value="${total}"/><br/>
        <form method="post" action="ControleurServlet" class="form-horizontal" role="form">
            <input type="hidden" name="origin" value="confirmation"/>
            <button type="submit" class="btn btn-primary" name="bouton" value="ok">confirmer</button>
            <button type="submit" class="btn" name="bouton" value="cancel">annuler</button>
         
        </form>         
        </div>
        <!-- Le javascript
		================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
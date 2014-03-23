<%-- 
    Document   : achat
    Created on : 21 mars 2014, 14:29:42
    Author     : zhao
--%>
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
        <script language="javascript">
        var nb_art=0; //nb article
        var total=0; //total
        var prix = 0;
        function modifier(increment, nb_art, prix) {

                nb_art =  nb_art + increment;
                total = total + nb_art * prix;
              if(nb_art>=0&&total>=0){   
                document.getElementById('nb_art').value=nb_art;
                document.getElementById('total').value=total;
            }else
            {
                nb_art=0;
                total=0;
            }
        }

        </script>
    </head>
    
<body>
    <form class="form-inline">
    <div class="container container-fluid">
    <legend for="text">Nombre d'article :</legend>
    <input class="form-control" type="text" id="nb_art" name="nb_art">

         <table class="table table-striped">
         <form>
	<thead><tr>
        <th>Article</th>
        <th>Prix unitaire</th>
        <th>Quantit&eacute;</th>
        <th>Modifier</th>
     	</tr></thead>

	<tbody>     
	<c:forEach items="${cartBooks}" var="book">
		<tr>
			<td><c:out value="${book.titre}" /></td>
			<td><c:out value="${book.prix}" /></td>
                        <td><input class="form-control" type="text" id="nb_art" name="nb_art" ></td>
                        <td><input class="btn btn-info" type="button" value="+" onClick="modifier(1,1,<c:out value="${book.prix}" />)">
                            <input class="btn btn-danger" type="button" value="-" onClick="modifier(-1,-1,<c:out value="${book.prix}" />)"></td>
		</tr>
	</c:forEach>
	</tbody>
         
   </table>   
    <legend for="text">Prix total :</legend>
    <input class="form-control" type="text" id="total" name="total">
    <script language="javascript">
    document.getElementById('text').value=valeur;
    
    
    document.getElementById('text').value=total;
    </script>
    </br></br></br>   
    <button type="submit" class="  btn btn-success btn-lg"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;&nbsp;Acheter</button>
    
    </form>
    <script src="jquery/css/start/images/jquery-ui.min.js"></script>    
    <script src="jquery/js/jquery-ui.min.js"></script> 
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
    
   
</html>

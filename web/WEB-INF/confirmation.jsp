
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation</title>
         <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
    
    
    
    
    <body>
        <div class="container container-fluid">
    <h1>Confirmation de la demande</h1>
        <br/><br/><br/>
        <legend>Nom de l'utilisateur : <c:out value="${user.nom}"/></legend>
         
        <legend>adresse : <c:out value="${user.adresse}"/></legend><br/><br/><br/>
        
    <legend>Liste d'achat</legend>     
    
    
    
    
	<table class="table table-striped">
    	<thead><tr>
	    <th>Titre</th>
	    <th>Genre</th>
	</tr></thead>
        
	<tbody>
	<!--<c:forEach items="${list_pb}" var="pb">
		<tr ${(pb.solved == true) ? 'class="success"' : 'class="danger"'}>
		  <td><c:out value="${pb.pseudo}" /></td>
		  <td><c:out value="${pb.op_sys}" /></td>
		  <td><c:out value="${pb.software}" /></td>
		  <td><c:out value="${pb.probleme}" /></td>
		  <td><c:out value="${pb.solved ==true ? 'Oui' : 'Non'} "/></td>
		</tr>
	</c:forEach>-->
	</tbody>
	</table>                      

         
         <button type="submit" class="btn btn-primary" name="confirmer">confirmer</button>
         <button type="submit" class="btn" name="annuler">annuler</button>
                
        </div>
    <!-- Le javascript
		================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
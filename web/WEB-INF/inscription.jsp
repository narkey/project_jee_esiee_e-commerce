<%-- 
    Document   : register
    Created on : 21 mars 2014, 10:16:57
    Author     : zhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enregistrement du client</title>
         <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
    </head>
    <body>
        <div class="container container-fluid">
        <h1>Enregistrement du client</h1>
        
        
        
        <legend>Informations requises :</legend>
        <br/>
        <form class="form-horizontal" role="form" method="post" action="ControleurServlet">
            <div class="form-group"><label for="nom">Nom : </label><input placeholder="Enter your lastname" class="form-control" type="text" name="nom" required="required"/></div>
            <div class="form-group"><label for="prenom">Prénom : </label><input placeholder="Enter your name" class="form-control" type="text" name="prenom" required="required"/></div>
            <div class="form-group"><label for="mdp">Mot de Passe :</label><div class="controls"><input type="password" id="mdp" name="mdp"  class="form-control" placeholder="Password"></div></div>
            <div class="form-group"><label for="email">Email : </label><input placeholder="Enter your mail address" class="form-control" type="text" name="email" required="required"/></div>
            <div class="form-group"><label for="tel">Adresse : </label><input placeholder="Enter your phone number" class="form-control" type="text" name="tel" required="required"/></div>
            <input type="hidden" name="origin" value="register"/>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        
        </div>

         <!-- Le javascript
		================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>

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
        <h1>Enregistrement du livre</h1>
        
        
        
        <legend>Informations requises :</legend>
        <br/>
        <form class="form-horizontal" role="form" method="post" action="ControleurServlet">
            <div class="form-group"><label for="titre">Titre : </label><input placeholder="Entrez le titre du livre" class="form-control" type="text" name="titre" required="required"/></div>
            <div class="form-group"><label for="auteur">Auteur : </label><input placeholder="Entrez le nom de l'auteur" class="form-control" type="text" name="auteur" required="required"/></div>
            <div class="form-group"><label for="genre">Genre : </label><input placeholder="Entrez le genre du livre" class="form-control" type="text" name="genre" required="required"/></div>
            <div class="form-group"><label for="description">Description : </label><textarea class="form-control" name="description"  rows="5" cols="25"/>Entrez la description du livre</textarea></div>
            <div class="form-group"><label for="prix">Prix : </label><input placeholder="Entrez le prix du livre" class="form-control" type="text" name="prix" required="required"/></div>
            <div class="form-group"><label for="type">Type du livre : </label><select class="form-control" type="text" name="type"/><option>Manga</option><option>Comic</option><option>Bande Dessinée</option></select></div>
            <input type="hidden" name="origin" value="bookRegister"/>
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

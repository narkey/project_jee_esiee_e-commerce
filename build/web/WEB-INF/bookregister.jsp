<%-- 
    Document   : register
    Created on : 21 mars 2014, 10:16:57
    Author     : zhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enregistrement du client</title>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
        <c:import url="../header.jsp"></c:import>        
    </head>
    <body>
        <div class="container container-fluid">
        <h1>Enregistrement d'un livre</h1>
        
        
        
        <legend>Ajouter un livre</legend>
        
        <form class="form-horizontal" role="form" method="post" action="ControleurServlet">
            <div class="form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['titre'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
                <label class="control-label col-sm-2" for="titre" >Titre</label>
                <div class="col-sm-10">
                <input placeholder="Entrez le titre du livre" class="form-control" type="text" name="titre" value="<c:out value="${livre.titre }"/>" required="required"/>
                <c:if test="${!empty form.resultat}">
			<span class="glyphicon glyphicon-${empty form.erreurs['titre'] ? 'ok': 'remove'} form-control-feedback"></span>
			<p class="text-danger">${empty form.erreurs['titre'] ? '' : form.erreurs['titre']}</p>
		</c:if>
		</div>
            </div>
            
            <div class="form-group form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['auteur'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
                <label class="control-label col-sm-2" for="auteur">Auteur</label>
                <div class="col-sm-10">
                <input placeholder="Entrez le nom de l'auteur" class="form-control" type="text" name="auteur" value="<c:out value="${livre.auteur }"/>" required="required"/>
                <c:if test="${!empty form.resultat}">
			<span class="glyphicon glyphicon-${empty form.erreurs['auteur'] ? 'ok': 'remove'} form-control-feedback"></span>
			<p class="text-danger">${empty form.erreurs['auteur'] ? '' : form.erreurs['auteur']}</p>
		</c:if>
		</div>
            </div>
            
            
            <div class="form-group form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['genre'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
                <label class="control-label col-sm-2" for="genre">Genre</label>
                <div class="col-sm-10">
                <input placeholder="Entrez le genre du livre" class="form-control" type="text" name="genre" value="<c:out value="${livre.genre }"/>" required="required"/>
                <c:if test="${!empty form.resultat}">
			<span class="glyphicon glyphicon-${empty form.erreurs['genre'] ? 'ok': 'remove'} form-control-feedback"></span>
			<p class="text-danger">${empty form.erreurs['genre'] ? '' : form.erreurs['genre']}</p>
		</c:if>
		</div>
            </div>
            
            <div class="form-group form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['description'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
                <label class="control-label col-sm-2" for="description">Description</label>
                <div class="col-sm-10">
                <textarea class="form-control" name="description"  placeholder="Entrez la description du livre" rows="5" cols="25"><c:out value="${livre.description }"/></textarea>
                <c:if test="${!empty form.resultat}">
			<span class="glyphicon glyphicon-${empty form.erreurs['description'] ? 'ok': 'remove'} form-control-feedback"></span>
			<p class="text-danger">${empty form.erreurs['description'] ? '' : form.erreurs['description']}</p>
		</c:if>
		</div>
            </div>
            
            <div class="form-group form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['prix'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
                <label class="control-label col-sm-2" for="prix">Prix</label>
                <div class="col-sm-10">
                <input placeholder="Entrez le prix du livre" class="form-control" type="text" name="prix" value="${!empty form.erreurs['prix'] ? '' : livre.prix}" required="required"/>
                <c:if test="${!empty form.resultat}">
			<span class="glyphicon glyphicon-${empty form.erreurs['prix'] ? 'ok': 'remove'} form-control-feedback"></span>
			<p class="text-danger">${empty form.erreurs['prix'] ? '' : form.erreurs['prix']}</p>
		</c:if>
		</div>
            </div>
            <div class="form-group form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['type'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
                <label class="control-label col-sm-2" for="type">Type du livre</label>
                <div class="col-sm-10">
                <select class="form-control" name="type">
                    <option value="Mangas">Mangas</option>
                    <option value="Comics">Comics</option>
                    <option value="BD">Bande Dessinée</option>
                </select>
                <c:if test="${!empty form.resultat}">
			<span class="glyphicon glyphicon-${empty form.erreurs['type'] ? 'ok': 'remove'} form-control-feedback"></span>
			<p class="text-danger">${empty form.erreurs['type'] ? '' : form.erreurs['type']}</p>
		</c:if>
		</div>
            </div>
            
            
            <input type="hidden" name="origin" value="bookRegister"/>
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-book"></span>&nbsp;&nbsp;Ajouter</button>
            <button type="reset" class="btn btn-default"><span class="glyphicon glyphicon-remove-sign"></span>&nbsp;&nbsp;Annuler</button>
        </form>
        
        </div>

        <!-- Le javascript
		================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>

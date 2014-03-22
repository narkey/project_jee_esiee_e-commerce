<%-- 
    Document   : register
    Created on : 21 mars 2014, 10:16:57
    Author     : zhao
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
		<c:import url="../header.jsp"></c:import>    
    
    </head>
    <body>
        <div class="container container-fluid">
        <h1>Inscription</h1>
        
        
        
        <legend>Ajout d'utilisateur</legend>
        <br/>
        <form class="form-horizontal" role="form" method="post" action="ControleurServlet">
            <div class="col-sm-5">
            <div class="form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['nom'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
            	<label class="control-label col-sm-5" for="nom">Nom</label>
            	<div class="col-sm-7">
            	<input name="nom" value="<c:out value="${utilisateur.nom}"/>" data-toggle="tooltip" data-placement="bottom" title="Veuillez renseigner votre nom" class="form-control" type="text"/>
            	<c:if test="${!empty form.resultat}">
					<span class="glyphicon glyphicon-${empty form.erreurs['nom'] ? 'ok': 'remove'} form-control-feedback"></span>
					<p class="text-danger">${empty form.erreurs['nom'] ? '' : form.erreurs['nom']}</p>
				</c:if>
				</div>
            </div>
            
            <div class="form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['prenom'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
            	<label class="control-label col-sm-5" for="prenom">Prénom</label>
            	<div class="col-sm-7">
            	<input name="prenom" class="form-control" value="<c:out value="${utilisateur.prenom}"/>" data-toggle="tooltip" data-placement="bottom" title="Veuillez renseigner votre pr&eacute;nom"  type="text"/>
            	<c:if test="${!empty form.resultat}">
					<span class="glyphicon glyphicon-${empty form.erreurs['prenom'] ? 'ok': 'remove'} form-control-feedback"></span>
					<p class="text-danger">${empty form.erreurs['prenom'] ? '' : form.erreurs['prenom']}</p>
				</c:if>
				</div>
			</div>
			
            <div class="form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['mdp'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
            	<label class="control-label col-sm-5" for="mdp">Mot de Passe</label>
            	<div class="col-sm-7">
            	<input  name="mdp"  class="form-control" data-toggle="tooltip" data-placement="bottom" title="Veuillez saisir votre mot de passe" type="password">
            	<c:if test="${!empty form.resultat}">
					<span class="glyphicon glyphicon-${empty form.erreurs['mdp'] ? 'ok': 'remove'} form-control-feedback"></span>
					<p class="text-danger">${empty form.erreurs['mdp'] ? '' : form.erreurs['mdp']}</p>
				</c:if>
				</div>
            </div>
            
            <div class="form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['mdp_confirm'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
            	<label class="control-label col-sm-5" for="mdp_confirm">Confirmer le mot de Passe</label>
            	<div class="col-sm-7">
            	<input id="mdp_confirm"  name="mdp_confirm" class="form-control" data-toggle="tooltip" data-placement="bottom" title="Veuillez confirmer votre mot de passe" type="password" >
            	<c:if test="${!empty form.resultat}">
					<span class="glyphicon glyphicon-${empty form.erreurs['mdp_confirm'] ? 'ok': 'remove'} form-control-feedback"></span>
					<p class="text-danger">${empty form.erreurs['mdp_confirm'] ? '' : form.erreurs['mdp_confirm']}</p>
				</c:if>
				</div>
            </div>
            
            <div class="form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['email'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
            	<label class="control-label col-sm-5" for="email">Email</label>
            	<div class="col-sm-7">
            	<input class="form-control" name="email" type="email" data-toggle="tooltip" data-placement="bottom" title="Veuillez saisir votre adresse mail" placeholder="toto@domain.com"/>
            	<c:if test="${!empty form.resultat}">
					<span class="glyphicon glyphicon-${empty form.erreurs['email'] ? 'ok': 'remove'} form-control-feedback"></span>
					<p class="text-danger">${empty form.erreurs['email'] ? '' : form.erreurs['email']}</p>
				</c:if>
				</div>
            </div>
            
            <div class="form-group" >
            	<label class="control-label col-sm-5" for="adresse">Adresse</label>
            	<div class="col-sm-7">
                    <input class="form-control" name="adresse" type="adresse" data-toggle="tooltip" data-placement="bottom" title="Veuillez saisir votre adresse" required="require"/>
                </div>
            </div>                
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="hidden" name="origin" value="register"/>
            
            <button type="submit" class="btn btn-primary "><span class="glyphicon glyphicon-ok-sign"></span>&nbsp;&nbsp;S'enregistrer</button>
            <button type="reset" class="btn btn-default "><span class="glyphicon glyphicon-remove-sign"></span>&nbsp;&nbsp;Annuler</button>
 			</div>     
        </form>
        
        </div>

         <!-- Le javascript
		================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>

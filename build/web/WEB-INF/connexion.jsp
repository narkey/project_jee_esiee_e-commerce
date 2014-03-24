<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Connexion</title>
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
		<c:import url="../header.jsp"></c:import>    
    
    </head>
    <body>
        <div class="container container-fluid">
        
        <legend>Connexion</legend>
		<p>Connectez-vous via ce formulaire</p>

        
		<form method="post" action="ControleurServlet" class="form-horizontal" role="form">
			
			<div class="col-sm-5">
			<div class="form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['email'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
				<label class="control-label col-sm-4" for="email">Login</label>
				<div class="col-sm-7">
				<input class="form-control" type="email" id="email" data-toggle="tooltip" data-placement="bottom" title="Veuillez renseigner votre login" name="email" placeholder="toto@domain.com" value="<c:out value="${utilisateur.email}"/>" maxlength="60" />
				<c:if test="${!empty form.resultat}">
					<span class="glyphicon glyphicon-${empty form.erreurs['email'] ? 'ok': 'remove'} form-control-feedback"></span>
					<p class="text-danger">${empty form.erreurs['email'] ? '' : form.erreurs['email']}</p>
				</c:if>
				</div>
			</div>
			
			<div class="form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['password'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
				<label class="control-label col-sm-4" for="password">Mot de passe</label>
				<div class="col-sm-7">
				<input name="password" class="form-control" type="password" id="password" data-toggle="tooltip" data-placement="bottom" title="Veuillez renseigner votre mot de passe" maxlength="40"/>
				<c:if test="${!empty form.resultat}">
					<span class="glyphicon glyphicon-${empty form.erreurs['password'] ? 'ok': 'remove'} form-control-feedback"></span>
					<p class="text-danger">${empty form.erreurs['password'] ? '' : form.erreurs['password']}</p>
				</c:if>
				</div>
			</div>
			<input type="hidden" name="origin" value="connect"/>
			<p class="text-primary">Vous n'êtes pas encore inscrit ? <a href="Inscription">Inscrivez-vous</a></p>
			<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;Connexion</button>				
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

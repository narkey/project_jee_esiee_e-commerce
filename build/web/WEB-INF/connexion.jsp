<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Connexion</title>
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
		
    </head>
    <body>
        <div class="container container-fluid">
		<form method="post" action="ControleurServlet" class="form-horizontal" role="form">
			
			
			<legend>Connexion</legend>
			<p>Connectez-vous via ce formulaire</p>

			<div class="form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['email'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
				<label class="control-label col-sm-2" for="email">Login</label>
				<div class="col-sm-10">
				<input class="form-control" type="email" id="email" data-toggle="tooltip" data-placement="bottom" title="Veuillez renseigner votre login" name="email" placeholder="toto@domain.com" value="<c:out value="${utilisateur.email}"/>" maxlength="60" />
				<c:if test="${!empty form.resultat}">
					<span class="glyphicon glyphicon-${empty form.erreurs['email'] ? 'ok': 'remove'} form-control-feedback"></span>
					<p class="text-danger">${empty form.erreurs['email'] ? '' : form.erreurs['email']}</p>
				</c:if>
				</div>
			</div>
			
			<div class="form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['password'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
				<label class="control-label col-sm-2" for="password">Mot de passe</label>
				<div class="col-sm-10">
				<input class="form-control" type="password" id="password" data-toggle="tooltip" data-placement="bottom" title="Veuillez renseigner votre mot de passe" name="password" value="<c:out value="${utilisateur.mdp}"/>"/>
				<c:if test="${!empty form.resultat}">
					<span class="glyphicon glyphicon-${empty form.erreurs['email'] ? 'ok': 'remove'} form-control-feedback"></span>
					<p class="text-danger">${empty form.erreurs['password'] ? '' : form.erreurs['password']}</p>
				</c:if>
				</div>
			</div>
			<input type="hidden" name="origin" value="connect"/>
			<p class="text-primary">Vous n'êtes pas encore inscrit? <a href="Register">Inscrivez-vous</a></p>
			<button type="submit" class="btn btn-primary">Connexion</button>				
			
			<%-- Vérification de la présence d'un objet utilisateur en session --%>
			<c:if test="${ !empty sessionScope.sessionUtilisateur }">
			<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
			<p class="text-success">Vous êtes connecté(e) avec l'adresse : ${ sessionScope.sessionUtilisateur.email }</p>
			</c:if>
				
		</form>
		</div>
        
        <!-- Le javascript
		================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>

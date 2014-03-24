<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Mon compte</title>
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
		<c:import url="../header.jsp"></c:import>
</head>
<body>

	<div class="container container-fluid">
	
		<legend>Changer de mot de passe</legend>
		<form role="form" method="post" action="ControleurServlet" class="form-horizontal">
			
			<p>Vous pouvez changer de mot de passe via ce formulaire.</p>

			<div class="col-sm-5">
            <div class="form-group">
            	<label class="control-label col-sm-5" for="login">Identifiant</label>
            	<div class="col-sm-7">
            	<input disabled="disabled" name="login" value="<c:out value="${sessionScope.sessionUtilisateur.email}"/>" class="form-control" type="email"/>
            	</div>
            </div>
			
			
            <div class="form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['mdp'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
            	<label class="control-label col-sm-5" for="mdp">Mot de passe actuel</label>
            	<div class="col-sm-7">
            	<input name="mdp" class="form-control" type="password" maxlength="50"/>
            	<c:if test="${!empty form.resultat}">
					<span class="glyphicon glyphicon-${empty form.erreurs['mdp'] ? 'ok': 'remove'} form-control-feedback"></span>
					<p class="text-danger">${empty form.erreurs['mdp'] ? '' : form.erreurs['mdp']}</p>
				</c:if>
				</div>
            </div>
			
			
			<div class="form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['new_mdp'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
            	<label class="control-label col-sm-5" for="new_mdp">Nouveau mot de passe</label>
            	<div class="col-sm-7">
            	<input name="new_mdp" class="form-control" type="password" maxlength="50"/>
            	<c:if test="${!empty form.resultat}">
					<span class="glyphicon glyphicon-${empty form.erreurs['new_mdp'] ? 'ok': 'remove'} form-control-feedback"></span>
					<p class="text-danger">${empty form.erreurs['new_mdp'] ? '' : form.erreurs['new_mdp']}</p>
				</c:if>
				</div>
            </div>
			
			
			<div class="form-group <c:if test="${!empty form.resultat}">${empty form.erreurs['confirmation'] ? 'has-success has-feedback': 'has-error has-feedback'}</c:if>">
            	<label class="control-label col-sm-5" for="confirmation">Confirmer le mot de passe</label>
            	<div class="col-sm-7">
            	<input name="confirmation" class="form-control" type="password" maxlength="50"/>
            	<c:if test="${!empty form.resultat}">
					<span class="glyphicon glyphicon-${empty form.erreurs['confirmation'] ? 'ok': 'remove'} form-control-feedback"></span>
					<p class="text-danger">${empty form.erreurs['confirmation'] ? '' : form.erreurs['confirmation']}</p>
				</c:if>
				</div>
            </div>


			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="hidden" name="origin" value="change_mdp"/>
            
            <button type="submit" class="btn btn-primary "><span class="glyphicon glyphicon-ok-sign"></span>&nbsp;&nbsp;Valider</button>
            <button type="reset" class="btn btn-default "><span class="glyphicon glyphicon-remove-sign"></span>&nbsp;&nbsp;Annuler</button>
		</form>
	</div>
	<!-- Le javascript
		================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
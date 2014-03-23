<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="Index"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;Acceuil</a>
    </div>


    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;Cat&eacute;gories <b class="caret"></b></a>
          <ul class="dropdown-menu">
          	<li><a href="Catalogue"><span class="glyphicon glyphicon-book"></span>&nbsp;&nbsp;Catalogue</a></li>
          	<li class="divider"></li>
            <li><a href="Catalogue?cat=bd">BD franco-belge</a></li>
            <li><a href="Catalogue?cat=comic">Comics am&eacute;ricain</a></li>
            <li><a href="Catalogue?cat=manga">Mangas / manhwas</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
      	<fieldset disabled="disabled">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search" size="60">
        </div>
        <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;Search</button>
      </fieldset>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <c:if test="${empty sessionScope.sessionUtilisateur.email}">
        	<li><a href="Connexion"><span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;Se connecter</a></li>
        	<li><a href="Inscription"><span class="glyphicon glyphicon-check"></span>&nbsp;&nbsp;S'inscrire</a></li>
        </c:if>
        <c:if test="${!empty sessionScope.sessionUtilisateur.email}">
		
		<li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<b></b><c:out value="${sessionScope.sessionUtilisateur.email}"/></b>&nbsp;&nbsp;<b class="caret"></b><span class="sr-only">Toggle Dropdown</span></a>
          <ul class="dropdown-menu">
            <li><a href="Edition"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;&Eacute;diter son profil</a></li>
            <li><a href="ChangeMDP"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Modifier son mot de passe</a></li>
            <li><a href="Reinitialiser"><span class="glyphicon glyphicon-question-sign"></span>&nbsp;&nbsp;Mot de passe oubli&eacute; ?</a></li>
            
            <c:if test="${sessionScope.sessionUtilisateur.access > 2}">
            	<li class="divider"></li>
            	<li><a href="LastCommands"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;Derni&egrave;res commandes</a></li>
            	<li><a href="Commands"><span class="glyphicon glyphicon-refresh"></span>&nbsp;&nbsp;Commandes en cours</a></li>
            </c:if>
            <li class="divider"></li>
            <li><a href="Deconnexion"><span class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;Se d&eacute;connecter</a></li>
          </ul>
        </li>
 
 	<c:if test="${sessionScope.sessionUtilisateur.access < 2}">
 		<li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;Administration&nbsp;&nbsp;<b class="caret"></b><span class="sr-only">Toggle Dropdown</span></a>
          <ul class="dropdown-menu">
            <li><a href="AjouterRef"><span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;Ajouter une r&eacute;f&eacute;rence</a></li>
            <li><a href="SupprimerRef"><span class="glyphicon glyphicon-minus"></span>&nbsp;&nbsp;Supprimer une r&eacute;f&eacute;rence</a></li>
            <li><a href="ModifierRef"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Modifier une r&eacute;f&eacute;rence</a></li>
            <li><a href="Recap"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;R&eacute;capitulatif</a></li>
          </ul>
        </li>
 	</c:if>
    <c:if test="${sessionScope.sessionUtilisateur.access > 1}">    
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;&nbsp;Mon panier&nbsp;&nbsp;<b class="caret"></b><span class="sr-only">Toggle Dropdown</span></a>
          <ul class="dropdown-menu">
            <li><a href="VoirPanier"><span class="glyphicon glyphicon-eye-open"></span>&nbsp;&nbsp;Voir mon panier</a></li>
            <li><a href="ViderPanier"><span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;Vider mon panier</a></li>
            <li><a href="PasserCommande"><span class="glyphicon glyphicon-circle-arrow-right"></span>&nbsp;&nbsp;Passer ma commande</a></li>
          </ul>
        </li>
        </c:if>
        </c:if>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

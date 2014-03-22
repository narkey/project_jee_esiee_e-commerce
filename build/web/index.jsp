<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choix de cat&eacute;gorie</title>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
		<c:import url="header.jsp"></c:import>    
    
    </head>
    <body>
		<div class="container container-fluid">
    	<%-- Vérification de la présence d'un objet utilisateur en session --%>
		<c:if test="${ !empty sessionScope.sessionUtilisateur }">
		<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
		<p class="text-success">Vous êtes connecté(e) avec l'adresse : ${ sessionScope.sessionUtilisateur.email }</p>
		</c:if>
			
        <h1>Choisissez votre cat&eacute;gorie</h1>
        <!--<div class="container container-fluid">
        <abbr title="attribute">attr</abbr>-->
        <a href="#">BD franco-belge</a>
        <a href="#">Comic am&eacute;ricain</a>
        <a href="#">Manga japonais</a>
        
        <div id="MyCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#MyCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#MyCarousel" data-slide-to="1"></li>
    <li data-target="#MyCarousel" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner">
    <div class="item active">
      <img src="img/bd.jpg" alt="Bandes-d&eacute;ssinn&eacute;es" class="img-responsive">
      <div class="carousel-caption">
        Bandes-d&eacute;ssinn&eacute;es
      </div>
    </div>
    
    <div class="item">
      <img src="img/comics.jpg" alt="Comics am&eacute;ricains" class="img-responsive">
      <div class="carousel-caption">
        Comics am&eacute;ricains
      </div>
    </div>
    
    <div class="item ">
      <img src="img/mangas.jpg" alt="Mangas" class="img-responsive">
      <div class="carousel-caption">
        Mangas
      </div>
    </div>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#MyCarousel" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
  </a>
  <a class="right carousel-control" href="#MyCarousel" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
  </a>
</div>
        
        </div>
        <div>
        <a href="/project_jee/Catalogue?cat=bd">BD franco-belge</a><br/>
        <a href="/project_jee/Catalogue?cat=comic">Comic am&eacute;ricain</a><br/>
        <a href="/project_jee/Catalogue?cat=manga">Manga japonais</a><br/>
        <a href="/project_jee/Catalogue">Tous</a><br/>
        </div>
        <!-- Le javascript
		================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	$('#MyCarousel').carousel();
    </script>
    </body>
</html>

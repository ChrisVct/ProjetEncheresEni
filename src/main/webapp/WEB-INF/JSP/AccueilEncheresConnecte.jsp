<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Page d'Accueil des enchères</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
      crossorigin="anonymous"
    />
    <link rel="icon" href="image/favicon.png" type="image/icon type" />
  </head>
  <body>
    <header>
   	  <c:if test="${empty utilisateur_connecte }">
	  	<%@include file="BarreNavigationNonConnecte.jsp" %>
	  </c:if>
	  <c:if test="${!empty utilisateur_connecte }">
	  	<%@include file="BarreNavigationConnecte.jspf" %>
	  </c:if>
    </header>
    
    <main>
    <c:if test="${!empty nouvel_article_ok }">
    	<p style="color:green;" class="text-center mt-5">${nouvel_article_ok } a bien été ajouté !</p>
    </c:if>
	<div>
	<h1 class="text-center my-3">Liste des enchères</h1></div>
		<div class="container">
		  <form class="row mt-4 p-6 mx-auto" role="search" style="max-width: 1000px;" action="ServletAccueilEncheresConnecte" method="POST">
		    <div class="col-md text-center">
			    <div class="input">
					<input type="text" class="form-control" style="max-width: 500px;" name="nomArticle" placeholder="Le nom de l'article contient...">
				</div>
		    		
			<div class="row mt-2" style="max-width: 524px;">
				<label for="libelle" class="col-sm-4 col-form-label">Catégorie :</label>
				<div class="col-sm-8">
					<select class="form-select" name="libelle">
					  <option>Toutes</option>
					  <option>Informatique</option>
					  <option>Ameublement</option>
					  <option>Vêtement</option>
					  <option>Sport&Loisirs</option>
					</select>
				</div>
			</div>
		    </div>
		    <div class="col-md text-center">
		      <button type="submit" name="recherche" class="btn btn-primary btn-lg btn-block mt-3">Rechercher</button>
		    </div>
		    
				<div class="container mt-3">
					<div class="row">
						<div class="col-md">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" id="flexRadioDefault" name="flexRadioDefault1" checked>
								<label class="form-check-label" for="flexRadioDefault">Mes achats</label>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="" id="encheresOuvertes" name="encheresOuvertes">
									<label class="form-check-label" for="encheresOuvertes">Enchères ouvertes</label>
								</div>
								<div class="form-check">
							  		<input class="form-check-input" type="checkbox" value="" id="mesEncheresEnCours" name="mesEncheresEnCours">
									<label class="form-check-label" for="mesEncheresEnCours">Enchères en cours</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="" id="mesAchatsRemportes" name="mesAchatsRemportes">
									<label class="form-check-label" for="mesAchatsRemportes">Enchères remportées</label>
								</div>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"  value="" id="flexRadioDefault2" name="flexRadioDefault1">
							<label class="form-check-label" for="flexRadioDefault2">Mes ventes</label>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="" id="ventesEnCours" name="ventesEnCours">
									<label class="form-check-label" for="ventesEnCours">Ventes en cours</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="" id="ventesAttente" name="ventesAttente">
									<label class="form-check-label" for="ventesAttente">Ventes non débutées</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="" id="ventesFinies" name="ventesFinies">
									<label class="form-check-label" for="ventesFinies">Ventes terminées</label>
								</div>
							</div>
						</div>
					</div>
				</div>
		  </form>
		</div>


    </main>

	<div class="row mx-auto p-5" style="max-width: 1280px;">
		<c:if test="${!empty listeEncheres}">
			<c:forEach var ="l" items="${listeEncheres}">
				<div class="card mb-4 mx-auto" style="max-width: 540px;">
				  <div class="row g-0">
				    <div class="col-md-4">
				      <img src="data:image/jpeg;base64,${l.getArticle().image }" class="img-fluid rounded-start" alt="...">
				    </div>
				    <div class="col-md-8">
				      <div class="card-body">
				        <h5 class="card-title">Nom de l'article : ${l.getArticle().getNomArticle()}</h5>
				        
				        <c:if test="${l.getMontantEnchere() > 0}">
				       		 <p class="card-text"> Prix : ${l.getMontantEnchere()} points </p>
				        </c:if>
     				    <c:if test="${l.getMontantEnchere()==0}">
				       		 <p class="card-text"> Prix : ${l.getArticle().getPrixInitial()} points </p>
				        </c:if>
				        
				        
				        <p class="card-text"> Fin de l'enchère : ${l.getArticle().getDateFinEncheres()}</p>
				        <p class="card-text"> Vendeur : <a href="ServletProfil?pseudoAAfficher=${l.getArticle().getVendeur().getPseudo()}">${l.getArticle().getVendeur().getPseudo()}</a></p>
				      </div>
				    </div>
				  </div>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${empty listeEncheres}">
			<h3 class="text-center p-5"> Aucune enchère actuellement en cours !</h3>
		</c:if>
	</div>

    <footer></footer>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
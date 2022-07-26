<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Page mon profil</title>
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
   
      <div
        class="position-absolute top-50 start-50 translate-middle row col-12 col-md-6"
      >
        <!-- boite affichage profil -->

        <div class="mb-3 row">
          <label for="pseudo" class="col-sm-6 col-form-label">Pseudo</label>
          <p class="col-sm-6">${utilisateurAAfficher.getPseudo()}</p>
        </div>

        <div class="mb-3 row">
          <label for="nom" class="col-sm-6 col-form-label">Nom</label>
          <p class="col-sm-6">${utilisateurAAfficher.getNom()}</p>
        </div>

        <div class="mb-3 row">
          <label for="prenom" class="col-sm-6 col-form-label">Prènom</label>
          <p class="col-sm-6">${utilisateurAAfficher.getPrenom()}</p>
        </div>

        <div class="mb-3 row">
          <label for="email" class="col-sm-6 col-form-label">Email</label>
          <p class="col-sm-6">${utilisateurAAfficher.getEmail()}</p>
        </div>

        <div class="mb-3 row">
          <label for="telephone" class="col-sm-6 col-form-label"
            >Téléphone</label
          >
          <p class="col-sm-6">${utilisateurAAfficher.getTelephone()}</p>
        </div>

        <div class="mb-3 row">
          <label for="rue" class="col-sm-6 col-form-label">Rue</label>
          <p class="col-sm-6">${utilisateurAAfficher.getRue()}</p>
        </div>

        <div class="mb-3 row">
          <label for="codePostal" class="col-sm-6 col-form-label"
            >Code postal</label
          >
          <p class="col-sm-6">${utilisateurAAfficher.getCodePostal()}</p>
        </div>

        <div class="mb-3 row">
          <label for="ville" class="col-sm-6 col-form-label">Ville</label>
          <p class="col-sm-6">${utilisateurAAfficher.getVille()}</p>
        </div>
        <button type="submit" class="btn btn-secondary col-6 m-auto">
          Modifier
        </button>
      </div>
      <!-- fermeture boite affichage profil -->
    </main>

    <footer></footer>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
      crossorigin="anonymous"
    ></script>
  </body>
</html>

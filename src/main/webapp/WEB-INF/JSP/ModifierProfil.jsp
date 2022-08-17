<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Modification du profil</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
      crossorigin="anonymous"
    />
    <link rel="icon" href="image/favicon.png" type="image/icon type" />
  </head>
  <header>
    <c:if test="${empty utilisateur_connecte }">
      <%@include file="BarreNavigationNonConnecte.jsp" %>
    </c:if>

    <c:if test="${!empty utilisateur_connecte }">
      <%@include file="BarreNavigationConnecte.jspf" %>
    </c:if>
  </header>
  <body>
    <h1 class="my-3 text-center">Modifier profil</h1>
    <form
    class="container text-center row col-12 col-md-8 mx-auto""
    method="post"
    action="ServletModifierProfil"
    >
    <c:if test="${!empty listeCodesErreur }">
      <c:forEach var="err" items="${listeCodesErreur}">
        <p style="color: red">${err }</p>
      </c:forEach>
    </c:if>

          <div class="col-6">
            <div class="mb-3 row">
              <label for="pseudo" class="col-sm-6 col-form-label"
                >Pseudo*</label
              >
              <div class="col-sm-6">
                <input
                  type="text"
                  class="form-control"
                  id="pseudo"
                  name="pseudo"
                  value="${utilisateur_connecte.getPseudo()}"
                  required
                />
              </div>
            </div>

            <div class="mb-3 row">
              <label for="prenom" class="col-sm-6 col-form-label">Prénom</label>
              <p class="col-sm-5 text-start my-auto">
                ${utilisateur_connecte.getPrenom()}
              </p>
            </div>

            <div class="mb-3 row">
              <label for="telephone" class="col-sm-6 col-form-label"
                >Téléphone</label
              >
              <div class="col-sm-6">
                <input
                  type="text"
                  class="form-control"
                  id="telephone"
                  name="telephone"
                  value="${utilisateur_connecte.getTelephone()}"
                />
              </div>
            </div>
            <div class="mb-3 row">
              <label for="codePostal" class="col-sm-6 col-form-label"
                >Code postal*</label
              >
              <div class="col-sm-6">
                <input
                  type="text"
                  class="form-control"
                  id="codePostal"
                  name="codePostal"
                  required
                  value="${utilisateur_connecte.getCodePostal()}"
                />
              </div>
            </div>
            <div class="mb-3 row">
              <label for="motDePasse" class="col-sm-6 col-form-label"
                >Mot de passe*</label
              >
              <div class="col-sm-6">
                <input
                  type="text"
                  class="form-control"
                  id="motDePasse"
                  name="motDePasse"
                  required
                />
              </div>
            </div>

            <div class="mb-3 row">
              <label for="MotDePasse" class="col-sm-6 col-form-label">Crédit</label>
              <p class="col-sm-5 text-center my-auto">${utilisateur_connecte.getCredit()}</p>
            </div>
          </div>
          <div class="col-6">
            <div class="mb-3 row">
              <label for="nom" class="col-sm-6 col-form-label">Nom</label>
              <p class="col-sm-5 text-start my-auto">
                ${utilisateur_connecte.getNom()}
              </p>
            </div>

            <div class="mb-3 row">
              <label for="email" class="col-sm-6 col-form-label">email*</label>
              <div class="col-sm-6">
                <input
                  type="text"
                  class="form-control"
                  id="email"
                  name="email"
                  required
                  value="${utilisateur_connecte.getEmail()}"
                />
              </div>
            </div>
            <div class="mb-3 row">
              <label for="rue" class="col-sm-6 col-form-label">Rue*</label>
              <div class="col-sm-6">
                <input
                  type="text"
                  class="form-control"
                  id="rue"
                  name="rue"
                  required
                  value="${utilisateur_connecte.getRue()}"
                />
              </div>
            </div>
            <div class="mb-3 row">
              <label for="ville" class="col-sm-6 col-form-label">Ville*</label>
              <div class="col-sm-6">
                <input
                  type="text"
                  class="form-control"
                  id="ville"
                  name="ville"
                  required
                  value="${utilisateur_connecte.getVille()}"
                />
              </div>
            </div>
            <div class="mb-3 row">
              <label for="nouveauMotDePasse" class="col-sm-6 col-form-label"
                >Nouveau mot de passe</label
              >
              <div class="col-sm-6 my-auto">
                <input
                  type="text"
                  class="form-control "
                  id="nouveauMotDePasse"
                  name="nouveauMotDePasse"
                />
              </div>
            </div>
            <div class="mb-3 row ">
              <label for="confirmationMotDePasse" class="col-sm-6 col-form-label"
                >Confirmer nouveau mot de passe</label
              >
              <div class="col-sm-6 my-auto">
                <input
                  type="text"
                  class="form-control"
                  id="confirmationMotDePasse"
                  name="confirmationMotDePasse"
                />
              </div>
            </div>
          </div>
        </div>
        <button type="submit" class="btn btn-primary col-4 offset-4">
          Enregistrer
        </button>
    </form>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
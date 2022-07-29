<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Vendre un nouvel article</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
      crossorigin="anonymous"
    />
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
    <main>
      <h2 class="mx-auto text-center col-6">Nouvelle vente</h2>
      <p class="mx-auto text-center col-6 text-danger">(*Champs obligatoires)</p>
      <form
        class="row col-4 mx-auto"
        action="ServletVendreNouvelArticle"
        method="POST"
      >
        <div class="mb-3 row">
          <label for="nomArticle" class="col-sm-4 col-form-label"
            >Article*</label
          >
          <div class="col-sm-8">
            <input
              type="text"
              class="form-control"
              id="nomArticle"
              name="nomArticle"
              required
            />
          </div>
        </div>
        <div class="mb-3 row">
          <label for="description" class="form-label col-4">Description</label>
          <div class="col-sm-8">
            <textarea
              class="form-control col-6"
              id="description"
              rows="3"
              name="description"
            ></textarea>
          </div>
        </div>
        <div class="mb-3 row">
          <label for="libelle" class="col-sm-4 col-form-label"
            >Catégories*</label
          >
          <div class="col-sm-8">
            <select class="form-select" name="libelle" required>
              <option selected disabled value="">Toutes</option>
              <option >Informatique</option>
              <option >Ameublement</option>
              <option value="Vetement">Vêtement</option>
              <option >Sport&Loisirs</option>
            </select>
          </div>
        </div>
        <div class="mb-3 row">
          <label for="photoArticle" class="col-sm-4 col-form-label"
            >Photo de l'article</label
          >
          <div class="col-sm-8">
            <input
              type="button"
              class="form-control"
              id="photoArticle"
              name="photoArticle"
              value="UPLOADER"
            />
          </div>
        </div>
        <div class="mb-3 row">
          <label for="prixInitial" class="col-sm-4 col-form-label"
            >Mise à prix*</label
          >
          <div class="col-sm-8">
            <input
              type="number"
              class="form-control"
              id="prixInitial"
              name="prixInitial"
              required
            />
          </div>
        </div>
        <div class="mb-3 row">
          <label for="dateDebutEncheres" class="col-sm-4 col-form-label"
            >Début de l'enchère*</label
          >
          <div class="col-sm-8">
            <input
              type="date"
              class="form-control"
              id="dateDebutEncheres"
              name="dateDebutEncheres"
              required
            />
          </div>
        </div>
        <div class="mb-3 row">
          <label for="dateFinEncheres" class="col-sm-4 col-form-label"
            >Fin de l'enchère*</label
          >
          <div class="col-sm-8">
            <input
              type="date"
              class="form-control"
              id="dateFinEncheres"
              name="dateFinEncheres"
              required
            />
          </div>
        </div>
        <div class="border border-dark p-3 my-3">
          <div class="mb-3 row">
            <label for="rueRetrait" class="col-sm-4 col-form-label" >Rue*</label>
            <div class="col-sm-8">
              <input
                type="text"
                class="form-control"
                id="rueRetrait"
                name="rueRetrait"
                value="${utilisateur_connecte.getRue()}"
                required
              />
            </div>
          </div>
          <div class="mb-3 row">
            <label for="codePostalRetrait" class="col-sm-4 col-form-label" 
              >Code Postal*</label
            >
            <div class="col-sm-8">
              <input
                type="text"
                class="form-control"
                id="codePostalRetrait"
                name="codePostalRetrait"
                value="${utilisateur_connecte.getCodePostal()}"
                required
              />
            </div>
          </div>
          <div class="mb-3 row">
            <label for="villeRetrait" class="col-sm-4 col-form-label" 
              >Ville*</label
            >
            <div class="col-sm-8">
              <input
                type="text"
                class="form-control"
                id="villeRetrait"
                name="villeRetrait"
                value="${utilisateur_connecte.getVille()}"
                required
              />
            </div>
          </div>
        </div>
        <div class="row col-6 m-auto">
          <button type="submit" class="btn btn-primary col-5">
            Enregistrer
          </button>
          <a
            href="ServletAccueilEncheresConnecte"
            class="btn btn-secondary col-5 offset-2 m-auto"
          >
            Annuler
          </a>
        </div>
      </form>
	      <c:if test="${!empty listeCodesErreur }">
		      	<c:forEach var="err" items="${listeCodesErreur}">
					<p style="color:red;">${err }</p>
				</c:forEach>
		</c:if>
    </main>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
      crossorigin="anonymous"
    ></script>
  </body>
</html>

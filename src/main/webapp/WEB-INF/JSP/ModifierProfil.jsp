<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Modification du profil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
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
	 
	    
	    <h1 class="position-absolute top-50 start-50 translate-middle-x">Mon profil</h1>
	    
	    <form class="container text-center" method="post" action="ServletInscription">
	    
	       <c:if test="${!empty listeCodesErreur }">
			<c:forEach var="err" items="${listeCodesErreur}">
				<p style="color:red;">${err }</p>
			</c:forEach>
		</c:if>
		
	    	<div class="row"><!-- ouverture boite info  -->
	    	
	    	 <div class="mb-3 row">
                    <label for="pseudo" class="col-sm-4 col-form-label"
                    >Pseudo</label
                    >
                    <div class="col-sm-6">
                    <input
                        type="text"
                        class="form-control"
                        id="pseudo"
                        name="pseudo"
                        required
                        placeholder="${miseAJourUtilisateur.getPseudo()}"
                    />
                    </div>
                </div>
		      <div class="mb-3 row">
		          <label for="nom" class="col-sm-6 col-form-label">Nom</label>
		          <p class="col-sm-6">${utilisateurAAfficher.getNom()}</p>
		        </div>
		
		        <div class="mb-3 row">
		          <label for="prenom" class="col-sm-6 col-form-label">Prènom</label>
		          <p class="col-sm-6">${utilisateurAAfficher.getPrenom()}</p>
		      </div>
		                
	    	</div>
	    
	    
	    </form>
	    
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
	  </body>
</html>
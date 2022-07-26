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
	 
	    
	    
	    
	    <form class="container text-center" method="post" action="ServletInscription">
	    
	       <c:if test="${!empty listeCodesErreur }">
			<c:forEach var="err" items="${listeCodesErreur}">
				<p style="color:red;">${err }</p>
			</c:forEach>
		</c:if>
		
	    	<div class="position-absolute top-50 start-50 translate-middle row col-12 col-md-6"><!-- ouverture boite info  -->
	    		<h1 class="position-absolute top-0 start-50 translate-middle-x">Mon profil</h1>
	    		<div class="position-absolute top-50 start-0 translate-middle"><!-- ouverture boite de gauche -->
				    	 <div class=" mb-3 row">
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
			                        values="${miseAJourUtilisateur.getPseudo()}"
			                    />
			                    </div>
			                </div>
			               
					      <div class="mb-3 row">
					          <label for="prenom" class="col-sm-6 col-form-label ">Prènom</label>
					          <p class="col-sm-6">${utilisateur_connecte.getPrenom()}</p>
					      </div>
					       <div class="mb-3 row">
			                    <label for="telephone" class="col-sm-4 col-form-label"
			                    >Téléphone</label
			                    >
			                    <div class="col-sm-6">
			                    <input
			                        type="text"
			                        class="form-control"
			                        id="telephone"
			                        name="telephone"
			                        required
			                        values="${miseAJourUtilisateur.getTelephone()}"
			                    />
			                    </div>
			                 </div>
			                     <div class="mb-3 row">
			                    <label for="codePostal" class="col-sm-4 col-form-label"
			                    >Code postal</label
			                    >
			                    <div class="col-sm-6">
			                    <input
			                        type="text"
			                        class="form-control"
			                        id="codePostal"
			                        name="codePostal"
			                        required
			                        values="${miseAJourUtilisateur.getCodePostal()}"
			                    />
			                    </div>
		                    </div>
			                    <div class="mb-3 row">
			                    
					          <label for="MotDePasse" class="col-sm-6 col-form-label">Mot de passe actuel</label>
					          <p class="col-sm-6">${utilisateur_connecte.getMotDePasse()}</p>
					      </div>
			                    
		         </div>   <!-- fermeture boite de gauche -->   
		         <div class="position-absolute top-50 start-100 translate-middle"><!-- ouverture boite de droite -->
		         	<div class="mb-3 row">
					          <label for="nom" class="col-sm-6 col-form-label">Nom</label>
					          <p class="col-sm-6">${utilisateur_connecte.getNom()}</p>
					        </div>
					        
					         <div class="mb-3 row">
			                    <label for="email" class="col-sm-4 col-form-label"
			                    >email</label
			                    >
			                    <div class="col-sm-6">
			                    <input
			                        type="text"
			                        class="form-control"
			                        id="email"
			                        name="email"
			                        required
			                        values="${miseAJourUtilisateur.getEmail()}"
			                    />
			                    </div>
		                    </div>
			                    <div class="mb-3 row">
			                    <label for="rue" class="col-sm-4 col-form-label"
			                    >Rue</label
			                    >
			                    <div class="col-sm-6">
			                    <input
			                        type="text"
			                        class="form-control"
			                        id="rue"
			                        name="rue"
			                        required
			                        values="${miseAJourUtilisateur.getRue()}"
			                    />
			                    </div>
	                       </div>
			                    <div class="mb-3 row">
			                    <label for="ville" class="col-sm-4 col-form-label"
			                    >Ville</label
			                    >
			                    <div class="col-sm-6">
			                    <input
			                        type="text"
			                        class="form-control"
			                        id="ville"
			                        name="ville"
			                        required
			                        values="${miseAJourUtilisateur.getVille()}"
			                    />
			                    </div>
		                    </div>
		         </div><!-- fermeture boite de droite -->
	    	</div><!-- fermetureboite info  -->
	   
	    
	  
	    
	    </form>
	    
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
	  </body>
</html>
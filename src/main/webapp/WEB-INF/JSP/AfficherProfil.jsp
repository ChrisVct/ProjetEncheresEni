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
      <h1>ENI-Enchères</h1>
    </header>
    <main>
    
    	
    	<div class ="position-absolute top-50 start-50 translate-middle"><!-- boite affichage profil -->
    	
	    	  <div class="mb-3 row">
	       <label for="pseudo" class="col-sm-6 col-form-label"
	       >Pseudo</label
	       >
	       <div class="col-sm-6">
	       <input
	           type="text"
	           class="form-control"
	           id="pseudo"
	           name="pseudo"
	           value="${utilisateurAAfficher.getPseudo()}"
	           disabled
	       />
	       </div>
	   </div>
	   
	     <div class="mb-3 row">
	       <label for="nom" class="col-sm-6 col-form-label"
	       >Nom</label
	       >
	       <div class="col-sm-6">
	       <input
	           type="text"
	           class="form-control"
	           id="nom"
	           name="nom"
	           value="${utilisateurAAfficher.getNom()}"
	           disabled
	       />
	       </div>
	   </div>
	  
	   <div class="mb-3 row">
	       <label for="prenom" class="col-sm-6 col-form-label"
	       >Prènom</label
	       >
	       <div class="col-sm-6">
	       <input
	           type="text"
	           class="form-control"
	           id="prenom"
	           name="prenom"
	           value="${utilisateurAAfficher.getPrenom()}"
	           disabled
	       />
	       </div>
	   </div>
	  
	   <div class="mb-3 row">
	       <label for="email" class="col-sm-6 col-form-label"
	       >Email</label
	       >
	       <div class="col-sm-6">
	       <input
	           type="text"
	           class="form-control"
	           id="email"
	           name="email"
	           value="${utilisateurAAfficher.getEmail()}"
	           disabled
	       />
	       </div>
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
	           value="${utilisateurAAfficher.getTelephone()}"
	           disabled
	       />
	       </div>
	   </div>
	   
	   <div class="mb-3 row">
	       <label for="rue" class="col-sm-6 col-form-label"
	       >Rue</label
	       >
	       <div class="col-sm-6">
	       <input
	           type="text"
	           class="form-control"
	           id="rue"
	           name="rue"
	           value="${utilisateurAAfficher.getRue()}"
	           disabled
	       />
	       </div>
	   </div>
	    	
	    	<div class="mb-3 row">
	       <label for="codePostal" class="col-sm-6 col-form-label"
	       >Code postal</label
	       >
	       <div class="col-sm-6">
	       <input
	           type="text"
	           class="form-control"
	           id="codePostal"
	           name="codePostal"
	           value="${utilisateurAAfficher.getCodePostal()}"
	           disabled
	       />
	       </div>
	   </div>
	   
	   <div class="mb-3 row">
	       <label for="ville" class="col-sm-6 col-form-label"
	       >Ville</label
	       >
	       <div class="col-sm-6">
	       <input
	           type="text"
	           class="form-control"
	           id="ville"
	           name="ville"
	           value="${utilisateurAAfficher.getVille()}"
	           disabled
	       />
	       </div>
       </div>
	   <div><!-- bouton modifier -->
	   <br>
	   	<div class="position-absolute top-100 start-50 translate-middle">
	   	<button type="submit" class="btn btn-secondary" >Modifier</button>
	   	
	   </div><!-- fermeture bouton -->
  
    	</div><!-- fermeture boite affichage profil -->
    
   </main>
   
  
	  

    <footer></footer>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
      crossorigin="anonymous"
    ></script>
  </body>
</html>

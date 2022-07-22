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
    <div class =""><!-- boite principale -->
    	<div><!-- boite titre -->
    		<h2>Profil</h2>
    	</div>
    	<div><!-- boite affichage profil -->
    	
    	</div><!-- fermeture boite affichage profil -->
      </div><!-- fermeture boite principale -->
   </main>
   
   <a href="ServletProfil?pseudoAAfficher=CV" >Christophe</a>
   
	<h2>${utilisateurAAfficher.getNom()}</h2>
	
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
           value="${utilisateurAAfficher.getNom()}"
           disabled
       />
       </div>
   </div>
	  

    <footer></footer>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
      crossorigin="anonymous"
    ></script>
  </body>
</html>

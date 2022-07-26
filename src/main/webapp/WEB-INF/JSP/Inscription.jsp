<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Page d'inscription utilisateur</title>
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
  
        <form class="container text-center" method="post" action="ServletInscription"><!--boite principal-->
           
            <c:if test="${!empty listeCodesErreur }">
			<c:forEach var="err" items="${listeCodesErreur}">
				<p style="color:red;">${err }</p>
			</c:forEach>
		</c:if>
		
            <div >
                <div class="row">
                  <div class="col">
                    
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
                        placeholder="Votre pseudo"
                    />
                    </div>
                </div>
                <div class="mb-3 row"><!-- entrée prenom-->
                    <label for="prenom" class="col-sm-4 col-form-label"
                    >Prenom</label
                    >
                    <div class="col-sm-6">
                    <input
                        type="text"
                        class="form-control"
                        id="prenom"
                        name="prenom"
                        required
                        placeholder="Votre prénom"
                    />
                    </div>
                </div>
                <div class="mb-3 row"><!-- entrée téléphone-->
                    <label for="telephone" class="col-sm-4 col-form-label" 
                    >Téléphone</label
                    >
                    <div class="col-sm-6">
                    <input
                    	
                        type="text"
                        class="form-control"
                        id="telephone"
                        name="telephone"
                        placeholder="Ex : 0606060606"
                    />
                    </div>
                </div>
                
                <div class="mb-3 row"><!-- entrée code postal-->
                    <label for="codePostal" class="col-sm-4 col-form-label"
                    >Code Postal</label
                    >
                    <div class="col-sm-6">
                    <input
                        type="text"
                        class="form-control"
                        id="codePostal"
                        name="codePostal"
                        required
                        placeholder="Ex : 83000"
                    />
                    </div>
                </div>
                
                <div class="mb-3 row"><!-- entrée mot de passe-->
                    <label for="motDePasse" class="col-sm-4 col-form-label" 
                    >Votre mot de passe</label
                    >
                    <div class="col-sm-6">
                    <input
                        type="password"
                        class="form-control"
                        id="motDePasse"
                        name="motDePasse"
                        required
                        placeholder="doit contenir 1 MAJ et 1 MIN @!:/;.?, "
                    />
                    
                    </div>
                </div>
            </div><!--fermeture clonne droite-->
            <div class="col">
                <!--colonne de gauche-->
                <div class="mb-3 row"><!-- entrée nom-->
                    <label for="nom" class="col-sm-4 col-form-label"
                    >Nom</label
                    >
                    <div class="col-sm-6">
                    <input
                        type="text"
                        class="form-control"
                        id="nom"
                        name="nom"
                        required
                        placeholder="Votre nom"
                    />
                    </div>
                </div>
                <div class="mb-3 row"><!-- entrée email-->
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
                        placeholder="Ex : email@email.com"
                    />
                    </div>
                </div>
                <div class="mb-3 row"><!-- entrée rue-->
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
                        placeholder="Nom de rue"
                    />
                    </div>
                </div>
                <div class="mb-3 row"><!-- entrée ville-->
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
                        placeholder="Ex : Toulon"
                    />
                    </div>
                
                    </div>
                   <div class="mb-3 row"><!-- entrée confimation-->
                            <label for="motDePasseConfirmation" class="col-sm-4 col-form-label"
                            >Confirmation mot de passe</label
                            >
                            <div class="col-sm-6">
                            <input
                                type="password"
                                class="form-control"
                                id="motDePasseConfirmation"
                                name="motDePasseConfirmation"
                                onpaste="return false"
                                required
                                placeholder="********"
                            />
                            
                            </div>
                        </div>
                                                    
            </div> <!--fermeture clonne gauche-->   
        </div class="col-6"> <!--colonne bouton-->
	       	<div class="mb-3 row">
	           <button type="submit" class="btn btn-primary col-5 ">Créer</button>  
	           <a href="<%=request.getContextPath()%>/ServletAccueilEncheres" class="btn btn-secondary col-5 offset-1">Annuler</a>                 
	        </div>
<%-- 	           <%request.getContextPath(); %> --%>
				</div><!--fermeture boite principal-->
      
    </form>
        
   </main>

    <footer></footer>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
      crossorigin="anonymous"
    ></script>
  </body>
</html>

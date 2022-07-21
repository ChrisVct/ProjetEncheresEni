<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Page de Connexion utilisateur</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
      crossorigin="anonymous"
    />
  </head>
  <body>
    <header>
      <h1>ENI-Enchères</h1>
    </header>
    <main>
        <div class="container text-center"><!--boite principal-->
            <div >
                <div class="row">
                  <div class="col">
                    
                <div class="mb-3 row">
                    <label for="identifiant" class="col-sm-4 col-form-label"
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
                    <label for="identifiant" class="col-sm-4 col-form-label"
                    >Prenom</label
                    >
                    <div class="col-sm-6">
                    <input
                        type="text"
                        class="form-control"
                        id="prenom"
                        name="prenom"
                        required
                        placeholder="Votre prenom"
                    />
                    </div>
                </div>
                <div class="mb-3 row"><!-- entrée téléphone-->
                    <label for="identifiant" class="col-sm-4 col-form-label"
                    >Téléphone</label
                    >
                    <div class="col-sm-6">
                    <input
                        type="text"
                        class="form-control"
                        id="telephone"
                        name="telephone"
                        required
                        placeholder="Ex : 0606060606"
                    />
                    </div>
                </div>
                
                <div class="mb-3 row"><!-- entrée code postal-->
                    <label for="identifiant" class="col-sm-4 col-form-label"
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
                    <label for="identifiant" class="col-sm-4 col-form-label"
                    >Votre mot de passe</label
                    >
                    <div class="col-sm-6">
                    <input
                        type="text"
                        class="form-control"
                        id="motDePasse"
                        name="motDePasse"
                        required
                        placeholder="********"
                    />
                    
                    </div>
                </div>
            </div><!--fermeture clonne droite-->
            <div class="col">
                <!--colonne de gauche-->
                <div class="mb-3 row"><!-- entrée nom-->
                    <label for="identifiant" class="col-sm-4 col-form-label"
                    >Nom</label
                    >
                    <div class="col-sm-6">
                    <input
                        type="text"
                        class="form-control"
                        id="Nom"
                        name="Nom"
                        required
                        placeholder="Votre nom"
                    />
                    </div>
                </div>
                <div class="mb-3 row"><!-- entrée email-->
                    <label for="identifiant" class="col-sm-4 col-form-label"
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
                    <label for="identifiant" class="col-sm-4 col-form-label"
                    >Rue</label
                    >
                    <div class="col-sm-6">
                    <input
                        type="text"
                        class="form-control"
                        id="Rue"
                        name="Rue"
                        required
                        placeholder="Nom de rue"
                    />
                    </div>
                </div>
                <div class="mb-3 row"><!-- entrée ville-->
                    <label for="identifiant" class="col-sm-4 col-form-label"
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
                            <label for="identifiant" class="col-sm-4 col-form-label"
                            >Confirmation</label
                            >
                            <div class="col-sm-6">
                            <input
                                type="text"
                                class="form-control"
                                id="motDePasse"
                                name="motDePasse"
                                required
                                placeholder="********"
                            />
                            
                            </div>
                        </div>
                                                    
            </div> <!--fermeture clonne gauche-->   
        </div class="col-6"> <!--colonne bouton-->
        <div class="mb-3 row">
            <button type="submit" class="btn btn-primary col-5 ">Créer</button>  
            <button type="submit" class="btn btn-primary col-5 offset-1">Annuler</button>                 
            </div>
            <a
    href="cheminversServletconnection"></a>

    


                </div><!--fermeture boite principal-->
            </div>
        </div>
        
    </main>

    <footer></footer>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
      crossorigin="anonymous"
    ></script>
  </body>
</html>

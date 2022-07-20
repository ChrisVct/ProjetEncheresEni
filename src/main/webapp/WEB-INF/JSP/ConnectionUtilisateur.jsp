<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      <form
        class="row col-12 col-md-6 position-absolute top-50 start-50 translate-middle"
        action="ServletConnectionUtilisateur"
        method="POST"
      >
        <div class="mb-3 row">
          <label for="identifiant" class="col-sm-4 col-form-label"
            >Identifiant</label
          >
          <div class="col-sm-8">
            <input
              type="text"
              class="form-control"
              id="identifiant"
              name="identifiant"
              required
            />
          </div>
        </div>
        <div class="mb-3 row">
          <label for="motDePasse" class="col-sm-4 col-form-label"
            >Mot de Passe</label
          >
          <div class="col-sm-8">
            <input
              type="text"
              class="form-control"
              name="motDePasse"
              required
            />
          </div>
        </div>
        <div class="row">
          <button type="submit" class="btn btn-primary col-3">Connexion</button>

          <div class="col-8 offset-1">
            <div class="form-check">
              <input
                class="form-check-input"
                type="checkbox"
                value=""
                id="flexCheckDefault"
              />
              <label class="form-check-label" for="flexCheckDefault">
                Se souvenir de moi
              </label>
            </div>

            <a href="">Mot de passe oublié</a>
          </div>
        </div>
        <a
          href="cheminversServletinscription"
          class="btn btn-secondary btn-lg mt-5 col-10 m-auto"
        >
          Créer un compte
        </a>
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

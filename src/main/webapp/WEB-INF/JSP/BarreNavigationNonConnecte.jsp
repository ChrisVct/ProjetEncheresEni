<nav class="navbar navbar-expand-lg bg-info col-xxl-8 mx-auto rounded-3"style="--bs-bg-opacity: .5;">
  <div class="container-fluid">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/ServletAccueilEncheres">
    	<img
    	alt="logo du site"
    	src="image/logoENIEncheres.png"
    	style="width:125px;border-radius:5px;"
    	>
    </a>
    
    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#navbarNav"
      aria-controls="navbarNav"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link" href="<%=request.getContextPath()%>/ServletConnexionUtilisateur">Se connecter/S'inscrire</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

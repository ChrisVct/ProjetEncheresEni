package fr.eni.projetEncheres.bll;

import java.time.LocalDate;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Article;
import fr.eni.projetEncheres.bo.Categorie;
import fr.eni.projetEncheres.bo.Retrait;
import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.dal.DAOArticle;
import fr.eni.projetEncheres.dal.DAOFactory;


public class ArticleManager {
	private DAOArticle daoArticle;
	private static ArticleManager instance;
	
	private ArticleManager() {
		this.daoArticle = DAOFactory.getDAOArticle();
	}
	
	public static ArticleManager getInstance() {
		if(instance==null) {
			instance = new ArticleManager();
		}
		return instance;
	}
	
	public void ajouterArticle(String nomArticle, String description, LocalDate dateDebutEncheres,
		LocalDate dateFinEncheres, int prixInitial, String libelle,
		int noUtilisateur, String rueRetrait, String codePostalRetrait, String villeRetrait) throws BusinessException {
		
		//vérifier les données et les envoyer dans la DAL
		BusinessException businessException = new BusinessException();
		this.verifierNullite(nomArticle,businessException);
		this.verifierNullite(libelle,businessException);
		this.verifierNullite(String.valueOf(prixInitial),businessException);
		this.verifierNullite(String.valueOf(dateDebutEncheres),businessException);
		this.verifierNullite(String.valueOf(dateFinEncheres),businessException);
		this.verifierNullite(rueRetrait,businessException);
		this.verifierNullite(codePostalRetrait,businessException);
		this.verifierNullite(villeRetrait,businessException);
		if (businessException.hasErreurs()) {
			throw businessException;
		}
		
		this.verifierTailleChamps(nomArticle, 30, businessException);
		this.verifierTailleChamps(description, 300, businessException);
		this.verifierTailleChamps(rueRetrait, 30, businessException);
		this.verifierTailleChamps(codePostalRetrait, 15, businessException);
		this.verifierTailleChamps(villeRetrait, 30, businessException);
		if (businessException.hasErreurs()) {
			throw businessException;
		}
		
		if(dateDebutEncheres.isBefore(LocalDate.now())) {
			businessException.ajouterErreur(CodesResultatBLL.DATE_DEBUT_ENCHERE_INVALIDE);
			throw businessException;
		}
		if(dateFinEncheres.isBefore( dateDebutEncheres)) {
			businessException.ajouterErreur(CodesResultatBLL.DATE_FIN_ENCHERE_INVALIDE);
			throw businessException;
		}
		
		String statut = "ATT";
		if(dateDebutEncheres.isEqual(LocalDate.now())) {
			statut="ECO";
		}
		
		Categorie cat =new Categorie(libelle);
		Article article = new Article(nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial,
				cat, statut, new Utilisateur(noUtilisateur));
		Retrait retrait = new Retrait(rueRetrait, codePostalRetrait, villeRetrait);
		
		daoArticle.insertArticleRetrait(article, retrait);
		
	}
	private void verifierNullite(String identifiant, BusinessException businessException) {
		if(identifiant.trim()=="") {
			businessException.ajouterErreur(CodesResultatBLL.CHAMPS_VIDE);
		}
	}
	private void verifierTailleChamps(String champsAVerifier, int nbMax, BusinessException businessException) throws BusinessException {
		if(champsAVerifier.length()>nbMax) {
			businessException.ajouterErreur(CodesResultatBLL.SAISIE_TROP_LONGUE_VENDRE_ARTICLE);
		}
	}
}

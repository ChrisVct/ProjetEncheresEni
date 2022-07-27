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
			int noUtilisateur, String rueRetrait, String codePostalRetrait, String villeRetrait) {
		
		//vérifier données
		//envoyer données en DAL
		String statut = "ATT";
		if(dateDebutEncheres.isEqual(LocalDate.now())) {
			statut="ECO";
		}
		Article article = new Article(nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial,
				new Categorie(libelle), statut, new Utilisateur(noUtilisateur));
		Retrait retrait = new Retrait(rueRetrait, codePostalRetrait, villeRetrait);
		
		try {
			daoArticle.insertArticleRetrait(article, retrait);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

package fr.eni.projetEncheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Article;
import fr.eni.projetEncheres.bo.Categorie;
import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.dal.DAO;
import fr.eni.projetEncheres.dal.DAOFactory;


public class ArticleManager {
	private DAO<Article> daoArticle;
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
//ici
	public void ajouterArticle(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int prixInitial, int prixVente, Categorie categorie, String statut, Utilisateur vendeur) {
		
	}


}

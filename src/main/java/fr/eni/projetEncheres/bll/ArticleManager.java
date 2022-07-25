package fr.eni.projetEncheres.bll;

import java.util.List;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Article;
import fr.eni.projetEncheres.dal.DAOArticle;
import fr.eni.projetEncheres.dal.DAOFactory;


public class ArticleManager {
	private DAOArticle daoArticle;
	
	private ArticleManager() {
		this.daoArticle = DAOFactory.getDAOArticle();
	}
	
	private static ArticleManager instance;
	
	public static ArticleManager getInstance() {
		if(instance==null) {
			instance = new ArticleManager();
		}
		return instance;
	}

	public List<Article> recupererListeArticles() throws BusinessException {
		return daoArticle.selectAll();
	}

	

}

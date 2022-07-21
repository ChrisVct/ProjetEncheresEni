package fr.eni.projetEncheres.bll;

import java.util.List;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Article;
import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.dal.DAOArticle;
import fr.eni.projetEncheres.dal.DAOFactory;
import fr.eni.projetEncheres.dal.jdbc.ArticleDAOJdbcImpl;


public class ArticleManager {
	private static ArticleDAOJdbcImpl dao = new ArticleDAOJdbcImpl();
	
	
	private static ArticleManager instance;
	
	private ArticleManager() {		
	}
	
	public static ArticleManager getInstance() {
		if(instance==null) {
			instance = new ArticleManager();
		}
		return instance;
	}

	public List<Article> getListeArticles() throws BusinessException {
		return DAOFactory.getDAOArticle().selectAll();
	}
	
	/*private DAOArticle DAOArticle;
	
	public ArticleManager() {
		this.DAOArticle=DAOFactory.getListeCourseDAO();
	}
	
	public List<ListeCourse> selectionnerListes() throws BusinessException
	{
		return this.listeCourseDAO.selectAll();
	}

	/*
	Public ListeCourse selectionnerListe(int idListeCourse) throws BusinessException {
	return this.listeCourseDAO.selectById(idListeCourse);
	}
	*/
}
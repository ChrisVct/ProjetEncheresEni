package fr.eni.projetEncheres.bll;

import java.util.List;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Article;
import fr.eni.projetEncheres.dal.DAOArticle;
import fr.eni.projetEncheres.dal.DAOFactory;


public class ArticleManager {
	private DAOArticle daoArticle;
	
	public ArticleManager() {
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

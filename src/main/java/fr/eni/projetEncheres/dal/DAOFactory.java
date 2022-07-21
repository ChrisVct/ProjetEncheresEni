package fr.eni.projetEncheres.dal;

import fr.eni.projetEncheres.dal.jdbc.ArticleDAOJdbcImpl;

public class DAOFactory {
	
	public static DAOArticle getDAOArticle() {
		return new ArticleDAOJdbcImpl();
	}
}

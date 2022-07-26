package fr.eni.projetEncheres.dal;

import fr.eni.projetEncheres.bo.Article;
import fr.eni.projetEncheres.bo.Enchere;
import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.projetEncheres.dal.jdbc.EnchereDAOJdbcImpl;
import fr.eni.projetEncheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public class DAOFactory {
	
	public static DAO<Article> getDAOArticle() {
		return new ArticleDAOJdbcImpl();
	}
	public static DAO<Utilisateur> getDAOUtilisateur(){
		return new UtilisateurDAOJdbcImpl();
	}
	public static DAO<Enchere> getDAOEnchere(){
		return new EnchereDAOJdbcImpl();
	}
}

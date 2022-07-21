package fr.eni.projetEncheres.dal;

import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.projetEncheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public class DAOFactory {
	
	public static DAOArticle getDAOArticle() {
		return new ArticleDAOJdbcImpl();
	}
	public static DAO<Utilisateur> getDAOUtilisateur(){
		return new UtilisateurDAOJdbcImpl();
	}
}

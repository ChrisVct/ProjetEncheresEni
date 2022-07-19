package fr.eni.projetEncheres.dal;

import fr.eni.projetEncheres.bo.Article;

public interface DAOArticle extends DAO<Article> {
	public Article selectByNomArticle(String NomArticle);
}

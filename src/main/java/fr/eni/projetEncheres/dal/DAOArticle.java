package fr.eni.projetEncheres.dal;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Article;
import fr.eni.projetEncheres.bo.Retrait;

public interface DAOArticle extends DAO<Article> {
	public void insertArticleRetrait(Article article, Retrait retrait) throws BusinessException;
}

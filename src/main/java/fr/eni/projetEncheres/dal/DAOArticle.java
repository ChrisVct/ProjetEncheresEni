package fr.eni.projetEncheres.dal;

import java.io.InputStream;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Article;
import fr.eni.projetEncheres.bo.Retrait;

public interface DAOArticle extends DAO<Article> {
	public void insertArticleRetrait(Article article, Retrait retrait,InputStream cheminImage) throws BusinessException;
}

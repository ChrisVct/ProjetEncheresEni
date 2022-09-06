package fr.eni.projetEncheres.dal;

import java.io.InputStream;
import java.util.List;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Article;
import fr.eni.projetEncheres.bo.Retrait;

public interface DAOArticle extends DAO<Article> {
	public void insertArticleRetrait(Article article, Retrait retrait,InputStream cheminImage) throws BusinessException;
	public List<Article> selectMesVentes(int noUtilisateur, List<String> listeStatus) throws BusinessException; 
}

package fr.eni.projetEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Article;
import fr.eni.projetEncheres.bo.Retrait;
import fr.eni.projetEncheres.dal.CodesResultatDAL;
import fr.eni.projetEncheres.dal.ConnectionProvider;
import fr.eni.projetEncheres.dal.DAO;
import fr.eni.projetEncheres.dal.DAOArticle;

public class ArticleDAOJdbcImpl implements DAOArticle {
	
	private static final String INSERT_ARTICLES ="INSERT INTO ARTICLES VALUES (?, ?,?,?,?,?,?,?,?)";
	private static final String INSERT_RETRAITS ="INSERT INTO RETRAITS VALUES (?,?,?,?)";

	  

	@Override
	public void insertArticleRetrait(Article article, Retrait retrait) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLES, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setString(3, String.valueOf(article.getDateDebutEncheres()));
			pstmt.setString(4, String.valueOf(article.getDateFinEncheres()));
			pstmt.setInt(5, article.getPrixInitial());
			pstmt.setInt(6, article.getPrixVente());
			pstmt.setInt(7, article.getVendeur().getNoUtilisateur());
			pstmt.setInt(8, article.getCategorie().getNoCategorie());
			pstmt.setString(9, article.getStatut());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				article.setNoArticle(rs.getInt(1));
				System.out.println(rs.getInt(1));
			}
			pstmt = cnx.prepareStatement(INSERT_RETRAITS);
			pstmt.setInt(1, article.getNoArticle());
			pstmt.setString(2, retrait.getRue());
			pstmt.setString(3, retrait.getCodePostal());
			pstmt.setString(4, retrait.getVille());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLES_ECHEC);
			throw businessException;
		}
	}

	@Override
	public Article selectByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Article t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Article> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Article t) throws BusinessException {
		// TODO Auto-generated method stub
		
	}


	
}


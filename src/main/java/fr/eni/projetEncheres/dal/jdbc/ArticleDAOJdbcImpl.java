package fr.eni.projetEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Article;
import fr.eni.projetEncheres.dal.CodesResultatDAL;
import fr.eni.projetEncheres.dal.ConnectionProvider;
import fr.eni.projetEncheres.dal.DAO;

public class ArticleDAOJdbcImpl implements DAO<Article> {
	
	private static final String INSERT_ARTICLES ="INSERT INTO ARTICLES VALUES (?, ?,?,?,?,?,?,?,?)";

	
	@Override
	public void insert(Article t) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLES, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, t.getNomArticle());
			pstmt.setString(2, t.getDescription());
			pstmt.setString(3, String.valueOf(t.getDateDebutEncheres()));
			pstmt.setString(4, String.valueOf(t.getDateFinEncheres()));
			pstmt.setInt(5, t.getPrixInitial());
			pstmt.setInt(6, t.getPrixVente());
			pstmt.setInt(7, t.getVendeur().getNoUtilisateur());
			pstmt.setInt(8, t.getCategorie().getNoCategorie());
			pstmt.setString(9, t.getStatut());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				t.setNoArticle(rs.getInt(1));
			}
			
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
	
}


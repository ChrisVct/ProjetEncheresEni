package fr.eni.projetEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Article;
import fr.eni.projetEncheres.bo.Enchere;
import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.dal.CodesResultatDAL;
import fr.eni.projetEncheres.dal.ConnectionProvider;
import fr.eni.projetEncheres.dal.DAO;

public class EnchereDAOJdbcImpl implements DAO<Enchere> {
	private static final String SELECT_ALL = 	"SELECT nom_article, montant_enchere, date_fin_encheres, pseudo as pseudo_vendeur, "+
												"ENCHERES.no_utilisateur as no_utilisateur_acheteur, ENCHERES.no_article FROM ARTICLES "+
												 "JOIN CATEGORIES ON Articles.no_categorie = CATEGORIES.no_categorie "+
												 "JOIN UTILISATEURS ON ARTICLES.no_utilisateur_vendeur = UTILISATEURS.no_utilisateur "+
												 "LEFT JOIN ENCHERES ON ARTICLES.no_article = ENCHERES.no_article "+
												 "WHERE statut_vente LIKE 'ECO' "+
												 "ORDER BY date_debut_encheres desc; ";
	@Override
	public void insert(Enchere t) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Enchere> selectAll() throws BusinessException {
		List<Enchere> listeEncheres = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			
			while (rs.next()) {
				String nom_article = rs.getString("nom_article");
				LocalDate dateFinEncheres = rs.getDate("date_fin_encheres").toLocalDate();
				int montant_enchere=rs.getInt("montant_enchere");
				String pseudoVendeur= rs.getString("pseudo_vendeur");
				int noUtilisateurAcheteur= rs.getInt("no_utilisateur_acheteur");
				int noArticle =rs.getInt("no_article");
				
				Enchere enchere = new Enchere(montant_enchere,new Utilisateur(noUtilisateurAcheteur),
						new Article(noArticle,nom_article, dateFinEncheres, new Utilisateur(pseudoVendeur)));
				
				listeEncheres.add(enchere);
			}
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_BDD_ERREUR);
			e.printStackTrace();
			throw businessException;
		}
		return listeEncheres;
	}
	@Override
	public Enchere selectByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void update(Enchere t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	

}

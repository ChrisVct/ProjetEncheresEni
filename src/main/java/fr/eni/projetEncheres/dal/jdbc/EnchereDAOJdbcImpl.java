package fr.eni.projetEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Article;
import fr.eni.projetEncheres.bo.Categorie;
import fr.eni.projetEncheres.bo.Enchere;
import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.dal.CodesResultatDAL;
import fr.eni.projetEncheres.dal.ConnectionProvider;
import fr.eni.projetEncheres.dal.DAO;

public class EnchereDAOJdbcImpl implements DAO<Enchere> {
	private static final String SELECT_ALL = "SELECT nom_article, montant_enchere, date_fin_encheres, prix_initial, pseudo as pseudo_vendeur,"+
												"ench.no_utilisateur as no_utilisateur_acheteur, ench.no_article, libelle FROM ARTICLES "+
												"JOIN CATEGORIES ON Articles.no_categorie = CATEGORIES.no_categorie "+
												"JOIN UTILISATEURS ON ARTICLES.no_utilisateur_vendeur = UTILISATEURS.no_utilisateur "+
												"LEFT JOIN (select e.* from ENCHERES e JOIN (select no_article, max(montant_enchere) "+
												"as max_montant from encheres group by no_article) tmp "+
												"ON e.no_article = tmp.no_article and e.montant_enchere=tmp.max_montant) ench "+
												"ON ARTICLES.no_article = ench.no_article "+
												"WHERE statut_vente LIKE 'ECO' "+
												"ORDER BY date_debut_encheres desc";
												
			//"SELECT * FROM ARTICLES JOIN ENCHERES ON ARTICLES.no_article = ENCHERES.no_article JOIN UTILISATEURS ON ARTICLES.no_utilisateur_vendeur = UTILISATEURS.no_utilisateur";
	
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
				String categorie = rs.getString("libelle");
				
				Enchere enchere = new Enchere(montant_enchere, new Utilisateur(noUtilisateurAcheteur),new Article(noArticle,nom_article, dateFinEncheres, new Categorie(categorie), new Utilisateur(pseudoVendeur)));
				
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

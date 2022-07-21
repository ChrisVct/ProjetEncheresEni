package fr.eni.projetEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.bo.Article;
import fr.eni.projetEncheres.bo.Categorie;
import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.dal.ConnectionProvider;
import fr.eni.projetEncheres.dal.DAOArticle;

public class ArticleDAOJdbcImpl implements DAOArticle {
	private static final String SELECT_ALL = SELECT nom_article, montant_enchere, date_fin_encheres, prix_initial, pseudo FROM ARTICLES
											 JOIN CATEGORIES ON Articles.no_categorie = CATEGORIES.no_categorie
											 JOIN UTILISATEURS ON ARTICLES.no_utilisateur_vendeur = UTILISATEURS.no_utilisateur
											 LEFT JOIN ENCHERES ON ARTICLES.no_article = ENCHERES.no_article
											 WHERE statut_vente LIKE 'ECO'
											 ORDER BY date_debut_encheres desc;
	
	//Jointure entre Article et Utilisateur
	//Jointure entre Article et categorie
	//ajouter une jointure vers encheres
	@Override
	public void insert(Article t) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Article> selectAll() {
		
		List<Article> listeArticles = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				int noArticle = rs.getInt("no_article");
				String nomArticle = rs.getString("nom_article");
				String description = rs.getString("description");
				LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFinEncheres = rs.getDate("date_fin_encheres").toLocalDate();
				int prixInitial = rs.getInt("prix_initial");
				int prixVente = rs.getInt("prix_vente");
				/*Utilisateur vendeur = rs.getInt("no_utilisateur_vendeur");
				Categorie categorie = rs.getInt("no_categorie");
				
				Categorie categorie = new Categorie(rs.getInt(no_categorie), rs.getString(libelle));
				
				String statut = rs.getString("statut_vente");
				*/
				Article article = new Article(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, prixVente);
				listeArticles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listeArticles;
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
	public Article selectByNomArticle(String NomArticle) {
		// TODO Auto-generated method stub
		return null;
	}
	


}


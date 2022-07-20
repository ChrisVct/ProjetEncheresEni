package fr.eni.projetEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.dal.CodesResultatDAL;
import fr.eni.projetEncheres.dal.ConnectionProvider;
import fr.eni.projetEncheres.dal.DAO;

public class UtilisateurDAOJdbcImpl implements DAO<Utilisateur> {
	private static final String SELECT_ALL = "SELECT * FROM UTILISATEURS";

	@Override
	public void insert(Utilisateur t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Utilisateur> selectAll() throws BusinessException {
		List<Utilisateur> listeUtilisateurs=new ArrayList<>();

		try(Connection cnx = ConnectionProvider.getConnection())
		{
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			
			while (rs.next()) {
				int noUtilisateur = rs.getInt("no_utilisateur");
				String pseudo = rs.getString("pseudo");
				String email = rs.getString("email");
				String pwd = rs.getString("mot_de_passe");
				String nom =rs.getString("nom");
				String prenom =rs.getString("prenom");
				Utilisateur tmpUtilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, pwd);
				listeUtilisateurs.add(tmpUtilisateur);
			}
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_BDD_ERREUR);
			throw businessException;
		}
		return listeUtilisateurs;
	}

	@Override
	public Utilisateur selectByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Utilisateur t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}



}

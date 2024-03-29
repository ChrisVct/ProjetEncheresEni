package fr.eni.projetEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	private static final String INSERT_UTILISATEURS ="INSERT INTO UTILISATEURS VALUES(?,?,?,?,?,?,?,?,?,100,0)";
	private static final String UPDATE_UTILISATEURS="UPDATE UTILISATEURS SET pseudo=?,email=?,telephone=?,rue=?,code_postal=?,ville=?,mot_de_passe=? WHERE no_utilisateur=?";
	
	@Override
	public  void insert(Utilisateur utilisateur)throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt ;
			ResultSet rs;
			
			pstmt = cnx.prepareStatement(INSERT_UTILISATEURS, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1,utilisateur.getPseudo());
			pstmt.setString(2,utilisateur.getNom());
			pstmt.setString(3,utilisateur.getPrenom());
			pstmt.setString(4,utilisateur.getEmail());
			pstmt.setString(5,utilisateur.getTelephone());
			pstmt.setString(6,utilisateur.getRue());
			pstmt.setString(7,utilisateur.getCodePostal());
			pstmt.setString(8,utilisateur.getVille());
			pstmt.setString(9,utilisateur.getMotDePasse());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
		}
		catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEURS_ECHEC);
			throw businessException;
		}
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
				String nom =rs.getString("nom");
				String prenom =rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				String motDePasse = rs.getString("mot_de_passe");
				int credit= rs.getInt("credit");
				boolean administrateur= (rs.getByte("administrateur")) == 0 ? false : true;
				
				Utilisateur tmpUtilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
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
	public void  update(Utilisateur utilisateur) throws BusinessException {
		
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt ;
			
			pstmt = cnx.prepareStatement(UPDATE_UTILISATEURS);
		
			pstmt.setString(1,utilisateur.getPseudo());
			pstmt.setString(2,utilisateur.getEmail());
			pstmt.setString(3,utilisateur.getTelephone());
			pstmt.setString(4,utilisateur.getRue());
			pstmt.setString(5,utilisateur.getCodePostal());
			pstmt.setString(6,utilisateur.getVille());
			pstmt.setString(7,utilisateur.getMotDePasse());
			pstmt.setInt(8,utilisateur.getNoUtilisateur());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.Update_UTILISATEUR_ERREUR);
			e.printStackTrace();
			throw businessException;
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}



}

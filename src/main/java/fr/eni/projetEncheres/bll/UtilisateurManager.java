package fr.eni.projetEncheres.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.dal.jdbc.UtilisateurDAOJdbcImpl;


public class UtilisateurManager {
	private static UtilisateurDAOJdbcImpl dao = new UtilisateurDAOJdbcImpl();
	
	public Utilisateur verifierConnection(String identifiant, String motDePasse) throws BusinessException {
		Utilisateur utilisateurARetouner = null;
		BusinessException businessException = new BusinessException();
		
		this.verifierNullite(identifiant, businessException);
		if (businessException.hasErreurs()) {
			throw businessException;
		}
		List<Utilisateur> listeUtilisateurs = dao.selectAll();
		utilisateurARetouner = this.verifierIdentifiants(identifiant,motDePasse, listeUtilisateurs);

		if(utilisateurARetouner==null) {
			businessException.ajouterErreur(CodesResultatBLL.AUTHENTIFICATION_ERREUR);
			throw businessException;
		}
		return utilisateurARetouner;
	}
	

	private Utilisateur verifierIdentifiants(String identifiant, String motDePasse, List<Utilisateur> listeUtilisateurs) {
		Utilisateur utilisateurARetourner=null; 
		for (Utilisateur u : listeUtilisateurs) {
			if(identifiant.equalsIgnoreCase(u.getEmail()) ||
					identifiant.equalsIgnoreCase(u.getPseudo()))
			{
				if(hasherMotDePasse(motDePasse).equals(u.getMotDePasse())) {
					utilisateurARetourner=new Utilisateur(u.getNoUtilisateur(), u.getPseudo(), u.getNom(), u.getPrenom(), u.getEmail(),
							u.getTelephone(), u.getRue(), u.getCodePostal(), u.getVille(), u.getMotDePasse(), u.getCredit(), u.isAdministrateur());
				}
			}
		}
		return utilisateurARetourner;
	}

	private void verifierNullite(String identifiant, BusinessException businessException) {
		if(identifiant.trim()=="") {
			businessException.ajouterErreur(CodesResultatBLL.CHAMPS_VIDE);
		}
		
	}
	public void  ajouterUtilisateur(String pseudo, String nom,String prenom,String email,String telephone,String rue,String code_postal,String ville,String mot_de_passe) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		// nettoyer les données tous en minuscule
		// verifier la nullité (attention le téléphone peut être null)
		// verifie le password
		// verifie l'inicité du pseudo
		// verifie l'inicité de l'email et que l'email est conforme
		// hasher le mot de passe --> voir la méthode ci-dessous
		UtilisateurManager uManager = new UtilisateurManager();
		Utilisateur utilisateur =new Utilisateur(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe);
		dao.insert(utilisateur);
		
	
	}
	
	public String hasherMotDePasse(String motDePasseClair) {
		StringBuffer hexString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(motDePasseClair.getBytes());
			byte[] digest = md.digest();
		      hexString = new StringBuffer();
		      for (int i = 0;i<digest.length;i++) {
		         hexString.append(Integer.toHexString(0xFF & digest[i]));
		      }
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hexString.toString();
	}

}

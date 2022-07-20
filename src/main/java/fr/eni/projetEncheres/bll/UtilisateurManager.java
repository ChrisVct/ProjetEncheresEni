package fr.eni.projetEncheres.bll;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.dal.jdbc.UtilisateurDAOJdbcImpl;


public class UtilisateurManager {
	private static UtilisateurDAOJdbcImpl dao = new UtilisateurDAOJdbcImpl();
	
	public boolean verifierConnection(String identifiant, String motDePasse) throws BusinessException {
		BusinessException businessException = new BusinessException();
		boolean identifiantOK=false;
		boolean motDePasseOK=false;
		
		this.verifierIdentifiant(identifiant, businessException);
		
		
		
		if (businessException.hasErreurs()) {
			throw businessException;
		}
		List<Utilisateur> listeUtilisateurs = dao.selectAll();
		//Je vérifie si l'identifiant passé en argument est présent dans la liste d'utilisateur
		for (Utilisateur u : listeUtilisateurs) {
			if(identifiant.equals(u.getEmail()) ||
					identifiant.equals(u.getPseudo()))
			{
				identifiantOK=true;
			}
		}
		if(!identifiantOK) {
			businessException.ajouterErreur(CodesResultatBLL.IDENTIFIANT_ERREUR_INEXISTANT);
			throw businessException;
		}
		
		//Si l'identifiant est bien présent dans la liste d'utilisateurs je reparcours le tableau
		//et vérifie si le mot de passe si u = GetEmail() ou u=getPseudo
		if(identifiantOK==true) {
			for (Utilisateur u : listeUtilisateurs) {
				if(identifiant.equals(u.getEmail()) ||
						identifiant.equals(u.getPseudo()))
				{
					if(motDePasse.equals(u.getMotDePasse())) {
						motDePasseOK=true;
					}
				}
			}
		}
		if(!motDePasseOK) {
			businessException.ajouterErreur(CodesResultatBLL.MOT_DE_PASSE_ERREUR_ERRONE);
			throw businessException;
		}
		
		return true;

	}

	private void verifierIdentifiant(String identifiant, BusinessException businessException) {
		if(identifiant.trim()=="") {
			businessException.ajouterErreur(CodesResultatBLL.IDENTIFIANT_ERREUR_VIDE);
		}
		
	}
}
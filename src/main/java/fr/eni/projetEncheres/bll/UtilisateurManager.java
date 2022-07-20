package fr.eni.projetEncheres.bll;

import java.util.List;

import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public class UtilisateurManager {
	private static UtilisateurDAOJdbcImpl dao = new UtilisateurDAOJdbcImpl();
	
	public void verifierConnection(String identifiant, String motDePasse) {
		boolean identifiantOK=false;
		boolean motDePasseOK=false;
		
		List<Utilisateur> listeUtilisateurs = dao.selectAll();
		//Je vérifie si l'identifiant passé en argument est présent dans la liste d'utilisateur
		for (Utilisateur u : listeUtilisateurs) {
			if(identifiant.equals(u.getEmail()) ||
					identifiant.equals(u.getPseudo()))
			{
				identifiantOK=true;
			}
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
		System.out.println("L'identifiant est-il valide ? : "+identifiantOK);
		if(identifiantOK==true) {
			System.out.println("Le mot de passe est-il valide ? : "+ motDePasseOK);
		}

	}
}
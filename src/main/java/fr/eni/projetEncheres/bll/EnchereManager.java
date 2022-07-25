package fr.eni.projetEncheres.bll;

import java.util.List;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Categorie;
import fr.eni.projetEncheres.bo.Enchere;
import fr.eni.projetEncheres.dal.DAO;
import fr.eni.projetEncheres.dal.DAOFactory;


public class EnchereManager {
	private DAO<Enchere> daoEnchere;
	private static EnchereManager instance;
	private static List<Enchere> listeEncheres;
	
	private  EnchereManager() throws BusinessException {
		this.daoEnchere = DAOFactory.getDAOEnchere();
		listeEncheres = daoEnchere.selectAll();
		
	}
	
	public static EnchereManager getInstance() throws BusinessException {
		if(instance==null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
	public List<Enchere> afficherEncheresEnCours() throws BusinessException {
		return listeEncheres;
	}
	
	public List<Enchere> afficherEncheresAvecParametres(String nomArticle, String libelle, List<Enchere> listeEncheres) {		
		List<Enchere> listeEncheresAvecParametres = null;
					
		/*for (List<Enchere> e : listeEncheres)
		
		if (nomArticle.equals(e.getArticle()))	
			listeEncheresAvecParametres = new List<Enchere>();
		
		if ( libelle.equals(libelle)  || nomArticle.equals(nomArticle)) {
		}
		*/
		return listeEncheresAvecParametres;
	}
	
}


package fr.eni.projetEncheres.bll;

import java.util.List;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Enchere;
import fr.eni.projetEncheres.dal.DAO;
import fr.eni.projetEncheres.dal.DAOFactory;


public class EnchereManager {
//	private static UtilisateurDAOJdbcImpl dao = new UtilisateurDAOJdbcImpl();
	private DAO<Enchere> daoEnchere;
	private static EnchereManager instance;
//	private static List<Enchere> listeUtilisateurs;
	
	public  EnchereManager() throws BusinessException {
		this.daoEnchere = DAOFactory.getDAOEnchere();
	}
	
	public static EnchereManager getInstance() throws BusinessException {
		if(instance==null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
	public List<Enchere> afficherEncheresEnCours() throws BusinessException{
		List<Enchere> listeEncheres= daoEnchere.selectAll();
		return listeEncheres;
	}
	
}


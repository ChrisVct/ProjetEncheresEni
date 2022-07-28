package fr.eni.projetEncheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Enchere;
import fr.eni.projetEncheres.dal.DAOEnchere;
import fr.eni.projetEncheres.dal.DAOFactory;

public class EnchereManager {
	private DAOEnchere daoEnchere;
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
	
	public List<Enchere> afficherEncheresOuvertes() throws BusinessException {
		return listeEncheres;
	}
	
	public List<Enchere> afficherEncheresAvecParametres(String nomArticle, String libelle, List<Enchere> liste) {		
		List<Enchere> listeEncheresAvecParametres = new ArrayList<>();
			if(liste==null) {
				for (Enchere e : listeEncheres) {
					if ((e.getArticle().getNomArticle().toLowerCase()).contains(nomArticle.toLowerCase().trim()) &&
							(libelle.equals("Toutes") ? true : (e.getArticle().getCategorie().getLibelle().equals(libelle))) ) {
						listeEncheresAvecParametres.add(e);
					}
				}
			}else {
				for (Enchere e : liste) {
					if ((e.getArticle().getNomArticle().toLowerCase()).contains(nomArticle.toLowerCase().trim()) &&
							(libelle.equals("Toutes") ? true : (e.getArticle().getCategorie().getLibelle().equals(libelle))) ) {
						listeEncheresAvecParametres.add(e);
					}
				}
			 }
		return listeEncheresAvecParametres;
	}
	
	public List<Enchere> afficherAchatEnCours(int noUtilisateur) throws BusinessException {
		List<Enchere> listeAchatsEnCours = new ArrayList<>();
		listeAchatsEnCours = daoEnchere.selectEcoById(noUtilisateur);
		return listeAchatsEnCours;
	}
	
	public List<Enchere> afficherAchatsRemportes(int noUtilisateur) throws BusinessException {
		List<Enchere> listeAchatsRemportes = new ArrayList<>();
		listeAchatsRemportes = daoEnchere.selectAllFinWinnedById(noUtilisateur);
		return listeAchatsRemportes;
	}
}


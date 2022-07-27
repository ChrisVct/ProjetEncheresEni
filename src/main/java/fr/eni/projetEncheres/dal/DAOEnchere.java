package fr.eni.projetEncheres.dal;

import java.util.List;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Enchere;
import fr.eni.projetEncheres.bo.Utilisateur;

public interface DAOEnchere extends DAO<Enchere> {
	public List<Enchere> selectEcoById(int id) throws BusinessException;
	public List<Enchere> selectAllFinWinnedById(int id) throws BusinessException;
}

package fr.eni.projetEncheres.dal;

import java.util.List;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Article;
import fr.eni.projetEncheres.bo.Enchere;
import fr.eni.projetEncheres.bo.Retrait;

public interface DAOEnchere extends DAO<Enchere> {
	public List<Enchere> selectAllEcoById(int id) throws BusinessException;
	public List<Enchere> selectAllFinWinnedById(int id) throws BusinessException;
}

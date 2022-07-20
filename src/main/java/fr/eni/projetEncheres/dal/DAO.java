package fr.eni.projetEncheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetEncheres.BusinessException;

public interface DAO<T> {
	public void insert(T t) throws BusinessException ;
	public List<T> selectAll() throws BusinessException;
	public T selectByID(int id);
	public void update(T t);
	public void delete(int id);
	
}

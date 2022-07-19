package fr.eni.projetEncheres.dal;

import java.util.List;

public interface DAO<T> {
	public void insert(T t);
	public List<T> selectAll();
	public T selectByID(int id);
	public void update(T t);
	public void delete(int id);
	
}

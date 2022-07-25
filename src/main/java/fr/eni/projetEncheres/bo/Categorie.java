package fr.eni.projetEncheres.bo;

import java.io.Serializable;

public class Categorie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int noCategorie;
	private String libelle;
	
	public Categorie() {
	}
	
	/**
	 * @param libelle
	 */
	public Categorie(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Categorie : " +this.getNoCategorie()+" - Libelle= " +this.getLibelle();
	}
	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}

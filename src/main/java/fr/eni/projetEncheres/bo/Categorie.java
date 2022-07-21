package fr.eni.projetEncheres.bo;

public class Categorie {
	private int noCategorie;
	private String libelle;
	
	public Categorie() {
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

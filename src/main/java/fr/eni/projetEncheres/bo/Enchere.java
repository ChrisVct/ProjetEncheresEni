package fr.eni.projetEncheres.bo;

import java.io.Serializable;
import java.time.LocalDate;

public class Enchere implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private LocalDate dateEnchere;
	private int montantEnchere;
	private Utilisateur acheteur;
	private Article article;
	
	public Enchere() {
	}
	
	
	
	/**
	 *  utilisé pour le select_all enchere de la page d'accueil(mode non connecte)
	 * @param montantEnchere
	 * @param acheteur
	 * @param article
	 */
	public Enchere(int montantEnchere, Utilisateur acheteur, Article article) {
		this.montantEnchere = montantEnchere;
		this.acheteur = acheteur;
		this.article = article;
	}
	
	@Override
	public String toString() {
		return "Date Enchère : " +this.getDateEnchere()+" - Montant : " +this.getMontantEnchere()+
				" - Utilisateur : " +this.getAcheteur()+" - Article : " +this.getArticle()+
				"\n";
	}
	
		
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	public Utilisateur getAcheteur() {
		return acheteur;
	}
	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}
	public Article getArticle() {
		return article;
	}
	
	public void setArticle(Article article) {
		this.article = article;
	}
	
}


//this.article ajouterencheres

//dans article ajouter une méthode pour recuperer tableau enchere ou modifier enchere en cours
package fr.eni.projetEncheres.bo;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * Cette classe représente les articles vendus sur le site
 */
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private Categorie categorie;
	private String statut; 		//Valeur ATT, ECO, FIN
	private Utilisateur vendeur;
	
	public Article() {
	}
	
	/*
	 * Constructeur servant à récupérer la liste des articles
	 */
	public Article(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int prixInitial, int prixVente, Categorie categorie, String statut, Utilisateur vendeur) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.categorie = categorie;
		this.statut = statut;
		this.vendeur = vendeur;
	}
	
	
	
	/**
	 * Constructeur pou l'ajout d'un nouvel article
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param prixInitial
	 * @param categorie
	 * @param statut
	 * @param vendeur
	 */
	public Article(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int prixInitial, Categorie categorie, String statut, Utilisateur vendeur) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.categorie = categorie;
		this.statut = statut;
		this.vendeur = vendeur;
	}

	/**
	 *  utilisé pour le select_all enchere de la page d'accueil(mode non connecte)
	 * @param noArticle
	 * @param nomArticle
	 * @param dateFinEncheres
	 * @param vendeur
	 */
	public Article(int noArticle, String nomArticle, LocalDate dateFinEncheres, Categorie categorie, Utilisateur vendeur) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.dateFinEncheres = dateFinEncheres;
		this.categorie = categorie;
		this.vendeur = vendeur;
	}

	@Override
	public String toString() {
		return noArticle+" "+this.getNomArticle()+
						 " Description= " +this.getDescription()+
						 " Date de début enchere= " +this.getDateDebutEncheres()+
						 " Date de fin enchère= " +this.getDateFinEncheres()+
						 " Prix initial= " +this.getPrixInitial()+
						 " Prix de vente= " +this.getPrixVente()+
						 " Categorie=" +this.getCategorie()+
						 " Statut=" +this.getStatut()+
						 " Vendeur=" +this.getVendeur();
	}					 
	
	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

}

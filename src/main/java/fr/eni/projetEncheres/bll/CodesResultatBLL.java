package fr.eni.projetEncheres.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	/**
	 * Le champs identifiant est vide (ou " " uniquement)
	 */
	public static final int IDENTIFIANT_ERREUR_VIDE=20000;
	/**
	 * Identifiant n'existe pas en BDD
	 */
	public static final int IDENTIFIANT_ERREUR_INEXISTANT=20001;
	/**
	 * Identifiant OK, mais mot de passe erroné
	 */
	public static final int MOT_DE_PASSE_ERREUR_ERRONE=20002;



}

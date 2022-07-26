package fr.eni.projetEncheres.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	
	/**
	 * Echec de lecture dans la base données
	 */
	public static final int SELECT_ALL_BDD_ERREUR=10000;
	/**
	 * Echec de l'enregistrement de cet utilisateur
	 */
	public static final int INSERT_UTILISATEURS_ECHEC = 10001;
	/**
	 * Echec de l'enregistrement de cet article
	 */
	public static final int INSERT_ARTICLES_ECHEC = 10002;

	/**
	 * Echec de la mise à jour de l'utilisateur.
	 */
	public static final int Update_UTILISATEUR_ERREUR =10003;



}

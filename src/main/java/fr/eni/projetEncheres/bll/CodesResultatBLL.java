package fr.eni.projetEncheres.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	/**
	 * Un champs null ou rempli avec espacement
	 */
	public static final int CHAMPS_VIDE=20000;
	/**
	 * Erreur d'authenification
	 */
	public static final int AUTHENTIFICATION_ERREUR=20001;
	public static final int PSEUDO_PRIS = 20002;
	public static final int EMAIL_DEJA_ENREGISTRER = 20003;
	public static final int MDP_COURT = 20004;
	public static final int TEL_COURT = 20005;
	public static final int MDP_PAS_IDENTIQUE = 20006;
	public static final int Password_INCORRECT = 20007;
	public static final int FORMAT_EMAIL_INCORRECT = 20008;
	public static final int SAISIE_TROP_LONGUE = 20009;
	public static final int TEL_CONTIENT_CHARACTER_NON_NUMERIQUE = 20010;


}

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
	/**
	 * Le pseudo est déjà utilisé
	 */
	//
	public static final int PSEUDO_PRIS = 20002;
	/**
	 * email est déjà enregistré par un autre utilisateur 
	 */
	public static final int EMAIL_DEJA_ENREGISTRER = 20003;
	/**
	 * le mot de passe est trop court
	 */
	public static final int MDP_COURT = 20004;
	/**
	 * le numéro de téléphone ne fait pas 10 chiffres
	 */
	public static final int TEL_COURT = 20005;
	/**
	 * les mots de passe ne corresponde pas 
	 */
	public static final int MDP_PAS_IDENTIQUE = 20006;
	/**
	 * le mot de passe ne contien pas une MAJ ou une Minuscule ou un caratére spécial
	 */
	public static final int MDP_INCORRECT = 20007;
	/**
	 * l'email ne ressemble pas a un email email@email.fr
	 */
	public static final int FORMAT_EMAIL_INCORRECT = 20008;
	/**
	 * la taille des champs requis est trop grand
	 */
	public static final int SAISIE_TROP_LONGUE = 20009;
	/**
	 * le numéro de téléphone contien des caratères spéciaux
	 */
	public static final int TEL_CONTIENT_CHARACTER_NON_NUMERIQUE = 20010;
	/**
	 * Un champs null ou rempli avec espacement
	 */
	public static final int CHAMPS_ARTICLE_VIDE=20011;
	/**
	 * Date de début enchère inférieure à date du jour
	 */
	public static final int DATE_DEBUT_ENCHERE_INVALIDE=20012;
	/**
	 * Date de début enchère supérieure à date de fin d'enchère
	 */
	public static final int DATE_FIN_ENCHERE_INVALIDE=20013;



}

package fr.eni.projetEncheres.bll;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.dal.DAO;
import fr.eni.projetEncheres.dal.DAOFactory;

public class UtilisateurManager {
	private DAO<Utilisateur> daoUtilisateur;
	private static UtilisateurManager instance;
	private static List<Utilisateur> listeUtilisateurs;
	
	private UtilisateurManager() throws BusinessException {
		this.daoUtilisateur = DAOFactory.getDAOUtilisateur();
		listeUtilisateurs = daoUtilisateur.selectAll();
	}
	
	public static UtilisateurManager getInstance() throws BusinessException {
		if(instance==null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	public Utilisateur recupererInfoProfil(String pseudo) {
		Utilisateur utilisateurARetourner=null;
		for (Utilisateur u : listeUtilisateurs) {
			if(pseudo.equals(u.getPseudo())) {
				utilisateurARetourner = new Utilisateur(u.getPseudo(),u.getNom(),u.getPrenom(),u.getEmail(),u.getTelephone(),u.getRue(),u.getCodePostal(),u.getVille());
			}
		}
		return utilisateurARetourner;
	}
	
	public Utilisateur verifierConnection(String identifiant, String motDePasse) throws BusinessException {
		Utilisateur utilisateurARetouner = null;
		BusinessException businessException = new BusinessException();
		this.verifierNullite(identifiant, businessException);
		if (businessException.hasErreurs()) {
			throw businessException;
		}
		
		utilisateurARetouner = this.verifierIdentifiants(identifiant,motDePasse, listeUtilisateurs);

		if(utilisateurARetouner==null) {
			businessException.ajouterErreur(CodesResultatBLL.AUTHENTIFICATION_ERREUR);
			throw businessException;
		}
		return utilisateurARetouner;
	}

	public Utilisateur ajouterUtilisateur(String pseudo, String nom,String prenom,String email,String telephone,String rue,String codePostal,String ville,String motDePasse, String motDePasseConfirmation) throws BusinessException {
		BusinessException businessException = new BusinessException();
		
		// verifier la nullité des saisies
		this.verifierNullite(pseudo, businessException);
		this.verifierNullite(nom, businessException);
		this.verifierNullite(prenom, businessException);
		this.verifierNullite(email, businessException);
		this.verifierNullite(rue, businessException);
		this.verifierNullite(codePostal, businessException);
		this.verifierNullite(ville, businessException);
		this.verifierNullite(motDePasse, businessException);
		
		if (businessException.hasErreurs()) {
			throw businessException;
		}
		
		//vérifier la conformité des saisies (par rapport à un modèle ou à une taille maxi)
		this.verifierConformiteMotDePasse(motDePasse, businessException);
		this.verifierFormatEmail(email, businessException);
		this.verifierFormatTelephone(telephone, businessException);
		
		this.verifierTailleChamps(pseudo, 30, businessException);
		this.verifierTailleChamps(nom, 30, businessException);
		this.verifierTailleChamps(prenom, 30, businessException);
		this.verifierTailleChamps(rue, 30, businessException);
		this.verifierTailleChamps(ville, 30, businessException);
		this.verifierTailleChamps(email, 100, businessException);
		this.verifierTailleChamps(codePostal, 10, businessException);
		
		if (businessException.hasErreurs()) {
				throw businessException;
		}

		// Concordance des mot de passe		
		if(!motDePasseConfirmation.equals(motDePasse) ) {
			businessException.ajouterErreur(CodesResultatBLL.MDP_PAS_IDENTIQUE);
			throw businessException;
		}
		
		this.verifierUnicitePseudo(pseudo, businessException);
		this.verifierUniciteEmail(email, businessException);
		if (businessException.hasErreurs()) {
			throw businessException;
		}

		Utilisateur utilisateur =new Utilisateur(pseudo.trim(), nom.trim(), prenom.trim(), email.trim(), telephone.trim(), rue.trim(), codePostal.trim(), ville.trim(), hasherMotDePasse(motDePasse));
		daoUtilisateur.insert(utilisateur);
		utilisateur.setCredit(100);
		utilisateur.setAdministrateur(false);
		
		//Enregistre le nouvel utilisateur en mémoire tampon
		listeUtilisateurs.add(utilisateur);
		return utilisateur;
	}

	public Utilisateur miseAJourUtilisateur(int noUtilisateur,String pseudo,String nom,String prenom,String email,String telephone,String rue,String codePostal,String ville,String motDePasse,Integer credit,boolean administrateur) throws BusinessException {
			BusinessException businessException = new BusinessException();
			Utilisateur utilisateurARetourner =null;

			for(Utilisateur util : listeUtilisateurs) {
				if(noUtilisateur == util.getNoUtilisateur()) {
					//vérfier si pseudo a été changé
					if(!pseudo.trim().equalsIgnoreCase(util.getPseudo())) {
						this.verifierNullite(pseudo, businessException);
						this.verifierTailleChamps(pseudo, 30, businessException);
						this.verifierUnicitePseudo(pseudo, businessException);
					}
					//vérifier email a changé (si oui, faire les vérifs)
					if(!email.equalsIgnoreCase(util.getEmail())) {
						this.verifierNullite(email, businessException);
						this.verifierTailleChamps(email, 100, businessException);
						this.verifierFormatEmail(email, businessException);
						this.verifierUniciteEmail(email, businessException);
					}
					//vérifier code postal a changé (si oui, faire les vérifs)
					if(!rue.trim().equalsIgnoreCase(util.getRue())) {
						this.verifierNullite(rue, businessException);
						this.verifierTailleChamps(rue, 30, businessException);
					}
					if(!ville.trim().equalsIgnoreCase(util.getVille())) {
						this.verifierNullite(ville, businessException);
						this.verifierTailleChamps(ville, 30, businessException);
					}
					if(!codePostal.trim().equalsIgnoreCase(util.getCodePostal())) {
						this.verifierNullite(codePostal, businessException);
						this.verifierTailleChamps(codePostal, 10, businessException);
					}
					//vérifier telephone a changé (si oui, faire les vérifs)
					if((!telephone.trim().equals(util.getTelephone())) && !telephone.equals("")) {
						this.verifierFormatTelephone(telephone, businessException);
					}
					//vérifier telephone a changé (si oui, faire les vérifs)
				}
			}
			
			if (businessException.hasErreurs()) {
				throw businessException;
			}
			
			utilisateurARetourner= new Utilisateur(noUtilisateur, pseudo.trim(), nom.trim(), prenom.trim(), email.trim(), telephone.trim(), rue.trim(), codePostal.trim(), ville.trim(), motDePasse, credit, administrateur);
			daoUtilisateur.update(utilisateurARetourner);
			
			for (Utilisateur u : listeUtilisateurs) {
				if(noUtilisateur == u.getNoUtilisateur()) {
					u.setPseudo(pseudo.trim());
					u.setEmail(email.trim());
					u.setTelephone(telephone.trim());
					u.setRue(rue.trim());
					u.setCodePostal(codePostal.trim());
					u.setVille(ville.trim());
				}
			}
			//penser à mettre à jour la liste tampon
			return utilisateurARetourner;
		}
	
	//Sous fonctions ci-dessous à partir d'ici
	private void verifierNullite(String identifiant, BusinessException businessException) {
		if(identifiant.trim()=="") {
			businessException.ajouterErreur(CodesResultatBLL.CHAMPS_VIDE);
		}
	}
	public String hasherMotDePasse(String motDePasseClair) {
		StringBuffer hexString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(motDePasseClair.getBytes());
			byte[] digest = md.digest();
		      hexString = new StringBuffer();
		      for (int i = 0;i<digest.length;i++) {
		         hexString.append(Integer.toHexString(0xFF & digest[i]));
		      }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}
	private void verifierConformiteMotDePasse(String motDePasse, BusinessException businessException) throws BusinessException {
		// verifie le password
		final int MAX=12;
		 final int MIN_UPPERCASE=1;// nombre minimun de lettres majuscules dans le mot de passe
		 final int MIN_LOWERCASE=1;// nombre minimun de lettres minuscules dans le mot de passe
		 final int NUM_DIGITS=1;// nombre minimun de numéro dans le mot de passe
		 final int SPECIAL=1;// nombre minimun de caractères spéciaux dans le mot de passe
		 
		 int uppercaseCounter=1;// Compte le nombre de lettres majuscules dans un mot de passe
		 int lowercaseCounter=1;// Compte les lettres minuscules dans un mot de passe
		 int digitCounter=0;// Compte le nombre de digit dans le mot de passe
		 int specialCounter=0;// Compte le nombre de caractères spéciaux dans le mot de passe
		
		 for (int i=0; i < motDePasse.length(); i++ ) {
		        char c = motDePasse.charAt(i);
		        if(Character.isUpperCase(c)) 
		              uppercaseCounter++;
		        else if(Character.isLowerCase(c)) 
		              lowercaseCounter++;
		        else if(Character.isDigit(c)) 
		              digitCounter++;     
		        if(c>=33&&c<=46||c==64){
		          specialCounter++;
		         }
		 }
			             
         if (motDePasse.length() >= MAX && uppercaseCounter >= MIN_UPPERCASE 
        		 	&& lowercaseCounter >= MIN_LOWERCASE && digitCounter >= NUM_DIGITS && specialCounter >= SPECIAL) { 
         }else{
        	 businessException.ajouterErreur(CodesResultatBLL.MDP_INCORRECT);
         }
		
	}
	
	private void verifierFormatEmail(String email, BusinessException businessException) {
		if(email.matches(".+@.+\\.[a-z]+")==false) {
			businessException.ajouterErreur(CodesResultatBLL.FORMAT_EMAIL_INCORRECT);
		}
	}
	
	private void verifierTailleChamps(String champsAVerifier, int nbMax, BusinessException businessException) throws BusinessException {
		if(champsAVerifier.length()>nbMax) {
			businessException.ajouterErreur(CodesResultatBLL.SAISIE_TROP_LONGUE);
		}
		
	}

	private void verifierFormatTelephone(String telephone, BusinessException businessException) throws BusinessException {
		// test du téléphone pour 10 caractère 
		if(telephone != "") {
			if(telephone.length()!=10) {
			businessException.ajouterErreur(CodesResultatBLL.TEL_COURT);
			}
			for(int i=0; i<telephone.length();i++) {
				char tmp = telephone.charAt(i);
				if(!Character.isDigit(tmp)) {
					businessException.ajouterErreur(CodesResultatBLL.TEL_CONTIENT_CHARACTER_NON_NUMERIQUE);
				}
			}
		}	
	}
	
	private void verifierUnicitePseudo(String pseudo, BusinessException businessException) {
		for (Utilisateur u : listeUtilisateurs) {
			if(pseudo.trim().equalsIgnoreCase(u.getPseudo())){
				businessException.ajouterErreur(CodesResultatBLL.PSEUDO_PRIS);
			}
		}		
	}

	private void verifierUniciteEmail(String email, BusinessException businessException) {
		for (Utilisateur u : listeUtilisateurs) {
			if(email.trim().equalsIgnoreCase(u.getEmail())){
				businessException.ajouterErreur(CodesResultatBLL.EMAIL_DEJA_ENREGISTRER);
			}
		}
	}

	private Utilisateur verifierIdentifiants(String identifiant, String motDePasse, List<Utilisateur> listeUtilisateurs) {
		Utilisateur utilisateurARetourner=null; 
		for (Utilisateur u : listeUtilisateurs) {
			if(identifiant.equalsIgnoreCase(u.getEmail()) ||
					identifiant.equalsIgnoreCase(u.getPseudo()))
			{
				if(hasherMotDePasse(motDePasse).equals(u.getMotDePasse())) {
					utilisateurARetourner=new Utilisateur(u.getNoUtilisateur(), u.getPseudo(), u.getNom(), u.getPrenom(), u.getEmail(),
							u.getTelephone(), u.getRue(), u.getCodePostal(), u.getVille(), "", u.getCredit(), u.isAdministrateur());
				}
			}
		}
		return utilisateurARetourner;
	}
}




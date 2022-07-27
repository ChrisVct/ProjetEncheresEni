package fr.eni.projetEncheres.bll;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.websocket.Session;
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

	private void verifierNullite(String identifiant, BusinessException businessException) {
		if(identifiant.trim()=="") {
			businessException.ajouterErreur(CodesResultatBLL.CHAMPS_VIDE);
		}
		
	}
	public Utilisateur ajouterUtilisateur(String pseudo, String nom,String prenom,String email,String telephone,String rue,String codePostal,String ville,String motDePasse, String motDePasseConfirmation) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		Utilisateur utilisateurARetourner = null;
		
		// verifier la nullité (attention le téléphone peut être null)
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
        	 businessException.ajouterErreur(CodesResultatBLL.Password_INCORRECT);
 				throw businessException;
         }
			   
		// Concordance des mot de passe		
		if(!motDePasseConfirmation.equals(motDePasse) ) {
			businessException.ajouterErreur(CodesResultatBLL.MDP_PAS_IDENTIQUE);
			throw businessException;
		}
		// test du téléphone pour 10 caractère 
		if(telephone != "") {
			if(telephone.length()<10 || telephone.length()>10) {
			businessException.ajouterErreur(CodesResultatBLL.TEL_COURT);
			throw businessException;
			}
		}		
				
		// verifie l'unicité du pseudo (listeUtilisateurs)
		for (Utilisateur u : listeUtilisateurs) {
			if(pseudo.equalsIgnoreCase(u.getPseudo())){
				businessException.ajouterErreur(CodesResultatBLL.PSEUDO_PRIS);
				throw businessException;
			}
		}
				
		// verifie l'unicité de l'email et que l'email est conforme
		for (Utilisateur u : listeUtilisateurs) {
			email.matches(".+@.+\\.[a-z]+");
			if(email.equals(u.getEmail())){
				businessException.ajouterErreur(CodesResultatBLL.EMAIL_DEJA_ENREGISTRER);
				throw businessException;
			}
		}
			
		Utilisateur utilisateur =new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, hasherMotDePasse(motDePasse));
		daoUtilisateur.insert(utilisateur);
		utilisateur.setCredit(100);
		utilisateur.setAdministrateur(false);
		utilisateur.setMotDePasse("");
		
		listeUtilisateurs.add(utilisateur);
		return utilisateurARetourner;
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
	
	public void miseAJourUtilisateur(int noUtilisateur,String pseudo,String nom,String prenom,String email,String telephone,String rue,String codePostal,String ville,String motDePasse,Integer credit,boolean administrateur) throws BusinessException {
			BusinessException businessException = new BusinessException();
			
			Utilisateur utilisateur =new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville);

			this.verifierNullite(pseudo, businessException);
			
			for (Utilisateur u : listeUtilisateurs) {
			if(pseudo.equalsIgnoreCase(u.getPseudo())){
				}
			else(pseudo.equalsIgnoreCase("listeUtilisateurs")) {
					businessException.ajouterErreur(CodesResultatBLL.PSEUDO_PRIS);
					throw businessException;
				}
			}
				
			
			// rechercher dans list utilisateur dont le noUtilisateur est egal a l'utilisateur
			
			// verifie la nullité (attention le téléphone peut être null)
			
			this.verifierNullite(email, businessException);
			this.verifierNullite(rue, businessException);
			this.verifierNullite(codePostal, businessException);
			this.verifierNullite(ville, businessException);
			
			if (businessException.hasErreurs()) {
					throw businessException;
				}
	
			// Verification du numéro de telephone	
			if(telephone !="") {
				if(telephone.length()<10 || telephone.length()>10) {
				businessException.ajouterErreur(CodesResultatBLL.TEL_COURT);
				throw businessException;
					}
			}
			
			//Verification du format de l'email
			for (Utilisateur u : listeUtilisateurs) {
				email.matches(".+@.+\\.[a-z]+");
				
				if(email.equals(u.getEmail())){
					businessException.ajouterErreur(CodesResultatBLL.EMAIL_DEJA_ENREGISTRER);
					throw businessException;
				}
			}
					
			//pensez a mettre à jour la liste utilisateur tampon
			//Utilisateur utilisateur =new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
			listeUtilisateurs.update(utilisateur);
		}
	}




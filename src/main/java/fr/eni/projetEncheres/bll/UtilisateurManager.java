package fr.eni.projetEncheres.bll;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.dal.DAO;
import fr.eni.projetEncheres.dal.DAOFactory;



public class UtilisateurManager {
//	private static UtilisateurDAOJdbcImpl dao = new UtilisateurDAOJdbcImpl();
	private static DAO<Utilisateur> daoUtilisateur;
	private static UtilisateurManager instance;
	private static List<Utilisateur> listeUtilisateurs;
	
	public  UtilisateurManager() throws BusinessException {
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
			break;
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
							u.getTelephone(), u.getRue(), u.getCodePostal(), u.getVille(), u.getMotDePasse(), u.getCredit(), u.isAdministrateur());
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
	public void ajouterUtilisateur(String pseudo, String nom,String prenom,String email,String telephone,String rue,String code_postal,String ville,String motDePasse, String motDePasseConfirmation) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		
		// verifier la nullité (attention le téléphone peut être null)
		this.verifierNullite(pseudo, businessException);
		this.verifierNullite(nom, businessException);
		this.verifierNullite(prenom, businessException);
		this.verifierNullite(email, businessException);
		this.verifierNullite(rue, businessException);
		this.verifierNullite(code_postal, businessException);
		this.verifierNullite(ville, businessException);
		this.verifierNullite(motDePasse, businessException);
	
			
			if (businessException.hasErreurs()) {
					throw businessException;
					
				}
				
			// verifie le password
				
			if(motDePasse.length()<=11){
				businessException.ajouterErreur(CodesResultatBLL.MDP_COURT);
				throw businessException;
			}
	
			// Concordance des mot de passe		
			if(!motDePasseConfirmation.equals(motDePasse) ) {
				businessException.ajouterErreur(CodesResultatBLL.MDP_PAS_IDENTIQUE);
				throw businessException;
			}
			// test du téléphone pour 10 caractère 
			
			if(telephone.length()<10 || telephone.length()>10){
				businessException.ajouterErreur(CodesResultatBLL.TEL_COURT);
				throw businessException;
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
				
			
		// hasher le mot de passe --> voir la méthode ci-dessous
		UtilisateurManager uManager = new UtilisateurManager();
		Utilisateur utilisateur =new Utilisateur(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, hasherMotDePasse(motDePasse));
		daoUtilisateur.insert(utilisateur);
		
		//si insert ok, ajouter l'utilisateur à la liste 'listeUtilisateurs'
		
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

	
}



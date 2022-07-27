package fr.eni.projetEncheres;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.bo.Enchere;
import fr.eni.projetEncheres.dal.jdbc.EnchereDAOJdbcImpl;

@WebServlet("/ServletTestChristophe")
public class ServletTestChristophe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		pour obtenir le mot de passe hashé à copier en BDD
		//System.out.println(hasherMotDePasse("admin"));
		EnchereDAOJdbcImpl dao = new EnchereDAOJdbcImpl();
		List<Enchere> liste = new ArrayList<>();
		
		try {
			liste=dao.selectAllEcoById(1);
			System.out.println("la liste eco by id est : " +liste);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try {
//			EnchereManager eManager = EnchereManager.getInstance();
//			List<Enchere> toto = eManager.afficherEncheresAvecParametres("cH", "Toutes");
//			System.out.println(toto);
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hexString.toString();
	}
}

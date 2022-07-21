package fr.eni.projetEncheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bll.UtilisateurManager;
import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.messages.LecteurMessage;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		String pseudo = request.getParameter("pseudo");
		String nom= request.getParameter("nom");
		String prenom= request.getParameter("prenom");
		String email= request.getParameter("email");
		String telephone= request.getParameter("telephone");
		String rue= request.getParameter("rue");
		String code_postal= request.getParameter("codePostal");
		String ville= request.getParameter("ville");
		String mot_de_passe= request.getParameter("motDePasse");
		Utilisateur ajouterUtilisateur=null;
		UtilisateurManager UManager = new UtilisateurManager();
		
		try {
			 UManager.ajouterUtilisateur(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe);
			
//			if(ajouterUtilisateur!=null) {
//				System.out.println(ajouterUtilisateur);
//		//reste à monter l'utilisateur en session
//				request.getSession().setAttribute(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe);
//				rd=request.getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp");
			} catch (BusinessException e) {
				//List<String> msgErr = new ArrayList<>();
				
//				for(int i : ((BusinessException) e).getListeCodesErreur()) {
//					msgErr.add(LecteurMessage.getMessageErreur(i));
//				}
//				request.setAttribute("listeCodesErreur", msgErr);
//				rd=request.getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp");	
			}

		doGet(request, response);
	

}
}

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
	RequestDispatcher rd= request.getRequestDispatcher("WEB-INF/JSP/Inscription.jsp");
	rd.forward(request, response);
	}	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("j'arrive dans le do post");
		//RequestDispatcher rd = null;
		String pseudo = request.getParameter("pseudo");
		String nom= request.getParameter("nom");
		String prenom= request.getParameter("prenom");
		String email= request.getParameter("email");
		String telephone= request.getParameter("telephone");
		String rue= request.getParameter("rue");
		String code_postal= request.getParameter("codePostal");
		String ville= request.getParameter("ville");
		String mot_de_passe= request.getParameter("motDePasse");
		
		//System.out.println("j'arrive dans le do post après");
		try {
			UtilisateurManager UManager =  UtilisateurManager.getInstance();
			 UManager.ajouterUtilisateur(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe);
			//System.out.println("je passe par le try ");
			
			} catch (BusinessException e) {
				e.printStackTrace();	
			}

		//System.out.println("traitement fini");
		//rd.forward(request, response);

}
}

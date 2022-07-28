package fr.eni.projetEncheres.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bll.ArticleManager;
import fr.eni.projetEncheres.bo.Utilisateur;
@WebServlet("/ServletVendreNouvelArticle")
public class ServletVendreNouvelArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/VendreArticle.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomArticle = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		String libelle = request.getParameter("libelle");
		int prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
		LocalDate dateDebutEncheres = LocalDate.parse(request.getParameter("dateDebutEncheres"));
		LocalDate dateFinEncheres = LocalDate.parse(request.getParameter("dateFinEncheres"));
		String rueRetrait = request.getParameter("rueRetrait");
		String codePostalRetrait = request.getParameter("codePostalRetrait");
		String villeRetrait = request.getParameter("villeRetrait");
		
		int noUtilisateur = ((Utilisateur)request.getSession().getAttribute("utilisateur_connecte")).getNoUtilisateur();
		
		//envoyer en BLL
		try {
			ArticleManager aManager=ArticleManager.getInstance();
			aManager.ajouterArticle(nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, libelle,
					noUtilisateur , rueRetrait, codePostalRetrait, villeRetrait);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}
}

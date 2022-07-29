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
import fr.eni.projetEncheres.bll.EnchereManager;
import fr.eni.projetEncheres.bo.Enchere;
import fr.eni.projetEncheres.bo.Utilisateur;
@WebServlet("/ServletAccueilEncheresConnecte")
public class ServletAccueilEncheresConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/JSP/AccueilEncheresConnecte.jsp");
		if (request.getAttribute("nouvel_article_ok")!=null) {
			request.setAttribute("nouvel_article_ok", request.getAttribute("nouvel_article_ok"));
		}
		EnchereManager eManager;
		try {
			eManager = EnchereManager.getInstance();
			List<Enchere> listeEncheres = eManager.afficherEncheresOuvertes();
			request.setAttribute("listeEncheres", listeEncheres);
		} catch (BusinessException e) {
			e.printStackTrace();
		  }
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getAttribute("nouvel_article_ok")!=null) {
			request.setAttribute("nouvel_article_ok", request.getAttribute("nouvel_article_ok"));
		}
		if(request.getAttribute("connexion")==null && request.getAttribute("nouvel_article_ok")==null) {

			int noUtilisateur =  ((Utilisateur)request.getSession().getAttribute("utilisateur_connecte")).getNoUtilisateur();
			List<Enchere> listeEncheres=new ArrayList<>();
			try {
				EnchereManager eManager = EnchereManager.getInstance();
				if(request.getParameter("encheresOuvertes")!=null) {
					List<Enchere> tmp = eManager.afficherEncheresOuvertes();
					listeEncheres.addAll(tmp);
				}
				if(request.getParameter("mesEncheresEnCours")!=null) {
					if(request.getParameter("encheresOuvertes")==null) {
						List<Enchere> tmp = eManager.afficherAchatEnCours(noUtilisateur);
						listeEncheres.addAll(tmp);
					}
				}
				if(request.getParameter("mesAchatsRemportes")!=null) {
					List<Enchere> tmp = eManager.afficherAchatsRemportes(noUtilisateur);
					listeEncheres.addAll(tmp);
				}
				String nomArticle = request.getParameter("nomArticle");
				String libelle = request.getParameter("libelle");
				listeEncheres = eManager.afficherEncheresAvecParametres(nomArticle, libelle, listeEncheres);
				request.setAttribute("listeEncheres", listeEncheres);
			} catch (BusinessException e) {
				e.printStackTrace();
			  }
			RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/JSP/AccueilEncheresConnecte.jsp");
			rd.forward(request, response);
		}else {
			doGet(request, response);
		}
			
	}
}
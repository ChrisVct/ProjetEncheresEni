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


/**
 * Servlet implementation class ServletAccueilEncheres
 */
@WebServlet("/ServletAccueilEncheres")
public class ServletAccueilEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd =null;
		if(request.getParameter("deconnexion")!=null) {
			request.getSession().invalidate();
		}
		if(request.getSession().getAttribute("utilisateur_connecte")!=null) {
			rd=request.getRequestDispatcher("ServletAccueilEncheresConnecte");
			rd.forward(request, response);
		}
		
		List<Enchere> listeEncheres= null;
		try {
			EnchereManager eManager = EnchereManager.getInstance();
			listeEncheres = eManager.afficherEncheresOuvertes();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("listeEncheres", listeEncheres);
		rd = request.getRequestDispatcher("/WEB-INF/JSP/AccueilEncheres.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getAttribute("connexion")==null) {
			String nomArticle = request.getParameter("nomArticle");
			String libelle = request.getParameter("libelle");
			List<Enchere> listeEncheres = new ArrayList<>();
			List<Enchere> listeNulle = null;
			try {
				EnchereManager eManager = EnchereManager.getInstance();
				listeEncheres = eManager.afficherEncheresAvecParametres(nomArticle, libelle, listeNulle);
				request.setAttribute("listeEncheres", listeEncheres);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/AccueilEncheres.jsp");
			rd.forward(request, response);
			
		}else if(request.getAttribute("connexion")!=null){
			doGet(request, response);
		}
	}
}

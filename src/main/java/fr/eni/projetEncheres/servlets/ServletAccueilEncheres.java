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
		
		if(request.getParameter("deconnexion")!=null) {
			request.getSession().invalidate();
		}
		
		List<Enchere> listeEncheres= null;
		try {
			EnchereManager eManager = EnchereManager.getInstance();
			listeEncheres = eManager.afficherEncheresEnCours();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(listeEncheres);
		request.setAttribute("listeEncheres", listeEncheres);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/AccueilEncheres.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getAttribute("connexion")==null) {
			//Récuperer les données du formulaire
			String nomArticle = request.getParameter("nomArticle");
			String libelle = request.getParameter("libelle");
			List<Enchere> listeEncheres = new ArrayList<>();
			//Envoi des données à la BLL
			try {
				EnchereManager eManager = EnchereManager.getInstance();
				//déclarer une liste(tableau) (listeEnchere) + (afficherEncheresAvecParametres)
				listeEncheres = eManager.afficherEncheresAvecParametres(nomArticle, libelle);
				//Retourner la liste à la JSP
				request.setAttribute("listeEncheres", listeEncheres);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/AccueilEncheres.jsp");
			rd.forward(request, response);
			
		}else if(request.getAttribute("connexion")!=null){
			doGet(request, response);
		}
	}
}

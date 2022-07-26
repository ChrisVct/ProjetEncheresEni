package fr.eni.projetEncheres.servlets;

import java.io.IOException;
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
import fr.eni.projetEncheres.dal.DAOFactory;

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
		System.out.println(listeEncheres);
		request.setAttribute("listeEncheres", listeEncheres);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/AccueilEncheres.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Récuperer les données du formulaire
		String nomArticle = request.getParameter("nomArticle");
		String libelle = request.getParameter("libelle");
		System.out.println(nomArticle + libelle);
		
		//Envoi des données à la BLL
		//déclarer une liste(tableau) (listeEnchere) + (afficherEncheresAvecParametres)
		//puis la liste retourner à la JSP
		
		
		
		request.setAttribute("listeEncheres", listeEncheres);
						
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/AccueilEncheres.jsp");
		rd.forward(request, response);
		
	}
}

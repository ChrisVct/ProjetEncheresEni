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
			listeEncheres = eManager.afficherEncheresOuvertes();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(listeEncheres);
		request.setAttribute("listeEncheres", listeEncheres);
		System.out.println(request.getSession().getAttribute("utilisateur_connecte"));
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/AccueilEncheres.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getAttribute("connexion")==null) {
			//Récuperer les données du formulaire
			String nomArticle = request.getParameter("nomArticle");
			String libelle = request.getParameter("libelle");
			List<Enchere> listeEncheres = new ArrayList<>();
			List<Enchere> listeNulle = null;
			//Envoi des données à la BLL
			try {
				EnchereManager eManager = EnchereManager.getInstance();
				//déclarer une liste(tableau) (listeEnchere) + (afficherEncheresAvecParametres)
				listeEncheres = eManager.afficherEncheresAvecParametres(nomArticle, libelle, listeNulle);
				//Retourner la liste à la JSP
				request.setAttribute("listeEncheres", listeEncheres);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/AccueilEncheres.jsp");
			rd.forward(request, response);
			
		}else if(request.getAttribute("connexion")!=null){
			System.out.println("je passe dans e esleif");
			doGet(request, response);
		}
	
		//int ??noUtilisateur?? = ((Utilisateur)request.getSession().getAttribute("utilisateur_connecte")).getNoUtilisateur();
		//si tu recois ... tu fais ...
//		getinstance
//		if(request.getParameter("enchereouverte") !null
//				emanager.afficherEncheresOuvertes()
//				
//				if(request.getParameter("ecobyID") !null
//						if(request.getParameter("ecobyID")
//						emanager.afficherAchatencours()
		
		//recuperer le cas ou mes encheres en cours sont cochés => lancer fonction en bll
				
		
	}
}

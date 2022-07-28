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
		System.out.println("Arrivé dans le doget");
		EnchereManager eManager;
		try {
			eManager = EnchereManager.getInstance();
			List<Enchere> listeEncheres = eManager.afficherEncheresOuvertes();
			request.setAttribute("listeEncheres", listeEncheres);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getAttribute("connexion")!=null) {
			doGet(request, response);
		}
		System.out.println("juste après le if");
		List<Enchere> listeEncheres=new ArrayList<>();
		int noUtilisateur =  ((Utilisateur)request.getSession().getAttribute("utilisateur_connecte")).getNoUtilisateur();
		try {
			EnchereManager eManager = EnchereManager.getInstance();
			System.out.println("--"+request.getParameter("encheresOuvertes")+"--");
		//je créé une liste à retourner en fonction des checkbox cochées
			if(request.getParameter("encheresOuvertes")!=null) {
				List<Enchere> tmp = eManager.afficherEncheresOuvertes();
				listeEncheres.addAll(tmp);
				System.out.println("premier if");
			}
			if(request.getParameter("mesEncheresEnCours")!=null) {
				if(request.getParameter("encheresOuvertes")==null) {
					System.out.println("dans le if mesEncheressEnCours");
					List<Enchere> tmp = eManager.afficherAchatEnCours(noUtilisateur);
					listeEncheres.addAll(tmp);
				}
			}
			if(request.getParameter("mesAchatsRemportes")!=null) {
//				List<Enchere> tmp = eManager.afficherMesAchatsRemportes(id);
//				listeEncheres.addAll(tmp);
			}
		//si un nom d'article et/ou un libelle est précisé, je filtre cette liste à retourner
			
			String nomArticle = request.getParameter("nomArticle");
			String libelle = request.getParameter("libelle");
			//Envoi des données à la BLL
			listeEncheres = eManager.afficherEncheresAvecParametres(nomArticle, libelle, listeEncheres);
			request.setAttribute("listeEncheres", listeEncheres);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/JSP/AccueilEncheresConnecte.jsp");
		rd.forward(request, response);
	}
}

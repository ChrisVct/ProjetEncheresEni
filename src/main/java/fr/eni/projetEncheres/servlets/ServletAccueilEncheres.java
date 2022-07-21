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
import fr.eni.projetEncheres.bll.ArticleManager;
import fr.eni.projetEncheres.bo.Article;

/**
 * Servlet implementation class ServletAccueilEncheres
 */
@WebServlet("/ServletAccueilEncheres")
public class ServletAccueilEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleManager aManager =  new ArticleManager();
		
		List<Article> listeArticles = null;
		
		try {
			listeArticles = aManager.recupererListeArticles();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(listeArticles);
		request.setAttribute("Accueil", listeArticles);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/AccueilEncheres.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

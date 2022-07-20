package fr.eni.projetEncheres.servlets;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.bll.UtilisateurManager;

@WebServlet("/ServletConnectionUtilisateur")
public class ServletConnectionUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ConnectionUtilisateur.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiant = request.getParameter("identifiant");
		String motDePasse= request.getParameter("motDePasse");
		UtilisateurManager UManager = new UtilisateurManager();
		UManager.verifierConnection(identifiant, motDePasse);
		
		doGet(request, response);
	}

}

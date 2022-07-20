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
import fr.eni.projetEncheres.messages.LecteurMessage;

@WebServlet("/ServletConnectionUtilisateur")
public class ServletConnectionUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ConnectionUtilisateur.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String identifiant = request.getParameter("identifiant");
		String motDePasse= request.getParameter("motDePasse");
		boolean connexionOK = false;
		UtilisateurManager UManager = new UtilisateurManager();
		try {
			connexionOK = UManager.verifierConnection(identifiant, motDePasse);
			if(connexionOK) {
				rd=request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
			}
		} catch (BusinessException e) {
			List<String> msgErr = new ArrayList<>();
			
			for(int i : ((BusinessException) e).getListeCodesErreur()) {
				msgErr.add(LecteurMessage.getMessageErreur(i));
			}
			request.setAttribute("listeCodesErreur", msgErr);
			rd=request.getRequestDispatcher("/WEB-INF/JSP/ConnectionUtilisateur.jsp");
		}
		
		rd.forward(request, response);
	}


}

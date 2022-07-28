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
import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.messages.LecteurMessage;

@WebServlet("/ServletConnexionUtilisateur")
public class ServletConnexionUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=null;
		if(request.getSession().getAttribute("utilisateur_connecte")!=null) {
			rd=request.getRequestDispatcher("ServletAccueilEncheresConnecte");
		}else {
			rd = request.getRequestDispatcher("/WEB-INF/JSP/ConnexionUtilisateur.jsp");
		}
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String identifiant = request.getParameter("identifiant");
		String motDePasse= request.getParameter("motDePasse");
		Utilisateur utilisateurATester=null;
		try {
			UtilisateurManager UManager = UtilisateurManager.getInstance();
			utilisateurATester = UManager.verifierConnection(identifiant, motDePasse);
			if(utilisateurATester!=null) {
				request.getSession().setAttribute("utilisateur_connecte", utilisateurATester);
				request.setAttribute("connexion", "ok");
				rd=request.getRequestDispatcher("ServletAccueilEncheresConnecte");
			}
		} catch (BusinessException e) {
			List<String> msgErr = new ArrayList<>();
			
			for(int i : ((BusinessException) e).getListeCodesErreur()) {
				msgErr.add(LecteurMessage.getMessageErreur(i));
			}
			request.setAttribute("listeCodesErreur", msgErr);
			rd=request.getRequestDispatcher("/WEB-INF/JSP/ConnexionUtilisateur.jsp");
		}
		
		rd.forward(request, response);
	}


}

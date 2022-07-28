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

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=null;
		if(request.getSession().getAttribute("utilisateur_connecte")!=null) {
			rd=request.getRequestDispatcher("ServletAccueilEncheresConnecte");
		}else {
			rd = request.getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp");
		}
		rd.forward(request, response);
	}	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudo = request.getParameter("pseudo");
		String nom= request.getParameter("nom");
		String prenom= request.getParameter("prenom");
		String email= request.getParameter("email");
		String telephone= request.getParameter("telephone");
		String rue= request.getParameter("rue");
		String code_postal= request.getParameter("codePostal");
		String ville= request.getParameter("ville");
		String mot_de_passe= request.getParameter("motDePasse");
		String motDePasseConfirmation=request.getParameter("motDePasseConfirmation");
		
		RequestDispatcher rd = null;
		try {
			UtilisateurManager UManager =  UtilisateurManager.getInstance();
			Utilisateur utilisateurConnecte = UManager.ajouterUtilisateur(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,motDePasseConfirmation);
			utilisateurConnecte.setMotDePasse("");
			request.getSession().setAttribute("utilisateur_connecte", utilisateurConnecte);
			request.setAttribute("connexion", "ok");
			rd=request.getRequestDispatcher("ServletAccueilEncheresConnecte");
		} catch (BusinessException e) {
			List<String> msgErr = new ArrayList<>();
			
			for(int i : ((BusinessException) e).getListeCodesErreur()) {
				msgErr.add(LecteurMessage.getMessageErreur(i));
			}
			request.setAttribute("listeCodesErreur", msgErr);
			rd=request.getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp");
			e.printStackTrace();	
		}

		rd.forward(request, response);

	}
}

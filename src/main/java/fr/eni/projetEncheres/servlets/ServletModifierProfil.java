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
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet("/ServletModifierProfil")
public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/JSP/ModifierProfil.jsp");
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String pseudo = request.getParameter("pseudo");
		String email= request.getParameter("email");
		String telephone= request.getParameter("telephone");
		String rue= request.getParameter("rue");
		String codePostal= request.getParameter("codePostal");
		String ville= request.getParameter("ville");
		
		String nom = ((Utilisateur)request.getSession().getAttribute("utilisateur_connecte")).getNom();
		String prenom = ((Utilisateur)request.getSession().getAttribute("utilisateur_connecte")).getPrenom();
		String motDePasse = ((Utilisateur)request.getSession().getAttribute("utilisateur_connecte")).getMotDePasse();
		int noUtilisateur = ((Utilisateur)request.getSession().getAttribute("utilisateur_connecte")).getNoUtilisateur();
		int credit = ((Utilisateur)request.getSession().getAttribute("utilisateur_connecte")).getCredit();
		boolean administrateur = ((Utilisateur)request.getSession().getAttribute("utilisateur_connecte")).isAdministrateur();
		
		//recupere nom prénom credit admin 
		
		RequestDispatcher rd = null;
		Utilisateur tmpUtilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
		
		try {
			UtilisateurManager uManager=UtilisateurManager.getInstance();
			uManager.miseAJourUtilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
			request.setAttribute("utilisateurAAfficher", tmpUtilisateur);
			request.setAttribute("succes", "Votre profil a bien était mis à jour");
			rd=request.getRequestDispatcher("ServletProfil");
		} catch (BusinessException e) {
			List<String> msgErr = new ArrayList<>();
			
			for(int i : ((BusinessException) e).getListeCodesErreur()) {
				msgErr.add(LecteurMessage.getMessageErreur(i));
			}
			request.setAttribute("listeCodesErreur", msgErr);
			rd=request.getRequestDispatcher("/WEB-INF/JSP/ModifierProfil.jsp");
			e.printStackTrace();	
		}
	
		rd.forward(request, response);
	}

}

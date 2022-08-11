package fr.eni.projetEncheres.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.eni.projetEncheres.BusinessException;
import fr.eni.projetEncheres.bll.ArticleManager;
import fr.eni.projetEncheres.bo.Utilisateur;
import fr.eni.projetEncheres.messages.LecteurMessage;
@WebServlet("/ServletVendreNouvelArticle")
@MultipartConfig(maxFileSize = 16177215)
public class ServletVendreNouvelArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/VendreArticle.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String nomArticle = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		String libelle = request.getParameter("libelle");
		System.out.println("request.getParameter(\"nomArticle\") : "+request.getParameter("nomArticle"));
		int prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
		LocalDate dateDebutEncheres = LocalDate.parse(request.getParameter("dateDebutEncheres"));
		LocalDate dateFinEncheres = LocalDate.parse(request.getParameter("dateFinEncheres"));
		String rueRetrait = request.getParameter("rueRetrait");
		String codePostalRetrait = request.getParameter("codePostalRetrait");
		String villeRetrait = request.getParameter("villeRetrait");
		
		InputStream inputStream = null;
		Part filePart = request.getPart("cheminImage");
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        }
		
		int noUtilisateur = ((Utilisateur)request.getSession().getAttribute("utilisateur_connecte")).getNoUtilisateur();
		
		try {
			ArticleManager aManager=ArticleManager.getInstance();
			aManager.ajouterArticle(nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, libelle,
					noUtilisateur , rueRetrait, codePostalRetrait, villeRetrait, inputStream);
			request.setAttribute("nouvel_article_ok", nomArticle);
			rd=request.getRequestDispatcher("ServletAccueilEncheresConnecte");
		} catch (BusinessException e) {
			e.printStackTrace();

			List<String> msgErr = new ArrayList<>();
			for(int i : ((BusinessException) e).getListeCodesErreur()) {
				msgErr.add(LecteurMessage.getMessageErreur(i));
			}
			request.setAttribute("listeCodesErreur", msgErr);
			rd=request.getRequestDispatcher("/WEB-INF/JSP/VendreArticle.jsp");
		}
		rd.forward(request, response);
	}
}

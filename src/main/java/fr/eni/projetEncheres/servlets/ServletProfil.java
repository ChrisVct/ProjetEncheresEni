package fr.eni.projetEncheres.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
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


/**
 * Servlet implementation class ServletProfil
 */
@WebServlet("/co/ServletProfil")
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/JSP/AfficherProfil.jsp");
		
		if(request.getParameter("pseudoAAfficher")!=null) {
			String pseudoAAfficher=request.getParameter("pseudoAAfficher");
			
			try {
				UtilisateurManager uManager=UtilisateurManager.getInstance();
				Utilisateur utilisateurAAfficher = uManager.recupererInfoProfil(pseudoAAfficher);
				request.setAttribute("utilisateurAAfficher", utilisateurAAfficher);
				
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
package fr.eni.projetEncheres.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.bo.Utilisateur;

@WebFilter(
		dispatcherTypes = {
					DispatcherType.REQUEST,
					
		},		
		urlPatterns = {
				"/ServletProfil",
<<<<<<< HEAD
				
=======
>>>>>>> branch 'main' of https://github.com/ChrisVct/ProjetEncheresEni.git
				"/ServletModifierProfil",
				"/ServletVendreNouvelArticle"
//				,"/ServletInscription"
				}
		)

public class FilterRedirigeantUtilisateurNonConnecte extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
				//A supprimer plus tard, sert à simuler l'utilisateur connecté
//				Utilisateur moi = new Utilisateur("cricri", "vichit", "christophe", "mail@mail.com", "0606060606", "rue", "14000", "caen");
//				httpRequest.getSession().setAttribute("utilisateur_connecte", moi);
		
		if(httpRequest.getSession().getAttribute("utilisateur_connecte")==null) {
			RequestDispatcher rd = httpRequest.getRequestDispatcher("/ServletConnexionUtilisateur");
			rd.forward(httpRequest, httpResponse);
		}else {
			chain.doFilter(request, response);
		}

	}

}

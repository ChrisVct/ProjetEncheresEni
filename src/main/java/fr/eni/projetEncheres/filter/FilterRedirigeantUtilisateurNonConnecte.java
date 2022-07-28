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
					DispatcherType.REQUEST
		},		
		urlPatterns = {
				"/ServletProfil",
				"/ServletModifierProfil",
				"/ServletVendreNouvelArticle",
				"/ServletAccueilEncheresConnecte"
				}
		)

public class FilterRedirigeantUtilisateurNonConnecte extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if(httpRequest.getSession().getAttribute("utilisateur_connecte")==null) {
			RequestDispatcher rd = httpRequest.getRequestDispatcher("/ServletConnexionUtilisateur");
			rd.forward(httpRequest, httpResponse);
		}else {
			chain.doFilter(request, response);
		}
	}

}

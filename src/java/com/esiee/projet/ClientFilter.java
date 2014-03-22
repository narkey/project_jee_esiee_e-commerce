package com.esiee.projet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * filtre pour les gens pas connectés (inscription, livre)
 * @author Remi
 */
public class ClientFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		/* Cast des objets request et response */
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		/* Non-filtrage vers la page d'inscription */
		String chemin = request.getRequestURI().substring( request.getContextPath().length() );
		
		if ( chemin.startsWith( "/Connexion" ) 
				|| chemin.startsWith( "/Inscription" ) 
				|| chemin.startsWith( "/Index" ) 
				|| chemin.startsWith( "/Catalogue" ) 
				|| chemin.startsWith( "/Mangas" ) 
				|| chemin.startsWith( "/Comics" ) 
				|| chemin.startsWith( "/BD" ) 
				|| chemin.startsWith( "/img" ) 
				|| chemin.startsWith( "/index.jsp" ) 
				|| chemin.startsWith( "/header.jsp" ) 
				|| chemin.startsWith( "/bootstrap" ))
		{
			chain.doFilter( request, response );
			return;		
		}
       
		/* Recuperation de la session depuis la requete */
		HttpSession session = request.getSession();
		/**
		 * Si l'objet utilisateur n'existe pas dans la session en cours, alors
		 * l'utilisateur n'est pas connect&eacute;.
		 */
	
		if ( session.getAttribute( "sessionUtilisateur" ) == null ) {
			/* Redirection vers la page publique */
			request.getRequestDispatcher( "/Connexion" ).forward( request, response );
			
		} else {
				/* Affichage de la page restreinte */
				chain.doFilter( request, response );
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}

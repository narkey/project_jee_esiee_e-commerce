/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esiee.projet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author tithf
 */

public class ControleurServlet extends HttpServlet {
    
	private DAO dao;
	
	/**
	 * Methode permettant d'instancier les diff&eacute;rents DAO utilis&eacute;s par cette
	 * servlet pour acceder &agrave; la base de donn&eacute;es
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		/* R�cup�ration d'une instance de notre DAO Utilisateur */
		this.dao = new DAO("tutu", "tutu", "project_jee");
	}
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	switch(request.getServletPath()) {
    	
    	case "/Connexion" : 
            System.out.println("pouetbrvgftrbfgbfg0");    
            this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
    		break;
    	
    	case "/Index" : this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			break;
    	
    	case "/Inscription" : this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
		break;
    	case "/Catalogue" : 
            ArrayList<Livre> listBook= new ArrayList<Livre>();
            System.out.println(request.getParameter("cat"));
            String category = request.getParameter("cat");
            if(category.equals("manga")||category.equals("bd")||category.equals("comic"))
                {
                    listBook = dao.getCategoryBook(request.getParameter("cat"));
                    System.out.println("ya une categorie");
                }
               
            
            else
            {
                listBook = dao.getAllBook();
                System.out.println("ya pas une categorie");
            }
            for(Livre object: listBook){
              System.out.println(object.toString());
            }
            request.setAttribute("list_book", listBook);
            this.getServletContext().getRequestDispatcher("/WEB-INF/catalogue.jsp").forward(request, response);
            break;            
    	}
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           switch(request.getParameter("origin"))
           {
               case "connect":    
                   
                   /*String email = request.getParameter("email");
                   Utilisateur user = dao.getUtilisateur(email);
                   request.setAttribute( "user", user );
                   if(user!=null)
                        this.getServletContext().getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request, response);
                   else
                       this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                   break;*/

            	/* Preparation de l'objet formulaire */
           		ConnexionForm form = new ConnexionForm( dao );
           		
           		/* Appel au traitement et a la validation de la requete, et recuperation du bean en resultant */
           		Utilisateur user = form.connecterUtilisateur( request );

           		/* Recuperation de la session depuis la requete */
           		HttpSession session = request.getSession();
           		
           		/* Si aucune erreur de validation, alors ajout du bean Utilisateur a la session, sinon suppression du bean de la session */
           		if (form.getErreurs().isEmpty()) {
           			session.setAttribute( "utilisateur", user );
           			response.sendRedirect( this.getServletContext().getContextPath() + "/index.jsp");
           			
           		} else {
           			session.setAttribute( "sessionUtilisateur", null );
           			
           			/* Stockage du formulaire et du bean dans l'objet request */
           			request.setAttribute( "form", form );
           			request.setAttribute( "utilisateur", user );

           			/* Reaffichage de la page de login */
           			this.getServletContext().getRequestDispatcher( "/WEB-INF/connexion.jsp" ).forward( request, response );
           		}
           		break;
               case "register":
                    Utilisateur utilisateur = new Utilisateur(
                                    request.getParameter("nom"),
                                    request.getParameter("prenom"),
                                    request.getParameter("mdp"),
                                    request.getParameter("email"),
                                    request.getParameter("adresse"));   
                    dao.insert_user(utilisateur); 
                    this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                   break;
                                      
                   
               default:
                   this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                  break;
           }
    }
}

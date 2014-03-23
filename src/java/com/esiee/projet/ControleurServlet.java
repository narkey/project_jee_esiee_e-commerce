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
		/* Recuperation d'une instance de notre DAO Utilisateur */
		this.dao = new DAO("tutu", "tutu", "project_jee");
	}
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	switch(request.getServletPath()) {
    	
    	case "/Connexion" : this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
    		break;
    	
    	case "/Index" : this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			break;
    	
    	case "/Inscription" : this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
		break;
    	case "/Catalogue" : 
            ArrayList<Livre> listBook= new ArrayList<Livre>();
            String category;
            if(request.getParameter("cat")==null)
                category = "all";
            else
                category = request.getParameter("cat");
            if(category.equals("manga")||category.equals("bd")||category.equals("comic"))
                {
                    listBook = dao.getCategoryBook(request.getParameter("cat"));
                }
            else
            {
                listBook = dao.getAllBook();
            }
            /*for(Livre object: listBook){
              System.out.println(object.toString());
            }*/
            request.setAttribute("list_book", listBook);
            this.getServletContext().getRequestDispatcher("/WEB-INF/catalogue.jsp").forward(request, response);
            break;		
    	case "/Edition" : this.getServletContext().getRequestDispatcher("/WEB-INF/editprofil.jsp").forward(request, response);
		break;
		
    	case "/Deconnexion" :
    		
    		/* Recuperation et destruction de la session en cours */
    		HttpSession session = request.getSession();
    		session.invalidate();
    		
    		/* Redirection vers la page de connexion */
    		response.sendRedirect( this.getServletContext().getContextPath() + "/Index");
    		break;
    	
    	case "/AjouterRef" : this.getServletContext().getRequestDispatcher("/WEB-INF/test.jsp").forward(request, response);
			break;
        case "/Confirmation"  :
            /* Recuperation de la session depuis la requete */
            /*HttpSession session2 = request.getSession();
            Utilisateur user = (Utilisateur)session2.getAttribute( "sessionUtilisateur");
            request.setAttribute( "user", user );*/
            this.getServletContext().getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request, response);
            break;
        case "/VoirPanier" :
            /*
            HttpSession session2 = request.getSession();
            Utilisateur user = (Utilisateur)session2.getAttribute( "sessionUtilisateur");
            ArrayList<Livre> cartBooks = new ArrayList<Livre>();
            cartBooks = dao.getChartBook(user.getEmail());
            System.out.println("ici = "+user.getEmail());
            for(Livre object : cartBooks)
            {
                System.out.println("la : " + object.toString());
            }
            request.setAttribute("cartBooks", cartBooks);
            this.getServletContext().getRequestDispatcher("/WEB-INF/achat.jsp").forward(request, response);
            */
            
            break;
	
    	}
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           switch(request.getParameter("origin"))
           {
               case "connect":    
                   
                /* Preparation de l'objet formulaire */
           		ConnexionForm form = new ConnexionForm( dao );
           		
           		/* Appel au traitement et a la validation de la requete, et recuperation du bean en resultant */
           		Utilisateur user = form.connecterUtilisateur( request );

           		/* Recuperation de la session depuis la requete */
           		HttpSession session = request.getSession();
           		
           		/* Si aucune erreur de validation, alors ajout du bean Utilisateur a la session, sinon suppression du bean de la session */
           		if (form.getErreurs().isEmpty()) {
           			session.setAttribute( "sessionUtilisateur", user );
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
            	   /* Preparation de l'objet formulaire */
            	   InscriptionForm form_inscription = new InscriptionForm( dao );

            	   /* Appel au traitement et a la validation de la requete, et recuperation du bean en resultant */
            	   Utilisateur utilisateur = form_inscription.inscrireUtilisateur( request );

            	   /* Stockage du formulaire et du bean dans l'objet request */
            	   request.setAttribute( "form", form_inscription );
            	   request.setAttribute( "utilisateur", utilisateur );
            	   if(!form_inscription.getErreurs().isEmpty())
            		   /* Affichage de la page d'inscription */
            		   this.getServletContext().getRequestDispatcher( "/WEB-INF/inscription.jsp" ).forward( request, response );
            	   else
            		   this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
                   break;
                   
                   
               case "bookRegister":
                
                   BookRegisterForm form_book = new BookRegisterForm(dao);
                   
                   Livre livre = form_book.enregistrerBook(request );

            	   /* Stockage du formulaire et du bean dans l'objet request */
            	   request.setAttribute( "form", form_book );
            	   request.setAttribute( "livre", livre );
                   
            	   if(!form_book.getErreurs().isEmpty())
            		   /* Affichage de la page d'inscription */
            		   this.getServletContext().getRequestDispatcher( "/WEB-INF/bookregister.jsp" ).forward( request, response );
            	   else
            		   this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
                   break;
                   
                    

                   
               case "edit_profil":
            	   
            	   /* Preparation de l'objet formulaire */
              		EditProfilForm edit_form = new EditProfilForm( dao );
              		
              		/* Appel au traitement et a la validation de la requete, et recuperation du bean en resultant */
              		Utilisateur user_edit = edit_form.modifierUtilisateur( request );

              		/* Recuperation de la session depuis la requete */
              		HttpSession session2 = request.getSession();
              		
              		/* Si aucune erreur de validation, alors ajout du bean Utilisateur a la session, sinon suppression du bean de la session */
               		if (edit_form.getErreurs().isEmpty()) {
               			session2.setAttribute( "sessionUtilisateur", null );
               			session2.setAttribute( "sessionUtilisateur", user_edit );
               			response.sendRedirect( this.getServletContext().getContextPath() + "/index.jsp");
               		}
               		else {
               			/* Stockage du formulaire et du bean dans l'objet request */
               			request.setAttribute( "form", edit_form );
               			request.setAttribute( "utilisateur", user_edit );

               			/* Reaffichage de la page d'�dition */
               			this.getServletContext().getRequestDispatcher( "/WEB-INF/editprofil.jsp" ).forward( request, response );
               		}
               		
            	   break;
               case "catalogue":
                   /* Recuperation de la session depuis la requete */
           		HttpSession session_book = request.getSession();
                        Commandes cart_book = (Commandes)session_book.getAttribute("cart_book");
                        int book_id = Integer.parseInt(request.getParameter("book_id"));
                        int quantity = 1; 
                        //Utilisateur user = (Utilisateur)session_book.getAttribute("sessionUtilisateur");
                        System.out.println("book_id : "+request.getParameter("book_id"));
                        System.out.println("cart_book : "+cart_book);
                   if(cart_book==null)
                   {
                       cart_book = new Commandes();
                       session_book .setAttribute("cart_book", cart_book );
                   }
                       
                   cart_book.addCommand(book_id, quantity);
                   for(int i : cart_book.getAllbooks().keySet())
                   {
                       System.out.println("valeur de la clé" + i);
                   }
                   
                   response.sendRedirect("Catalogue");
                   break;
               default:
                   this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                  break;
           }
    }
}

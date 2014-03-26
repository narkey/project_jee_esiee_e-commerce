/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esiee.projet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
    	private ArrayList<Livre> alBooks;	

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
                alBooks = dao.getAllBook();
	}
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur)session.getAttribute( "sessionUtilisateur");
         Commandes cart_book;
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
    		
    		session.invalidate();
    		
    		/* Redirection vers la page de connexion */
    		response.sendRedirect( this.getServletContext().getContextPath() + "/Index");
    		break;
    	
    	case "/AjouterRef" : this.getServletContext().getRequestDispatcher("/WEB-INF/bookregister.jsp").forward(request, response);
			break;
    	case "/Recap" : 
    		
    		request.setAttribute("list_customers", dao.getAllUser());
    		request.setAttribute("list_commands", dao.getAllCommands());
    		
    		this.getServletContext().getRequestDispatcher("/WEB-INF/recap.jsp").forward(request, response);
		break;
    	
    	case "/Commands" : 
    		
    		request.setAttribute("list_commands_user", dao.getAllCommandsByUser(((Utilisateur)session.getAttribute("sessionUtilisateur")).getEmail()));
    		
    		this.getServletContext().getRequestDispatcher("/WEB-INF/recap_user.jsp").forward(request, response);
		
    	break;			
        case "/Confirmation"  :
     		cart_book = (Commandes) session.getAttribute("cart_book");
    		cart_book.getAllbooks().clear();
    		session.setAttribute("cart_book", null);
    		session.setAttribute("prixTotal", null);
    		this.getServletContext().getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request, response);       
	break;
			
        case "/VoirPanier" :          
           cart_book = (Commandes)session.getAttribute("cart_book");
            if(cart_book!=null)
            {
               
                request.setAttribute("list_book", cart_book.getAllbooks());
            }
                this.getServletContext().getRequestDispatcher("/WEB-INF/achat.jsp").forward(request, response);
            
            break;
    	case "/ViderPanier":
    		Commandes panier2 = (Commandes) session.getAttribute("cart_book");
    		panier2.getAllbooks().clear();
    		session.setAttribute("cart_book", null);
    		session.setAttribute("prixTotal", null);
    		
    		/* Redirection vers la page de connexion */
    		response.sendRedirect( this.getServletContext().getContextPath() + "/VoirPanier");
    	break;
    	
    	case "/ChangeMDP" :
    		if(user != null) {
    			
    			this.getServletContext().getRequestDispatcher("/WEB-INF/changePassword.jsp").forward(request, response);
    		}
    		else {
    			/* Redirection vers la page de connexion */
        		response.sendRedirect( this.getServletContext().getContextPath() + "/Connexion");
    		}
    	break;
    	
    	case "/detailsCommande":
    		
    		ArrayList<HashMap<String, String>> details = dao.getDetailsCommand(Integer.parseInt(request.getParameter("idCmd")));
    		
    		request.setAttribute("listeDetails", details);
            
    		this.getServletContext().getRequestDispatcher("/WEB-INF/details.jsp").forward(request, response);
    	
    	break;	
    	}
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                int total = 0;
                /* Recuperation de la session depuis la requete */
                HttpSession session = request.getSession();
                Utilisateur user = (Utilisateur)session.getAttribute( "sessionUtilisateur");
           switch(request.getParameter("origin"))
           {
               case "connect":    
                   
                /* Preparation de l'objet formulaire */
           		ConnexionForm form = new ConnexionForm( dao );
           		
           		/* Appel au traitement et a la validation de la requete, et recuperation du bean en resultant */
           		user = form.connecterUtilisateur( request );
           		
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
            		   this.getServletContext().getRequestDispatcher( "/connexion.jsp" ).forward( request, response );
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


              		
              		/* Si aucune erreur de validation, alors ajout du bean Utilisateur a la session, sinon suppression du bean de la session */
               		if (edit_form.getErreurs().isEmpty()) {
               			session.setAttribute( "sessionUtilisateur", null );
               			session.setAttribute( "sessionUtilisateur", user_edit );
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

                case "change_mdp":
                	
                	/* Preparation de l'objet formulaire */
            		PasswordForm password_form = new PasswordForm(dao);
            		
            		/* Verification du formulaire et modifications */
            		if(!password_form.modifierPasswordUtilisateur(request, ((Utilisateur)session.getAttribute("sessionUtilisateur")).getEmail())) {
            		
	            		/* Stockage du formulaire et du bean dans l'objet request */
	            		request.setAttribute( "form", password_form );
	            		
	            		/* Affichage de la page de gestion de compte */
	            		this.getServletContext().getRequestDispatcher( "/WEB-INF/changePassword.jsp" ).forward( request, response );
            		}
            		else
            			/* Affichage de la page de gestion de compte */
                		this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
                break;
                case "supp_livre": 
                    System.out.println("pouuuuet");
                   int bookId =  Integer.parseInt(request.getParameter("book_id")); 
                        //Livre aRetirer = alBooks.get(Integer.parseInt(request.getParameter("book_id")) - 1);
                	Commandes panier = (Commandes) session.getAttribute("cart_book");
                	//if(panier.getAllbooks().containsKey(aRetirer)) {
                		System.out.println("Le livre existe dans la liste !");
                		panier.delCommand(dao.getBookFromId(bookId));
                	//}
                        session.setAttribute("cart_book", null);
                	session.setAttribute("cart_book", panier);
                	response.sendRedirect(this.getServletContext().getContextPath() +"/VoirPanier");      
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
                   }
                   
                   float prixTot = cart_book.getPrix_total();
                   cart_book.setPrix_total(prixTot);
                   
                   session_book.setAttribute("cart_book", cart_book );                      
                   cart_book.addCommand(dao.getBookFromId(book_id), quantity);
                   //TODO DOUBLON
                   response.sendRedirect("Catalogue");
                   break;
               case "achat":
                   cart_book = (Commandes)session.getAttribute("cart_book");
                   

                   if(request.getParameter("bouton").equals("confirmation"))
                   {
                      cart_book = (Commandes)session.getAttribute("cart_book");
                       if(user != null) {
                           
                                    cart_book.setEmail(user.getEmail());
                                    request.setAttribute("total", cart_book.getPrix_total());
                                    this.getServletContext().getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request, response);
                            }
                        else {
                        this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
                        }
                       
                   }
                   
                  
                   break;
               case "confirmation":
                   HttpSession sessionConf = request.getSession();
                   cart_book = (Commandes)sessionConf.getAttribute("cart_book");
                   
                   if(request.getParameter("bouton").equals("ok"))
                   {
                       
                       cart_book.setEmail(user.getEmail());
                       dao.insertCommands(cart_book); 
                   }
                   else
                   {
                       this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                   }
                        
                   break;
               default:
                   this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                  break;
           }
    }
}

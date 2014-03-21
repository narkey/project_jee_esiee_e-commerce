/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esiee.projet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tithf
 */
public class ControleurServlet extends HttpServlet {
    DAO dao;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        switch(request.getServletPath())
        {
            case "/Connexion" : this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
                break;
            case "/Inscription" : this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
              break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           switch(request.getParameter("origin"))
           {
               case "connect":    
                   
                   String email = request.getParameter("email");
                   String mdp = request.getParameter("mdp");
                   Utilisateur user = dao.getUtilisateur(email);
                   request.setAttribute( "user", user );
                   if(user!=null)
                   {
                       
                        //this.getServletContext().getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request, response);
                   }
                   else
                   {
                       response.setContentType("text/html");
                       response.getWriter().println("Vous n'êtes pas enregistré, voulez-vous vous <a href='inscription.jsp'>inscrire</a> ?");                     
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esiee.projet;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author zhao
 */
public final class BookRegisterForm extends Form{
    
        private static final String CHAMP_TITRE = "titre";
	private static final String CHAMP_AUTEUR = "auteur";
        private static final String CHAMP_GENRE = "genre";
	private static final String CHAMP_DESCRIPTION = "description";
        private static final String CHAMP_PRIX = "prix";
	private static final String CHAMP_TYPE = "type";
	
	private DAO dao;
	private Utilisateur utilisateur;
	
	public BookRegisterForm( DAO utilisateurDao ) {
		super();
		this.utilisateur = new Utilisateur();
		this.dao = utilisateurDao;
	}
    
        
        public Livre enregistrerBook( HttpServletRequest request ){}
}

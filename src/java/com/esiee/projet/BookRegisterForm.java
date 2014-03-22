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
	private Livre livre;
	
	public BookRegisterForm( DAO livreDao ) {
		super();
		this.livre = new Livre();
		this.dao = livreDao;
	}
    
        
        public Livre enregistrerBook( HttpServletRequest request ){
            
                String titre = getValeurChamp( request, CHAMP_TITRE );
		String auteur = getValeurChamp( request, CHAMP_AUTEUR );
                String genre = getValeurChamp( request, CHAMP_GENRE );
		String description = getValeurChamp( request, CHAMP_DESCRIPTION );
                String prix = getValeurChamp( request, CHAMP_PRIX );
		String type = getValeurChamp( request, CHAMP_TYPE );
	
                
		traiterTitre( titre, livre );
		traiterAuteur( auteur, livre );
		traiterGenre( genre, livre );
		traiterDescription( description, livre );
                traiterPrix( prix, livre );
                
                
			
		if ( erreurs.isEmpty() ) {
                    try {
                            validationChampRequis(type);
                    } 
                    catch ( Exception e ) {
                            setErreur( CHAMP_TYPE , e.getMessage() );
                    }

                    /*if(type.equals("Mangas")){
                        Manga manga = new Manga(livre.getId(),titre,auteur,genre,description,Float.parseFloat(prix));
                        return (Livre)manga;
                    }
                    
                    if(type.equals("Comics")){
                       Bd bd = new Bd(livre.getId(),titre,auteur,genre,description,Float.parseFloat(prix));
                       return (Livre)bd;
                    }
                    
                    if(type.equals("BD")){
                        Comic comic = new Comic(livre.getId(),titre,auteur,genre,description,Float.parseFloat(prix));
                        return (Livre)comic;
                    }*/

		}
                
                return null;
	}

        
        
        
	
	private void traiterTitre( String titre, Livre livre) {
		
		try {
			validationChampRequis(titre);
		} 
		catch ( Exception e ) {
			setErreur( CHAMP_TITRE, e.getMessage() );
		}
		
		livre.setTitre( titre );
	}
	
        private void traiterAuteur( String auteur, Livre livre) {
		
		try {
			validationChampRequis(auteur);
		} 
		catch ( Exception e ) {
			setErreur( CHAMP_AUTEUR, e.getMessage() );
		}
		
		livre.setAuteur( auteur );
	}
        
        private void traiterGenre( String genre, Livre livre) {
		
		try {
			validationChampRequis(genre);
		} 
		catch ( Exception e ) {
			setErreur( CHAMP_GENRE, e.getMessage() );
		}
		
		livre.setGenre( genre );
	}
        
         private void traiterDescription( String description, Livre livre) {
		
		try {
			validationChampRequis(description);
		} 
		catch ( Exception e ) {
			setErreur( CHAMP_DESCRIPTION, e.getMessage() );
		}
		
		livre.setDescription( description );
	}
      
        private void traiterPrix( String prix, Livre livre) {
		
		try {
			validationChampRequis(prix);
		} 
		catch ( Exception e ) {
			setErreur( CHAMP_PRIX , e.getMessage() );
		}
		
		livre.setPrix( Float.parseFloat(prix));
	}
       
	
}


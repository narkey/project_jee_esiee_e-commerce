package com.esiee.projet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Form {

	protected String resultat;
	protected Map<String, String> erreurs;
	
	
	public Form() {
		
		erreurs = new HashMap<String, String>();
	}
	
	public String getResultat() {
		return this.resultat;
	}

	public Map<String, String> getErreurs() {
		return this.erreurs;
	}
	
	/*
	* Ajoute un message correspondant au champ sp√©cifi√© √† la map des erreurs.
	*/
	protected void setErreur( String champ, String message ) {
		this.erreurs.put( champ, message );
	}
	
	/*
	 * M√©thode utilitaire qui retourne null si un champ est vide, et soncontenu
	 * sinon.
	*/
	protected static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
		
		//
		String valeur = request.getParameter( nomChamp );
		if ( valeur == null || valeur.trim().length() == 0 ) {
			return null;
		} 
		else {
			return valeur.trim();
		}
	}
	
	protected void validationChampRequis(String valeurChamp) throws FormValidationException {
		
		if(valeurChamp == null)
			throw new FormValidationException( "Champ requis." );
	}
	
	protected void validationDate ( String date) throws FormValidationException {
		
		DateValidator dateValid = new DateValidator();
		
		if(!dateValid.validate(date))
			throw new FormValidationException("Veuillez saisir une date valide.");
	}
	
	protected void validationEmail( String email ) throws FormValidationException {
		
		if ( email != null ) {
			if ( !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
				throw new FormValidationException( "Merci de saisir une adresse mail valide." );
			}
		} 
		else {
			throw new FormValidationException( "Merci de saisir une adresse mail." );
		}
	}
	
	protected void validationMotsDePasse( String motDePasse, String confirmation ) throws FormValidationException {
		
		if ( motDePasse != null && confirmation != null ) {
			if ( !motDePasse.equals( confirmation ) ) {
				throw new FormValidationException( "Les mots de passe entr&eacute;s sont diff&eacute;rents, merci de les saisir &agrave;† nouveau." );
			} 
			else if ( motDePasse.length() < 3 ) {
				throw new FormValidationException( "Les mots de passe doivent contenir au moins 3 caract&egrave;res." );
			}
		} 
		else {
			throw new FormValidationException( "Merci de saisir et confirmer votre mot de passe." );
		}
	}
	
	protected void validationCodePostal ( String codePostal) throws FormValidationException {
		if( codePostal != null)
		{
			if ( !codePostal.matches("[0-9]{5}")) {
				throw new FormValidationException( "Merci de saisir un num&eacute;ro valide &agrave;† 5 chiffres." );
			}
		}
	}
	
	protected void validationNumRue( String numRue ) throws FormValidationException {

		if ( numRue != null ) {
			if ( !numRue.matches("[0-9]*") || numRue.trim().length() >4) {
				throw new FormValidationException( "Merci de saisir un num&eacute;ro de rue valide." );
			}
		}
	}

	protected void validationNumTel ( String numTel) throws FormValidationException {

		if( numTel != null)
		{
			if ( !numTel.matches("[0-9]{10}")) {
				throw new FormValidationException( "Merci de saisir un num&eacute;ro valide &agrave;† 10 chiffres." );
			}
		}
	}
}

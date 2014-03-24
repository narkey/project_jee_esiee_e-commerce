package com.esiee.projet;

import javax.servlet.http.HttpServletRequest;

public final class PasswordForm extends Form{

	private static final String CHAMP_OLDPASS = "mdp";
	private static final String CHAMP_NEWPASS = "new_mdp";
	private static final String CHAMP_CONF = "confirmation";
	
	private DAO dao;
	
	
	public PasswordForm( DAO dao ) {
		super();
		this.dao = dao;
	}		
	
	public boolean modifierPasswordUtilisateur( HttpServletRequest request, String email ) {
	
		boolean modifie = false;
		String identifiant = email;
		String motDePasse = getValeurChamp( request, CHAMP_OLDPASS );
		String newMotDePasse = getValeurChamp( request, CHAMP_NEWPASS );
		String confirmation = getValeurChamp( request, CHAMP_CONF );
	
		
		Utilisateur utilisateur = this.getUser(identifiant); 
		traiterOldPwd(motDePasse, utilisateur);
		String motDePasseChiffre = traiterNouveauxMotsDePasse( newMotDePasse, confirmation, motDePasse);
		
		if ( erreurs.isEmpty() ) {
			if (dao.updateMdp(motDePasseChiffre, utilisateur.getEmail())) {
				resultat = "Succ&egrave;s de la modification.";
				//System.out.println(resultat);
				modifie = true;
			}
			else {
				setErreur(null, "Erreur lors de la modification du mot de passe de l'utilisateur.");
				resultat = "Erreur technique (SQL).";
				//System.out.println(resultat);
			}
		} 
		else {
			//for(String key : erreurs.keySet())
				//System.out.println("Key>"+ key + " Value>" + erreurs.get(key));
			resultat = "Echec de la modification.";
		}
		return modifie;
	}
	
	private String traiterNouveauxMotsDePasse( String newMotDePasse, String confirmation, String oldMdp) {
		
		try {
			validationMotsDePasse( newMotDePasse, confirmation, oldMdp );
		}
		catch ( Exception e ) {
			setErreur( CHAMP_NEWPASS, e.getMessage() );
			setErreur( CHAMP_CONF, null );
		}
		
		String motDePasseChiffre = MD5(newMotDePasse);
		
		return motDePasseChiffre;
	}
	
	private void validationMotsDePasse( String motDePasse, String confirmation, String oldMdp ) throws FormValidationException {
		
		if ( motDePasse != null && confirmation != null && oldMdp != null) {
			if ( !motDePasse.equals( confirmation ) ) {
				throw new FormValidationException( "Les mots de passe entr&eacute;s sont diff&eacute;rents, merci de les saisir &agrave; nouveau." );
			}
			else if(motDePasse.equals(oldMdp))
				throw new FormValidationException( "Vous avez entr&eacute; le m&ecirc;me mot de passe..." );
			else if ( motDePasse.length() < 3 ) {
				throw new FormValidationException( "Les mots de passe doivent contenir au moins 3 caract&egrave;res." );
			}
		} 
		else {
			throw new FormValidationException( "Merci de saisir et confirmer votre mot de passe." );
		}
	}
	
	private void traiterOldPwd( String OldMotDePasse, Utilisateur user) {
		
		try {
			correctOldMotDePasse( OldMotDePasse, user );
		}
		catch ( Exception e ) {
			setErreur( CHAMP_OLDPASS, e.getMessage() );
		}		
	}
	
	private void correctOldMotDePasse( String motDePasse, Utilisateur user ) throws FormValidationException {
		
		/* Recuperer le mot de passe actuel dans la DB */ 
		
		if ( user.getMdp() != null ) {
			if (MD5(motDePasse).equals(user.getMdp())) {
				// Mot de passe correct
			} 
			else {
				// Mot de passe errone
				throw new FormValidationException( "Ce mot de passe est incorrect." );
			}
		} 
		else {
			throw new FormValidationException( "Merci de saisir votre mot de passe actuel." );
		}
	}
	
	private Utilisateur getUser(String email) {
		
		if( email != null ) {
			Utilisateur user = dao.getUtilisateur( email );
			if( user != null )
				return user;
		}
			return null;
	}
	
	private String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
}
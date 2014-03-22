package com.esiee.projet;

import javax.servlet.http.HttpServletRequest;

public class InscriptionForm extends Form {

	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS = "mdp";
	private static final String CHAMP_CONF = "mdp_confirm";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";

	private DAO dao;
	private Utilisateur utilisateur;

	public InscriptionForm( DAO dao ) {

		super();
		this.dao = dao;
		this.utilisateur = new Utilisateur();
	}


	public Utilisateur inscrireUtilisateur( HttpServletRequest request ) {

		String email = getValeurChamp( request, CHAMP_EMAIL );
		String motDePasse = getValeurChamp( request, CHAMP_PASS );
		String confirmation = getValeurChamp( request, CHAMP_CONF );
		String nom = getValeurChamp( request, CHAMP_NOM );
		String prenom = getValeurChamp( request, CHAMP_PRENOM );


		traiterEmail( email, true );
		traiterMotsDePasse( motDePasse, confirmation, true);
		traiterNom( nom , true);
		traiterPrenom( prenom, true );

		if ( this.erreurs.isEmpty() ) {
			if (this.dao.insert_user(this.utilisateur))
				resultat = "Succ&egrave;s de l'inscription.";
			else {
				setErreur(null, "Erreur lors de l'ajout de l'utilisateur.");
				this.resultat = "Erreur technique (SQL).";
			}
		} 
		else {
			this.resultat = "&Eeacute;chec de l'inscription.";
		}

		return this.utilisateur;
	}

	private void traiterEmail( String email, boolean requis) {

		try {
			if(requis)
				validationChampRequis(email);
			validationEmail( email );
		} 
		catch ( Exception e ) {
			setErreur( CHAMP_EMAIL, e.getMessage() );
		}

		this.utilisateur.setEmail( email );
	}

	private void traiterMotsDePasse( String motDePasse, String confirmation, boolean requis) {

		try {
			if(requis) {
				validationChampRequis(motDePasse);
				validationChampRequis(confirmation);
			}
			validationMotsDePasse( motDePasse, confirmation );
		}
		catch ( Exception e ) {
			setErreur( CHAMP_PASS, e.getMessage() );
			setErreur( CHAMP_CONF, e.getMessage() );
		}

		String motDePasseChiffre = this.MD5(motDePasse);
		utilisateur.setMdp( motDePasseChiffre );

	}

	private void traiterNom( String nom, boolean requis) {

		try {
			if(requis)
				validationChampRequis(CHAMP_NOM);
			//validationNom( nom );
		}
		catch ( Exception e ) {
			setErreur( CHAMP_NOM, e.getMessage() );
		}

		this.utilisateur.setNom( nom );
	}
	
	private void traiterPrenom( String prenom, boolean requis) {

		try {
			if(requis)
				validationChampRequis(CHAMP_PRENOM);
			//validationNom( prenom );
		}
		catch ( Exception e ) {
			setErreur( CHAMP_PRENOM, e.getMessage() );
		}

		this.utilisateur.setPrenom( prenom );
	}

	@Override
	protected void validationEmail( String email ) throws FormValidationException {

		if ( email != null ) {
			if ( !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
				throw new FormValidationException( "Merci de saisir une adresse mail valide." );
			}
			else if ( dao.getUtilisateur( email ) != null ) {
				throw new FormValidationException( "Cette adresse email est d&eacutej&agrave;a utilis&eacute;e, merci d'en choisir une autre." );
			}
		} 
		else {
			throw new FormValidationException( "Merci de saisir une adresse mail." );
		}
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

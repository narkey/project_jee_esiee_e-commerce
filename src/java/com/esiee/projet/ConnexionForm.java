package com.esiee.projet;

import javax.servlet.http.HttpServletRequest;

public final class ConnexionForm extends Form{

	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS = "password";
	
	private DAO dao;
	private Utilisateur utilisateur;
	
	public ConnexionForm( DAO utilisateurDao ) {
		super();
		this.utilisateur = new Utilisateur();
		this.dao = utilisateurDao;
	}
	
	
	public Utilisateur connecterUtilisateur( HttpServletRequest request ) {
	
		/* Récupération des champs de formulaire*/
		String email = getValeurChamp( request, CHAMP_EMAIL );
		String motDePasse = getValeurChamp( request, CHAMP_PASS );
	
		traiterEmail( email, utilisateur );
		traiterMotDePasse( motDePasse, utilisateur );
		
		Utilisateur userSearch = new Utilisateur();
			
		if ( erreurs.isEmpty() ) {
			
			// Recuperation de l'utilisateur en fonction de son email
			userSearch = dao.getUtilisateur(utilisateur.getEmail());
			// Si la recherche retourne un resultat
			if ( userSearch != null ) {
				if (MD5(utilisateur.getMdp()).equals(userSearch.getMdp())) {
				  // correct!
					/*if( userSearch.getIsActivated() == 1 ) {
						resultat = "Connexion réussie.";
					}
					else {
						setErreur( CHAMP_EMAIL, "Ce compte n'est pas activé" );
						resultat = "Ce compte n'est pas activé.";
					}*/
				} else {
				  // bad login!
					setErreur( CHAMP_PASS, "Mauvais mot de passe" );
					resultat = "Mauvais mot de passe";
				}
			}
			// Si la recherche ne retourne rien
			else {
				setErreur( CHAMP_EMAIL, "Identifiant introuvable" );
				setErreur( CHAMP_PASS, "Erreur" );
				resultat = "Ce compte utilisateur n'existe pas.";
			}
		} 
		else {
			resultat = "L'identifiant ou le mot de passe saisi est incorrect.";
		}

		return userSearch;
	}

	
	private void traiterEmail( String email, Utilisateur utilisateur) {
		
		try {
			validationChampRequis(email);
			validationEmail( email );
		} 
		catch ( Exception e ) {
			setErreur( CHAMP_EMAIL, e.getMessage() );
		}
		
		utilisateur.setEmail( email );
	}
	
	
	private void traiterMotDePasse( String motDePasse, Utilisateur utilisateur ) {
		
		try {
			this.validationChampRequis( motDePasse );
		}
		catch ( Exception e ) {
			setErreur( CHAMP_PASS, e.getMessage() );
		}
		
		utilisateur.setMdp( motDePasse );
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
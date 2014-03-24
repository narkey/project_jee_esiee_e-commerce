package com.esiee.projet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EditProfilForm extends Form {

	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_ADRESSE = "adresse";
	
	private DAO dao;
	private Utilisateur utilisateur;

	public EditProfilForm( DAO dao ) {

		super();
		this.dao = dao;
		this.utilisateur = new Utilisateur();
	}


	public Utilisateur modifierUtilisateur( HttpServletRequest request ) {

		String nom = getValeurChamp( request, CHAMP_NOM );
		String prenom = getValeurChamp( request, CHAMP_PRENOM );
		String adresse = getValeurChamp( request, CHAMP_ADRESSE );
		
		/* Recuperation de la session depuis la requete */
  		HttpSession session = request.getSession();
  		Utilisateur user_session = (Utilisateur) session.getAttribute("sessionUtilisateur");
		String email = user_session.getEmail();
		
		traiterNom( nom , true);
		traiterPrenom( prenom, true );
		traiterAdresse( adresse, true );
		utilisateur.setEmail(email);
		
		if ( this.erreurs.isEmpty() ) {
			if (this.dao.update_user(this.utilisateur))
				resultat = "Succ&egrave;s de modification de profil.";
			else {
				setErreur(null, "Erreur lors de la modification du profil de l'utilisateur.");
				this.resultat = "Erreur technique (SQL).";
			}
		} 
		else {
			this.resultat = "&Eeacute;chec de modification de profil.";
		}

		return this.utilisateur;
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
	
	private void traiterAdresse( String adresse, boolean requis) {

		try {
			if(requis)
				validationChampRequis(CHAMP_ADRESSE);
			//validationNom( nom );
		}
		catch ( Exception e ) {
			setErreur( CHAMP_ADRESSE, e.getMessage() );
		}

		this.utilisateur.setAdresse( adresse );
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
}

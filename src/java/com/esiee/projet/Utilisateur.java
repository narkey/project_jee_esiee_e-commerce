/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esiee.projet;

/**
 *
 * @author tithf
 */
public class Utilisateur {
    private String nom;
    private String prenom;
    private String mdp;
    private String email;
    private String adresse;
    private int access;
    
    public Utilisateur() {
        this.nom = "";
        this.prenom = "";
        this.mdp = "";
        this.email = "";
        this.adresse = "";
        this.access = 5;
    }

    public Utilisateur(String nom, String prenom, String mdp, String email, String adresse, int access) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.adresse = adresse;
        this.access = access;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}
    
    
}

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
public  class Livre {
    protected String titre;
    protected String auteur;
    protected String genre;
    protected String description;
    protected float prix;

    public Livre() {
        this.titre = "";
        this.auteur = "";
        this.genre = "";
        this.description = "";
        this.prix = -1;
    }
        
    public Livre(String titre, String auteur, String genre, String description, float prix) {
        this.titre = titre;
        this.auteur = auteur;
        this.genre = genre;
        this.description = description;
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}

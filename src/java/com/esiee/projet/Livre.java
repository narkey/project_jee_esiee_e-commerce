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
    protected int id;
    protected String titre;
    protected String auteur;
    protected String genre;
    protected String description;
    protected float prix;

    public Livre() {
        this.id=-1;
        this.titre = "";
        this.auteur = "";
        this.genre = "";
        this.description = "";
        this.prix = -1;
    }
        
    public Livre(int id, String titre, String auteur, String genre, String description, float prix) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.genre = genre;
        this.description = description;
        this.prix = prix;
    }
    
    public int getId() {
        return this.id;
    }

    public void setTitre(int id) {
        this.id = id;
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
    
    public String toString(){
        String info_livre = "Le titre du livre est " + this.titre + " de " + this.auteur;
        return info_livre;
    }
}

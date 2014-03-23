/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esiee.projet;

import java.util.ArrayList;

/**
 *
 * @author Titou
 */
public class Commandes {
    //attributs pour la commande
    private int id;
    private String email;
    private String date_commande;
    private float prix_total;
    //attributs pour la liste des produits de la commande
    private ArrayList<Livre> allBooks = new ArrayList<Livre>();
    
    public Commandes() {
        this.id = -1;
        this.email = "";
        this.date_commande = "";
        this.prix_total = (float) -1.1;
        this.quantite = -1;
    }

    public Commandes(String email, String date_commande, float prix_total, int quantite) {
        this.id = id;
        this.email = email;
        this.date_commande = date_commande;
        this.prix_total = prix_total;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(String date_commande) {
        this.date_commande = date_commande;
    }

    public float getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(float prix_total) {
        this.prix_total = prix_total;
    }

    public ArrayList<Livre> getAllbooks() {
        return allBooks;
    }

    public void setAllbooks(ArrayList<Livre> allbooks) {
        this.allBooks = allBooks;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    private int quantite;
    
    public boolean addCommand(Livre livre)
    {
        boolean ok = this.allBooks.add(livre);
        return ok; 
    }
    
}

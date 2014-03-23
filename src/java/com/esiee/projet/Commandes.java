/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esiee.projet;

import java.util.ArrayList;
import java.util.HashMap;

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
    private HashMap<Integer, Integer> allBooks = new HashMap<Integer,Integer>(); //contient id du livre en key et la quantite en value
    
    public Commandes() {
        this.id = -1;
        this.email = "";
        this.date_commande = "";
        this.prix_total = (float) -1.1;
    }

    public Commandes(String email) {
        this.email = email;
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

    public HashMap<Integer, Integer>getAllbooks() {
        return allBooks;
    }
    
    public void addCommand(int livre_id, int quantity)
    {
        this.allBooks.put(livre_id, quantity);
    }
    
}

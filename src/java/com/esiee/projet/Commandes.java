/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esiee.projet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Titou
 */
public class Commandes {
    //attributs pour la commande
    private int id;
    private String email;
    private float prix_total;
    //attributs pour la liste des produits de la commande
    private HashMap<Livre, Integer> allBooks = new HashMap<Livre,Integer>(); //contient id du livre en key et la quantite en value
    
    public Commandes() {
        this.id = -1;
        this.email = "";
        this.prix_total = (float) 0;
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

    public float getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(float prix_total) {
        this.prix_total = prix_total;
    }

    public HashMap<Livre, Integer>getAllbooks() {
        return allBooks;
    }
    
    public void addCommand(Livre livre, int quantity)
    {
        boolean newItem = true;
        for(Livre i : this.allBooks.keySet())
        {
            if(i.getId()==livre.getId())
            {
                newItem = false;
                //this.allBooks.remove(i);
                
                this.allBooks.put(i, this.allBooks.get(i)+quantity);
                this.prix_total += i.getPrix()*quantity;
            }
        }
        if(newItem)
        {
            this.prix_total += livre.getPrix();
            this.allBooks.put(livre, 1);
        }
    }

    public void delCommand(Livre livre)
    {
       
        Iterator<Livre> itLivre = this.allBooks.keySet().iterator();
        while(itLivre.hasNext())
        {
            
            Livre tmp = itLivre.next();
            if(tmp.getId()==livre.getId())
            {   
                System.out.println("ilivre trouver");
                this.prix_total -= livre.getPrix()*this.allBooks.get(tmp);
                itLivre.remove();
            }
        }

    }
    
    public void setQuantity(Livre livre, int quantity)
    {
        for(Livre i : this.allBooks.keySet())
        {
            if(i.getId()==livre.getId())
                this.allBooks.put(i, quantity);
        }
    }
    
}

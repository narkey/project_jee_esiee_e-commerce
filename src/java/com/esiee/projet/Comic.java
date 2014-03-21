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
public class Comic extends Livre{
    
    public Comic() {
        super();
    }

    public Comic(String titre, String auteur, String genre, String description, float prix) {
        super(titre, auteur, genre, description, prix);
    }
    
}

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
public class Manga extends Livre{
    
    public Manga() {
        super();
    }

    public Manga(int id, String titre, String auteur, String genre, String description, float prix) {
        super(id, titre, auteur, genre, description, prix);
    }
    
}

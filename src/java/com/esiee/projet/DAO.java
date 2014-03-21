/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esiee.projet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tithf
 */
public class DAO {
    private Connection connection;
    //Prepared request
    private static final String SQL_INSERT_UTILISATEUR = 
    "INSERT INTO TUTU.UTILISATEURS (NOM, PRENOM, MDP, EMAIL, ADRESSE) "
        + "VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_LIVRE = 
     "INSERT INTO TUTU.LIVRES (ID, TITRE, AUTEUR, GENRE, DESCRIPTION, PRIX) "
     + "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_PK = 
        "SELECT * FROM TUTU.UTILISATEURS  WHERE EMAIL = ?";
    private static final String SQL_SELECT_ALLUSER = 
        "SELECT * FROM TUTU.UTILISATEURS";
    
    public DAO(final String user, final String password, final String db){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch ( ClassNotFoundException e ) {
            System.out.println("Error driver");
        }
        try {
            this.connection = DriverManager.getConnection("jdbc:derby://localhost:1527/" + db, user, password);
        } catch (SQLException sqle) {
            System.out.println("" + sqle);
        }
    }
    /**
     * Méthode permettant de rajouter un utilisateur dans la bdd
     */
    public void insert_user(final Utilisateur user)
    {
        PreparedStatement preparedStatement = null;
         try {

            preparedStatement = initialisationRequetePreparee( this.connection, SQL_INSERT_UTILISATEUR, true, user.getNom(), user.getPrenom(), user.getMdp(),user.getEmail(), user.getAdresse());
            int statut = preparedStatement.executeUpdate();

            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
              System.out.println( "Échec de l'enregistrement, aucune ligne ajoutée dans la table." );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }
    /**
     * Methode verifiant si l'utilisateur avec la clé primaire en paramètre est dans la table d'utilisateurs
     */
     
    public Utilisateur getUtilisateur(final String primary_key) {
        
        boolean trouve= false;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Utilisateur utilisateur = null;
        
        try {
            System.out.println(primary_key);
        preparedStatement = initialisationRequetePreparee( this.connection, SQL_SELECT_PK, true, primary_key );
        resultSet = preparedStatement.executeQuery();
            
           
        while(resultSet.next()) {
                    
            //L'utilisateur a été trouvé dans notre base"
            utilisateur = map_user( resultSet );
                     
        }
        
        resultSet.close();
        } catch(SQLException sqle) {
            System.out.println("" + sqle);
        }
        
        return utilisateur;
    }    
     public ArrayList<Utilisateur> getAllUser(){
         
        boolean trouve= false;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Utilisateur> list = new ArrayList<Utilisateur>();
        
        
        try {
          
            preparedStatement = initialisationRequetePreparee( this.connection, SQL_SELECT_ALLUSER, true );
            resultSet = preparedStatement.executeQuery();
            
           
            while(resultSet.next()) {
                    
            //L'utilisateur a été trouvé dans notre base"
            list.add(map_user( resultSet ));
                     
        }
        
        resultSet.close();
        } catch(SQLException sqle) {
            System.out.println("" + sqle);
        }
        
        return list;
        
     }
    public static PreparedStatement initialisationRequetePreparee(Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException 
    {
        PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }

        return preparedStatement;
    }
    /**
    * Simple méthode utilitaire permettant de faire la correspondance (le
    * mapping) entre une ligne issue de la table des utilisateurs (un
    * ResultSet) et un bean Utilisateur.
    */
    private static Utilisateur map_user( ResultSet resultSet ) throws SQLException {
         
        Utilisateur  user = new Utilisateur(
                                    resultSet.getString( "NOM" ),
                                    resultSet.getString( "PRENOM" ),
                                    resultSet.getString( "MDP" ),
                                    resultSet.getString( "EMAIL" ),
                                    resultSet.getString("ADRESSE" ));
        return user;
    }
    private static Livre map_livre( ResultSet resultSet ) throws SQLException {
         
        Livre livre = new Livre(
                                    resultSet.getString( "TITRE" ),
                                    resultSet.getString( "AUTEUR" ),
                                    resultSet.getString( "GENRE" ),
                                    resultSet.getString("DESCRIPTION" ),
                                    resultSet.getFloat("PRIX" ) );

        return livre;
    }    
    
    private static Bd map_bd( ResultSet resultSet ) throws SQLException {
         
       Bd bd = new Bd(
                                    resultSet.getString( "TITRE" ),
                                    resultSet.getString( "AUTEUR" ),
                                    resultSet.getString( "GENRE" ),
                                    resultSet.getString("DESCRIPTION" ),
                                    resultSet.getFloat("PRIX" ) );

        return bd;
    }      
    
    private static Manga map_manga( ResultSet resultSet ) throws SQLException {
         
       Manga manga = new Manga(
                                    resultSet.getString( "TITRE" ),
                                    resultSet.getString( "AUTEUR" ),
                                    resultSet.getString( "GENRE" ),
                                    resultSet.getString("DESCRIPTION" ),
                                    resultSet.getFloat("PRIX" ) );

        return manga;
    }     
    private static Comic map_comic( ResultSet resultSet ) throws SQLException {
         
       Comic comic = new Comic(
                                    resultSet.getString( "TITRE" ),
                                    resultSet.getString( "AUTEUR" ),
                                    resultSet.getString( "GENRE" ),
                                    resultSet.getString("DESCRIPTION" ),
                                    resultSet.getFloat("PRIX" ) );

        return comic;
    }     
}


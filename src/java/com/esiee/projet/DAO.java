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

import com.esiee.projet.Utilisateur;

/**
 *
 * @author tithf
 */
public class DAO {
    private Connection connection;
    
    //Prepared request
    private static final String SQL_INSERT_UTILISATEUR = 
    "INSERT INTO TUTU.UTILISATEURS (NOM, PRENOM, MDP, EMAIL, ADRESSE, ACCESS) "
        + "VALUES (?, ?, ?, ?, 'MON ADRESSE',5)";
    
    private static final String SQL_UPDATE_UTILISATEUR = 
    "UPDATE TUTU.UTILISATEURS SET NOM = ?, PRENOM = ?, ADRESSE = ? "
    	+ "WHERE EMAIL = ?";
    
    private static final String SQL_INSERT_LIVRE = 
     "INSERT INTO TUTU.LIVRES (ID, TITRE, AUTEUR, GENRE, DESCRIPTION, PRIX, CATEGORIE) "
     + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_SELECT_PK = 
        "SELECT * FROM TUTU.UTILISATEURS  WHERE EMAIL = ?";
    
    private static final String SQL_SELECT_ALLUSER = 
        "SELECT * FROM TUTU.UTILISATEURS";
    
    private static final String SQL_SELECT_ALLBOOK = 
    	 "SELECT * FROM TUTU.LIVRES";
    	    
    private static final String SQL_SELECT_CATEGORY_BOOK = 
    	 "SELECT * FROM TUTU.LIVRES WHERE CATEGORIE = ?";
        
    /*private static final String SQL_INSERT_UTILISATEUR = 
    "INSERT INTO UTILISATEURS (NOM, PRENOM, MDP, EMAIL, ADRESSE, ACCESS) "
        + "VALUES (?, ?, ?, ?, 'MON ADRESSE',5)";
    
    private static final String SQL_UPDATE_UTILISATEUR = 
    "UPDATE UTILISATEURS SET NOM = ?, PRENOM = ?, ADRESSE = ? "
    	+ "WHERE EMAIL = ?";
    
    private static final String SQL_INSERT_LIVRE = 
     "INSERT INTO LIVRES ( TITRE, AUTEUR, GENRE, DESCRIPTION, PRIX) "
     + "VALUES (?, ?, ?, ?, ?)";
    
    private static final String SQL_SELECT_PK = 
        "SELECT * FROM UTILISATEURS  WHERE EMAIL = ?";
    
    private static final String SQL_SELECT_ALLUSER = 
        "SELECT * FROM UTILISATEURS";
    
    private static final String SQL_SELECT_ALLBOOK = 
    	 "SELECT * FROM LIVRES";
    	    
    private static final String SQL_SELECT_CATEGORY_BOOK = 
    	 "SELECT * FROM LIVRES WHERE CATEGORIE = ?";
    */
    
    /**
	 * Sélectionne un utilisateur en fonction de son login.
	 */
	private static final String SQL_SELECT_PAR_EMAIL = "SELECT * FROM Utilisateurs WHERE EMAIL = ?";
	
	//private static final String SQL_SELECT_ADRESSES_BY_USER = "SELECT * FROM Adresses WHERE Adresse.ID_USER = Utilisateurs.ID AND Utilisateurs.EMAIL = ?";
	//private static final String SQL_SELECT_INFOS_USER = "SELECT *,* FROM Adresse LEFT JOIN Utilisateurs user ON Adresse.ID_USER = user.ID WHERE user.email = ?";
	
    public DAO(final String user, final String password, final String db){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver"); //Pour derby
        	//Class.forName("com.mysql.jdbc.Driver"); //Pour MySQL
        } catch ( ClassNotFoundException e ) {
            System.out.println("Error driver");
        }
        try {
            this.connection = DriverManager.getConnection("jdbc:derby://localhost:1527/" + db, user, password); //Pour derby
        	
        //	this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, user, password); //Pour mysql
        	
        } catch (SQLException sqle) {
            System.out.println("" + sqle);
        }
    }
    
    /**
     * Méthode permettant de rajouter un utilisateur dans la bdd
     */
    public boolean insert_user(final Utilisateur user)
    {
    	boolean ok = false;
        PreparedStatement preparedStatement = null;
         try {

            preparedStatement = initialisationRequetePreparee( this.connection, SQL_INSERT_UTILISATEUR, true, user.getNom(), user.getPrenom(), user.getMdp(),user.getEmail());
            int statut = preparedStatement.executeUpdate();

            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
              System.out.println( "Echec de l'enregistrement, aucune ligne ajoutee dans la table." );
            }
            else
            	ok = true;
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
         
         return ok;
    }
    
       public boolean insert_Book (final Livre livre, String categorie)
    {
    	boolean ok = false;
        PreparedStatement preparedStatement = null;
         try {

            preparedStatement = initialisationRequetePreparee( this.connection, SQL_INSERT_LIVRE, true, livre.getId(), livre.getTitre(), livre.getAuteur(),livre.getGenre(),livre.getDescription(),livre.getPrix(),categorie);
            int statut = preparedStatement.executeUpdate();

            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
              System.out.println( "Echec de l'enregistrement, aucune ligne ajoutee dans la table." );
            }
            else
            	ok = true;
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
         
         return ok;
    } 
    
    /**
     * M$eacute;thode permettant de modifier un utilisateur dans la bdd
     */
    public boolean update_user(final Utilisateur user)
    {
    	boolean ok = false;
        PreparedStatement preparedStatement = null;
        System.out.println(user.getEmail());
         try {

            preparedStatement = initialisationRequetePreparee( this.connection, SQL_UPDATE_UTILISATEUR, true, user.getNom(), user.getPrenom(), user.getAdresse(), user.getEmail());
            int statut = preparedStatement.executeUpdate();

            /* Analyse du statut retourne par la requete d'insertion */
            if ( statut == 0 ) {
              System.out.println( "Echec de la modification du profil de l'utilisateur." );
            }
            else
            	ok = true;
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
         
         return ok;
    }
    /**
     * Methode verifiant si l'utilisateur avec la cl&eacute; primaire en param&eagrave;tre est dans la table d'utilisateurs
     */ 
    public Utilisateur getUtilisateur(final String email) {
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Utilisateur utilisateur = null;
        
        try {
            //System.out.println(primary_key);
        preparedStatement = initialisationRequetePreparee( this.connection, SQL_SELECT_PAR_EMAIL, true, email );
        resultSet = preparedStatement.executeQuery();
            
           
        if(resultSet.next()) {
                    
            //L'utilisateur a ete trouve dans notre base"
            utilisateur = map_user( resultSet );
                     
        }
        
        resultSet.close();
        } catch(SQLException sqle) {
            System.err.println("" + sqle);
        }
        
        return utilisateur;
    }    
     
    public ArrayList<Utilisateur> getAllUser(){
         
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
    public ArrayList<Livre> getAllBook()
    {
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       ArrayList<Livre> list = new ArrayList<Livre>();
       
       try {
           preparedStatement = initialisationRequetePreparee( this.connection, SQL_SELECT_ALLBOOK, true);
           resultSet = preparedStatement.executeQuery();
           while(resultSet.next()) {
           //L'utilisateur a �t� trouv� dans notre base"
           list.add(map_livre( resultSet ));            
       }
       
       resultSet.close();
       } catch(SQLException sqle) {
           System.out.println("" + sqle);
       }
       
       return list;
       
    }
    public ArrayList<Livre> getCategoryBook(String cat)
    {
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       ArrayList<Livre> list = new ArrayList<Livre>();
       
       try {
           preparedStatement = initialisationRequetePreparee( this.connection, SQL_SELECT_CATEGORY_BOOK, true, cat);
           resultSet = preparedStatement.executeQuery();

           if(resultSet.next()) {

               //L'utilisateur a �t� trouv� dans notre base"
               list.add(map_livre( resultSet ));

           }

           resultSet.close();
           } catch(SQLException sqle) {
               System.err.println("" + sqle);
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
                                    resultSet.getString( "ADRESSE" ),
                                    resultSet.getInt("ACCESS" )
        						);
        return user;
    }
    
    /*private static Adresse map_adresse( ResultSet resultSet ) throws SQLException {
        
        Adresse adresse = new Adresse(
                                    resultSet.getInt( "NUM_RUE" ),
                                    resultSet.getString( "RUE" ),
                                    resultSet.getString( "CP" ),
                                    resultSet.getString("VILLE" ),
                                    resultSet.getString("PAYS" ) );

        return adresse;
    }*/
    
    private static Livre map_livre( ResultSet resultSet ) throws SQLException {
         
        Livre livre = new Livre(
        							resultSet.getInt("ID"),
                                    resultSet.getString( "TITRE" ),
                                    resultSet.getString( "AUTEUR" ),
                                    resultSet.getString( "GENRE" ),
                                    resultSet.getString("DESCRIPTION" ),
                                    resultSet.getFloat("PRIX" ) );

        return livre;
    }    
    
    private static Bd map_bd( ResultSet resultSet ) throws SQLException {
         
       Bd bd = new Bd(
    		   			resultSet.getInt("ID"),
    		   			resultSet.getString( "TITRE" ),
                        resultSet.getString( "AUTEUR" ),
                        resultSet.getString( "GENRE" ),
                        resultSet.getString("DESCRIPTION" ),
                        resultSet.getFloat("PRIX" ) );

        return bd;
    }      
    
    private static Manga map_manga( ResultSet resultSet ) throws SQLException {
         
       Manga manga = new Manga(
    		   resultSet.getInt("ID"),
               resultSet.getString( "TITRE" ),
               resultSet.getString( "AUTEUR" ),
               resultSet.getString( "GENRE" ),
               resultSet.getString("DESCRIPTION" ),
               resultSet.getFloat("PRIX" ) );

        return manga;
    }     
    private static Comic map_comic( ResultSet resultSet ) throws SQLException {
         
       Comic comic = new Comic(
    		   resultSet.getInt("ID"),
               resultSet.getString( "TITRE" ),
               resultSet.getString( "AUTEUR" ),
               resultSet.getString( "GENRE" ),
               resultSet.getString("DESCRIPTION" ),
               resultSet.getFloat("PRIX" ) );

        return comic;
    }     
}


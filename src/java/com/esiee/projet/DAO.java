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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 *
 * @author tithf
 */
public class DAO {
    private Connection connection;
    private static int IDbook = 0;  
    private static int IDCommand = 0;
    //Prepared request
    private static final String SQL_INSERT_UTILISATEUR = 
    "INSERT INTO TUTU.UTILISATEURS (NOM, PRENOM, MDP, EMAIL, ADRESSE, ACCES) "
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
        "SELECT * FROM TUTU.UTILISATEURS WHERE ACCES > 2";
    
    private static final String SQL_SELECT_ALLBOOK = 
    	 "SELECT * FROM TUTU.LIVRES";
    	    
    private static final String SQL_SELECT_CATEGORY_BOOK = 
    	 "SELECT * FROM TUTU.LIVRES WHERE CATEGORIE = ?";
    
    private static final String SQL_SELECT_CART = 
        "SELECT  TUTU.COMMANDES.ID, TUTU.LISTE_COMMANDES.ID_COMMANDE, TUTU.LISTE_COMMANDES.ID_PRODUIT,TUTU.LIVRES.* FROM TUTU.COMMANDES INNER JOIN TUTU.LISTE_COMMANDES ON TUTU.COMMANDES.ID = TUTU.LISTE_COMMANDES.ID_COMMANDE INNER JOIN TUTU.LIVRES ON TUTU.LISTE_COMMANDES.ID_PRODUIT = TUTU.LIVRES.ID WHERE TUTU.COMMANDES.EMAIL = ?";
    
    private static final String SQL_SELECT_BOOK_FROM_ID = 
         "SELECT * FROM TUTU.LIVRES WHERE ID = ?";
    private static final String SQL_INSERT_COMMAND = 
         "INSERT INTO TUTU.COMMANDES (ID, EMAIL, DATE_COMMANDE, PRIX)"
            + "VALUES(?,?,?,?)";  
    private static final String SQL_INSERT_LIST_PRODUCT_COMMAND = 
         "INSERT INTO TUTU.LISTE_COMMANDES (ID_COMMANDE, ID_PRODUIT, QUANTITE)"
            + "VALUES(?,?,?)";  
    /*private static final String SQL_INSERT_UTILISATEUR = 
    "INSERT INTO UTILISATEURS (NOM, PRENOM, MDP, EMAIL, ADRESSE, ACCESS) "
        + "VALUES (?, ?, ?, ?, 'MON ADRESSE',5)";
    
    private static final String SQL_UPDATE_UTILISATEUR = 
    "UPDATE UTILISATEURS SET NOM = ?, PRENOM = ?, ADRESSE = ? "
    	+ "WHERE EMAIL = ?";
    
    private static final String SQL_INSERT_LIVRE = 
     "INSERT INTO LIVRES ( ID,TITRE, AUTEUR, GENRE, DESCRIPTION, PRIX, CATEGORIE) "
     + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_SELECT_PK = 
        "SELECT * FROM UTILISATEURS  WHERE EMAIL = ?";
    
    private static final String SQL_SELECT_ALLUSER = 
        "SELECT * FROM UTILISATEURS WHERE ACCESS > 2";
    
    private static final String SQL_SELECT_ALLBOOK = 
    	 "SELECT * FROM LIVRES";
    	    
    private static final String SQL_SELECT_CATEGORY_BOOK = 
    	 "SELECT * FROM LIVRES WHERE CATEGORIE = ?";
    */
    
    private static final String SQL_SELECT_ALL_COMMANDS =
    	"SELECT TUTU.COMMANDES.ID,  TUTU.COMMANDES.EMAIL,  TUTU.COMMANDES.DATE_COMMANDE,  TUTU.COMMANDES.PRIX,  TUTU.UTILISATEURS.ADRESSE "
    	+ "FROM  TUTU.COMMANDES "
    	+ "INNER JOIN  TUTU.UTILISATEURS ON  TUTU.UTILISATEURS.EMAIL =  TUTU.COMMANDES.EMAIL"
    	+ " ORDER BY  TUTU.COMMANDES.ID ASC";
    
    private static final String SQL_SELECT_ALL_COMMANDS_BY_USER =
        "SELECT  TUTU.UTILISATEURS.ADRESSE, comm.ID, comm.DATE_COMMANDE, comm.PRIX "
        + "FROM UTILISATEURS "
        + "INNER JOIN  TUTU.COMMANDES comm ON comm.EMAIL = UTILISATEURS.EMAIL "
        + "WHERE UTILISATEURS.EMAIL = ?";
        
    private static final String SQL_SELECT_DETAILS_COMMAND =
        	"SELECT  TUTU.LISTE_COMMANDES.QUANTITE, book.TITRE, book.AUTEUR, book.GENRE, book.PRIX"
        	+ " FROM  TUTU.LISTE_COMMANDES"
        	+ " INNER JOIN LIVRES book on TUTU.LISTE_COMMANDES.ID_PRODUIT = book.ID "
        	+ " WHERE TUTU.LISTE_COMMANDES.ID_COMMANDE = ?";
 	private static final String SQL_UPDATE_MDP = "UPDATE TUTU.Utilisateurs SET MDP = ? WHERE EMAIL = ?";
       
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
        	
        	//this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, user, password); //Pour mysql
        	
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
        IDbook++;
    	boolean ok = false;
        PreparedStatement preparedStatement = null;
          try {       	 
        	 livre.setId(IDbook);
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

    public ArrayList<HashMap<String, String>> getDetailsCommand(int id_cmd){
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<HashMap<String, String>> listDetails = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> details = new HashMap<String, String>();
        
        
        try {
          
            preparedStatement = initialisationRequetePreparee( this.connection, SQL_SELECT_DETAILS_COMMAND, true, id_cmd );
            resultSet = preparedStatement.executeQuery();
            
           
            while(resultSet.next()) {
                    
            details.put("quantite", String.valueOf(resultSet.getInt(1)));
            details.put("titre", resultSet.getString(2));
            details.put("auteur", resultSet.getString(3));
            details.put("genre", resultSet.getString(4));
            details.put("prix", String.valueOf(resultSet.getFloat(5)));
            
            listDetails.add(details);
            details = new HashMap<String, String>();
        }
        
            resultSet.close();
        } catch(SQLException sqle) {
            System.out.println("" + sqle);
        }
        
        return listDetails;
        
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
           //L'utilisateur a ete trouve dans notre base"
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

               //L'utilisateur a ete trouve dans notre base"
               list.add(map_livre( resultSet ));

           }

           resultSet.close();
           } catch(SQLException sqle) {
               System.err.println("" + sqle);
           }

       return list;
    }
    
    public ArrayList<Livre> getChartBook(String user_email)
    {
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       ArrayList<Livre> list = new ArrayList<Livre>();
       
       try {
           preparedStatement = initialisationRequetePreparee( this.connection, SQL_SELECT_CART, true, user_email);
           resultSet = preparedStatement.executeQuery();

           if(resultSet.next()) {

               //L'utilisateur a ete trouve dans notre base"
               list.add(map_livre( resultSet ));

           }

           resultSet.close();
           } catch(SQLException sqle) {
               System.err.println("" + sqle);
           }

       return list;
    }

    public ArrayList<HashMap<String, String>> getAllCommands()
    {
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
       HashMap<String, String> map = new HashMap<String, String>();
       
       try {
           preparedStatement = initialisationRequetePreparee( this.connection, SQL_SELECT_ALL_COMMANDS, true);
           resultSet = preparedStatement.executeQuery();
           while(resultSet.next()) {
        	   
        	   map.put("id", String.valueOf(resultSet.getInt(1)));
        	   map.put("email", resultSet.getString(2));
        	   map.put("date", resultSet.getDate(3).toString());
        	   map.put("prix", String.valueOf(resultSet.getFloat(4)));
        	   map.put("adresse", resultSet.getString(5));
        	   
        	   list.add(map);
        	   map = new HashMap<String, String>();
           }
       
       resultSet.close();
       } catch(SQLException sqle) {
           System.out.println("" + sqle);
       }
       
       return list;
       
    }
    
    public ArrayList<HashMap<String, String>> getAllCommandsByUser(String email)
    {
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
       HashMap<String, String> map = new HashMap<String, String>();
       
       try {
           preparedStatement = initialisationRequetePreparee( this.connection, SQL_SELECT_ALL_COMMANDS_BY_USER, true, email);
           resultSet = preparedStatement.executeQuery();
           while(resultSet.next()) {
        	   
        	   map.put("id", String.valueOf(resultSet.getInt(2)));
        	   map.put("date", resultSet.getDate(3).toString());
        	   map.put("prix", String.valueOf(resultSet.getFloat(4)));
        	   map.put("adresse", resultSet.getString(1));
        	   
        	   list.add(map);
        	   map = new HashMap<String, String>();
           }
       
       resultSet.close();
       } catch(SQLException sqle) {
           System.out.println("" + sqle);
       }
       
       return list;
       
    }
    public Livre getBookFromId(int book_id)
    {
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       Livre livre = new Livre();
       try {
           preparedStatement = initialisationRequetePreparee( this.connection, SQL_SELECT_BOOK_FROM_ID, true, book_id);
           resultSet = preparedStatement.executeQuery();

           if(resultSet.next()) {
               livre = map_livre(resultSet);
           }
           resultSet.close();
           } catch(SQLException sqle) {
               System.err.println("" + sqle);
           }

       return livre;
    }
    public boolean insertCommands(Commandes command)
    {
        IDCommand+=1;
    	boolean ok = false;
        PreparedStatement preparedStatement = null;
         try {
          
            for(Entry<Livre, Integer>entry : command.getAllbooks().entrySet())
            {
                System.out.println("key : " + entry.getKey());
                System.out.println("value : " + entry.getValue());
                preparedStatement = initialisationRequetePreparee( this.connection, SQL_INSERT_LIST_PRODUCT_COMMAND, true, IDCommand,entry.getKey().getId(), entry.getValue());
                int statut = preparedStatement.executeUpdate();
             
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
              System.out.println( "Echec de l'enregistrement, aucune ligne ajoutee dans la table." );
            }
            else
            	ok = true;
            }   
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
         try {
             System.out.println("date : " + Dates.date());
                preparedStatement = initialisationRequetePreparee( this.connection, SQL_INSERT_COMMAND, true, IDCommand, command.getEmail(), Dates.date(),  command.getPrix_total());
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


    public boolean updateMdp( String mdp, String email )  {
		
		PreparedStatement preparedStatement = null;
		boolean update = false;
		try {
			
			/*
             * Preparation de la requete avec les objets passes en arguments
             * (ici adresse email et mdp) et execution.
             */
			//System.out.println("Email>" + email + "\nMDP>" + mdp);
			preparedStatement = initialisationRequetePreparee( connection, SQL_UPDATE_MDP, true, mdp, email );
			int statut = preparedStatement.executeUpdate();
			
			/* Analyse du statut retournÃ© par la requÃªte d'insertion */
			if ( statut == 0 ) {
				System.out.println( "Echec de la modification de l'utilisateur." );
			}
			else
				update = true;
			
		} catch ( SQLException e ) {
			System.err.println( e.getMessage() );
		}
		
		return update;
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
                                    resultSet.getInt("ACCES" )
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


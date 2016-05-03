//** Laura Salivonaite
package coursework;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;
        
public class LibraryData {

    private static Connection con = null;
    private static Statement stmt = null;

    static {

        try {
            String connectionUrl = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="
                    + new File("MusicDB.accdb").getAbsolutePath() + ";";

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection(connectionUrl);

        } 
        catch (SQLException sqle) {
            System.out.println("Sql Exception :" + sqle.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception :" + e.getMessage());
        }
    }

    public static String listAll() {
        String output = "";
        try {
            stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM LibraryTable");
            while (res.next()) { 
                output += res.getString(1) + " " + res.getString(2) + " - "
                        + res.getString(3) + "\n";
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return output;
    }

    public static String getName(String key) {
        try {
            stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM LibraryTable WHERE ID = '" + key + "'");
            if (res.next()) { 
                return res.getString(2);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static String getArtist(String key) {
        try {stmt = con.createStatement();
                    ResultSet res = stmt.executeQuery("SELECT * FROM LibraryTable WHERE ID = '" + key + "'");
                    if (res.next()) {
                    return res.getString(3);
            } else {
                   return null;
                    }} catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
    }

    public static int getRating(String key) {
              try {stmt = con.createStatement();
                    ResultSet res = stmt.executeQuery("SELECT * FROM LibraryTable WHERE ID = '" + key + "'");
                    if (res.next()) {
                    return res.getInt(4);
            } else {
                   return -1;
                    }} catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    public static int getPlayCount(String key) {
           try {
               stmt = con.createStatement();
                    ResultSet res = stmt.executeQuery("SELECT * FROM LibraryTable WHERE ID = '" + key + "'");
                    if (res.next()) {
                    return res.getInt(5);
            } else {
                   return -1;
                    }} catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }
    


    public static void setRating(String key, int rating) {
        String updateStr = "UPDATE LibraryTable SET rating = " + rating + " WHERE ID = '" + key + "'";
        System.out.println(updateStr);
        try {
            stmt.executeUpdate(updateStr);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void incrementPlayCount(String key) {
        int play= getPlayCount(key);
        
        if(key != null){
            play+=1;
        }  
        String updateStr = "UPDATE LibraryTable SET PlayCount = " + play + " WHERE ID = '" + key + "'";
        System.out.println(updateStr);
       
        try {
            
            stmt.executeUpdate(updateStr);
        } catch (Exception e) {
            System.out.println(e);
        }
    }   
    
    // close the database
    public static void close() {
        try {
            con.close();
        } catch (Exception e) {
            // this shouldn't happen
            System.out.println(e);
        }
    }
}

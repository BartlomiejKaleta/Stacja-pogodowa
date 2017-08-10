package pomiary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * Created by bartekkaleta on 27.07.2017.
 */
public class DbConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;


    public DbConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://sql.grosart.nazwa.pl:3306/grosart_1","login", "password" );
            st = con.createStatement();

            System.out.println("Połączono z serwerem mySQL");

        } catch (Exception ex){
            System.out.println("Błąd połączenia z serwerem: " + ex);
        }
    }

    public ResultSet getQuery(String query) {
        try {
            rs = st.executeQuery(query);

        } catch (Exception ex){
            System.out.println("Blad: " + ex);
        }
        return rs;
    }

    public void DbDisconnect() {
        try {
            if (con != null) {
                con.close();
                System.out.println("Rozłączono z serwerem mySQL");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    public ResultSet getRs() {
        return rs;
    }
}

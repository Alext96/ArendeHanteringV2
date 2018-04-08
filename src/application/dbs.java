package application;
import java.sql.*;
import javax.swing.JOptionPane;

public class dbs {

    Connection conn = null;
    public static Connection java_db(){
        try{
            Class.forName("org.sqlite.JDBC"); //använder mig av JDBC för att ansluta till databasen
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Swagmaster\\IdeaProjects\\testbajs\\databas.sqlite");
            return conn;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }

}
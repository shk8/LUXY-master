package DB;

/**
 *
 * @author Sherry Zhuang
 */

//Imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Class to connect to database
public class connectDB
{
    //Default variables that can be changed according to user's personal settings
    static final String DB = "jdbc:mysql://luxy.cqqk9altr3km.us-east-1.rds.amazonaws.com:3306/luxy";
    static final String USER = "root";
    static final String PW = "rootROOT";
    
    //Method to get connection
    public static Connection getConnection()
    {
        Connection con = null;
        
        //Connect to database with database name, username, password
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB, USER, PW);
        }
        //Handle errors
        catch (ClassNotFoundException | SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return con;
    }
} //End connectDB

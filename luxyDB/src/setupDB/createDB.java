package setupDB;

/**
 *
 * @author Sherry Zhuang
 */

//Imports
import java.sql.*;

public class createDB
{
    public void createDB(String url, String user, String pw)
    {
        try
        {
            //Connect to database
            System.out.println("***Connecting to database***");
            Connection con = DriverManager.getConnection(url, user, pw);
            Statement stmt = con.createStatement();

            //Create new database
            String query = "CREATE database luxy";
            stmt.executeUpdate(query);
            System.out.println("SUCCESS: Database created successfully.");
        } //Handle errors
        catch (SQLException se)
        {
            se.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
} //end createDB

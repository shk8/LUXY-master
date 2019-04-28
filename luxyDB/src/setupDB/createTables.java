package setupDB;

/**
 *
 * @author Sherry Zhuang
 */

//Imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class createTables
{
    public void createTables(String db, String user, String pw)
    {
        try
        {
            //Connect to database
            Connection con = DriverManager.getConnection(db, user, pw);
            //Read database script
            scriptRunner runner = new scriptRunner(con, false, false);
            //Create tables
            runner.runScript(new BufferedReader(new FileReader("sql/luxyDB_create.sql")));
            System.out.println("SUCCESS: Tables created.");
        } //Handle errors
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
} //end createTables

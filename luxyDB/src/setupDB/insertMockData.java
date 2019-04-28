package setupDB;

/**
 *
 * @author Sherry Zhuang
 */
//Imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class insertMockData
{
    public void insertMockData(String db, String user, String pw)
    {
        try
        {
            //Connect to database
            Connection con = DriverManager.getConnection(db, user, pw);
            //Read database scripts
            scriptRunner runner = new scriptRunner(con, false, false);
            //Insert mock data
            runner.runScript(new BufferedReader(new FileReader("sql/address_insert.sql")));
            runner.runScript(new BufferedReader(new FileReader("sql/unit_lease_insert.sql")));
            runner.runScript(new BufferedReader(new FileReader("sql/community_insert.sql")));
            runner.runScript(new BufferedReader(new FileReader("sql/unit_insert.sql")));
            runner.runScript(new BufferedReader(new FileReader("sql/renter_insert.sql")));
            runner.runScript(new BufferedReader(new FileReader("sql/application_insert.sql")));
            runner.runScript(new BufferedReader(new FileReader("sql/unit_leasing_log_insert.sql")));
            runner.runScript(new BufferedReader(new FileReader("sql/service_category_insert.sql")));
            runner.runScript(new BufferedReader(new FileReader("sql/service_request_insert.sql")));
            runner.runScript(new BufferedReader(new FileReader("sql/payment_log_insert.sql")));
            System.out.println("SUCCESS: Mock data inserted.");
        } //Handle errors
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
} //end insertMockData

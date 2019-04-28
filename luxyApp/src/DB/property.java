package DB;

/**
 *
 * @author Sherry Zhuang
 */

//Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//Class to handle property information
public class property
{
    //Method to insert, update, or delete entry
    public void insertUpdateDeleteProperty(char choice, Integer id, 
            String address, String city, String state, String country, String zip)
    {
        //Connect to database
        Connection con = connectDB.getConnection();
        PreparedStatement ps;

        //i for insert
        if (choice == 'i')
        {
            try
            {
                //Query statement
                ps = con.prepareStatement("INSERT INTO address(st_address, city,"
                        + " state, country, zip) VALUES (?,?,?,?,?)");
                ps.setString(1, address);
                ps.setString(2, city);
                ps.setString(3, state);
                ps.setString(4, country);
                ps.setString(5, zip);
                
                //Insert success
                if (ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null, "New Property Added.");
                }
              //Handle errors
            } catch (SQLException ex)
            {
                Logger.getLogger(property.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //u for update
        if (choice == 'u')
        {
            try
            {
                //Query statement
                ps = con.prepareStatement("UPDATE address SET st_address= ?, "
                        + "city = ?, state= ?,country= ?, zip= ? WHERE id = ?");
                ps.setString(1, address);
                ps.setString(2, city);
                ps.setString(3, state);
                ps.setString(4, country);
                ps.setString(5, zip);
                ps.setInt(6, id);
                
                //Update success
                if (ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null, "Property Data Updated.");
                }
              //Handle errors
            } catch (SQLException ex)
            {
                Logger.getLogger(property.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //d for delete
        if (choice == 'd')
        {
            try
            {
                //Query statement
                ps = con.prepareStatement("DELETE FROM address WHERE id = ?");
                ps.setInt(1, id);
                
                //Delete success
                if (ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null, "Property Deleted.");
                }
              //Handle errors
            } catch (SQLException ex)
            {
                Logger.getLogger(property.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Method to fill table
    public void fillPropertyJtable(JTable table, String valueToSearch)
    {
        //Connect to database
        Connection con = connectDB.getConnection();
        try
        {
            Statement stmt = con.createStatement();
            //Query statement
            ResultSet rs = stmt.executeQuery("SELECT * FROM address WHERE "
                    + "CONCAT(id, st_address, city, state, country, zip) LIKE "
                    + "'%" + valueToSearch + "%'");
            
            //Set model
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            //Add array objects
            while (rs.next())
            {
                Object[] row = new Object[6];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                model.addRow(row);
            }
          //Handle errors
        } catch (SQLException ex)
        {
            Logger.getLogger(property.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
} //End property

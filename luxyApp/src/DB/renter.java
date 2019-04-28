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

//Class to handle renter information
public class renter
{
    //Method to insert, update, or delete entry
    public void insertUpdateDeleteRenter(char choice, Integer id, String fname, 
            String lname, String idproof, String address)
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
                ps = con.prepareStatement("INSERT INTO renter(first_name, last_name,"
                        + " identity_proof, perm_address) VALUES (?,?,?,?)");
                ps.setString(1, fname);
                ps.setString(2, lname);
                ps.setString(3, idproof);
                ps.setString(4, address);
                
                //Insert success
                if (ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null, "New Renter Added.");
                }
              //Handle errors
            } catch (SQLException ex)
            {
                Logger.getLogger(renter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //u for update
        if (choice == 'u')
        {
            try
            {
                //Query statement
                ps = con.prepareStatement("UPDATE renter SET first_name= ?, "
                        + "last_name= ?, identity_proof = ?, perm_address= ? "
                        + "WHERE id = ?");
                ps.setString(1, fname);
                ps.setString(2, lname);
                ps.setString(3, idproof);
                ps.setString(4, address);
                ps.setInt(5, id);
                
                //Update success
                if (ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null, "Renter Data Updated.");
                }
              //Handle errors
            } catch (SQLException ex)
            {
                Logger.getLogger(renter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //d for delete
        if (choice == 'd')
        {
            try
            {
                //Query statement
                ps = con.prepareStatement("DELETE FROM renter WHERE id = ?");
                ps.setInt(1, id);
                
                //Delete success
                if (ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null, "Renter Deleted.");
                }
              //Handle errors
            } catch (SQLException ex)
            {
                Logger.getLogger(renter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void fillRenterJtable(JTable table, String valueToSearch)
    {
        //Connect to database
        Connection con = connectDB.getConnection();
        try
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM renter WHERE CONCAT"
                    + "(id, first_name, last_name, identity_proof, perm_address) "
                    + "LIKE '%" + valueToSearch + "%'");
            //Set model
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            //Add array objects
            while (rs.next())
            {
                Object[] row = new Object[5];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                model.addRow(row);
            }
          //Handle errors
        } catch (SQLException ex)
        {
            Logger.getLogger(renter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
} //End renter

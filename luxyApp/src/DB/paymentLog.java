package DB;

/**
 *
 * @author Sherry Zhuang
 */

//Imports
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//Class to handle payment logs
public class paymentLog
{
    //Method to insert, update, or delete entry
    public void insertUpdateDeletePaymentLog(char choice, Integer id, String leasingid,
            String status, String date, String type, String check, String transact)
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
                ps = con.prepareStatement("INSERT INTO payment_log"
                        + "(unit_leasing_log_id, payment_status, payment_date, "
                        + "payment_type, check_num, transact_num) VALUES "
                        + "(?,?,?,?,?,?)");
                ps.setString(1, leasingid);
                ps.setString(2, status);
                ps.setString(3, date);
                ps.setString(4, type);
                ps.setString(5, check);
                ps.setString(6, transact);

                //Insert success
                if (ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null, "New Payment Log Added.");
                }
                //Handle errors
            } catch (SQLException ex)
            {
                Logger.getLogger(paymentLog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //u for update
        if (choice == 'u')
        {
            try
            {
                //Query statement
                ps = con.prepareStatement("UPDATE payment_log SET "
                        + "unit_leasing_log_id = ?, payment_status = ?, "
                        + "payment_date = ?, payment_type = ?, check_num = ?, "
                        + "transact_num = ? WHERE id = ?");
                ps.setString(1, leasingid);
                ps.setString(2, status);
                ps.setString(3, date);
                ps.setString(4, type);
                ps.setString(5, check);
                ps.setString(6, transact);
                ps.setInt(7, id);

                //Update success
                if (ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null, "Payment Log Data Updated.");
                }

                //Handle errors
            } catch (SQLException ex)
            {
                Logger.getLogger(paymentLog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //d for delete
        if (choice == 'd')
        {
            try
            {
                ps = con.prepareStatement("DELETE FROM payment_log WHERE id = ?");
                ps.setInt(1, id);

                //Delete success
                if (ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null, "Payment Log Deleted.");
                }
                //Handle errors
            } catch (SQLException ex)
            {
                Logger.getLogger(paymentLog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Method to fill table
    public void fillPaymentLogJtable(JTable table, String valueToSearch)
    {
        //Connect to database
        Connection con = connectDB.getConnection();
        try
        {
            //Query statement
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM payment_log WHERE CONCAT "
                    + "(id, unit_leasing_log_id, payment_status, payment_date, "
                    + "payment_type, IFNULL(check_num, ''), IFNULL(transact_num, ''))"
                    + " LIKE '%" + valueToSearch + "%'");

            //Set model
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            //Add array objects
            while (rs.next())
            {
                Object[] row = new Object[7];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                model.addRow(row);
            }
          //Handle errors
        } catch (SQLException ex)
        {
            Logger.getLogger(paymentLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
} //End paymentLog

package Products;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 * Class <code>ConnectionMySQL</code> provides static methods for connection and working with MySQL database.
 * @author Jakub Cichy
 */
public class ConnectionMySQL 
{
    
    /**
     * Establishes connection to MySQL database.
     */
    public static Connection getConnection()
    {
        /**
         * Establishes a connection to the database.
         */
        Connection connection = null;
        try 
        {
            connection = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/ProjectDataBase", "root", "xxxxx");
            //JOptionPane.showMessageDialog(null, "Connected.");
            return connection;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Connection failed.");
            return null;
        }
    }
    
    /**
     * Retrives products data from database and saves them in ArrayList.
     * @param sortByAmount tells whether products are to be downloaded and stored in descending order of amount.
     * @return ArrayList of products.
     */
    public static ArrayList<Product> getProducts(boolean sortByAmount)
    {
        ArrayList<Product> productList = new ArrayList<Product>();
        Connection connection = getConnection();
        String query;
        
        if(sortByAmount) query = "SELECT * FROM products ORDER BY amount ASC";
        else query = "SELECT * FROM products";
   
        Statement st;
        ResultSet rs;

        try 
        {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Product product;
            
            while(rs.next())
            {
                product = new Product(rs.getInt("id"), rs.getString("name"), rs.getFloat("price"), rs.getInt("amount"), rs.getString("last_update"));
                productList.add(product);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }
  
}

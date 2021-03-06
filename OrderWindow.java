package Products;

import static Products.MainWindow.order;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class OrderWindow extends javax.swing.JDialog {
    
    /**
     * Creates new form OrderWindow.
     */
    public OrderWindow(java.awt.Frame parent, boolean modal, Order order) {
        super(parent, modal);
        initComponents();
        ShowProductsInJTable(order);
    }
    
    /**
     * Path where order file will be saved.
     */
    String savePath = null;
    
    /**
     * Allows user to choose path using <code>JFileChooser</code>.
     * @return choosen path
     */
    public String ChoosePath() {
        JFileChooser pathChooser = new JFileChooser();
        pathChooser.setCurrentDirectory(new java.io.File("./Users/jakubcichy"));
        pathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = pathChooser.showDialog(this, "Choose path");
        if(result == JFileChooser.APPROVE_OPTION)
        {
            return pathChooser.getSelectedFile().getAbsolutePath();
        }
        else
        {
            System.out.println("No path selected.");
            return null;
        }
    }
    
    /**
     * Fills the ordered products table with data from <code>Order</code> object.
     * @param order
     */
    public void ShowProductsInJTable(Order order) {
        DefaultTableModel model = (DefaultTableModel)orderedProductsTable.getModel();
        // clear JTable content
        model.setRowCount(0);
        Object[] row = new Object[3];
        for(int i = 0; i < order.getSize(); i++)
        {
            row[0] = order.getID(i);
            row[1] = order.getName(i);
            row[2] = order.getAmount(i);
            
            model.addRow(row);
        }
    } 
    
    /**
     * Creates order containg data from table.
     * @return String representing order
     */
    public String CreateOrderFromTable() {
        return "*** Order " + MainWindow.getCurrentDate() + " (id, name, amount) ***\n" + order.toString();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        orderedProductsTable = new javax.swing.JTable();
        removeSelectedButton = new javax.swing.JButton();
        generateOrderButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Order");

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));

        orderedProductsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(orderedProductsTable);

        removeSelectedButton.setText("Remove selected");
        removeSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSelectedButtonActionPerformed(evt);
            }
        });

        generateOrderButton.setText("Generate order");
        generateOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateOrderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(removeSelectedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(generateOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeSelectedButton)
                    .addComponent(generateOrderButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void removeSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSelectedButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel)orderedProductsTable.getModel();
        try
        {
            int selectedRow = orderedProductsTable.getSelectedRow();
            model.removeRow(selectedRow);
            MainWindow.order.deleteProduct(selectedRow);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "No selected product.");
        }
    }//GEN-LAST:event_removeSelectedButtonActionPerformed

    private void generateOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateOrderButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel)orderedProductsTable.getModel();
        if(model.getRowCount() != 0)
        {
            savePath = ChoosePath();
            if(savePath != null)
            {
                try 
                {
                    File orderFile = new File(savePath + "/", "order_" + MainWindow.getCurrentDate());
                    try (Writer writer = new FileWriter(orderFile)) {
                        writer.write(CreateOrderFromTable());
                    }
                    this.dispose();
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(OrderWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "No path selected.");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Order list is empty.");
        }
    }//GEN-LAST:event_generateOrderButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton generateOrderButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable orderedProductsTable;
    private javax.swing.JButton removeSelectedButton;
    // End of variables declaration//GEN-END:variables
}

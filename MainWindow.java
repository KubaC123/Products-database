package Products;

import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public final class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow.
     */
    public MainWindow() {
        this.amountToOrder = 0;
        this.position = 0;
        initComponents();
        this.setTitle("Products database");
        ConnectionMySQL.getConnection();
        ShowProductsInJTable(ConnectionMySQL.getProducts(sortByAmountCheckBox.isSelected()));
        ColorLowAmountCells();
    }
    
    /**
     * Index of the displayed product.
     */
    private int position;
    
    /**
     * Amount of product that we want to order.
     */
    private int amountToOrder;
    
    /**
     * List of products to order.
     */
    public static Order order = new Order();
    
    
    public String getPassword()
    {
        JPasswordField passwordField = new JPasswordField();
        int ok = JOptionPane.showConfirmDialog(this, passwordField, "Enter password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if(ok == JOptionPane.OK_OPTION)
        {
            return passwordField.getPassword().toString();
        }
        else 
        {
            return null;
        }
    }
    
    /**
     * Fills the product table with data.
     * @param products ArrayList of products
     */
    public void ShowProductsInJTable(ArrayList<Product> products) {
        DefaultTableModel model = (DefaultTableModel)productsTable.getModel();
        // clear JTable content
        model.setRowCount(0);
        Object[] row = new Object[5];
        for(int i = 0; i < products.size(); i++)
        {
            row[0] = products.get(i).getId();
            row[1] = products.get(i).getName();
            row[2] = products.get(i).getPrice();
            row[3] = products.get(i).getAmount(); 
            row[4] = products.get(i).getModifyDate();
            
            model.addRow(row);
        }
    }
    
    /**
     * Shows product data in text fields.
     * @param index of product from list
     */
    public void ShowProduct(int index) {
        try 
        {
            productIDTextField.setText(Integer.toString(ConnectionMySQL.getProducts(sortByAmountCheckBox.isSelected()).get(index).getId()));
            productNameTextField.setText(ConnectionMySQL.getProducts(sortByAmountCheckBox.isSelected()).get(index).getName());
            productPriceTextField.setText(Float.toString(ConnectionMySQL.getProducts(sortByAmountCheckBox.isSelected()).get(index).getPrice()));
            productAmountTextField.setText(Integer.toString(ConnectionMySQL.getProducts(sortByAmountCheckBox.isSelected()).get(index).getAmount()));
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    public void TableRowSelected() {
        productsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) 
            {
                ShowProduct(position);
            }
        });
    }
    
    /**
     * Colors amount cell in product table if it's value is less than <code>smallAmount</code> declared in <code>Product</code> class.
     */
    public void ColorLowAmountCells() {
        productsTable.setDefaultRenderer(Double.class, new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setForeground(((Integer) value) > Product.smallAmount ? Color.GREEN : Color.RED);
                return c;
            }
        });
    }
    
    /**
     * Checks if input is correct or not. 
     * @return false if input is incorrect or missing, else returns true
     */
    public boolean CheckInputs() {
        if(productIDTextField.getText() == null || productNameTextField.getText() == null) return false;
        else
        {
            try 
            {
                Float.parseFloat(productPriceTextField.getText());
                Integer.parseInt(productAmountTextField.getText());
                return true;
            } 
            catch (NumberFormatException e) 
            {
                return false;
            }
        }
    }
    
    /**
     * Gets current date.
     * @return current date in format yyyy-MM-dd
     */
    public static String getCurrentDate() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }
    
    /**
     * Clears input fields.
     */
    public void ClearTextFields() {
        productIDTextField.setText("");
        productNameTextField.setText("");
        productPriceTextField.setText("");
        productAmountTextField.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dataEditPanel = new javax.swing.JPanel();
        productNameLabel = new javax.swing.JLabel();
        productPriceLabel = new javax.swing.JLabel();
        productAmountLabel = new javax.swing.JLabel();
        productIDLabel = new javax.swing.JLabel();
        productNameTextField = new javax.swing.JTextField();
        productPriceTextField = new javax.swing.JTextField();
        productAmountTextField = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        productIDTextField = new javax.swing.JTextField();
        insertButton = new javax.swing.JButton();
        addToOrderButton = new javax.swing.JButton();
        editOrderButton = new javax.swing.JButton();
        productsTablePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productsTable = new javax.swing.JTable();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        sortByAmountCheckBox = new javax.swing.JCheckBox();
        firstButton = new javax.swing.JButton();
        lastButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dataEditPanel.setBackground(new java.awt.Color(153, 204, 255));

        productNameLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        productNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        productNameLabel.setText("Name");

        productPriceLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        productPriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        productPriceLabel.setText("Price");

        productAmountLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        productAmountLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        productAmountLabel.setText("Amount");

        productIDLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        productIDLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        productIDLabel.setText("ID");

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        insertButton.setText("Insert");
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });

        addToOrderButton.setText("Add to order");
        addToOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToOrderButtonActionPerformed(evt);
            }
        });

        editOrderButton.setText("Edit order");
        editOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editOrderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dataEditPanelLayout = new javax.swing.GroupLayout(dataEditPanel);
        dataEditPanel.setLayout(dataEditPanelLayout);
        dataEditPanelLayout.setHorizontalGroup(
            dataEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataEditPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(insertButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dataEditPanelLayout.createSequentialGroup()
                        .addGroup(dataEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(productAmountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(productPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(productNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(productIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dataEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(productNameTextField)
                            .addComponent(productPriceTextField)
                            .addComponent(productAmountTextField)
                            .addComponent(productIDTextField)))
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addToOrderButton, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(editOrderButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        dataEditPanelLayout.setVerticalGroup(
            dataEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataEditPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dataEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dataEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dataEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(insertButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addToOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        productsTablePanel.setBackground(new java.awt.Color(153, 204, 255));
        productsTablePanel.setPreferredSize(new java.awt.Dimension(423, 2000));

        productsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Price", "Amount", "Updated"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(productsTable);
        if (productsTable.getColumnModel().getColumnCount() > 0) {
            productsTable.getColumnModel().getColumn(2).setResizable(false);
            productsTable.getColumnModel().getColumn(3).setResizable(false);
        }

        previousButton.setBackground(new java.awt.Color(255, 255, 255));
        previousButton.setText("Previous");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        nextButton.setText("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        sortByAmountCheckBox.setText("Sort by amount");
        sortByAmountCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortByAmountCheckBoxActionPerformed(evt);
            }
        });

        firstButton.setText("First");
        firstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstButtonActionPerformed(evt);
            }
        });

        lastButton.setText("Last");
        lastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout productsTablePanelLayout = new javax.swing.GroupLayout(productsTablePanel);
        productsTablePanel.setLayout(productsTablePanelLayout);
        productsTablePanelLayout.setHorizontalGroup(
            productsTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productsTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(productsTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, productsTablePanelLayout.createSequentialGroup()
                        .addComponent(sortByAmountCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, productsTablePanelLayout.createSequentialGroup()
                        .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(firstButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(lastButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        productsTablePanelLayout.setVerticalGroup(
            productsTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sortByAmountCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(productsTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previousButton)
                    .addComponent(nextButton)
                    .addComponent(firstButton)
                    .addComponent(lastButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dataEditPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productsTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(productsTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                    .addComponent(dataEditPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        if(CheckInputs())
        {
            Connection connection = ConnectionMySQL.getConnection();
            try 
            {
                String updateQuery = "UPDATE products SET name = ?, price = ?, amount = ?, last_update = ? WHERE id = ?";
                PreparedStatement ps = connection.prepareStatement(updateQuery);
                ps.setString(1, productNameTextField.getText());
                ps.setFloat(2, Float.parseFloat(productPriceTextField.getText()));
                ps.setInt(3, Integer.parseInt(productAmountTextField.getText()));
                ps.setString(4, getCurrentDate());
                ps.setInt(5, Integer.parseInt(productIDTextField.getText()));
                ps.executeUpdate();
                ShowProductsInJTable(ConnectionMySQL.getProducts(sortByAmountCheckBox.isSelected()));
                JOptionPane.showMessageDialog(this, "Updated succesfully.");
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        position--;
        if(position < 0) position = ConnectionMySQL.getProducts(sortByAmountCheckBox.isSelected()).size() - 1;
        ShowProduct(position);
    }//GEN-LAST:event_previousButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if(!productIDTextField.getText().equals(""))
        {
            try 
            {
                java.sql.Connection con = ConnectionMySQL.getConnection();
                PreparedStatement ps;
                ps = con.prepareStatement("DELETE FROM products WHERE id = ?");
                ps.setInt(1, Integer.parseInt(productIDTextField.getText()));
                ps.executeUpdate();
                ClearTextFields();
                ShowProductsInJTable(ConnectionMySQL.getProducts(sortByAmountCheckBox.isSelected()));
                JOptionPane.showMessageDialog(this, "Deleted succesfully.");
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Product not deleted.");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please enter the product ID.");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed
       if(CheckInputs())
        {
            try 
            {
                Connection con = ConnectionMySQL.getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO products(id, name, price, amount, last_update) VALUES (?, ?, ?, ?, ?)");
                ps.setInt(1, Integer.parseInt(productIDTextField.getText()));
                ps.setString(2, productNameTextField.getText());
                ps.setFloat(3, Float.parseFloat(productPriceTextField.getText()));
                ps.setInt(4, Integer.parseInt(productAmountTextField.getText()));
                ps.setString(5, getCurrentDate());
                ps.executeUpdate();
                ClearTextFields();
                ShowProductsInJTable(ConnectionMySQL.getProducts(sortByAmountCheckBox.isSelected()));
                JOptionPane.showMessageDialog(this, "Product inserted.");
            } 
            catch (Exception e) 
            {
                JOptionPane.showMessageDialog(this, "One or more fields are empty.");
            }
        }
    }//GEN-LAST:event_insertButtonActionPerformed

    private void sortByAmountCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortByAmountCheckBoxActionPerformed
        ShowProductsInJTable(ConnectionMySQL.getProducts(((JCheckBox)evt.getSource()).isSelected()));
    }//GEN-LAST:event_sortByAmountCheckBoxActionPerformed

    private void addToOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToOrderButtonActionPerformed
        if(CheckInputs())
        {  
            String inputString = JOptionPane.showInputDialog(this, "Enter amount:");
            try 
            {
                amountToOrder = Integer.parseInt(inputString);
                order.addProduct(Integer.parseInt(productIDTextField.getText()), productNameTextField.getText(), amountToOrder);
            } 
            catch (NumberFormatException e) 
            {
                JOptionPane.showMessageDialog(this, "Amount must be a number.");
            }
        }
    }//GEN-LAST:event_addToOrderButtonActionPerformed

    private void firstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstButtonActionPerformed
        position = 0;
        ShowProduct(position);
    }//GEN-LAST:event_firstButtonActionPerformed

    private void lastButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastButtonActionPerformed
        position = ConnectionMySQL.getProducts(sortByAmountCheckBox.isSelected()).size() - 1;
        ShowProduct(position);
    }//GEN-LAST:event_lastButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        position++;
        if(position > ConnectionMySQL.getProducts(sortByAmountCheckBox.isSelected()).size() - 1) position = 0;
        ShowProduct(position);
    }//GEN-LAST:event_nextButtonActionPerformed

    private void productsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsTableMouseClicked
        DefaultTableModel model = (DefaultTableModel)productsTable.getModel();
        int selectedRow = productsTable.getSelectedRow();
        
        productIDTextField.setText(model.getValueAt(selectedRow, 0).toString());
        productNameTextField.setText(model.getValueAt(selectedRow, 1).toString());
        productPriceTextField.setText(model.getValueAt(selectedRow, 2).toString());
        productAmountTextField.setText(model.getValueAt(selectedRow, 3).toString());
    }//GEN-LAST:event_productsTableMouseClicked

    private void editOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editOrderButtonActionPerformed
        JDialog orderDialog = new OrderWindow(this, rootPaneCheckingEnabled, order);
        orderDialog.setLocationRelativeTo(this);
        orderDialog.setVisible(true);
    }//GEN-LAST:event_editOrderButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToOrderButton;
    private javax.swing.JPanel dataEditPanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editOrderButton;
    private javax.swing.JButton firstButton;
    private javax.swing.JButton insertButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lastButton;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton previousButton;
    private javax.swing.JLabel productAmountLabel;
    private javax.swing.JTextField productAmountTextField;
    private javax.swing.JLabel productIDLabel;
    private javax.swing.JTextField productIDTextField;
    private javax.swing.JLabel productNameLabel;
    private javax.swing.JTextField productNameTextField;
    private javax.swing.JLabel productPriceLabel;
    private javax.swing.JTextField productPriceTextField;
    private javax.swing.JTable productsTable;
    private javax.swing.JPanel productsTablePanel;
    private javax.swing.JCheckBox sortByAmountCheckBox;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}

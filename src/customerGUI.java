import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class customerGUI {
    private JComboBox<String> productTypeComboBox;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private JTextArea productDetailsTextArea;
    private JButton addToCartButton;
    private JButton viewCartButton;

    private List<Product> Product_List;

    public Product selectedP = null;
    private  ShoppingCart cart;


    // Sample column names
    private String[] columnNames = {"<html><b>Product ID</b></html>", "<html><b>Name</b></html>", "<html><b>Category</b></html>", "<html><b>Price(€)</b></html>", "<html><b>Info</b></html>"};

    public customerGUI(List<Product> Product_List) {
        this.Product_List = Product_List;
        JFrame frame = new JFrame("Westminster Shopping Center");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

        // Initialize components
        productTypeComboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        tableModel = new DefaultTableModel(null, columnNames);
        productTable = new JTable(tableModel);
        productDetailsTextArea = new JTextArea(5, 30);
        addToCartButton = new JButton("Add to Shopping Cart");
        viewCartButton = new JButton("Shopping Cart");

        // Set up the layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select Product Category"));
        topPanel.add(productTypeComboBox);
        topPanel.add(viewCartButton);

        panel.add(topPanel, BorderLayout.NORTH);

        JScrollPane tableScrollPane = new JScrollPane(productTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("<html><b>Selected Product - Details:</b></html>"));
        bottomPanel.add(productDetailsTextArea);
        bottomPanel.add(addToCartButton);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
        listUpdate("All");
         cart = new ShoppingCart();
        // Attach event listeners
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCart();
            }
        });

        viewCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewShoppingCart();
            }
        });
        productTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected value from the combo box
                String selectedProductType = (String) productTypeComboBox.getSelectedItem();

                // Call the listUpdate method with the selected product type
                listUpdate(selectedProductType);
            }
        });
        productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Get the selected row
                    int selectedRow = productTable.getSelectedRow();

                    if (selectedRow != -1) {
                        // Get the details of the clicked item
                        String selectedProductId = (String) productTable.getValueAt(selectedRow, 0);
                        selectedP = null;
                        for(Product p:Product_List) {
                            if(p.getProductId().equals(selectedProductId)) {
                                selectedP = p;
                            }
                        }
                        String productName = (String) productTable.getValueAt(selectedRow, 1);
                        String category = (String) productTable.getValueAt(selectedRow, 2);
                        String features;
                        if(category.equals("Electronics")) {
                            features = "Brand: " + selectedP.getValue1() + "\n"
                                    + "Warranty Period: " + selectedP.getValue2() + "\n";
                        } else {
                            features = "Size: " + selectedP.getValue1() + "\n"
                                    + "Color: " + selectedP.getValue2() + "\n";
                        }

                        // Display the details in the productDetailsTextArea
                        String detailsText = "Product ID: " + selectedProductId + "\n"
                                + "Category: " + category + "\n"
                                + "Name: " + productName + "\n"
                                + features + "Items Available: " + selectedP.getAvailableItems()   ;
                        productDetailsTextArea.setText(detailsText);
                    }
                }
            }
        });

    }

    private void addToCart() {
        if(selectedP!=null){
            cart.addProduct(selectedP);
        }


    }

    private void viewShoppingCart() {
       cart.openGui();
    }
    public void listUpdate(String category) {

        tableModel.setRowCount(0);

        // Add updated data to the table
        for (Product product : Product_List) {
            if(!category.equals(product.getCategory()) && !category.equals("All")){continue;}
            Object[] rowData = {product.getProductId(), product.getProductName(), product.getCategory(), product.getPrice(), product.productInfo()};
            tableModel.addRow(rowData);
        }

        // Optional: Auto-resize columns to fit content
        for (int i = 0; i < productTable.getColumnCount(); i++) {
            TableColumn column = productTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(150);  // Set your preferred column width
        }
    }

}

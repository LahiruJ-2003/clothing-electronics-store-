import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class shoppingCartgui{
    private JFrame frame;
    private DefaultTableModel tableModel;
    private JTable table;
    private JLabel totalLabel, firstPurchaseDiscountLabel, categoryDiscountLabel, finalTotalLabel;
    private JButton addButton;

    public shoppingCartgui() {
        frame = new JFrame("Shopping Cart");
        frame.setTitle("Shopping Cart");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialize table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("<html><b>Product Name</b></html>");
        tableModel.addColumn("<html><b>Quantity</b></html>");
        tableModel.addColumn("<html><b>Price</b></html>");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Initialize labels
        totalLabel = new JLabel("Total: $0.00");
        firstPurchaseDiscountLabel = new JLabel("First Purchase Discount: $0.00");
        categoryDiscountLabel = new JLabel("Category Discount: $0.00");
        finalTotalLabel = new JLabel("Final Total: $0.00");

        // Set layout and add components
        frame.setLayout(new BorderLayout());

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel labelPanel = new JPanel(new GridLayout(4, 1));
        labelPanel.add(totalLabel);
        labelPanel.add(firstPurchaseDiscountLabel);
        labelPanel.add(categoryDiscountLabel);
        labelPanel.add(finalTotalLabel);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(tablePanel);
        panel.add(labelPanel);

        frame.add(panel, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }

    private void updateTotalLabels() {
        // Calculate total, first purchase discount, category discount, and final total
        double total = 0;
        int rowCount = tableModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            double price = (double) tableModel.getValueAt(i, 1);
            int quantity = (int) tableModel.getValueAt(i, 2);
            total += price * quantity;
        }

        double firstPurchaseDiscount = 0.1 * total; // Assuming a 10% first purchase discount
        double categoryDiscount = 0.05 * total; // Assuming a 5% category discount

        double finalTotal = total - firstPurchaseDiscount - categoryDiscount;

        // Update labels
        totalLabel.setText("Total: $" + String.format("%.2f", total));
        firstPurchaseDiscountLabel.setText("First Purchase Discount: $" + String.format("%.2f", firstPurchaseDiscount));
        categoryDiscountLabel.setText("Category Discount: $" + String.format("%.2f", categoryDiscount));
        finalTotalLabel.setText("Final Total: $" + String.format("%.2f", finalTotal));
        updateCartTable();

    }
    public void updateCartTable() {
        for (Map.Entry<Product, Integer> entry : ShoppingCart.myHashMap.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            Object[] rowData = {product.getProductName(), quantity, product.getPrice()*quantity+ "£"};
            tableModel.addRow(rowData);
        }
    }
    public double totalPrice() {
        double totalPrice = 0;
        for (Product product : ShoppingCart.products) {
            totalPrice += product.getPrice();

        }
        return totalPrice;
    }
    public double catDisc(){
        double totalPrice = 0;
        double disc = 0;
        if(ShoppingCart.catDisc[0]>=3 || ShoppingCart.catDisc[1]>=3){
              for (Product product : ShoppingCart.products) {
                totalPrice += product.getPrice();

            }
              disc = totalPrice*0.2;

        }
        return disc;
    }
    public void finalTot(){
        double finalTotal = totalPrice() - catDisc();
        finalTotalLabel.setText("Final Total: $" + String.format("%.2f", finalTotal));
        totalLabel.setText("Total: $" + String.format("%.2f", totalPrice()));
        categoryDiscountLabel.setText("Category Discount: $" + String.format("%.2f", catDisc()));

    }
}
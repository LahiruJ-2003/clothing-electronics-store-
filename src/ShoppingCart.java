//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: 20221791
//UOW Number: w1985471
//Date: 17/01/2024
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    public static List<Product> products;
    public static Map<Product, Integer> myHashMap = new HashMap<>();
    public static int[] catDisc = {0,0};

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }


    public void addProduct(Product product) {
        if(product.getCategory().equals("Electronics")){
            catDisc[0]++;

        }else{
            catDisc[1]++;
        }
        products.add(product);
        myHashMap.put(product,myHashMap.getOrDefault(product,0)+1);
    }

    // Method to remove a product from the cart
    public void removeProduct(Product product) {
        products.remove(product);
    }

    // Method to calculate the total cost of products in the cart
    public double calculateTotalCost() {
        double totalCost = 0;
        for (Product product : products) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }

    public void openGui(){
    shoppingCartgui openGui = new shoppingCartgui();
       openGui.updateCartTable();
       openGui.finalTot();
           }

}

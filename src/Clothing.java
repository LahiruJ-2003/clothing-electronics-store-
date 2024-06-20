//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: 20221791
//UOW Number: w1985471
//Date: 17/01/2024
public class Clothing extends Product {
    private String size;
    private String color;

    public Clothing(String productId, String productName, int availableItems, double price, String size, String color) {
        super(productId, productName, availableItems, price);
        this.size = size;
        this.color = color;
    }

    // Getters and Setters for additional instance variables
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Override abstract method from the superclass
    @Override
    public void displayInfo() {
        System.out.println("Clothing Info: " + getProductId() + " - " + getProductName());
    }

    @Override
    public String getCategory() {
        return "Clothing";
    }

    @Override
    public String productInfo() {
        return size + ", " + color;
    }
       public String getValue1(){
        return size;
    }
    public String getValue2(){
        return color;
    }
}

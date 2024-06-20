//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: 20221791
//UOW Number: w1985471
//Date: 17/01/2024
public class Electronics extends Product {
    private String brand;
    private int warrantyPeriod;

    public Electronics(String productId, String productName, int availableItems, double price, String brand, int warrantyPeriod) {
        super(productId, productName, availableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    // Getters and Setters for additional instance variables
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    // Override abstract method from the superclass
    @Override
    public void displayInfo() {
        System.out.println("Electronics Info: " + getProductId() + " - " + getProductName());
    }

    @Override
    public String getCategory() {
        return "Electronics";
    }

    @Override
    public String productInfo() {
        return brand + ", " + warrantyPeriod + " weeks warranty";
    }
    public String getValue1(){
        return brand;
    }
    public String getValue2(){
        return String.valueOf(warrantyPeriod);
    }
}

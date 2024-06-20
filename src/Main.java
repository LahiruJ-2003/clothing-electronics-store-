//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: 20221791
//UOW Number: w1985471
//Date: 17/01/2024
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List productList = new ArrayList<Product>();
    public static void main(String[] args) {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager(productList);
        while(true) {
            System.out.println("Enter Instance Type:\n1. Manager\n2. Customer\n0. Exit\n Enter choice (1,2 or 0):");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch(option) {
                case 1:
                    shoppingManager.displayMenu();
                    break;
                case 2:
                    customerGUI customer = new customerGUI(productList);
                    break;
                case 0:
                    System.out.println("Exiting..");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice\n\n");
                    break;
            }
        }
    }
}

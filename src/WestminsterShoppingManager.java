//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: 20221791
//UOW Number: w1985471
//Date: 17/01/2024
import java.io.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class WestminsterShoppingManager {
    private static final int Max_Products = 50;
    private List<Product> Product_List;

    public WestminsterShoppingManager(List<Product> Product_List) {
        this.Product_List = Product_List;
        load_Products_From_File();
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            try {
                System.out.println("\n------ Westminster Shopping Manager Menu ------");
                System.out.println("1. Add a new product to the system");
                System.out.println("2. Delete a product from the system");
                System.out.println("3. Print the list of products in the system");
                System.out.println("4. Save products to a file");
                System.out.println("5. Exit");
                System.out.print("Enter your choice (1-5):");

                String input = scanner.nextLine();
                choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        add_Product();
                        break;
                    case 2:
                        delete_Product();
                        break;
                    case 3:
                        Product_List();
                        break;
                    case 4:
                        save_Products_To_File();
                        break;
                    case 5:
                        System.out.println("Exiting Westminster Shopping Manager. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("**-Error-**");
                System.out.println("Please enter a valid integer between 1 and 5.");
                choice = 0;
            }
        } while (choice != 5);

    }

    //------------------------------------------------------------------------------------------------------------------------------\\
    private void add_Product() {
        if (Product_List.size() < Max_Products) {
            System.out.println("Select the type of product to add:");
            System.out.println("1. Electronics");
            System.out.println("2. Clothing");
            Scanner scanner = new Scanner(System.in);

            int product_Type = 0;

            while (true) {
                try {
                    System.out.print("Enter your choice (1 or 2): ");
                    String input = scanner.nextLine();
                    product_Type = Integer.parseInt(input);

                    if (product_Type == 1 || product_Type == 2) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter a valid integer (1 or 2).");
                }
            }

            switch (product_Type) {
                case 1:
                    add_Electronics();
                    break;
                case 2:
                    add_Clothing();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } else {
            System.out.println("Maximum number of products reached. Cannot add more.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------\\
    private void add_Electronics() {
        Scanner scanner = new Scanner(System.in);
    try {
        System.out.print("Enter Electronics ID: ");
        String productId = scanner.nextLine();

        System.out.print("Enter Electronics Name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter Number of Available Electronics: ");
        int availableItems = scanner.nextInt();

        System.out.print("Enter Electronics Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Electronics Brand: ");
        String brand = scanner.nextLine();

        System.out.print("Enter Electronics Warranty Period (in months): ");
        int warrantyPeriod = scanner.nextInt();

        Electronics electronics = new Electronics(productId, productName, availableItems, price, brand, warrantyPeriod);
        Product_List.add(electronics);
        System.out.println("Electronics added successfully.");
        } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid value");
        scanner.nextLine();
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------\\
    private void add_Clothing() {
        Scanner scanner = new Scanner(System.in);
    try {
        System.out.print("Enter Clothing ID: ");
        String productId = scanner.nextLine();

        System.out.print("Enter Clothing Name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter Number of Available Clothing: ");
        int availableItems = scanner.nextInt();

        System.out.print("Enter Clothing Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Clothing Size: ");
        String size = scanner.nextLine();

        System.out.print("Enter Clothing Color: ");
        String color = scanner.nextLine();

        Clothing clothing = new Clothing(productId, productName, availableItems, price, size, color);
        Product_List.add(clothing);
        System.out.println("Clothing added successfully.");
        } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid value");
        scanner.nextLine();
    }
    }

    //------------------------------------------------------------------------------------------------------------------------------\\
    private void delete_Product() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product ID to delete: ");
        String productId = scanner.nextLine();

        Product productToRemove = null;
        for (Product product : Product_List) {
            if (product.getProductId().equals(productId)) {
                productToRemove = product;
                break;
            }
        }

        if (productToRemove != null) {
            Product_List.remove(productToRemove);
            System.out.println("Product removed successfully.");
            System.out.println("Deleted Product Information:");
            productToRemove.displayInfo();
            System.out.println("Total number of products left: " + Product_List.size());
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------\\
    private void Product_List() {
        if (Product_List.isEmpty()) {
            System.out.println("Product List is empty!");
            return;
        }
        Collections.sort(Product_List, Comparator.comparing(Product::getProductId));

        System.out.println("\n------ Product List ------");
        for (Product product : Product_List) {
            product.displayInfo();
            if (product instanceof Electronics) {
                System.out.println("Product Type: Electronics");
            } else if (product instanceof Clothing) {
                System.out.println("Product Type: Clothing");
            }
            System.out.println("------------------------");
        }

    }

    //------------------------------------------------------------------------------------------------------------------------------\\
    private void save_Products_To_File() {
        try (FileWriter writer = new FileWriter("products.txt")) {
            for (Product product : Product_List) {
                if (product instanceof Electronics) {
                    writer.write("Electronics,");
                } else if (product instanceof Clothing) {
                    writer.write("Clothing,");
                }

                writer.write(product.getProductId() + ",");
                writer.write(product.getProductName() + ",");
                writer.write(product.getAvailableItems() + ",");
                writer.write(product.getPrice() + ",");

                if (product instanceof Electronics) {
                    writer.write(((Electronics) product).getBrand() + ",");
                    writer.write(((Electronics) product).getWarrantyPeriod() + "\n");
                } else if (product instanceof Clothing) {
                    writer.write(((Clothing) product).getSize() + ",");
                    writer.write(((Clothing) product).getColor() + "\n");
                }
            }
            System.out.println("Products saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving products to file:" + e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------\\
       public void load_Products_From_File() {
        try {File myObj = new File("products.txt");
             Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] arrayline = line.split(",");
                if (arrayline[0].equals("Electronics")) {
                    String productId = arrayline[1].trim();
                    String productName = arrayline[2].trim();
                    int availableItems = Integer.parseInt(arrayline[3].trim());
                    double price = Double.parseDouble(arrayline[4].trim());
                    String brand = arrayline[5].trim();
                    int warrantyPeriod = Integer.parseInt(arrayline[6].trim());

                    Electronics electronics = new Electronics(productId, productName, availableItems, price, brand, warrantyPeriod);
                    Product_List.add(electronics);
                } else if (arrayline[0].equals("Clothing")) {
                    String productId = arrayline[1].trim();
                    String productName = arrayline[2].trim();
                    int availableItems = Integer.parseInt(arrayline[3].trim());
                    double price = Double.parseDouble(arrayline[4].trim());
                    String size = arrayline[5].trim();
                    String color = arrayline[6].trim();

                    Clothing clothing = new Clothing(productId, productName, availableItems, price, size, color);
                    Product_List.add(clothing);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading products from file: " + e.getMessage());
        }
    }
}






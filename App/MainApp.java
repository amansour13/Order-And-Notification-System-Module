package App;


import App.AccountManager.*;

public class MainApp {

    private IAccountManager accountManager;

    // Account Manager functions
    private void register() {
        accountManager.register();
    }

    private void login() {
        if (accountManager.login()) {
            loggedInMenu();
        }
    }

    private void addBalance() {
        System.out.println("Enter the amount you want to add");
        float amount = Scan.scanner.nextFloat();
        Scan.scanner.nextLine();
        accountManager.addBalance(amount);

        System.out.println("Added Successfully, new balance: ");
    }

    private void displayProducts() {
        // TODO: get the products from the backend
        System.out.println("DISPLAYING THE PRODUCTS");
    }

    private void addProductToOrder() {
        System.out.println("Enter the product id");
        int productId = Scan.scanner.nextInt();
        // TODO: get the product from the backend

        System.out.println("Added the product to the cart");
    }

    private void placeOrder() {
        //TODO: send the order to the backend
    }

    private void shipOrder() {
        //TODO: send the order to the backend
    }

    private void cancelOrder() {
        // TODO: send the order to the backend
    }

    private void displayOrderDetails() {
        //TODO: get the order details from the backend
    }

    private void showItemStock() {
        // TODO: get the item stock from the backend
    }

    private void showMostNotifyEmail() {
        // TODO: get the most notify email from the backend
    }

    private void showMostNotifyPhone() {
        // TODO: get the most notify phone from the backend
    }

    private void showMostNotifyTemp() {
        // TODO: get the most notify temp from the backend
    }
    
    private void loggedInMenu() {
        String command = "";
        String commandsMenu = "1- Add to your balance\n2- Display products\n3- Add product to order\n4- Place order\n5- Cancel order\n6- Display order details\n7- Show item stock\n0- quit";
        boolean run = true;

        while (run) {
            try {
                System.out.println(commandsMenu);
                command = Scan.scanner.nextLine();
                switch (command) {
                    case "1":
                    addBalance();
                    break;
                    case "2":
                    displayProducts();
                    break;
                    case "3":
                    addProductToOrder();
                    break;
                    case "4":
                    placeOrder();
                    break;
                    case "5":
                    cancelOrder();
                    break;
                case "6":
                    displayOrderDetails();
                    break;
                case "7":
                showItemStock();
                break;
                case "0":
                    run = false;
                    break;
                default:
                System.out.println("Invalid command");
                break;
                }
            } catch (Exception e) {
            System.out.println("An Error occurred, plase try again");
            }
        }
    }

    public void run() {
        accountManager = new AccountManager();
        
        String command = "";
        String commandsMenu = "1- register\n2- login\n0- quit";
        boolean run = true;

        while (run) {
            try {
                System.out.println(commandsMenu);
                command = Scan.scanner.nextLine();
                switch (command) {
                    case "1":
                        register();
                        break;
                    case "2":
                        login();
                        break;
                    case "0":
                        run = false;
                        break;
                    default:
                        System.out.println("Invalid command");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An Error occurred, plase try again");
            }
        }
        Scan.scanner.close();
    }
}

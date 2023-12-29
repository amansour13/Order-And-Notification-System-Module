package App.AccountManager;

import java.util.Scanner;

import App.Scan;
import App.User;

public class AccountManager extends IAccountManager {
    
    // TODO: implements functions
    
    @Override
    public void register() {
        System.out.println("Register Function");

        System.out.println("Enter Your name");
        String name = Scan.scanner.nextLine(); 
        System.out.println("Enter Your email");
        String email = Scan.scanner.nextLine();
        System.out.println("Enter Your password");
        String password = Scan.scanner.nextLine();
        System.out.println("Enter Your phone number");
        String phoneNumber = Scan.scanner.nextLine();
        System.out.println("Enter Your address");
        String address = Scan.scanner.nextLine();


        //TODO: send to the backend
        System.out.println("Registered Successfully");
        // System.out.println("Error occurred");
    }

    @Override
    public boolean login() {
        System.out.println("Login Function");

        System.out.println("Enter Your email");
        String email = Scan.scanner.nextLine();
        System.out.println("Enter Your password");
        String password = Scan.scanner.nextLine();

        // TODO: send to the backend
        System.out.println("Logged in Successfully");
        // System.out.println("Email or password is incorrect");
        return true;
    }

    @Override
    public void addBalance(float newBalance) {
        this.user.addBalance(newBalance);
    }

    @Override
    public User getUser() {return user;};
}

package App.AccountManager;

import App.User;

public class AccountManager extends IAccountManager {
    
    // TODO: implements functions
    
    @Override
    public void register() {
        System.out.println("Register Function");
    }

    @Override
    public void login() {
        System.out.println("Login Function");
    }

    @Override
    public void addBalance(float newBalance) {
        this.user.addBalance(newBalance);
    }

    @Override
    public User getUser() {return user;};
}

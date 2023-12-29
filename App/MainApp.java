package App;

import App.AccountManager.*;

public class MainApp {

    private IAccountManager accountManager;

    // Account Manager functions
    private void register() {
        accountManager.register();
    }

    private void login() {
        accountManager.login();
    }

    private void addBalance() {
        // TODO: dummy parameter
        accountManager.addBalance(5);
    }
    
    // 
    public void run() {
        this.accountManager = new AccountManager();
        register();
        login();
        addBalance();
    }
}

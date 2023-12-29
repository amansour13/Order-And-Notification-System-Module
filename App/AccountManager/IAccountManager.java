package App.AccountManager;
import App.User;

public abstract class IAccountManager {
    protected User user;

    public abstract void register();
    public abstract void login();
    public abstract void addBalance(float balance);
    User getUser() {return user;};
}

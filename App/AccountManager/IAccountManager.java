package App.AccountManager;
import src.model.User;

public abstract class IAccountManager {
    protected User user;

    public abstract void register();
    public abstract boolean login();
    public abstract void addBalance(float balance);
    User getUser() {return user;};
}

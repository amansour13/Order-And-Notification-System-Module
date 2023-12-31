package src.model.Messages;

import src.model.User;
import src.model.ComponentOrder;

public abstract class MessageTemplate {
    private String content;
    private String language;
    
    abstract public void createMessage(ComponentOrder order, User user);
    

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
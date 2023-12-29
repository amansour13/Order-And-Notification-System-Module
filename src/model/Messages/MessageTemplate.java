package src.model.Messages;

import java.util.List;

import src.model.User;
import src.model.ComponentOrder;

public abstract class MessageTemplate {
    private String subject;
    private String content;
    private String language;
    
    abstract public void createMessage(ComponentOrder order, User user);
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

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
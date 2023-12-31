package com.example.notificationorderapp.model.Messages;

import com.example.notificationorderapp.model.User;
import com.example.notificationorderapp.Langauges.ILangauge;
import com.example.notificationorderapp.model.ComponentOrder;

public abstract class MessageTemplate {
    private String subject;
    private String content;
    
    abstract public void createMessage(ComponentOrder order, User user, ILangauge langauge );
    
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
}
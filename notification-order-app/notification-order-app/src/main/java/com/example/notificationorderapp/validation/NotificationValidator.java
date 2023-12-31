package com.example.notificationorderapp.validation;

import com.example.notificationorderapp.Channels.ChannelStrategy;
import com.example.notificationorderapp.Channels.Email;
import com.example.notificationorderapp.Channels.SMS;
import com.example.notificationorderapp.Langauges.Arabic;
import com.example.notificationorderapp.Langauges.English;
import com.example.notificationorderapp.Langauges.ILangauge;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NotificationValidator{

    @NotBlank(message = "The username is required.")
    @NotEmpty(message = "The username is required.")
    @NotNull(message = "The username is required.")
    @Size(min = 3, max = 20, message = "The username must be from 3 to 20 characters.")
    private String username;

    @NotBlank(message = "The password is required.")
    @NotEmpty(message = "The password is required.")
    @NotNull(message = "The password is required.")
    @Size(min = 6, max = 20)
    private String password;

    @NotBlank(message = "The notifaction is required.")
    @NotEmpty(message = "The notifaction is required.")
    @NotNull(message = "The notifaction is required.")
    @Size(min = 0)
    private String notificationMethod;

    @NotBlank(message = "The language is required.")
    @NotEmpty(message = "The language is required.")
    @NotNull(message = "The langauge is required.")
    @Size(min = 0)
    private String langauge;

    
    
    public ChannelStrategy getNotficationChannel() {
        notificationMethod = notificationMethod.toLowerCase();
        switch (notificationMethod) {
            case "sms":
                return new SMS(); 
            case "email":
                return new Email(); 
            default:
                return null;
        }
    }

    public ILangauge getLangaugeClass() {
        langauge = langauge.toLowerCase();
        switch (langauge) {
            case "english":
                return new English(); 
            case "arabic":
                return new Arabic(); 
            default:
                return null;
        }
    }

    public String getLangauge() {
        return langauge;
    }

    public String getNotificationMethod() {
        return notificationMethod;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

package com.example.notificationorderapp.model.Messages;

import com.example.notificationorderapp.model.User;
import com.example.notificationorderapp.Langauges.ILangauge;
import com.example.notificationorderapp.Langauges.LangaugeCodes;
import com.example.notificationorderapp.model.ComponentOrder;
import com.example.notificationorderapp.model.Order;



public class Shipment extends MessageTemplate {
    @Override
    public void createMessage(ComponentOrder order, User user, ILangauge langauge) {
        String conString = langauge.createMessage(LangaugeCodes.SHIP, user.getUsername(), ((Order)order).toString());
        setContent(conString);

    };    
}

package com.example.notificationorderapp.model.Messages;
import com.example.notificationorderapp.Langauges.ILangauge;
import com.example.notificationorderapp.Langauges.LangaugeCodes;
import com.example.notificationorderapp.model.ComponentOrder;
import com.example.notificationorderapp.model.Order;
import com.example.notificationorderapp.model.User;


public class Placement extends MessageTemplate {
    @Override
    public void createMessage(ComponentOrder order, User user, ILangauge langauge) {
        String conString = langauge.createMessage(LangaugeCodes.PLACE, user.getUsername(), ((Order)order).toString());
      
        setContent(conString);

    };

}

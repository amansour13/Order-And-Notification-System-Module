package com.example.notificationorderapp.service;

import static com.example.notificationorderapp.util.Database.stats;

public class StatisticsServiceImpl implements StatisticsService{

    @Override
    public String getMostNotifyEmail() {
        try {
            return stats.emailsCounter.peek().getKey();
        } catch (Exception e) {
            System.out.println("Exception in getMostNotifyEmail as" + e.getMessage());
            return null;
        }
    }

    @Override
    public String getMostNotifyPhone() {
        try {
            return stats.phonesCounter.peek().getKey();
        } catch (Exception e) {
            System.out.println("Exception in getMostNotifyPhone as" + e.getMessage());
            return null;
        }
    }

    @Override
    public String getMostNotifyTemp() {
        try {
            if (stats.placeTempCounter >= stats.shipTempCounter && stats.placeTempCounter >= stats.cancelTempCounter )
            return "place";
            else if (stats.shipTempCounter >= stats.placeTempCounter && stats.shipTempCounter >= stats.cancelTempCounter )
                return "ship";
            else
                return "cancel";
        } catch (Exception e) {
            System.out.println("Exception in getMostNotifyTemp as" + e.getMessage());
            return null;
        }
    }
    
}

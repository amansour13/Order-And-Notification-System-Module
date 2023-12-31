package com.example.notificationorderapp.Langauges;

import java.util.Map;

public class Arabic implements ILangauge {
    public static final String LANGUAGE_NAME = "Arabic";
    private static final Map<LangaugeCodes, String> LANGUAGE_MAP = Map.of(
        LangaugeCodes.CANCEL, "لغي",
        LangaugeCodes.SHIP, "شحن",
        LangaugeCodes.PLACE, "حجز"
        );
    
    private String DEFAULT_MESSAGE = "\n[\nعزيزي %s , حجزك لطلب  %s قد %s. شكرا لاستخادمك متجرنا :)\n]\n";

    @Override
    public String createMessage(LangaugeCodes code, String username, String order) {
        return String.format(DEFAULT_MESSAGE, username, order, LANGUAGE_MAP.get(code));
    }
}
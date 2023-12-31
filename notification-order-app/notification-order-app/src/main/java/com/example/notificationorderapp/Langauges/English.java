package com.example.notificationorderapp.Langauges;

import java.util.Map;

public class English implements ILangauge {
    public static final String LANGUAGE_NAME = "English";
    private static final Map<LangaugeCodes, String> LANGUAGE_MAP = Map.of(
        LangaugeCodes.CANCEL, "cancelled",
        LangaugeCodes.SHIP, "shipped",
        LangaugeCodes.PLACE, "placed"
        );
    
    private String DEFAULT_MESSAGE = "\n[\nDear %s , your booking of the  %sis %s. thanks for using our store :)\n]\n";

    @Override
    public String createMessage(LangaugeCodes code, String username, String order) {
        return String.format(DEFAULT_MESSAGE, username, order, LANGUAGE_MAP.get(code));
    }
}

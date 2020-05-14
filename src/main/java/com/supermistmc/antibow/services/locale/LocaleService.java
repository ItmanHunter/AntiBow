package com.supermistmc.antibow.services.locale;


public class LocaleService {

    private static ILocaleService iLocaleService;

    public static ILocaleService getILocaleService() {
        return iLocaleService;
    }

    public static void setILocaleService(ILocaleService iLocaleService) {
        LocaleService.iLocaleService = iLocaleService;
    }
}

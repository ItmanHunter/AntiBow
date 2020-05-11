package com.supermistmc.antibow.services;

public class RegionServiceFactory {

    private static IRegionService iRegionService;

    public static void setRegionService(IRegionService iRegionService) {
        RegionServiceFactory.iRegionService = iRegionService;
    }

    public static IRegionService getRegionService() {
        return iRegionService;
    }

}

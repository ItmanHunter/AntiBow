package com.supermistmc.antibow.services.region;

public class RegionService {

    private static IRegionService iRegionService;

    public static void setRegionService(IRegionService iRegionService) {
        RegionService.iRegionService = iRegionService;
    }

    public static IRegionService getRegionService() {
        return iRegionService;
    }

}

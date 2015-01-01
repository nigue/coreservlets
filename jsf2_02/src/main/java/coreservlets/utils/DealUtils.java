package coreservlets.utils;

import coreservlets.model.DailyDeal;

public class DealUtils {

    private static final DailyDeal CORE_JSF_DEAL
            = new DailyDeal("Core JavaServer Faces", 199.95, "http://corejsf.com/");

    public static DailyDeal todaysDeal() {
        return (CORE_JSF_DEAL);
    }

    private DealUtils() {
    } // Uninstantiatable class. Static methods only.
}

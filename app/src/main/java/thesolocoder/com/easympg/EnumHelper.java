package thesolocoder.com.easympg;

import android.content.Context;

class EnumHelper {
    private static final String ODOMETER_MILES = "MILES";
    private static final String ODOMETER_KM = "KM";

    private static final String FUEL_MEASUREMENT_LITERS = "LITERS (L)";
    private static final String FUEL_MEASUREMENT_US_GALLONS = "US GALLONS (GAL)";
    private static final String FUEL_MEASUREMENT_IMPERIAL_GALLONS = "IMPERIAL GALLONS";

    String getOdometerType(Context appContext, String odometerSelection){
        String value = ODOMETER_KM;
        if(odometerSelection.equals(appContext.getString(R.string.Miles))){
            value = ODOMETER_MILES;
        }
        return value;
    }

    String getFuelType(Context appContext, String fuelSelection) {
        String value = FUEL_MEASUREMENT_LITERS;

        if (fuelSelection.equals(appContext.getString(R.string.USGallons))) {
            value = FUEL_MEASUREMENT_US_GALLONS;
        } else if (fuelSelection.equals(appContext.getString(R.string.UKGallons))) {
            value = FUEL_MEASUREMENT_IMPERIAL_GALLONS;
        }
        return value;
    }
}

package thesolocoder.com.easympg;

import android.content.Context;

class FillUpAdmin {
    private static final String UNINITIALIZED_PK = "-1";
    private Context _appContext;

    private static final String _fillUpID = "_fillUpID";
    private static final String VEHICLE_ID = "_vehicleID";
    private static final String MISSING_PREVIOUS_FILL = "missing_previous_fill";
    private static final String FUEL_QUANTITY = "fuel_quantity";
    private static final String PRICE = "price";
    private static final String DATE = "date";
    private static final String ODOMETER = "odometer";
    private static final String OUTDATED = "outdated";
    private static final String  PARENT_ID = "parentID";

    FillUpAdmin(Context context) {
        _appContext = context;
    }
}

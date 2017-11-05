package thesolocoder.com.easympg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    private static final String CHILD_ID = "childID";
    private static final String FILL_UP_TABLE = "FILL_UP";

    FillUpAdmin(Context context) {
        _appContext = context;
    }

    FillUpInfoStruct getLastFillUpFromPK(String vehiclePK) {
        DatabaseMain database = new DatabaseMain(_appContext);
        SQLiteDatabase db = database.getReadableDatabase();
        final String query = "SELECT * FROM " + FILL_UP_TABLE + " WHERE " + _fillUpID + " = (SELECT max(" + _fillUpID + ") from " +
                FILL_UP_TABLE + " WHERE " + VEHICLE_ID + "=" + vehiclePK + ")";
        Cursor c = db.rawQuery(query, null);
        FillUpInfoStruct fillUp = null;
        c.moveToFirst();
        while (!c.isAfterLast()) {
            fillUp = new FillUpInfoStruct(Integer.valueOf(c.getString(c.getColumnIndex(_fillUpID))),
                    Integer.valueOf(c.getString(c.getColumnIndex(VEHICLE_ID))),
                    Integer.valueOf(c.getString(c.getColumnIndex(MISSING_PREVIOUS_FILL))),
                    Double.valueOf(c.getString(c.getColumnIndex(FUEL_QUANTITY))),
                    Double.valueOf(c.getString(c.getColumnIndex(PRICE))),
                    c.getString(c.getColumnIndex(DATE)),
                    Integer.valueOf(c.getString(c.getColumnIndex(ODOMETER))),
                    Integer.valueOf(c.getString(c.getColumnIndex(CHILD_ID))),
                    Integer.valueOf(c.getString(c.getColumnIndex(OUTDATED))));
            c.moveToNext();
        }
        c.close();
        db.close();
        return fillUp;
    }

    public void addNewFillUp(FillUpInfoStruct fillUpInfo){
        DatabaseMain database = new DatabaseMain(_appContext);
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues vehicleDbEntry = repackageFillUpInfo(fillUpInfo);
        db.insert(FILL_UP_TABLE, null, vehicleDbEntry);
        db.close();
    }

    private ContentValues repackageFillUpInfo(FillUpInfoStruct fillUpInfo) {
        ContentValues values = new ContentValues();
        values.put(VEHICLE_ID, fillUpInfo.get_vehicleID());
        values.put(MISSING_PREVIOUS_FILL, fillUpInfo.getIsMissedPreviousFillUp());
        values.put(FUEL_QUANTITY, fillUpInfo.get_fuelQuantity());
        values.put(DATE, fillUpInfo.get_date());
        values.put(ODOMETER, fillUpInfo.get_odometer());
        values.put(PRICE, fillUpInfo.get_price());
        values.put(CHILD_ID, fillUpInfo.getChild());
        values.put(OUTDATED, fillUpInfo.getIsOutdated());
        return values;
    }
}

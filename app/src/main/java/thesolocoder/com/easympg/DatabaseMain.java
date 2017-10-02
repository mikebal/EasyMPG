package thesolocoder.com.easympg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseMain extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "easyMPG.db";

    public DatabaseMain(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String queryCreateTableVehicle = "CREATE TABLE vehicle (_vehicleID INTEGER PRIMARY KEY, nickName TEXT UNIQUE, make TEXT, model TEXT, year TEXT, odometerUnits TEXT, defaultFuelUnits TEXT);";
        db.execSQL(queryCreateTableVehicle);
        final String queryCreateTableFillUp = "CREATE TABLE FILL_UP (_fillUpID INTEGER PRIMARY KEY, _vehicleID INTEGER, missing_previous_fill INTEGER DEFAULT 0, fuel_quantity REAL, price REAL, date TEXT, odometer INTEGER, outdated INTEGER DEFAULT 0, parentID INTEGER UNIQUE, FOREIGN KEY (_vehicleID) REFERENCES vehicle(_id));";
        db.execSQL(queryCreateTableFillUp);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

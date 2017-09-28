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
        final String queryCreateTableVehicle = "CREATE TABLE vehicle (_id INTEGER PRIMARY KEY, nickName TEXT UNIQUE, make TEXT, model TEXT, year TEXT, odometerUnits TEXT, defaultFuelUnits TEXT);";
        db.execSQL(queryCreateTableVehicle);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

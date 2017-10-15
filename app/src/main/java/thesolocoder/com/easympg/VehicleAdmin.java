package thesolocoder.com.easympg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class VehicleAdmin{
    private static final String UNINITIALIZED_PK = "-1";
    private Context _appContext;

    private static final String _vehicleID = "_vehicleID";
    private static final String NICK_NAME = "nickName";
    private static final String MAKE = "make";
    private static final String MODEL = "model";
    private static final String YEAR = "year";
    private static final String ODOMETER_UNITS = "odometerUnits";
    private static final String DEFAULT_FUEL_UNITS = "defaultFuelUnits";
    private static final String VEHICLE_TABLE = "vehicle";

    public VehicleAdmin(Context context){
        _appContext = context;
    }

    public void addNewVehicleIfValid(VehicleInfoStruct newVehicle){
        if(verifyNewVehicleIsUnique(newVehicle)){
            addNewVehicle(newVehicle);
        }
    }

    private boolean verifyNewVehicleIsUnique(VehicleInfoStruct newVehicle){
        DatabaseMain database = new DatabaseMain(_appContext);
        SQLiteDatabase db = database.getReadableDatabase();
        boolean isValid = false;

        if(!newVehicle.getVehiclePK().equals(UNINITIALIZED_PK)){
            throw new RuntimeException("Vehicle ID already exists can not add new vehicle to the database!");
        }

        if(!newVehicle.getNickName().equals("")){
            String uniqueNickNameQuery = String.format("SELECT * FROM vehicle WHERE nickName='%s'", newVehicle.getNickNameSqlSafe());
            Cursor cursor = db.rawQuery(uniqueNickNameQuery, null);
            cursor.moveToFirst();
            if(cursor.isAfterLast()){
                isValid = true;
            }
            cursor.close();
        }

        db.close();
        database.close();
        return isValid;
    }

    private void addNewVehicle(VehicleInfoStruct vehicleInfo){
        DatabaseMain database = new DatabaseMain(_appContext);
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues vehicleDbEntry = repackageVehicleInfo(vehicleInfo);
        db.insert(VEHICLE_TABLE, null, vehicleDbEntry);
        db.close();
    }

    private ContentValues repackageVehicleInfo(VehicleInfoStruct vehicleInfo){
        ContentValues values = new ContentValues();
        values.put(NICK_NAME, vehicleInfo.getNickName());
        values.put(MAKE, vehicleInfo.getMake());
        values.put(MODEL, vehicleInfo.getModel());
        values.put(YEAR, vehicleInfo.getYear());
        values.put(ODOMETER_UNITS, vehicleInfo.getOdometerUnits());
        values.put(DEFAULT_FUEL_UNITS, vehicleInfo.getFuelUnits());
        return values;
    }

    public ArrayList<VehicleInfoStruct> getVehicleList(){
        DatabaseMain database = new DatabaseMain(_appContext);
        SQLiteDatabase db = database.getReadableDatabase();
        final String query = "SELECT * FROM " + VEHICLE_TABLE;
        ArrayList<VehicleInfoStruct> vehicleList = new ArrayList<>();
        Cursor c = db.rawQuery(query, null);
        VehicleInfoStruct vehicle;
        c.moveToFirst();
        while (!c.isAfterLast()){
            vehicle = new VehicleInfoStruct(Integer.valueOf(c.getString(c.getColumnIndex(_vehicleID))),
                                            c.getString(c.getColumnIndex(NICK_NAME)),
                                            c.getString(c.getColumnIndex(MAKE)),
                                            c.getString(c.getColumnIndex(MODEL)),
                                            c.getString(c.getColumnIndex(YEAR)),
                                            c.getString(c.getColumnIndex(ODOMETER_UNITS)),
                                            c.getString(c.getColumnIndex(DEFAULT_FUEL_UNITS)));
            vehicleList.add(vehicle);
            c.moveToNext();
        }
        c.close();
        db.close();
        return vehicleList;
    }

    public VehicleInfoStruct getVehicleByPK(String vehiclePK){
        DatabaseMain database = new DatabaseMain(_appContext);
        SQLiteDatabase db = database.getReadableDatabase();
        final String query = "SELECT * FROM " + VEHICLE_TABLE + " WHERE " + _vehicleID +" =" + vehiclePK;
        Cursor c = db.rawQuery(query, null);
        VehicleInfoStruct vehicle = null;
        c.moveToFirst();
        while (!c.isAfterLast()){
            vehicle = new VehicleInfoStruct(Integer.valueOf(c.getString(c.getColumnIndex(_vehicleID))),
                    c.getString(c.getColumnIndex(NICK_NAME)),
                    c.getString(c.getColumnIndex(MAKE)),
                    c.getString(c.getColumnIndex(MODEL)),
                    c.getString(c.getColumnIndex(YEAR)),
                    c.getString(c.getColumnIndex(ODOMETER_UNITS)),
                    c.getString(c.getColumnIndex(DEFAULT_FUEL_UNITS)));
            c.moveToNext();
        }
        c.close();
        db.close();
        return vehicle;
    }

    public void updateVehicleInfo(VehicleInfoStruct updatedVehicleInfo){
       // boolean isValid = true;
      //  VehicleInfoStruct originalVehicle = getVehicleByPK(updatedVehicleInfo.getVehiclePK());
       /* if(!originalVehicle.getNickName().equals(updatedVehicleInfo.getNickName())){
            isValid = verifyNewVehicleIsUnique(updatedVehicleInfo);
        }*/

     //   if (isValid){
            ContentValues values = repackageVehicleInfo(updatedVehicleInfo);
            DatabaseMain database = new DatabaseMain(_appContext);
            SQLiteDatabase db = database.getWritableDatabase();
            String[] updateIdArguments = {updatedVehicleInfo.getVehiclePK()} ;
            db.update(VEHICLE_TABLE, values, _vehicleID + "=?", updateIdArguments);
     //   }
     //   return isValid;
    }
}

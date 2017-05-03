package thesolocoder.com.easympg;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class VehicleAdmin{

    private static final int UNINITIALIZED_PK = -1;
    private Context _appContext;

    public VehicleAdmin(Context context){
        _appContext = context;
    }

    public boolean verifyNewVehicleIsUnique(VehicleInfoStruct newVehicle){
        DatabaseMain database = new DatabaseMain(_appContext);
        SQLiteDatabase db = database.getReadableDatabase();
        boolean isValid = true;

        if(newVehicle.get_vehiclePK() != UNINITIALIZED_PK){
            throw new RuntimeException("Vehicle ID already exists can not add new vehicle to the database!");
        }

        if(!newVehicle.get_nickName().equals("")){
            String uniqueNickNameQuery = String.format("SELECT * FROM vehicle WHERE nickName='%s'", newVehicle.get_nickName());
            Cursor cursor = db.rawQuery(uniqueNickNameQuery, null);
            if(!cursor.isAfterLast()){
                isValid = false;
            }
        }
        return isValid;
    }
}

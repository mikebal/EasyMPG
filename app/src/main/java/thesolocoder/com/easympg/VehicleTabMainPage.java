package thesolocoder.com.easympg;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class VehicleTabMainPage extends VehicleInfoViewPage {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.add("saveVehicle");
        menuItem.setIcon(R.mipmap.ic_check_white_36dp);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals("saveVehicle"))
            confirmButtonClicked();
        return super.onOptionsItemSelected(item);
    }

    private void confirmButtonClicked(){
        VehicleInfoStruct newVehicle = getVehicleInfo();
        VehicleAdmin vehicleAdmin = new VehicleAdmin(getApplicationContext());
        vehicleAdmin.addNewVehicleIfValid(newVehicle);
        getSavedVehicles();
    }

    private ArrayList<VehicleInfoStruct> getSavedVehicles(){
        VehicleAdmin vehicleAdmin = new VehicleAdmin(getApplicationContext());
        ArrayList<VehicleInfoStruct> vehicles = vehicleAdmin.getVehicleList();

        return vehicles;
    }

}
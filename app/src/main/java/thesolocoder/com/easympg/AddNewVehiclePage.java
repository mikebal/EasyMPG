package thesolocoder.com.easympg;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AddNewVehiclePage extends VehicleInfoViewPage {

    @Override
    public void onStart() {
        super.onStart();
        findViewById(R.id.vehicleViewNavigation).setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.add("confirm");
        menuItem.setIcon(R.mipmap.ic_check_white_36dp);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals("confirm"))
            confirmButtonClicked();
        return super.onOptionsItemSelected(item);
    }

    private void confirmButtonClicked(){
        VehicleInfoStruct newVehicle = getVehicleInfo();
        VehicleAdmin vehicleAdmin = new VehicleAdmin(getApplicationContext());
        vehicleAdmin.addNewVehicleIfValid(newVehicle);
        finish();
    }
}

package thesolocoder.com.easympg;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class EditVehiclePage extends VehicleInfoViewPage {

    private static String _vehiclePK;

    @Override
    public void onStart(){
        super.onStart();
        Bundle extras = getIntent().getExtras();
        _vehiclePK = extras.getString("vehiclePK");
        if(_vehiclePK == null) {
            finish();
        }

        findViewById(R.id.vehicleViewNavigation).setVisibility(View.INVISIBLE);
        VehicleAdmin vehicleAdmin = new VehicleAdmin(this);
        showVehicleInfo(vehicleAdmin.getVehicleByPK(_vehiclePK));
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuItem menuItem = menu.add("saveVehicle");
            menuItem.setIcon(R.mipmap.ic_check_white_36dp);
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            return super.onCreateOptionsMenu(menu);
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getTitle().equals("saveVehicle")) {
                VehicleAdmin vehicleAdmin = new VehicleAdmin(this);
                VehicleInfoStruct updatedVehicle = getVehicleInfo();
                updatedVehicle.setVehiclePK(Integer.valueOf(_vehiclePK));
                boolean isEditValid = vehicleAdmin.updateVehicleInfo(updatedVehicle);
                if(isEditValid){
                    Intent output = new Intent();
                    output.putExtra("successfulEditedVehiclePK", Integer.valueOf(_vehiclePK));
                    setResult(1, output);
                    finish();
                }
            }
            return super.onOptionsItemSelected(item);
        }

    private void showVehicleInfo(VehicleInfoStruct vehicle){
        _year.setText(vehicle.getYear());
        _make.setText(vehicle.getMake());
        _model.setText(vehicle.getModel());
        _nickName.setText(vehicle.getNickName());
    }
}


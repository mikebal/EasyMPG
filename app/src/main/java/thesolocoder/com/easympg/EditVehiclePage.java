package thesolocoder.com.easympg;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class EditVehiclePage extends VehicleInfoViewPage {

    ArrayList<VehicleInfoStruct> _vehicles = new ArrayList<>();
    int _currentVehicleToView = 0;

    @Override
    public void onStart(){
        super.onStart();
        Bundle extras = getIntent().getExtras();
        String _toView = extras.getString("vehiclePK");
        if(_toView == null) {
            _currentVehicleToView = 0;
        }
       else{
            _currentVehicleToView = findVehicleListPositionById(_toView);
        }

        VehicleAdmin vehicleAdmin = new VehicleAdmin(this);
        _vehicles = vehicleAdmin.getVehicleList();
        showVehicleInfo();
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuItem menuItem = menu.add("addVehicle");
            menuItem.setIcon(R.mipmap.ic_add_white_36dp);
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            return super.onCreateOptionsMenu(menu);
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getTitle().equals("addVehicle"))
                switchViewToAddNewVehicle();
            return super.onOptionsItemSelected(item);
        }

    private void switchViewToAddNewVehicle() {
        Intent addNewVehicle = new Intent(EditVehiclePage.this, AddNewVehiclePage.class);
        startActivity(addNewVehicle);
    }

    private int findVehicleListPositionById(String id){
        int index = -1;

        for(int i = 0; i < _vehicles.size(); i++){
            if(id.equals(_vehicles.get(i).getVehiclePK())){
                index = i;
                break;
            }
        }
        return index;
    }

    private void showVehicleInfo(){
        VehicleInfoStruct vehicle = _vehicles.get(_currentVehicleToView);
        _year.setText(vehicle.getYear());
        _make.setText(vehicle.getMake());
        _model.setText(vehicle.getModel());
        _nickName.setText(vehicle.getNickName());


    }
}


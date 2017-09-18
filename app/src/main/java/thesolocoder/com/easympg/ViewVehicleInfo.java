package thesolocoder.com.easympg;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewVehicleInfo extends VehicleInfoViewPage {

    ArrayList<VehicleInfoStruct> _vehicles = new ArrayList<>();
    int _currentVehicleToView = 0;
    final int UNSET_VALUE = -1;

    Button _nextVehicleButton;
    Button _backVehicleButton;
    TextView _vehicleNameHeader;


    @Override
    public void onStart(){
        super.onStart();
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            _currentVehicleToView = extras.getInt("vehiclePK", -1);
        }
        else{
            _currentVehicleToView = UNSET_VALUE;
        }

        VehicleAdmin vehicleAdmin = new VehicleAdmin(this);
        _vehicles = vehicleAdmin.getVehicleList();
        _nextVehicleButton = (Button) findViewById(R.id.vehicleNavigationForward);
        _backVehicleButton = (Button) findViewById(R.id.vehicleNavigationBack);
        _vehicleNameHeader = (TextView) findViewById(R.id.vehicleDisplayNameText);

        _make.setOnTouchListener(consumeKeyboard);
        _make.setFocusable(false);
        _model.setOnTouchListener(consumeKeyboard);
        _model.setFocusable(false);
        _year.setOnTouchListener(consumeKeyboard);
        _year.setFocusable(false);
        _fuelSpinner.setOnTouchListener(consumeKeyboard);
        _odometerSpinner.setOnTouchListener(consumeKeyboard);
        _nickName.setOnTouchListener(consumeKeyboard);
        _nickName.setFocusable(false);

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
        Intent addNewVehicle = new Intent(ViewVehicleInfo.this, AddNewVehiclePage.class);
        startActivity(addNewVehicle);
    }

    private void showVehicleInfo(){
        if(_currentVehicleToView != UNSET_VALUE) {
            VehicleInfoStruct vehicle = _vehicles.get(_currentVehicleToView);
            _year.setText(vehicle.getYear());
            _make.setText(vehicle.getMake());
            _model.setText(vehicle.getModel());
            _nickName.setText(vehicle.getNickName());
        }
    }

    public void viewNextVehicle(View v){
        _currentVehicleToView = _currentVehicleToView + 1;
        if(_currentVehicleToView == _vehicles.size()){
            _currentVehicleToView = 0;
        }
        showVehicleInfo();
    }

    public void viewPreviousVehicle(View v){
        _currentVehicleToView = _currentVehicleToView -1;
        if(_currentVehicleToView == -1){
            _currentVehicleToView = _vehicles.size() - 1;
        }
        showVehicleInfo();
    }

    private View.OnTouchListener consumeKeyboard = new View.OnTouchListener() {
        public boolean onTouch (View v, MotionEvent event) {
            return true; // the listener has consumed the event
        }
    };
}


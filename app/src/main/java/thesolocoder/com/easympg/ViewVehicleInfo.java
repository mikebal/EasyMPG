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

    private ArrayList<VehicleInfoStruct> _vehicles = new ArrayList<>();
    Button _nextVehicleButton;
    Button _backVehicleButton;
    TextView _vehicleNameHeader;

    @Override
    public void onStart(){
        super.onStart();
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            _currentVehicleToView = extras.getInt("vehiclePK", UNSET_VALUE);
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

        showVehicleInfo(_vehicles.get(_currentVehicleToView));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItemEditCurrentVehicle = menu.add("edit");
        menuItemEditCurrentVehicle.setIcon(R.mipmap.ic_mode_edit_white_36dp);
        menuItemEditCurrentVehicle.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        MenuItem menuItemAddVehicle = menu.add("addVehicle");
        menuItemAddVehicle.setIcon(R.mipmap.ic_add_white_36dp);
        menuItemAddVehicle.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getTitle().equals("addVehicle")) {
            switchViewToAddNewVehicle();
        }
        else if(item.getTitle().equals("edit")){
            switchViewToEditVehicle();
        }

        return super.onOptionsItemSelected(item);
    }

    private void switchViewToAddNewVehicle() {
        Intent addNewVehicle = new Intent(ViewVehicleInfo.this, AddNewVehiclePage.class);
        startActivity(addNewVehicle);
    }

    private void switchViewToEditVehicle(){
        Intent editVehicle = new Intent(ViewVehicleInfo.this, EditVehiclePage.class);
        editVehicle.putExtra("vehiclePK", String.valueOf(_vehicles.get(_currentVehicleToView).getVehiclePK()));
        startActivityForResult(editVehicle, 1);
    }

    public void viewNextVehicle(View v){
        _currentVehicleToView = _currentVehicleToView + 1;
        if(_currentVehicleToView == _vehicles.size()){
            _currentVehicleToView = 0;
        }
        showVehicleInfo(_vehicles.get(_currentVehicleToView));
    }

    public void viewPreviousVehicle(View v){
        _currentVehicleToView = _currentVehicleToView -1;
        if(_currentVehicleToView == -1){
            _currentVehicleToView = _vehicles.size() - 1;
        }
        showVehicleInfo(_vehicles.get(_currentVehicleToView));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1) {
            VehicleAdmin vehicleAdmin = new VehicleAdmin(this);
            _vehicles = vehicleAdmin.getVehicleList();
            setCurrentVehicleToViewByPK(data.getIntExtra("successfulEditedVehiclePK", -1));
            showVehicleInfo(_vehicles.get(_currentVehicleToView));
        }
    }

    private void setCurrentVehicleToViewByPK(int vehiclePK){
        if(vehiclePK != UNSET_VALUE) {
            for (int i = 0; i < _vehicles.size(); i++) {
                if (vehiclePK == Integer.valueOf(_vehicles.get(i).getVehiclePK())) {
                    _currentVehicleToView = i;
                    break;
                }
            }
        }
    }

    private View.OnTouchListener consumeKeyboard = new View.OnTouchListener() {
        public boolean onTouch (View v, MotionEvent event) {
            return true; // the listener has consumed the event
        }
    };
}


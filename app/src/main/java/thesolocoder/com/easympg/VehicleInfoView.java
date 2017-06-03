package thesolocoder.com.easympg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class VehicleInfoView extends AppCompatActivity{

    private static final int UNINITIALIZED_PK = -1;
    protected EditText _nickName;
    protected EditText _make;
    protected EditText _model;
    protected EditText _year;
    protected Spinner _odometerSpinner;
    protected Spinner _fuelSpinner;

    private float x1,x2;
    static final int MIN_DISTANCE = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicleinfo);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
       getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initializeVariables();
    }

    private void initializeVariables(){
        _nickName = (EditText)findViewById(R.id.editTextVehicleNickName);
        _make     = (EditText)findViewById(R.id.editTextVehicleMake);
        _model    = (EditText)findViewById(R.id.editTextVehicleModel);
        _year     =  (EditText)findViewById(R.id.editTextVehicleYear);

        _odometerSpinner = (Spinner) findViewById(R.id.spinnerOdometerUnits);
        ArrayAdapter<CharSequence> odometerAdapter = ArrayAdapter.createFromResource(this, R.array.odometerMeasurementArray, android.R.layout.simple_spinner_item);
        odometerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _odometerSpinner.setAdapter(odometerAdapter);

        _fuelSpinner = (Spinner) findViewById(R.id.spinnerFuelUnits);
        ArrayAdapter<CharSequence> fuelAdapter = ArrayAdapter.createFromResource(this, R.array.fuelMeasurementArray, android.R.layout.simple_spinner_item);
        fuelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _fuelSpinner.setAdapter(fuelAdapter);
    }

    protected VehicleInfoStruct getVehicleInfo(){
        if(_nickName.getText().toString().equals("")){
            String generatedNickName = _make.getText().toString() + " " + _model.getText().toString() + " " + _year.getText().toString();
            _nickName.setText(generatedNickName);
        }
       return new VehicleInfoStruct(UNINITIALIZED_PK,
                                    _nickName.getText().toString(),
                                    _make.getText().toString(),
                                    _model.getText().toString(),
                                    _year.getText().toString(),
                                    getOdometerMeasurementValue(),
                                    getFuelMeasurementValue());
    }

    private String getOdometerMeasurementValue(){
        EnumHelper enumHelper = new EnumHelper();
        return enumHelper.getOdometerType(getApplicationContext(), _odometerSpinner.getSelectedItem().toString());
    }

    private String getFuelMeasurementValue(){
        EnumHelper enumHelper = new EnumHelper();
        return enumHelper.getFuelType(getApplicationContext(), _fuelSpinner.getSelectedItem().toString());
    }

}

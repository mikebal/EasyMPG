package thesolocoder.com.easympg;
;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class FillUpView extends AppCompatActivity{

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    ArrayList<VehicleInfoStruct> _vehicles = new ArrayList<>();
    TextView _vehicleNameHeader;
    int _currentVehicleToView = 0;
    private EditText _odometerInput;
    Calendar dateSelected;
    private Button _dateButton;
    private DatePickerDialog _datePickerDialog;
    private Spinner _fuelMeasurementSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fillup);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setupVariables();

        int i = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.add("saveFillUp");
        menuItem.setIcon(R.mipmap.ic_check_white_36dp);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals("saveFillUp")) {
            VehicleAdmin vehicleAdmin = new VehicleAdmin(this);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void setupVariables(){
        _fuelMeasurementSpinner = (Spinner) findViewById(R.id.spinnerFuelMeasurement);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fuelMeasurementArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _fuelMeasurementSpinner.setAdapter(adapter);
        _odometerInput = (EditText) findViewById(R.id.editTextOdometer);
        setOdometerListener();
        dateSelected = Calendar.getInstance();
        _dateButton = (Button) findViewById(R.id.dateButton);
        _vehicleNameHeader = (TextView) findViewById(R.id.vehicleDisplayNameText);
        setDateTimeField();
        updateVehicleList();
    }

    public void dateButtonClicked(View v){
      toggleCalendarLayout();
    }
    private void setDateTimeField() {
        Calendar newCalendar = dateSelected;
        _datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateSelected.set(year, monthOfYear, dayOfMonth, 0, 0);
                _dateButton.setText(DateFormat.format("mm/dd/yyyy",dateSelected.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        _dateButton.setText(DateFormat.format("mm/dd/yyyy",dateSelected.getTime()));
    }


    private void toggleCalendarLayout(){
        LinearLayout defaultLayout = (LinearLayout) findViewById(R.id.defaultLinearLayout);
        defaultLayout.setVisibility(View.GONE);
        findViewById(R.id.linearLayoutDateSelector).setVisibility(View.VISIBLE);
    }


    private void setOdometerListener(){
        _odometerInput.addTextChangedListener(new TextWatcher() {

            boolean isManualChange = false;

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (isManualChange) {
                    isManualChange = false;
                    return;
                }

                try {
                    String value = s.toString().replace(",", "");
                    String reverseValue = new StringBuilder(value).reverse()
                            .toString();
                    StringBuilder finalValue = new StringBuilder();
                    for (int i = 1; i <= reverseValue.length(); i++) {
                        char val = reverseValue.charAt(i - 1);
                        finalValue.append(val);
                        if (i % 3 == 0 && i != reverseValue.length() && i > 0) {
                            finalValue.append(",");
                        }
                    }
                    isManualChange = true;
                    _odometerInput.setText(finalValue.reverse());
                    _odometerInput.setSelection(finalValue.length());
                } catch (Exception e) {
                    // Do nothing since not a number
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
    }


    private void updateVehicleList(){
        VehicleAdmin vehicleAdmin = new VehicleAdmin(this);
        _vehicles = vehicleAdmin.getVehicleList();

        Bundle extras = getIntent().getExtras();
        String vehiclePK = extras.getString("vehiclePK", "-1");

        if(vehiclePK.equals("-1")){
            finish();
        }
        else {
            for (int i = 0; i < _vehicles.size(); i++) {
                if (vehiclePK.equals(_vehicles.get(i).getVehiclePK())) {
                    _currentVehicleToView = i;
                    break;
                }
            }
            updateScreenWithVehicleInfo();
        }
    }

    public void fillUpViewPreviousVehicleClicked(View v){
        if(--_currentVehicleToView < 0){
            _currentVehicleToView = _vehicles.size() - 1;
        }
        updateScreenWithVehicleInfo();
    }

    public void fillUpViewNextVehicleClicked(View v){
        if(++_currentVehicleToView >= _vehicles.size()){
            _currentVehicleToView = 0;
        }
        updateScreenWithVehicleInfo();
    }

    private void updateScreenWithVehicleInfo(){
        _vehicleNameHeader.setText(_vehicles.get(_currentVehicleToView).getNickName());
        String[] fuelSpinnerChoices = getResources().getStringArray(R.array.fuelMeasurementArray);
        int index = 0;
        for (String fuelMeasurement : fuelSpinnerChoices) {
            if (fuelMeasurement.toUpperCase().equals(_vehicles.get(_currentVehicleToView).getFuelUnits().toUpperCase())) {
                _fuelMeasurementSpinner.setSelection(index);
            }
            index++;
        }
    }

}

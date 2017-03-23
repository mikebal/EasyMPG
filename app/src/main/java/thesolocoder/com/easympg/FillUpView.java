package thesolocoder.com.easympg;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class FillUpView extends AppCompatActivity {

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    private EditText _odometerInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fillup);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setupVariables();
    /*    SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);
        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark_NoActionBar);
            isDarkThemeEnabled = true;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecord);
        setupVariables();
        if(useDarkTheme){
            setIconsColorFilter();
        }
        Bundle extras = getIntent().getExtras();
        dbTableReferenced = extras.getString("toAddToTable");*/
    }

    private void setupVariables(){
        Spinner spinner = (Spinner) findViewById(R.id.spinnerFuelMeasurement);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fuelMeasurementArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        _odometerInput = (EditText) findViewById(R.id.editTextOdometer);
        setOdometerListener();
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
}

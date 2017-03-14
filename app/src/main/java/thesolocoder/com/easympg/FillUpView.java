package thesolocoder.com.easympg;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class FillUpView extends AppCompatActivity {

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fillup);
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
}

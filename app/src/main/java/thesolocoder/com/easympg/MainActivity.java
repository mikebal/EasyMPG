package thesolocoder.com.easympg;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String AD_UNIT_ID = "ca-app-pub-5668026099954241/1605639619";
    final int UNSET_VALUE = -1;
    ArrayList<VehicleInfoStruct> _vehicles = new ArrayList<>();
    int _currentVehicleToView = 0;
    TextView _vehicleNameHeader;
    private AdView adView;
    private String DEVICE_ID = "A8C632156BC7C4AD5AB1ABA12C95349A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DEVICE_ID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        DEVICE_ID = md5(DEVICE_ID).toUpperCase();

        adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                                           .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                                           .addTestDevice(DEVICE_ID)
                                           .build();
        adView.loadAd(adRequest);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fillUpPage = new Intent(MainActivity.this, FillUpView.class);
                fillUpPage.putExtra("vehiclePK", _vehicles.get(_currentVehicleToView).getVehiclePK());
                startActivity(fillUpPage);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        _vehicleNameHeader = (TextView) findViewById(R.id.vehicleDisplayNameText);

        updateVehicleList();
        if(_vehicles.isEmpty()){
            isVehiclesInTheDatabaseOrAddNewVehicle();
        }
       else{
            updateVehicleList();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_vehicleInfo) {
            Intent viewVehiclePage = new Intent(MainActivity.this, ViewVehicleInfo.class);
            startActivity(viewVehiclePage);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void updateVehicleList(){
        VehicleAdmin vehicleAdmin = new VehicleAdmin(this);
        _vehicles = vehicleAdmin.getVehicleList();
        if(!_vehicles.isEmpty()){
            updateScreenWithVehicleInfo();
        }
    }

    public void mainViewPreviousVehicleClicked(View v){
       if(--_currentVehicleToView < 0){
           _currentVehicleToView = _vehicles.size() - 1;
       }
        updateScreenWithVehicleInfo();
    }

    public void mainViewNextVehicleClicked(View v){
        if(++_currentVehicleToView >= _vehicles.size()){
            _currentVehicleToView = 0;
        }
        updateScreenWithVehicleInfo();
    }

    private void updateScreenWithVehicleInfo(){
        _vehicleNameHeader.setText(_vehicles.get(_currentVehicleToView).getNickName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateVehicleList();
    }

    private boolean isVehiclesInTheDatabaseOrAddNewVehicle() {
        VehicleAdmin vehicleAdmin = new VehicleAdmin(this);
        boolean vehiclesAlreadyExist = true;

        if (vehicleAdmin.getVehicleList().isEmpty()) {
            vehiclesAlreadyExist = false;
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle(getString(R.string.firstStartDialogTitle))
                    .setMessage(getString(R.string.firstStartDialogBody))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent addNewVehicle = new Intent(MainActivity.this, AddNewVehiclePage.class);
                            startActivity(addNewVehicle);
                        }
                    })
                    .show();
        }
        return vehiclesAlreadyExist;
    }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}

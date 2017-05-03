package thesolocoder.com.easympg;


import android.view.Menu;
import android.view.MenuItem;

public class AddNewVehicle extends VehicleInfoView{

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
            confirmButtonClicked1();
        return super.onOptionsItemSelected(item);
    }

    private void confirmButtonClicked1(){
        VehicleInfoStruct newVehicle = getVehicleInfo();
        int i = 0;
    }

}

package thesolocoder.com.easympg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class EditVehicle extends VehicleInfoView{

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

    private void switchViewToAddNewVehicle(){
        Intent addNewVehicle = new Intent(EditVehicle.this, AddNewVehicle.class);
        startActivity(addNewVehicle);
    }


}


package com.example.project309.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.project309.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainNavigationScreenAdmin extends AppCompatActivity {

    public enum SpecialFucntionType{

        ADD_STORE("Add_Store"),
        EDIT_MENU("Edit Menu");

        private String value;

        private SpecialFucntionType(String val){
            value = val;
        }








        public String getValue(){
            return value;
        }

    }

    public SpecialFucntionType currentType = SpecialFucntionType.ADD_STORE;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation_screen_admin);
        Toolbar toolbar = findViewById(R.id.toolbar_admin);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_admin);
        NavigationView navigationView = findViewById(R.id.nav_view_admin);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home_admin, R.id.nav_accounts_admin, R.id.nav_profile_admin, R.id.nav_log_out_admin)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_admin);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_navigation_screen_admin, menu);

        MenuItem item = menu.findItem(R.id.action_settings_admin);

        item.setTitle(currentType.getValue());

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_admin);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_edit_store:

                if(currentType == SpecialFucntionType.ADD_STORE){
                    Log.d("THIS", "Add_store");
                    Intent startAddCreateStore = new Intent(MainNavigationScreenAdmin.this, Create_Update_Store.class);
                    startAddCreateStore.putExtra("Create/Update","Create");
                    startActivity(startAddCreateStore);
                }
                else if(currentType == MainNavigationScreenAdmin.SpecialFucntionType.EDIT_MENU){
                    Log.d("THIS", "edut menu");
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void changeThreeDotFunction(MainNavigationScreenAdmin.SpecialFucntionType e){

        currentType = e;
        invalidateOptionsMenu();

    }
}

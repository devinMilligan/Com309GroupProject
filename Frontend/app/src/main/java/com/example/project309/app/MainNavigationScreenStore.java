package com.example.project309.app;

import android.content.ClipData.Item;
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

public class MainNavigationScreenStore extends AppCompatActivity {

    public enum SpecialFucntionType{

        EDIT_ORDER("Edit Order"),
        EDIT_MENU("Edit Menu");

        private String value;

        private SpecialFucntionType(String val){
            value = val;
        }

        public String getValue(){
            return value;
        }

    }


    private AppBarConfiguration mAppBarConfiguration;

    private SpecialFucntionType currentType = SpecialFucntionType.EDIT_ORDER;
    private View threeDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        Will need to get store values right here
         */

        setContentView(R.layout.activity_main_navigation_screen_store);
        Toolbar toolbar = findViewById(R.id.toolbar_store);
        setSupportActionBar(toolbar);

        threeDots = (View) findViewById(R.id.action_edit_store);

        FloatingActionButton fab = findViewById(R.id.fab_store);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This should still be able to make a new order", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout_store);
        NavigationView navigationView = findViewById(R.id.nav_view_store);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home_store, R.id.nav_menu_store, R.id.nav_log_out_store, R.id.nav_profile_store)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_store);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_navigation_screen_store, menu);

        MenuItem item = menu.findItem(R.id.action_edit_store);

        item.setTitle(currentType.getValue());

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_store);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_edit_store:

                if(currentType == SpecialFucntionType.EDIT_ORDER){
                   Log.d("THIS", "edut order");
                }
                else if(currentType == SpecialFucntionType.EDIT_MENU){
                    Log.d("THIS", "edut menu");
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void changeThreeDotFunction(SpecialFucntionType e){

        currentType = e;
        invalidateOptionsMenu();

    }
}

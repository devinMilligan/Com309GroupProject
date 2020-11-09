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

/**
 * This sets up and controls the structure for the fragments in the MainNavigation Screen Admin
 *
 * @author Devin Milligan
 */
public class MainNavigationScreenAdmin extends AppCompatActivity {

    /**
     * Enum to descibe and idenity different option available in the option menu
     */
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

    /**
     * Current option menu configuration
     */
    public SpecialFucntionType currentType = SpecialFucntionType.ADD_STORE;
    private AppBarConfiguration mAppBarConfiguration;

    /**
     * Runs on the creation of this activity and sets up the structure for the fragments and intializes it
     * then opens up the home fragment
     *
     * @param savedInstanceState
     */
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

    /**
     * Runs on the creation of the 3 dot menu, and sets it to the current configuration
     *
     * @param menu the menu that is being created
     * @return
     */
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

    /**
     * Determines what to do when a MenuItem is clicked in the Option Menu based off the item
     *
     * @param item the MenuItem Cliked
     * @return returns if this succeeds
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        try {
            switch (item.getTitle().toString()) {
                case "Add_Store":

                    if (currentType == SpecialFucntionType.ADD_STORE) {
                        Store temp = new Store();

                        Store.currentStore = temp;

                        Log.d("THIS", "Add_store");
                        Intent startAddCreateStore = new Intent(MainNavigationScreenAdmin.this, Create_Update_Store.class);
                        startAddCreateStore.putExtra("Create/Update", "Create");
                        startActivity(startAddCreateStore);
                    } else if (currentType == MainNavigationScreenAdmin.SpecialFucntionType.EDIT_MENU) {
                        Log.d("THIS", "edut menu");
                    }

                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Method that can be colled by fragments to changed the configuration of the options menu
     *
     * @param e The configration of the options menu
     */
    public void changeThreeDotFunction(MainNavigationScreenAdmin.SpecialFucntionType e){

        currentType = e;
        invalidateOptionsMenu();

    }
}

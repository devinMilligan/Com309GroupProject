package com.example.project309.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.project309.R;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * This Activity is used to create an order by a user and add quanitities for each menu item and place an order
 *
 * @author Devin Milligan
 */
public class OrderingScreen extends AppCompatActivity implements ViewListenerInter, AdapterView.OnItemClickListener, View.OnClickListener, PopUpViewListener {

    /**
     * Current order that is being changed and added
     */
    private Order currentOrder;
    /**
     * {@link JSONHandler} instance to be used to make requests
     */
    private JSONHandlerInter jsonH;

    private StringHandlerInter stringH;
    /**
     * current Menu that is being picked from
     */
    private Menu currentMenu;

    /**
     * Used to help display correctly a menu on the LIst View
     */
    private MenuListAdapter mAdapter;
    /**
     * ListView that the menu is being displayed on
     */
    private ListView lvMenu;

    /**
     * Button that is used to place the order
     */
    private Button btnPlaceOrder;

    /**
     * The pop up that is being used to select the quantities
     */
    private MenuItemPopUp menuItemPopUp;

    /**
     * Text view used to display the current menu items total on the order
     */
    private TextView txtTotal;

    /**
     * Message Box that will be used to display messaged to the user
     */
    private MessageBoxInter message;

    private int menuAddNUm = 0;

    /**
     * Runs when the activity creates and it is making a new order and displaying the menu on the screen
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_screen);

        currentMenu = Store.currentStore.getMenu();
        currentOrder = new Order();

        btnPlaceOrder = (Button)findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(this);

        jsonH = AppController.getInstance().getJSONHandlerInstance();
        jsonH.setListener(this);

        stringH = AppController.getInstance().getStringHandlerInstance();
        stringH.setListener(this);

        lvMenu = (ListView) findViewById(R.id.orderLvMenu);

        if(currentMenu == null || currentMenu.menuItems.isEmpty()){

            currentMenu = new Menu();
            loadMenu();

        }

        mAdapter = new MenuListAdapter(OrderingScreen.this, R.layout.menu_list_adapter, currentMenu.menuItems);
        lvMenu.setAdapter(mAdapter);
        lvMenu.setOnItemClickListener(this);

        menuItemPopUp = new MenuItemPopUp();
        menuItemPopUp.setListener(this);

        txtTotal = (TextView)findViewById(R.id.txtTotal);
        txtTotal.setText(String.format("Total: $%.2f",currentOrder.getOrderPrice()));

        message = new MessageBoxBuilder();
        message.setContext(this);

        ArrayList<JSONVariable> params = new ArrayList<>();
        params.add(new JSONVariable("userID", Integer.toString(Profile.currentLogin.getId())));
        params.add(new JSONVariable("storeID", Integer.toString(Store.currentStore.getID())));

        jsonH.makeJsonObjReqParams(Const.URL_JSON_NEW_ORDER, params, RequestMethod.POST);

    }

    /**
     * This loads the menu and the items from the server by making a request
     */
    private void loadMenu(){

        ArrayList<JSONVariable> params = new ArrayList<>();
        params.add(new JSONVariable("store",Integer.toString(Store.currentStore.getID())));

        jsonH.makeJsonArryReqParams(Const.URL_JSON_GET_STORE_MENU,params);

    }

    @Override
    public void onSuccess(JSONObject response) {

        boolean check = false;

        try{
            currentOrder.setStoreID(response.getInt("store"));
            currentOrder.setOrderNumber(response.getInt("id"));
            currentOrder.setStatus(response.getString("status"));
        }catch (JSONException e){
            check = true;
        }

        if(check){

            try{

                response.getInt("quantity");
                menuAddNUm++;
                if(menuAddNUm < currentOrder.getNumItems()){
                    ArrayList<JSONVariable> params = new ArrayList<>();
                    params.add(new JSONVariable("Order",Integer.toString(currentOrder.getOrderNumber())));
                    params.add(new JSONVariable("Item", Integer.toString(currentOrder.getItem(menuAddNUm).getId())));
                    params.add(new JSONVariable("Quantity",Integer.toString(currentOrder.getItem(menuAddNUm).getQuantity())));

                    jsonH.makeJsonObjReqParams(Const.URL_JSON_ADD_ITEM_TO_ORDER,params,RequestMethod.POST);
                }else {
                    Order.currentOrder = currentOrder;

                    ArrayList<JSONVariable> params = new ArrayList<>();
                    params.add(new JSONVariable("orderID",Integer.toString(currentOrder.getOrderNumber())));

                   stringH.makeStringParams(Const.URL_STRING_ADVANCE_STATUS, params, RequestMethod.POST);
                }

            }catch(JSONException e){



            }

        }

    }

    /**
     * Gets the response of a menu from the server and makes it into the current menu object and updates
     * the display to reflect this menu
     *
     * @param response JSONArray response that contatins a menu in json form
     */
    @Override
    public void onSuccess(JSONArray response) {

        currentMenu = Menu.getMenuJSON(response);
        mAdapter.clear();
        mAdapter.addAll(currentMenu.menuItems);
        mAdapter.notifyDataSetChanged();
        Store.currentStore.setMenu(currentMenu);

    }

    @Override
    public void onSuccess(String response) {

        if(response.equals("true") || response.equals("false")){
            currentOrder = null;
            finish();
        }else {
            message.dismissMessage();
            currentOrder.resetMenu();
            currentOrder.setStatus(response);
            Intent startConfirmation = new Intent(OrderingScreen.this, OrderConfirmation.class);
            startActivity(startConfirmation);
            finish();
        }


    }

    /**
     * Error handling if an error is returned from the request
     *
     * @param error the error that is returned from the request
     */
    @Override
    public void onError(VolleyError error) {

        Log.d("Order", "Error:" + error.toString());

    }

    /**
     * This handles and item click and opens up a menu item that was clicked in the pop up view to add it to the menu
     *
     * @param parent
     * @param view
     * @param position the position of the menu item to be clicked
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        MenuItem temp = (MenuItem)parent.getItemAtPosition(position);
        menuItemPopUp.showPopupWindow(view,temp,currentOrder);

    }

    /**
     * Recieves all the button clicks and determines what to do based off the button clicked
     *
     * @param v the view of the BUtton that contains the id
     */
    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btnPlaceOrder:

                if(currentOrder.getNumItems() == 0){
                    message.showMessage("Please Add an Item", 1);
                }else{
                    message.showMessage("Placing Order...",3);
                    ArrayList<JSONVariable> params = new ArrayList<>();
                    params.add(new JSONVariable("Order",Integer.toString(currentOrder.getOrderNumber())));
                    params.add(new JSONVariable("Item", Integer.toString(currentOrder.getItem(0).getId())));
                    params.add(new JSONVariable("Quantity",Integer.toString(currentOrder.getItem(0).getQuantity())));

                    jsonH.makeJsonObjReqParams(Const.URL_JSON_ADD_ITEM_TO_ORDER,params,RequestMethod.POST);

                }

                break;

        }

    }

    /**
     * This is called when the pop up view is dismissed
     */
    @Override
    public void onDismiss() {

        txtTotal.setText(String.format("Total: $%.2f",currentOrder.getOrderPrice()));

    }

    @Override
    public void onBackPressed(){

        ArrayList<JSONVariable> params = new ArrayList<>();
        params.add(new JSONVariable("orderID",Integer.toString(currentOrder.getOrderNumber())));

        stringH.makeStringParams(Const.URL_CANCEL_ORDER,params, RequestMethod.POST);

    }
}
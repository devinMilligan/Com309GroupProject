package com.example.project309.app;

import androidx.appcompat.app.AppCompatActivity;

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

public class OrderingScreen extends AppCompatActivity implements ViewListenerInter, AdapterView.OnItemClickListener, View.OnClickListener, PopUpViewListener {

    private Order currentOrder;
    private JSONHandlerInter jsonH;

    private Menu currentMenu;

    private MenuListAdapter mAdapter;
    private ListView lvMenu;

    private Button btnPlaceOrder;

    private MenuItemPopUp menuItemPopUp;

    private TextView txtTotal;

    private MessageBoxInter message;

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



    }

    private void loadMenu(){

        ArrayList<JSONVariable> params = new ArrayList<>();
        params.add(new JSONVariable("store",Integer.toString(Store.currentStore.getID())));

        jsonH.makeJsonArryReqParams(Const.URL_JSON_GET_STORE_MENU,params);

    }

    @Override
    public void onSuccess(JSONObject response) {

    }

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

    }

    @Override
    public void onError(VolleyError error) {

        Log.d("Order", "Error:" + error.toString());

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        MenuItem temp = (MenuItem)parent.getItemAtPosition(position);
        menuItemPopUp.showPopupWindow(view,temp,currentOrder);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btnPlaceOrder:

                if(currentOrder.getNumItems() == 0){
                    message.showMessage("Please Add an Item", 1);
                }

                break;

        }

    }

    @Override
    public void onDismiss() {

        txtTotal.setText(String.format("Total: $%.2f",currentOrder.getOrderPrice()));

    }
}
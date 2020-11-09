package com.example.project309.app;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project309.R;
import com.example.project309.app.MenuItem;

import org.w3c.dom.Text;

/**
 * This class is an a Pop up on top of another activity to pick a quanitity of food items that you want
 *
 * @author Devin Milligan
 */
public class MenuItemPopUp implements View.OnClickListener {

    /**
     * TextViews on this popup
     */
    TextView txtTitle, txtDescription;
    /**
     * Edit text on the pop up for quanitity of items
     */
    EditText editNumberQuanity;
    /**
     * Buttons on the pop up
     */
    Button btnUp,btnDown, btnUpdate;
    /**
     * Current MenuItem being altered
     */
    MenuItem currentItem;
    /**
     * Current Order being altered
     */
    Order currentOrder;

    PopupWindow popupWindow;

    /**
     * Listener for the pop up to call when it is dismissed
     */
    PopUpViewListener popUpViewListener;

    /**
     * initial value for the quanitity before changing
     */
    int initialQ = 0;

    /**
     * This displays the pop up window to the user of the menu item's information
     *
     * @param view
     * @param item Menu Item that is being changed
     * @param order Order that this menu Item is being changed on
     */
    public void showPopupWindow(final View view, MenuItem item, Order order) {

        currentItem = item;
        currentOrder = order;

        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_menudetails, null);

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        editNumberQuanity = (EditText)popupView.findViewById(R.id.editNumberQuantity);
        editNumberQuanity.setText(Integer.toString(currentItem.getQuantity()));
        initialQ = currentItem.getQuantity();

        txtTitle = (TextView)popupView.findViewById(R.id.txtItemTitle);
        txtTitle.setText(currentItem.getTitle());
        txtDescription = (TextView)popupView.findViewById(R.id.txtDescription);

        if(currentItem.getDescription().length() > 100){
            txtDescription.setText(currentItem.getDescription().substring(0,100) + "...");
        }else {
           txtDescription.setText(currentItem.getDescription());
        }


        btnDown = (Button)popupView.findViewById(R.id.btnDown);
        btnDown.setOnClickListener(this);
        btnUp = (Button)popupView.findViewById(R.id.btnUp);
        btnUp.setOnClickListener(this);
        btnUpdate = (Button)popupView.findViewById(R.id.btnUpdateCart);
        btnUpdate.setOnClickListener(this);


        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                popUpViewListener.onDismiss();

            }
        });


    }

    /**
     * Sets the listener for the dismissing of the pop up
     *
     * @param popUpViewListener the listener to be called
     */
    public void setListener(PopUpViewListener popUpViewListener){

        this.popUpViewListener = popUpViewListener;

    }

    /**
     * Recieves button clicks from all the buttons and determines what to do from the button that was clicked
     *
     * @param v the View of the button that was clicked that contains the id
     */
    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btnUp:

                currentItem.setQuantity(currentItem.getQuantity() + 1);
                editNumberQuanity.setText(Integer.toString(currentItem.getQuantity()));

                break;

            case R.id.btnDown:

                if(currentItem.getQuantity() == 0){
                    currentItem.setQuantity(1);
                }
                currentItem.setQuantity(currentItem.getQuantity() - 1);
                editNumberQuanity.setText(Integer.toString(currentItem.getQuantity()));

                break;

            case R.id.btnUpdateCart:

                if(currentItem.getQuantity() == 0){
                    currentOrder.removeMenuItem(currentItem, initialQ);
                }else {

                    int diff = currentItem.getQuantity() - initialQ;

                    if(diff > 0) {
                        currentOrder.addMenuItem(currentItem, diff);
                    }else if(diff<0) {
                        currentOrder.removeMenuItem(currentItem, -diff);
                    }

                }
                popupWindow.dismiss();
                break;

        }

    }
}

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

public class MenuItemPopUp implements View.OnClickListener {

    TextView txtTitle, txtDescription;
    EditText editNumberQuanity;
    Button btnUp,btnDown, btnUpdate;
    MenuItem currentItem;
    Order currentOrder;

    PopupWindow popupWindow;

    PopUpViewListener popUpViewListener;

    int initialQ = 0;

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

    public void setListener(PopUpViewListener popUpViewListener){

        this.popUpViewListener = popUpViewListener;

    }

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

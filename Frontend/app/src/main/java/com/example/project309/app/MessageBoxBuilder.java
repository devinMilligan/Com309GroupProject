package com.example.project309.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.example.project309.R;

public class MessageBoxBuilder implements MessageBoxInter, MessageBoxListenerInter{
    public static final String TAG = "MessageBoxBuilder";

    //Context of the class using this class
    protected Context context;
    //Dialog box that will be displayed
    private AlertDialog dialog;

    private String mesStr;

    private MessageBoxListenerInter mB;

    /**
     * Constructor
     */
    public MessageBoxBuilder(){

        mB = this;

    }

    public void setListener(MessageBoxListenerInter messageBoxListenerInter){
        mB = messageBoxListenerInter;
    }

    public void setContext(Context c){

        context = c;

    }


    /**
     * Creates an active AlertDialog box that will display a message to the user
     *
     * @param str  This is the message that will be displayed
     * @param style This is the check to determine which of the set layouts made below you want
     */
    public boolean showMessage(String str, int style){

        if(context != null) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.PositiveButtonStyle);

            mesStr = str;
            builder.setMessage(mesStr);

            Log.d(TAG, "Showing AlertBox with this str: " + str);

            //OK Button with dismissal
            if (style == 1) {
                final String message = mesStr;

                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismissMessage();
                        mB.neutralButtonPressed(message);
                    }
                });
            } else if (style == 2) {
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismissMessage();
                        mB.negativeButtonPressed(mesStr);
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismissMessage();
                        mB.positiveButtonPressed(mesStr);
                    }
                });
            } else if (style == 3) {


            }

            dialog = builder.create();
            dialog.show();
            return true;
        }
        return false;
    }


    /**
     * Dismisses the active AlertDialogBox
     */
    public void dismissMessage(){
        Log.d(TAG,"log box");
        if(dialog != null) {
            Log.d(TAG,"Dismissing the Dialog box");
            dialog.dismiss();
            dialog = null;
        }

        mB.onDismiss(mesStr);

    }


    @Override
    public void onDismiss(String message) {
        Log.d(TAG, message + ": The AlertDialog was dismissed");
    }

    @Override
    public void neutralButtonPressed(String message) {
        Log.d(TAG, message + ": The AlertDialog was dismissed");
    }

    @Override
    public void positiveButtonPressed(String message) {
        Log.d(TAG, message + ": The AlertDialog was dismissed");
    }

    @Override
    public void negativeButtonPressed(String message) {
        Log.d(TAG, message + ": The AlertDialog was dismissed");
    }

}

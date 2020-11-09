package com.example.project309.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.example.project309.R;

/**
 * This class is used to display messages to the user in an interactive way
 *
 * @author Devin Milligan
 */
public class MessageBoxBuilder implements MessageBoxInter, MessageBoxListenerInter{
    /**
     * Tag to be used in log statements to be unique to this class
     */
    public static final String TAG = "MessageBoxBuilder";

    /**
     * The Conext where this box will be displayed
     */
    protected Context context;
    /**
     * The dialog that will host this message
     */
    private AlertDialog dialog;
    /**
     * The message to be displayed
     */
    private String mesStr;

    /**
     * The class to send the responses to
     */
    private MessageBoxListenerInter mB;

    /**
     * Constructor initializing a default listener
     */
    public MessageBoxBuilder(){

        mB = this;

    }

    /**
     * This sets the listener for all the responses to sent to
     *
     * @param messageBoxListenerInter The listener to send all of the messages to
     */
    public void setListener(MessageBoxListenerInter messageBoxListenerInter){
        mB = messageBoxListenerInter;
    }

    /**
     * Sets the context where this box will be displayed
     *
     * @param c Context where it is being displayed
     */
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


    /**
     * Default listener for the dissmissal
     *
     * @param message the message of the box that is being utilized
     */
    @Override
    public void onDismiss(String message) {
        Log.d(TAG, message + ": The AlertDialog was dismissed");
    }

    /**
     * Default Listner for the ok button press
     *
     * @param message the message of the box that is being utilized
     */
    @Override
    public void neutralButtonPressed(String message) {
        Log.d(TAG, message + ": The AlertDialog was dismissed");
    }

    /**
     * Defult listener for the yes button press
     *
     * @param message the message of the box that is being utilized
     */
    @Override
    public void positiveButtonPressed(String message) {
        Log.d(TAG, message + ": The AlertDialog was dismissed");
    }

    /**
     * Default listener for the no button press
     *
     * @param message the message of the box that is being utilized
     */
    @Override
    public void negativeButtonPressed(String message) {
        Log.d(TAG, message + ": The AlertDialog was dismissed");
    }

}

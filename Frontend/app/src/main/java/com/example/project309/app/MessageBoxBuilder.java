package com.example.project309.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.example.project309.R;

public class MessageBoxBuilder {
    public static final String TAG = "MessageBoxBuilder";

    //Context of the class using this class
    private Context context;
    //Dialog box that will be displayed
    private AlertDialog dialog;

    /**
     * Constructor that collects the context to display this message
     * @param c     the context
     */
    public MessageBoxBuilder(Context c){

        context = c;

    }


    /**
     * Creates an active AlertDialog box that will display a message to the user
     *
     * @param str  This is the message that will be displayed
     * @param style This is the check to determine which of the set layouts made below you want
     */
    public void showMessage(String str, int style){
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.PositiveButtonStyle);

        builder.setMessage(str);

        Log.d(TAG,"Showing AlertBox with this str: "+str);

        //OK Button with dismissal
        if(style == 1){
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dismissMessage();
                }
            });
        }
        else if(style == 2){
            builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dismissMessage();

                }
            });
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dismissMessage();

                }
            });
        }
        else if(style == 3){



        }


        dialog = builder.create();
        dialog.show();

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

    }

}

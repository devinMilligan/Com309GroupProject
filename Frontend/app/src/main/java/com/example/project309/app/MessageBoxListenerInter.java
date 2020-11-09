package com.example.project309.app;

import android.util.Log;

/**
 * Interface to be used by Message Box listeners to recieve messages from the Message Box
 *
 * @author Devin Milligan
 */
public interface MessageBoxListenerInter {

    /**
     *
     * @param message the message of the box that is being utilized
     */
    public void onDismiss(String message);
    /**
     *
     * @param message the message of the box that is being utilized
     */
    public void neutralButtonPressed(String message);
    /**
     *
     * @param message the message of the box that is being utilized
     */
    public void positiveButtonPressed(String message);
    /**
     *
     * @param message the message of the box that is being utilized
     */
    public void negativeButtonPressed(String message);

}

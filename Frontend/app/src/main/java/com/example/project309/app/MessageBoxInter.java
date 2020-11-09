package com.example.project309.app;

import android.content.Context;

/**
 * Interface to be used by Message Box that sets fields inside of the Message Box
 *
 * @author Devin Milligan
 */
public interface MessageBoxInter {

    /**
     *
     * @param c Context where it is being displayed
     */
    void setContext(Context c);

    /**
     *
     * @param messageBoxListenerInter The listener to send all of the messages to
     */
    void setListener(MessageBoxListenerInter messageBoxListenerInter);

    /**
     *
     *
     * @param str message to be displayed
     * @param style the kind of message to display
     * @return true if succeed, false if fail
     */
    boolean showMessage(String str, int style);

    void dismissMessage();

}

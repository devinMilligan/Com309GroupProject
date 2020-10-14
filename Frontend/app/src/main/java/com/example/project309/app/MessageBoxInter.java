package com.example.project309.app;

import android.content.Context;

public interface MessageBoxInter {

    void setContext(Context c);
    void setListener(MessageBoxListenerInter messageBoxListenerInter);
    boolean showMessage(String str, int style);
    void dismissMessage();

}

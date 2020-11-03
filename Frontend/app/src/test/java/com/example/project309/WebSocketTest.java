package com.example.project309;

import android.util.Log;

import com.example.project309.app.SocketConnect;
import com.example.project309.app.SocketListener;

import org.junit.Test;

import java.net.URI;

public class WebSocketTest {

    @Test
    public void testSocket(){

        String uri = "wss://echo.websocket.org/";

        SocketConnect.connect(uri);
        SocketConnect.sendMessage("hey");


    }

}

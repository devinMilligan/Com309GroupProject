package com.example.project309.app;

import org.java_websocket.handshake.ServerHandshake;

public interface SocketListener {
    public void onMessage(String message);
}

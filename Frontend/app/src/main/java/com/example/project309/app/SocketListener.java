package com.example.project309.app;

import org.java_websocket.handshake.ServerHandshake;

/**
 * Interface for a listener to get a message from a socket
 *
 * @author Devin Milligan
 */
public interface SocketListener {

    /**
     *
     * @param message the message from the websocket
     */
    public void onMessage(String message);
}

package com.example.project309.app;

import android.util.Log;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.ArrayList;

/**
 * This class is to build a socket connection and communicate with the server
 *
 * @author Devin Milligan
 */
public class SocketConnect {

    /**
     * Tag unique to this class used in log statements
     */
    public static String TAG = "SOCKET_CONNECT";

    /**
     * The socket that is connected and communicating with
     */
    private static WebSocketClient socket;
    /**
     * The URI of the socket that is connected
     */
    private static String currentURI = "";

    /**
     * Arraylist of all the Socket Listeners that will be send a message from the server
     */
    private static ArrayList<SocketListener> arrListeners = new ArrayList<>();

    /**
     * Connects to a socket and disconnect from one if already connected and connects to the new one
     *
     *
     * @param uri the uri of the socket to connect to
     * @return
     */
    public static boolean connect(String uri){

        currentURI = uri;
        URI temp = URI.create(uri);

        if(socket == null){

            initiateSocket(temp);
            socket.connect();

            return true;
        }else if(!uri.equals(currentURI)){
            socket.close();
            initiateSocket(temp);
            socket.connect();
            return false;
        }

        if(!socket.isOpen()) {
            socket.connect();
        }
        return true;
    }

    /**
     * Closes the socket
     */
    public static void closeSocket(){

        socket.close();

    }

    /**
     * Takes a listener out of the array to not get messages anymore
     *
     * @param socketListener the listener to remove
     */
    public static void removeListener(SocketListener socketListener){

        for(int i = 0; i<arrListeners.size();i++){

            SocketListener temp  = arrListeners.get(i);

            if(temp == socketListener){
                arrListeners.remove(i);
            }
        }
    }

    /**
     * Adds a listener to also get a message
     *
     * @param socketListener the listener to add to get a message
     */
    public static void addListener(SocketListener socketListener){

        arrListeners.add(socketListener);

    }

    /**
     * Sends a message to the socket to be recieved by the serrver
     *
     * @param messsage message to be sent
     * @return
     */
    public static boolean sendMessage(String messsage){

        if(socket.isOpen()) {
            if (messsage != null & !messsage.isEmpty()) {
                socket.send(messsage);
                return true;
            }
        }
        return false;
    }

    /**
     * This intiates and opens up a socket connection
     *
     * @param uri the uri of the socket
     */
    private static void initiateSocket(URI uri){

        socket = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.d("SocketConnect","Opened");
            }

            @Override
            public void onMessage(String message) {
                Log.d(TAG, "Message Recieved: " + message);

                for(int i = 0; i<arrListeners.size();i++){

                    SocketListener temp = arrListeners.get(i);

                    if(temp != null) {
                        arrListeners.get(i).onMessage(message);
                    }
                }

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.d(TAG, "Socket Closed: " + reason);
            }

            @Override
            public void onError(Exception ex) {
                Log.d(TAG, "Socket Error: " + ex.getMessage());
            }
        };

    }

}



package com.example.project309.app;

import android.util.Log;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.ArrayList;

public class SocketConnect {

    public static String TAG = "SOCKET_CONNECT";

    private static WebSocketClient socket;
    private static String currentURI = "";

    private static ArrayList<SocketListener> arrListeners = new ArrayList<>();

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

    public static void closeSocket(){

        socket.close();

    }

    public static void removeListener(SocketListener socketListener){

        for(int i = 0; i<arrListeners.size();i++){

            SocketListener temp  = arrListeners.get(i);

            if(temp == socketListener){
                arrListeners.remove(i);
            }
        }
    }

    public static void addListener(SocketListener socketListener){

        arrListeners.add(socketListener);

    }

    public static boolean sendMessage(String messsage){

        if(socket.isOpen()) {
            if (messsage != null & !messsage.isEmpty()) {
                socket.send(messsage);
                return true;
            }
        }
        return false;
    }

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



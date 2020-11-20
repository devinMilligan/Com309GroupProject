package com.example.project309;

import android.util.Log;

import com.example.project309.app.RequestMethod;
import com.example.project309.app.SocketConnect;
import com.example.project309.app.SocketListener;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URI;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WebSocketTest {

    @Test
    public void testSocket(){

        String uri = "wss://echo.websocket.org/";

        SocketConnect.connect(uri);
        SocketConnect.sendMessage("hey");


    }

    @Test
    public void testListener(){

        SocketListener list = mock(SocketListener.class);
        SocketConnect.addListener(list);
        SocketConnect socketConnect = new SocketConnect();

        socketConnect.connectOnMessage("hello");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(list).onMessage(captor.capture());

        assertEquals(captor.getValue(), "hello");

    }



}

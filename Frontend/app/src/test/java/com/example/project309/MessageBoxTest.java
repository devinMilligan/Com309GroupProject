package com.example.project309;

import com.example.project309.app.MessageBoxBuilder;
import com.example.project309.app.MessageBoxListenerInter;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MessageBoxTest {

    @Test
    public void testListener(){

        MessageBoxListenerInter list = mock(MessageBoxListenerInter.class);
        MessageBoxBuilder messageBoxBuilder = new MessageBoxBuilder();

        messageBoxBuilder.setListener(list);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        messageBoxBuilder.onDismiss("dismiss");

        verify(list).onDismiss(captor.capture());

        assertEquals(captor.getValue(), "dismiss");

    }

}

package com.example.project309;

import com.android.volley.VolleyError;
import com.example.project309.app.DeliverUpdateOrderStatus;
import com.example.project309.app.JSONHandler;
import com.example.project309.app.JSONVariable;
import com.example.project309.app.LoginActivity;
import com.example.project309.app.Order;
import com.example.project309.app.Profile;
import com.example.project309.app.RequestMethod;
import com.example.project309.app.StringHandler;
import com.example.project309.app.StringHandlerInter;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class DeliverTest {

    @Test
    public void onSuccess_test() {
        final DeliverUpdateOrderStatus d = new DeliverUpdateOrderStatus();
        StringHandlerInter s = mock(StringHandlerInter.class);
        Order.currentOrder = new Order(999, 9.99);
        Order.currentOrder.setStatus("InTransit");

        doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocation){

                d.onSuccess("InTransit");
                return null;
            }
        }).when(s).makeStringParams(any(String.class), any(ArrayList.class), any(RequestMethod.class));

        assertEquals("InTransit", Order.currentOrder.getStatus());
    }

    @Test
    public void onError_test()  throws JSONException {
        final DeliverUpdateOrderStatus d = new DeliverUpdateOrderStatus();
        StringHandler s = mock(StringHandler.class);

        final JSONObject temp = new JSONObject();
        temp.put("orderID", "167");

        doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {

                d.onError(new VolleyError());
                return null;
            }
        }).when(s).makeStringParams(any(String.class), any(ArrayList.class), any(RequestMethod.class));

        assertEquals(0, d.success);
    }
}

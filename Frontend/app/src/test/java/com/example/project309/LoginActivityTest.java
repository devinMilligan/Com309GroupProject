package com.example.project309;

import com.android.volley.VolleyError;
import com.example.project309.app.AccountType;
import com.example.project309.app.JSONHandler;
import com.example.project309.app.LoginActivity;
import com.example.project309.app.Profile;
import com.example.project309.app.RequestMethod;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;


public class LoginActivityTest {

    @Test
    public void onSuccess_test()  throws JSONException {
        final LoginActivity l = new LoginActivity();
        JSONHandler j = mock(JSONHandler.class);

        final JSONObject temp = new JSONObject();
        temp.put("email", "testEmail");
        temp.put("type", "CustomerDeliverer");

        Profile p = Profile.getProfileInfo(temp);


        doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {

                l.onSuccess(temp);
                return null;
            }
        }).when(j).makeJsonObjReqParams(any(String.class), any(ArrayList.class), any(RequestMethod.class));

        assertEquals(Profile.currentLogin, p);
    }

    @Test
    public void onError_test()  throws JSONException {
        final LoginActivity l = new LoginActivity();
        JSONHandler j = mock(JSONHandler.class);

        final JSONObject temp = new JSONObject();
        temp.put("email", "testEmail");
        temp.put("type", "CustomerDeliverer");

        doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {

                l.onError(new VolleyError());
                return null;
            }
        }).when(j).makeJsonObjReqParams(any(String.class), any(ArrayList.class), any(RequestMethod.class));

        assertEquals(0, l.success);
    }
}

package com.example.project309;

import com.example.project309.app.JSONHandler;
import com.example.project309.app.JSONVariable;
import com.example.project309.app.LoginActivity;
import com.example.project309.app.Profile;
import com.example.project309.app.RequestMethod;
import com.example.project309.app.SignUpActivity;
import com.example.project309.app.StringHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SignUpActivityTesting {

    @Test
    public void onSuccessObject_test()  throws JSONException {
        final SignUpActivity s = new SignUpActivity();
        JSONHandler j = mock(JSONHandler.class);

        final JSONObject temp = new JSONObject();
        temp.put("email", "testEmail");
        temp.put("type", "CustomerDeliverer");

        Profile p = Profile.getProfileInfo(temp);


        doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {

                s.onSuccess(temp);
                return null;
            }
        }).when(j).makeJsonObjReqParams(any(String.class), any(ArrayList.class), any(RequestMethod.class));

        assertEquals(Profile.currentLogin, p);
    }

    @Test
    public void onSuccessString_test() {
        final SignUpActivity s = new SignUpActivity();
        StringHandler sH = mock(StringHandler.class);

        final String temp = "true";

        doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {

                s.onSuccess(temp);
                return null;
            }
        }).when(sH).makeStringParams(any(String.class), any(ArrayList.class), any(RequestMethod.class));

        assertEquals(0, s.signedUp);
    }
}

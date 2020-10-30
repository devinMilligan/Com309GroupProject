package com.example.project309;

import com.android.volley.toolbox.StringRequest;
import com.example.project309.app.AppController;
import com.example.project309.app.JSONVariable;

import com.example.project309.app.Profile;
import com.example.project309.app.RequestMethod;
import com.example.project309.app.StringAbstractListener;
import com.example.project309.app.StringHandler;
import com.example.project309.app.StringHandlerInter;
import com.example.project309.app.StringRequestInter;
import com.example.project309.app.StringRequestSpec;
import com.example.project309.app.ViewListenerInter;
import com.example.project309.app.ui.profile.ProfileFragment;
import com.example.project309.net_utils.Const;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StringRequestTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void setStringListenerTest() {

        StringAbstractListener list;
        StringHandlerInter stringHandler;
        StringRequestInter stringR =  mock(StringRequestSpec.class);

        ArgumentCaptor<StringAbstractListener> captor = ArgumentCaptor.forClass(StringAbstractListener.class);

        AppController.setStringRequestInstance(stringR);

        stringHandler = new StringHandler();

        verify(stringR, times(1)).setListener(captor.capture());

        assertEquals(captor.getValue(), stringHandler);

    }

    @Test
    public void setStringResponseTest(){

        StringAbstractListener list;
        final StringHandlerInter stringHandler;
        StringRequestInter stringR =  mock(StringRequestSpec.class);
        ViewListenerInter viewL = mock(ViewListenerInter.class);

        final ArgumentCaptor<StringAbstractListener> captor = ArgumentCaptor.forClass(StringAbstractListener.class);
        ArgumentCaptor<String> captorS = ArgumentCaptor.forClass(String.class);

        Mockito.doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {

                captor.getValue().responseListString("success");

                return null;
            }
        }).when(stringR).makeStringReqInner(any(String.class),any(ArrayList.class),any(RequestMethod.class));

        AppController.setStringRequestInstance(stringR);

        stringHandler = new StringHandler();
        stringHandler.setListener(viewL);

        verify(stringR, times(1)).setListener(captor.capture());


        stringHandler.makeStringParams("url", new ArrayList<JSONVariable>(), RequestMethod.GET);
        verify(viewL).onSuccess(captorS.capture());

        assertEquals(captorS.getValue(), "success");

    }

}

package com.example.project309;

import com.example.project309.app.AppController;
import com.example.project309.app.JSONAbstractListener;
import com.example.project309.app.JSONHandler;
import com.example.project309.app.JSONHandlerInter;
import com.example.project309.app.JSONRequestInter;
import com.example.project309.app.JSONVariable;
import com.example.project309.app.JsonRequestSpec;
import com.example.project309.app.RequestMethod;
import com.example.project309.app.StringAbstractListener;
import com.example.project309.app.StringHandler;
import com.example.project309.app.StringHandlerInter;
import com.example.project309.app.StringRequestInter;
import com.example.project309.app.StringRequestSpec;
import com.example.project309.app.ViewListenerInter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JSONRequestTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void setJSONListenerTest() {

        JSONAbstractListener list;
        JSONHandlerInter jsonHandler;
        JSONRequestInter jsonR =  mock(JsonRequestSpec.class);

        ArgumentCaptor<JSONAbstractListener> captor = ArgumentCaptor.forClass(JSONAbstractListener.class);

        AppController.setJSONRequestInstance(jsonR);

        jsonHandler = new JSONHandler();

        verify(jsonR, times(1)).setListener(captor.capture());

        assertEquals(captor.getValue(), jsonHandler);

    }

    @Test
    public void setJSONResponseTest() throws JSONException {

        JSONAbstractListener list;
        final JSONHandlerInter jsonHandler;
        JSONRequestInter jsonR =  mock(JsonRequestSpec.class);
        ViewListenerInter viewL = mock(ViewListenerInter.class);


        final JSONObject temp = new JSONObject();
        temp.put("test", "success");

        final ArgumentCaptor<JSONAbstractListener> captor = ArgumentCaptor.forClass(JSONAbstractListener.class);
        ArgumentCaptor<JSONObject> captorJ = ArgumentCaptor.forClass(JSONObject.class);

        Mockito.doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {

                captor.getValue().responseListObject(temp);

                return null;
            }
        }).when(jsonR).makeJsonObjReqInner(any(String.class),any(ArrayList.class),any(ArrayList.class),any(RequestMethod.class));

        AppController.setJSONRequestInstance(jsonR);

        jsonHandler = new JSONHandler();
        jsonHandler.setListener(viewL);

        verify(jsonR, times(1)).setListener(captor.capture());


        jsonHandler.makeJsonObjReqCombo("url", new ArrayList<JSONVariable>(), new ArrayList<JSONVariable>(), RequestMethod.GET);
        verify(viewL).onSuccess(captorJ.capture());

        assertEquals(captorJ.getValue(), temp);

    }

    @Test
    public void setJSONArrayResponseTest() throws JSONException {

        JSONAbstractListener list;
        final JSONHandlerInter jsonHandler;
        JSONRequestInter jsonR =  mock(JsonRequestSpec.class);
        ViewListenerInter viewL = mock(ViewListenerInter.class);


        final JSONObject temp = new JSONObject();
        temp.put("test", "success");
        final JSONArray arrTemp = new JSONArray();
        arrTemp.put(temp);

        final ArgumentCaptor<JSONAbstractListener> captor = ArgumentCaptor.forClass(JSONAbstractListener.class);
        ArgumentCaptor<JSONArray> captorJ = ArgumentCaptor.forClass(JSONArray.class);

        Mockito.doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {

                captor.getValue().responseListArray(arrTemp);

                return null;
            }
        }).when(jsonR).makeJsonArryReqInner(any(String.class),any(ArrayList.class));

        AppController.setJSONRequestInstance(jsonR);

        jsonHandler = new JSONHandler();
        jsonHandler.setListener(viewL);

        verify(jsonR, times(1)).setListener(captor.capture());


        jsonHandler.makeJsonArryReqParams("url", new ArrayList<JSONVariable>());
        verify(viewL).onSuccess(captorJ.capture());

        assertEquals(captorJ.getValue().get(0), arrTemp.get(0));

    }

}

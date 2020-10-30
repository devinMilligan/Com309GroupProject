package com.example.project309.app;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.project309.net_utils.LruBitmapCache;

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static AppController mInstance;
    private static JSONRequestInter jsonR;
    private static JSONHandlerInter jsonH;
    private static MessageBoxInter messageI;
    private static StringRequestInter stringR;
    private static StringHandlerInter stringH;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    public static synchronized AppController getInstance() {

        if (mInstance == null) {
            return new AppController();
        }
        return mInstance;
    }
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            //mImageLoader = new ImageLoader(this.mRequestQueue, new LruBitmapCache());
        }
        return this.mImageLoader;
    }
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public JSONRequestInter getJSONRequestInstance(){

        if(jsonR != null){
            return jsonR;
        }
        return new JsonRequestSpec();

    }

    public JSONHandlerInter getJSONHandlerInstance(){

        if(jsonH != null){
            return jsonH;
        }
        return new JSONHandler();

    }

    public StringRequestInter getStringRequestInstance(){

        if(stringR != null){
            return stringR;
        }
        return new StringRequestSpec();

    }

    public StringHandlerInter getStringHandlerInstance(){

        if(stringH != null){
            return stringH;
        }
        return new StringHandler();

    }

    public MessageBoxInter getMessageBoxBuilderInstance(){

        if(messageI != null){
            return messageI;
        }
        return new MessageBoxBuilder();

    }

    //Methods used for testing

    public static void setJSONRequestInstance(JSONRequestInter jR){

        jsonR = jR;

    }

    public static void setJSONHandlerInstance(JSONHandlerInter jH){

        jsonH = jH;

    }

    public static void setStringRequestInstance(StringRequestInter sR){

        stringR = sR;

    }

    public static void setStringHandlerInstance(StringHandlerInter sH){

        stringH = sH;

    }

    public static void setMessageBoxBuilderInstance(MessageBoxInter mB){

        messageI = mB;

    }

}
package com.example.project309;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.project309.app.AppController;
import com.example.project309.net_utils.Const;

import java.io.UnsupportedEncodingException;

public class ImageRequestSpec {


    private NetworkImageView imgNetWorkView;

    private String TAG = this.getClass().toString();
    private Context context;


    public ImageRequestSpec(Context mContext) {

        context = mContext;
        TAG = TAG +"," + context.getClass().toString();

        //imgNetWorkView = (NetworkImageView) findViewById(R.id.imgNetwork);

    }

    private void makeImageRequest() {
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        // If you are using NetworkImageView
        //imgNetWorkView.setImageUrl(Const.URL_IMAGE, imageLoader);


        // If you are using normal ImageView
		/*imageLoader.get(Const.URL_IMAGE, new ImageListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e(TAG, "Image Load Error: " + error.getMessage());
			}

			@Override
			public void onResponse(ImageContainer response, boolean arg1) {
				if (response.getBitmap() != null) {
					// load image into imageview
					imageView.setImageBitmap(response.getBitmap());
				}
			}
		});*/

        // Loading image with placeholder and error image
        //imageLoader.get(Const.URL_IMAGE, ImageLoader.getImageListener(imageView, R.drawable.ico_loading, R.drawable.ico_error));

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Entry entry = cache.get(Const.URL_IMAGE);
        if(entry != null){
            try {
                String data = new String(entry.data, "UTF-8");
                // handle data, like converting it to xml, json, bitmap etc.,
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else{
            // cached response doesn't exists. Make a network call here
        }

    }



}

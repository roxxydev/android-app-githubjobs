package com.githubjobs.android.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class HttpManager {

    private static HttpManager ourInstance;
    private static Context sCtx;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private HttpManager(Context context) {
        sCtx = context;
        requestQueue = getRequestQueue();

        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {

            private final LruCache<String, Bitmap> lruCache =
                    new LruCache<>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return lruCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                lruCache.put(url, bitmap);
            }
        });
    }

    public static HttpManager getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new HttpManager(context);
        }
        return ourInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(sCtx);
        }
        return requestQueue;
    }

    public <T> void addToRequestQuee(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

}

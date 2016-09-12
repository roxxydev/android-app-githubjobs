package com.githubjobs.android.network;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.githubjobs.android.BuildConfig;
import com.githubjobs.android.R;

import android.webkit.URLUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class RequestCreator {

    public interface Callback {

        void onResponse(String data);

        void onError(VolleyError err);
    }

    /**
     * Create instance of StringRequest to use for RequestQuee.
     * @param requestTag The request tag to be used for cancelling the request.
     * @param endpoint The API endpoint to call.
     * @param method The http method of API.
     * @param queryParams URL query parameters to pass.
     * @param cb The callback of http request made.
     * @return StringRequest instance created.
     */
    public static StringRequest createStringRequest(String requestTag, String endpoint,
                                                    int method, final Map<String, String> queryParams,
                                                    final Callback cb) {

        String serverUrl = BuildConfig.APP_SERVER_URL + endpoint;

        StringRequest request = new StringRequest(method, serverUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        cb.onResponse(response);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        cb.onError(error);
                    }
                }) {

            @Override
            public String getUrl() {
                if (queryParams != null && !queryParams.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(super.getUrl());
                    int i = 0;
                    for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                        try {
                            String key = URLEncoder.encode(entry.getKey(), "UTF-8");
                            String value = URLEncoder.encode(entry.getValue(), "UTF-8");
                            if (i == 0) {
                                sb.append("?").append(key).append("=").append(value);
                            } else {
                                sb.append("&").append(key).append("=").append(value);
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        i++;
                    }
                    return sb.toString();
                }
                return super.getUrl();
            }
        };

        request.setTag(requestTag);
        request.setShouldCache(false);

        return request;
    }

    /**
     * Load image source url to ImageView.
     * @param ctx The context to use with HttpManager.
     * @param imgUrl The image source url to load.
     * @param imageView The ImageView where to display the imgUrl.
     */
    public static void loadImage(Context ctx, String imgUrl, final ImageView imageView) {
        if (imgUrl != null && !imgUrl.isEmpty() && URLUtil.isValidUrl(imgUrl)) {
            HttpManager.getInstance(ctx).getImageLoader().get(
                    imgUrl,
                    new ImageLoader.ImageListener() {
                        @Override
                        public void onResponse(ImageLoader.ImageContainer response,
                                               boolean isImmediate) {
                            imageView.setImageBitmap(response.getBitmap());
                        }

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            imageView.setImageResource(R.mipmap.ic_launcher);
                        }
                    });
        } else {
            // No image url to load, set to app icon
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
    }

}

package com.example.tema3.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonUtil {
    private static SingletonUtil instance;
    private RequestQueue requestQueue;
    private static Context context;

    private SingletonUtil(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized SingletonUtil getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonUtil(context);
        }

        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}

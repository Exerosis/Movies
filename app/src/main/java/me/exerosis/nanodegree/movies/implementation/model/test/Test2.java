package me.exerosis.nanodegree.movies.implementation.model.test;

import com.android.volley.Response;

public class Test2 implements Response.Listener<RequestToken> {
    @Override
    public void onResponse(RequestToken response) {
        System.out.println("WORKING");
    }
}

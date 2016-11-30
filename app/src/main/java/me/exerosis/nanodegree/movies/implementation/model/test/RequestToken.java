package me.exerosis.nanodegree.movies.implementation.model.test;

import com.google.gson.annotations.SerializedName;

public class RequestToken {
    private boolean success = false;

    @SerializedName("request_token")
    private String token;


    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }
}

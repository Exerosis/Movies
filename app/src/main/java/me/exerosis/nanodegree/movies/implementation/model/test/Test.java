package me.exerosis.nanodegree.movies.implementation.model.test;

import com.android.volley.Response;

import me.exerosis.nanodegree.movies.implementation.Config;
import me.exerosis.nanodegree.movies.utilities.GsonGetRequest;


public class Test {

    public static GsonGetRequest<RequestToken> getToken(Response.Listener<RequestToken> listener, Response.ErrorListener errorListener) {
        final String url = "https://api.themoviedb.org/3/authentication/token/new?api_key=" + Config.KEY_THE_MOVIE_DB;

        return new GsonGetRequest<>(url, listener, errorListener);
    }
}

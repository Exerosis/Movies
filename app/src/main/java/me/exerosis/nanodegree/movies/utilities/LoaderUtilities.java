package me.exerosis.nanodegree.movies.utilities;


import com.android.volley.Response;

import java.net.URI;

public final class LoaderUtilities {
    private LoaderUtilities() {

    }

    public static <T> GsonGetRequest<T> getData(URI uri, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        if ("http".equalsIgnoreCase(uri.getScheme()) || "https".equalsIgnoreCase(uri.getScheme()))
            return new GsonGetRequest<>(uri.toString(), listener, errorListener);
        else
            return null;
    }

    public static <T> GsonGetRequest<T> getData(URI uri, Response.Listener<T> listener) {
        return getData(uri, listener, null);
    }

    public static <T> GsonGetRequest<T> getData(String uri, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        return getData(URI.create(uri), listener, errorListener);
    }

    public static <T> GsonGetRequest<T> getData(String uri, Response.Listener<T> listener) {
        return getData(uri, listener, null);
    }
}

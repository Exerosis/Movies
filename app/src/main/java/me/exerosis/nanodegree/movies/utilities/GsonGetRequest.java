package me.exerosis.nanodegree.movies.utilities;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

/**
 * Convert a JsonElement into a list of objects or an object with Google Gson.
 * <p>
 * The JsonElement is the response object for a {@link com.android.volley.Request.Method} GET call.
 *
 * @author https://plus.google.com/+PabloCostaTirado/about
 */
public class GsonGetRequest<T> extends Request<T> {
    private final Gson gson;
    private final Response.Listener<T> listener;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url           URL of the request to make
     * @param listener      is the listener for the right answer
     * @param errorListener is the listener for the wrong answer
     */
    public GsonGetRequest(String url, Gson gson, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);

        System.out.println(Types.getListenerType(listener));
        this.gson = gson;
        this.listener = listener;
    }

    public GsonGetRequest(String url, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(url, new Gson(), listener, errorListener);
    }

    public GsonGetRequest(String url, Response.Listener<T> listener) {
        this(url, new Gson(), listener);
    }

    public GsonGetRequest(String url, Gson gson, Response.Listener<T> listener) {
        this(url, gson, listener, null);
    }

    @Override
    protected void deliverResponse(T response) {
        if (listener != null)
            listener.onResponse(response);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return (Response<T>) Response.success(gson.fromJson(json, Types.getListenerType(listener)), HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }
}
package me.exerosis.nanodegree.movies.utilities;

import com.android.volley.Response;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Types {
    public static Type getListenerType(Response.Listener<?> listener) {
        return ((ParameterizedType) listener.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }
}

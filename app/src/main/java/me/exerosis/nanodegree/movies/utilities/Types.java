package me.exerosis.nanodegree.movies.utilities;

import com.android.volley.Response;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Types {

    @SuppressWarnings("unchecked")
    public static Type getInterfaceGeneric(Class<?> base) {
        TypeToken token = TypeToken.of(base);
        ParameterizedType type = (ParameterizedType) token.getSupertype(Response.Listener.class).getType();
        return type.getActualTypeArguments()[0];
    }
}

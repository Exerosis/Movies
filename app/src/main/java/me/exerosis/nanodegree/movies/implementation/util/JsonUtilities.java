package me.exerosis.nanodegree.movies.implementation.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.JsonReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public final class JsonUtilities {
    private JsonUtilities() {

    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @SuppressWarnings("ConstantConditions")
    public static int getIntegerAt(JsonElement element, String key) {
        JsonElement result = element.getAsJsonObject().get(key);
        return result == null || result.isJsonNull() ? null : result.getAsInt();
    }

    public static String getStringAt(JsonElement element, String key) {
        JsonElement result = element.getAsJsonObject().get(key);
        return result == null || result.isJsonNull() ? null : result.getAsString();
    }

    public static JsonArray getArrayAt(JsonElement element, String key) {
        JsonElement result = element.getAsJsonObject().get(key);
        return result == null || result.isJsonNull() ? null : result.getAsJsonArray();
    }


    public static JsonObject fromURL(URL url) throws IOException {
        Closeable reader = null;
        try {
            reader = url.openStream();
            reader = new InputStreamReader((InputStream) reader);
            reader = new BufferedReader((Reader) reader);

            return (JsonObject) new JsonParser().parse((Reader) reader);

        } finally {
            if (reader != null)
                reader.close();
        }
    }


}
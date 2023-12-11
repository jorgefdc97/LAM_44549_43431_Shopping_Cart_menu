package com.example.lam_44549_43431_shopping_cart_menu;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

class HttpHandler {
    private static final String TAG = HttpHandler.class.getSimpleName();

    public void getProducts(String urlParam) {
        ArrayList<String> resultado = null;
        URL url;
        HttpURLConnection conn;
        InputStream in;

        try {
            url = new URL(urlParam);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            in = new BufferedInputStream(conn.getInputStream());
            resultado = convertStreamToArray(in);
            for(String product:resultado) {
                MainActivity.db.add_product(new Product(product));
            }

        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    private ArrayList<String> convertStreamToArray(InputStream is) {
        String line;
        BufferedReader reader;
        ArrayList<String> sb;

        reader = new BufferedReader(new InputStreamReader(is));
        sb = new ArrayList<>();

        try {
            while ((line = reader.readLine()) != null) {
                sb.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb;
    }
}

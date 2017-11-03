package com.andresual.dev.urbankabinet.Controller;

import android.util.Log;

import com.andresual.dev.urbankabinet.API.API;
import com.andresual.dev.urbankabinet.Activity.SignUpActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andresual on 10/17/2017.
 */

public class NetworkManager {

    String baseUrl = "http://dev.urbankabinet.unicodesolution.com/api/funct/";
    String urlSignIn = baseUrl+"login";
    String urlSignUp = baseUrl+"register";
    String urlVerify = baseUrl+"verifyaccount";
    String urlResendOtp = baseUrl+"resendotp";
    String urlFetchDashboard = baseUrl+"fetchdashboard";

    //metode ini mengkonversi keyValue pairs data ke query string yang dibutuhkan server login
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}

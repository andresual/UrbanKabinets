package com.andresual.dev.urbankabinet.Model;

import org.json.JSONObject;

/**
 * Created by andresual on 10/18/2017.
 */

public class ReturnModel { //handle semua kembalian dari server
    int statusCode;
    String message;
    JSONObject obj;

    public ReturnModel(int statusCode, String message, JSONObject obj) {
        this.statusCode = statusCode;
        this.message = message;
        this.obj = obj;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONObject getObj() {
        return obj;
    }

    public void setObj(JSONObject obj) {
        this.obj = obj;
    }
}

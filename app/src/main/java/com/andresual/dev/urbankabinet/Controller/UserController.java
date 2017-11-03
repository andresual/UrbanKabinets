package com.andresual.dev.urbankabinet.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.andresual.dev.urbankabinet.Activity.SignInActivity;
import com.andresual.dev.urbankabinet.Activity.SignUpActivity;
import com.andresual.dev.urbankabinet.Activity.VerifyAccountActivity;
import com.andresual.dev.urbankabinet.Model.CustomerModel;
import com.andresual.dev.urbankabinet.Model.ReturnModel;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andresual on 10/17/2017.
 */

public class UserController extends NetworkManager { //manggil volley user pada network manager

    Context context;
    SharedPreferences sharedPreferences;
    SessionManager sessionManager;

    private static UserController mInstance;
    public static UserController getInstance() {
        if (mInstance == null) {
            mInstance = new UserController();
            mInstance.customerActive = new CustomerModel();
        }
        return mInstance;
    }

    private CustomerModel customerActive;

    public CustomerModel getCustomerActive() {
        return customerActive;
    }
    public void setCustomerActive(CustomerModel customerActive) {
        this.customerActive = customerActive;
    }

    public void SignUpApi(final SignUpActivity signUpActivity) {

        RequestHandler requestHandler = new RequestHandler();

        final Map<String, String> paramsSignUp = new HashMap<>();
        paramsSignUp.put("platform", "AND_CUSTOMER");
        paramsSignUp.put("version", "1");
        paramsSignUp.put("login_source", customerActive.getLogin_source());
        paramsSignUp.put("email", customerActive.getEmail());
        paramsSignUp.put("password", customerActive.getPassword());
        paramsSignUp.put("name", customerActive.getNama());
        paramsSignUp.put("alamat", customerActive.getAlamat());
        paramsSignUp.put("telp", customerActive.getTelp());
        paramsSignUp.put("referral_daftar", customerActive.getReferral_daftar());
        paramsSignUp.put("tanggal_lahir", customerActive.getTanggal_lahir());
        paramsSignUp.put("social_media_id", customerActive.getSocial_media_id());
        Log.i("PARAMETER SIGN UP",paramsSignUp.toString());

        RequestQueue queue = Volley.newRequestQueue(signUpActivity);
        StringRequest sr = new StringRequest(Request.Method.POST,urlSignUp,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("RESPONSE",response);
                try {
                    JSONObject obj = new JSONObject(response);
                    Log.i("urbankabinet", obj.getString("statuscode"));
                    Log.i("urbankabinet", obj.getString("message"));

                    ReturnModel returnModel = new ReturnModel(obj.getInt("statuscode"), obj.getString("message"), obj);
                    if (returnModel.getStatusCode() == -100 || returnModel.getStatusCode() == -200) {
                        signUpActivity.maintenanceAndUpdate(returnModel);
                    } else {
                        if (returnModel.getStatusCode() == 1) {
                            //
                        }
                        signUpActivity.handleSignup(returnModel);
                    }

                } catch (Throwable t) {
                    Log.i("urbankabinet", "Could not parse malformed JSON: \"" + response + "\"");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                mPostCommentResponse.requestEndedWithError(error);
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                return paramsSignUp;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

    public void SignInApi(final SignInActivity signInActivity) {
//        String url = "dev.urbankabinet.unicodesolution.com/api/funct/login";
        final Map<String, String> paramsSignIn = new HashMap<>();
        paramsSignIn.put("platform", "AND_CUSTOMER");
        paramsSignIn.put("version", "1");
        paramsSignIn.put("login_source", customerActive.getLogin_source());
        paramsSignIn.put("email", "-");
        paramsSignIn.put("password", customerActive.getPassword());
        paramsSignIn.put("name", customerActive.getNama());
        paramsSignIn.put("alamat", customerActive.getAlamat());
        paramsSignIn.put("telp", customerActive.getTelp());
        paramsSignIn.put("referral_daftar", customerActive.getReferral_daftar());
        paramsSignIn.put("tanggal_lahir", customerActive.getTanggal_lahir());
        paramsSignIn.put("social_media_id", customerActive.getSocial_media_id());
        Log.i("PARAMETER SIGN IN",paramsSignIn.toString());

        RequestQueue queue = Volley.newRequestQueue(signInActivity);
        StringRequest sr = new StringRequest(Request.Method.POST,urlSignIn,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("RESPONSE",response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            Log.i("urbankabinet", obj.getString("statuscode"));
                            Log.i("urbankabinet", obj.getString("message"));
                            System.out.println(obj.getString("statuscode"));

                            ReturnModel returnModel = new ReturnModel(obj.getInt("statuscode"),
                                    obj.getString("message"), obj);
                            //handle fungsi rootActivity
                            if (returnModel.getStatusCode() == -100 || returnModel.getStatusCode() == -200) {
                                signInActivity.maintenanceAndUpdate(returnModel);
                            } else {
                                if (returnModel.getStatusCode() == 1) {
                                    //simpan token
                                    System.out.println("ok" + obj.getString("token"));
                                    String token = obj.getString("token");
                                    customerActive.setToken(token);
                                    JSONObject data = obj.getJSONObject("data");
                                    customerActive.setEmail(data.getString("email"));
                                    customerActive.setNama(data.getString("name"));
                                    customerActive.setAlamat(data.getString("alamat"));
                                    customerActive.setTelp(data.getString("telp"));
                                    //SessionManager sessionManager = new SessionManager(context);
                                    //sessionManager.createLoginSession(token);
//                                    JSONObject = obj.getJSONObject("token");
                                }
                                signInActivity.handleSignIn(returnModel);
                            }

                        } catch (Throwable t) {
                            Log.i("urbankabinet", "Could not parse malformed JSON: \"" + response + "\"");
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                mPostCommentResponse.requestEndedWithError(error);
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                return paramsSignIn;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
//          recall user yang inactive 2 minggu = call service email dan sms gateway
//        callServiceSignIn(signInActivity, url, paramsSignIn);
    }

    public void VerifyAccount(final VerifyAccountActivity verifyAccountActivity) {
        final Map<String, String> params = new HashMap<>();
        params.put("platform", "AND_CUSTOMER");
        params.put("version", "1");
        params.put("telp", customerActive.getTelp());
        params.put("otp", customerActive.getOtp());
        params.put("function", "0");

        Log.i("PARAMETER VERIFY", params.toString());

        RequestQueue queue = Volley.newRequestQueue(verifyAccountActivity);
        StringRequest sr = new StringRequest(Request.Method.POST, urlVerify,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("RESPONSE", response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            Log.i("urbankabinet", obj.getString("statuscode"));
                            Log.i("urbankabinet", obj.getString("message"));

                            ReturnModel returnModel = new ReturnModel(obj.getInt("statuscode"), obj.getString("message"), obj);
                            if (returnModel.getStatusCode() == -100 || returnModel.getStatusCode() == -200) {
                                verifyAccountActivity.maintenanceAndUpdate(returnModel);
                            } else {
                                if (returnModel.getStatusCode() == 1) {
                                    //2
                                }
                                verifyAccountActivity.handleVerify(returnModel);
                            }

                        } catch (Throwable t) {
                            Log.i("urbankabinet", "Could not parse malformed JSON: \"" + response + "\"");
                            Toast.makeText(context, "otp salah", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                mPostCommentResponse.requestEndedWithError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

    public void resendOtp(VerifyAccountActivity verifyAccountActivity) {
        final Map<String, String> params = new HashMap<>();
        params.put("platform", "AND_CUSTOMER");
        params.put("version", "1");
        params.put("telp", customerActive.getTelp());
        params.put("function", "0");

        Log.i("PARAMETER SIGN IN", params.toString());

        RequestQueue queue = Volley.newRequestQueue(verifyAccountActivity);
        StringRequest sr = new StringRequest(Request.Method.POST, urlResendOtp,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("RESPONSE", response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            Log.i("urbankabinet", obj.getString("statuscode"));
                            Log.i("urbankabinet", obj.getString("message"));

                        } catch (Throwable t) {
                            Log.i("urbankabinet", "Could not parse malformed JSON: \"" + response + "\"");
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                mPostCommentResponse.requestEndedWithError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

}

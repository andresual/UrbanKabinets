package com.andresual.dev.urbankabinet.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.andresual.dev.urbankabinet.Controller.SessionManager;
import com.andresual.dev.urbankabinet.Controller.UserController;
import com.andresual.dev.urbankabinet.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity {

    private CallbackManager callbackManager;
    SessionManager session;
    ProfilePictureView profilePictureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        Button btnSignUp = (Button) findViewById(R.id.btn_signup);
        Button btnSignIn = (Button) findViewById(R.id.btn_signin);
        LoginButton loginButton = (LoginButton) findViewById(R.id.btn_login);

        loginButton.setReadPermissions("email");
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("fesbuk", response.toString());
//                                setProfileToView(object);

                                if (response.getError() != null) {
                                    //handle error
                                } else {
                                    //dapetin graphObject
                                    String id = object.optString("id");
                                    String name = object.optString("name");
                                    String email = response.getJSONObject().optString("email");
                                }
                            }
                        }
                );

                Bundle params = new Bundle();
                params.putString("fields", "id, name, email");
                request.setParameters(params);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "error to Login Facebook", Toast.LENGTH_SHORT).show();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });

        session = new SessionManager(getApplicationContext());
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        //cek login, fungction di session manager
        session.checkLogin();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        accessTokenTracker.stopTracking();
//        profileTracker.stopTracking();
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        callbackManager.onActivityResult(requestCode, responseCode, intent);
        Intent intenta = new Intent(this, SignUpActivity.class);
        startActivity(intenta);
    }

//    private void setProfileToView(JSONObject jsonObject) {
//        try {
//            profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
//            profilePictureView.setProfileId(jsonObject.getString("id"));
//            linearLayout.setVisibility(View.VISIBLE);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    public void guestClick(){

    }

}

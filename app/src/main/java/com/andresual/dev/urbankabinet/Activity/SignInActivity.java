package com.andresual.dev.urbankabinet.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andresual.dev.urbankabinet.Controller.SessionManager;
import com.andresual.dev.urbankabinet.Controller.UserController;
import com.andresual.dev.urbankabinet.Model.CustomerModel;
import com.andresual.dev.urbankabinet.Model.ReturnModel;
import com.andresual.dev.urbankabinet.R;

public class SignInActivity extends RootActivity {

    public String berhasilLogin;
    SessionManager sessionManager;
//    public static final String USER_PREF = "userPref" ;
//    public static final String USER_TOKEN = "userToken";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        sessionManager = new SessionManager(getApplicationContext());

        final EditText etPhone = (EditText) findViewById(R.id.et_phone_number_sign_in);
        final EditText etPassword = (EditText) findViewById(R.id.et_password);
        Button btnSignIn = (Button) findViewById(R.id.btn_sign_in);

        Toast.makeText(getApplicationContext(), "User Login Status: " + sessionManager.isLoggedIn(), Toast.LENGTH_LONG).show();

//        sharedPreferences = getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etPhone.getText().toString().equals("")) {
                    Toast.makeText(SignInActivity.this, "Insert phone", Toast.LENGTH_SHORT).show();
                } else if (etPassword.getText().toString().equals("")) {
                    Toast.makeText(SignInActivity.this, "Insert password", Toast.LENGTH_SHORT).show();
                }
                else {

                    ProgressDialog progressDialog = new ProgressDialog(SignInActivity.this);
                    progressDialog.setMessage("Signing in...");
                    progressDialog.show();

                    CustomerModel customerSignIn = new CustomerModel();
                    customerSignIn.setNama("-");
                    customerSignIn.setEmail("-");
                    customerSignIn.setTelp(etPhone.getText().toString());
                    customerSignIn.setPassword(etPassword.getText().toString());
                    customerSignIn.setLogin_source("HP");
                    customerSignIn.setAlamat("-");
                    customerSignIn.setReferral_daftar("-");
                    customerSignIn.setSocial_media_id("-");
                    customerSignIn.setTanggal_lahir("1995-06-13");

                    UserController.getInstance().setCustomerActive(customerSignIn);
                    UserController.getInstance().SignInApi(SignInActivity.this);

                }
            }
        });
    }

    public void handleSignIn(ReturnModel returnModel) {
        if (returnModel.getStatusCode() == 1) {
            //berhasil
            sessionManager.createLoginSession(UserController.getInstance().getCustomerActive().getToken());
//            sessionManager.checkLogin();
            Intent intent = new Intent(SignInActivity.this, BottomNavigationActivity.class);
                    startActivity(intent);
        } else {
            Toast.makeText(SignInActivity.this, returnModel.getMessage(), Toast.LENGTH_SHORT).show();
        } if (returnModel.getStatusCode() == 3) {
            Intent intent = new Intent(SignInActivity.this, VerifyAccountActivity.class);
            startActivity(intent);
        }
    }
}

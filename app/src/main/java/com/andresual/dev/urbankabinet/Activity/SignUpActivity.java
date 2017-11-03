package com.andresual.dev.urbankabinet.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.andresual.dev.urbankabinet.Controller.RequestHandler;
import com.andresual.dev.urbankabinet.Controller.UserController;
import com.andresual.dev.urbankabinet.Model.CustomerModel;
import com.andresual.dev.urbankabinet.Model.ReturnModel;
import com.andresual.dev.urbankabinet.R;

public class SignUpActivity extends RootActivity {

    TextView txtCoba;
    AlertDialog.Builder builder;
    RequestHandler requestHandler = new RequestHandler();
    EditText etNama, etEmail, etPhoneNumber, etPassword, etRetypePassword;
    String nama, email, phone, password, retype;
    EditText password1, conPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etNama = (EditText) findViewById(R.id.et_name_sign_up);
        etEmail = (EditText) findViewById(R.id.et_email_sign_up);
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_number_sign_up);
        etPassword = (EditText) findViewById(R.id.et_password_sign_up);
        etRetypePassword = (EditText) findViewById(R.id.et_retype_password_sign_up);
        Button btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        builder = new AlertDialog.Builder(SignUpActivity.this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etNama.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Insert name", Toast.LENGTH_SHORT).show();
                } else if (etEmail.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Insert email", Toast.LENGTH_SHORT).show();
                } else if (etPhoneNumber.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Insert phone number", Toast.LENGTH_SHORT).show();
                } else if (!etPassword.getText().toString().equals(etRetypePassword.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Password do not match", Toast.LENGTH_SHORT).show();
                } else if (etRetypePassword.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Password do not match", Toast.LENGTH_SHORT).show();
                }

//                String pass = etPassword.getText().toString();
//                String rePass = etRetypePassword.getText().toString();
//
//                if(pass.length() < 6)
//                {
//                    etPassword.setError("You must have 6 characters in your password");
//                }
//
//                if(rePass.length() < 7)
//                {
//                    etPassword.setError("You must have 6 characters in your password");
//                }

                else {

                    ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();

                    CustomerModel customerRegister = new CustomerModel();
                    customerRegister.setNama(etNama.getText().toString());
                    customerRegister.setEmail(etEmail.getText().toString());
                    customerRegister.setTelp(etPhoneNumber.getText().toString());
                    customerRegister.setPassword(etPassword.getText().toString());
                    customerRegister.setLogin_source("HP");
                    customerRegister.setAlamat("-");
                    customerRegister.setReferral_daftar("-");
                    customerRegister.setSocial_media_id("-");
                    customerRegister.setTanggal_lahir("1995-06-13");

                    UserController.getInstance().setCustomerActive(customerRegister);
                    UserController.getInstance().SignUpApi(SignUpActivity.this);

//                    Toast.makeText(SignUpActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void handleSignup(ReturnModel returnModel) {
        Log.e("handle", "status code = " + returnModel.getStatusCode());
        Log.e("handle", "message = " + returnModel.getMessage());
        if (returnModel.getStatusCode() == 1) {
            //berhasil
            Intent intent = new Intent(this, VerifyAccountActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, returnModel.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (returnModel.getStatusCode() == -1) {
            Intent intent = new Intent(this, VerifyAccountActivity.class);
            startActivity(intent);
        }
    }
}
package com.andresual.dev.urbankabinet.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andresual.dev.urbankabinet.Controller.UserController;
import com.andresual.dev.urbankabinet.Model.CustomerModel;
import com.andresual.dev.urbankabinet.Model.ReturnModel;
import com.andresual.dev.urbankabinet.R;

public class VerifyAccountActivity extends RootActivity {

    EditText etVerify;
    Button btnSubmitVerify;
    Button btnResendOtp;
    TextView tvCd;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_account);

        etVerify = (EditText) findViewById(R.id.et_pin_number);
        tvCd = (TextView) findViewById(R.id.tv_cd);
        btnSubmitVerify = (Button) findViewById(R.id.btn_submit_pin);
        btnResendOtp = (Button) findViewById(R.id.btn_resend);

        btnSubmitVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etVerify.getText().toString().equals("")) {
                    Toast.makeText(VerifyAccountActivity.this, "Insert code", Toast.LENGTH_SHORT).show();
                } else {
                    CustomerModel customerVerify = new CustomerModel();
//                    VerifyModel verifyAccount = new VerifyModel();
                    customerVerify.setOtp(etVerify.getText().toString());
                    customerVerify.setFunction("0");
                    customerVerify.setTelp("-");

                    UserController.getInstance().setCustomerActive(customerVerify);
//                    UserController.getInstance().getCustomerActive().setToken(etVerify.getText().toString()); //yang simpel ini
                    UserController.getInstance().VerifyAccount(VerifyAccountActivity.this);
                }
            }
        });

        btnResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etVerify.getText().toString().equals("")) {
                    Toast.makeText(VerifyAccountActivity.this, "Insert code", Toast.LENGTH_SHORT).show();
                } else {

                    CustomerModel customerResend = new CustomerModel();
                    customerResend.setTelp("-");
                    customerResend.setFunction("0");

                    UserController.getInstance().setCustomerActive(customerResend);
                    UserController.getInstance().resendOtp(VerifyAccountActivity.this);

                    btnResendOtp.setEnabled(false);
                    new CountDownTimer(30000, 1000) {

                        @Override
                        public void onTick(long millis) {
                            System.out.println(millis);
                            tvCd.setText("" + millis / 1000);
                        }

                        @Override
                        public void onFinish() {
                            btnResendOtp.setEnabled(true);
                        }
                    }.start();
                }
            }
        });

    }

    public void handleVerify(ReturnModel returnModel) {
        Log.e("handle", "status code = " + returnModel.getStatusCode());
        Log.e("handle", "message = " + returnModel.getMessage());
        if (returnModel.getStatusCode() == 1) {
            //berhasil
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, returnModel.getMessage(), Toast.LENGTH_SHORT).show();
        } if (returnModel.getStatusCode() == -1) {
            Toast.makeText(this, returnModel.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

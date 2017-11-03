package com.andresual.dev.urbankabinet.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.andresual.dev.urbankabinet.Model.ReturnModel;

//semua activity harus extends rootActivity

public class RootActivity extends AppCompatActivity {

    public void maintenanceAndUpdate(ReturnModel returnModel){
        if (returnModel.getStatusCode() == -200) {
            Toast.makeText(this, returnModel.getMessage(), Toast.LENGTH_SHORT).show();
        }else if (returnModel.getStatusCode() == -100) {
            Toast.makeText(this, returnModel.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

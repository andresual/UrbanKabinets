package com.andresual.dev.urbankabinet.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.andresual.dev.urbankabinet.R;

public class HotItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_item_details);

        TextView tvCoba = (TextView) findViewById(R.id.tv_coba);
        ImageView ivCoba = (ImageView) findViewById(R.id.iv_coba);

        tvCoba.setText(getIntent().getStringExtra("name"));
        ivCoba.setImageResource(getIntent().getIntExtra("image", 00));
    }
}

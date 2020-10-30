package com.example.project309.app;

import android.os.Bundle;
import android.widget.TextView;
import com.example.project309.R;

import androidx.appcompat.app.AppCompatActivity;

public class PlaceOrder extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        mTextView = (TextView) findViewById(R.id.text);
    }
}
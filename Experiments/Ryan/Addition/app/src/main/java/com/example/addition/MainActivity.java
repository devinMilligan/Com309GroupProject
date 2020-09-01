package com.example.addition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.addition.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText firstNumEditText = (EditText) findViewById(R.id.firstNumEditText);
                EditText secondNumEditText = (EditText) findViewById(R.id.secondNumEditText);
                TextView resultTextView = (TextView) findViewById(R.id.resultTextView);

                if(!firstNumEditText.getText().toString().equals("") && !secondNumEditText.getText().toString().equals("")) {
                    int num1 = Integer.parseInt(firstNumEditText.getText().toString());
                    int num2 = Integer.parseInt(secondNumEditText.getText().toString());
                    int result = num1 + num2;
                    resultTextView.setText(result + "");
                }
                else {
                    Toast myToast = Toast.makeText(getApplicationContext(), R.string.addToastText, Toast.LENGTH_SHORT);
                    myToast.show();
                }
            }
        });
    }

    public void sendMessage(View view) {
        String message;
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText firstNumEditText = (EditText) findViewById(R.id.firstNumEditText);
        EditText secondNumEditText = (EditText) findViewById(R.id.secondNumEditText);
        TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
        if(!firstNumEditText.getText().toString().equals("")) {
            message = firstNumEditText.getText().toString() + " + " + secondNumEditText.getText().toString() + " = " + resultTextView.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
        else {
            Toast myToast = Toast.makeText(this, R.string.pageTwoToastText, Toast.LENGTH_SHORT);
            myToast.show();
        }
    }
}
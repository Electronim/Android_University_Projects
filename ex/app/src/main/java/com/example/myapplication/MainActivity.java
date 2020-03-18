package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Interf1{
    private EditText fullName;
    private Button welcomeButton;
    private TextView textViewA;

    public void setTextViewA(String message) {
        textViewA.setText(message);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullName = findViewById(R.id.fullName);
        welcomeButton = findViewById(R.id.welcomeButton);
        textViewA = findViewById(R.id.textViewA);

        welcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WelcomeScreen fragment = (WelcomeScreen)getSupportFragmentManager().findFragmentByTag("message");
                String message = "Hello " + fullName.getText();
                fragment.setWelcomeText(message);
            }
        });
    }

    @Override
    public void onButtonClick(String message) {
        TextView tv = findViewById(R.id.textViewA);
        tv.setText(message);
    }
}

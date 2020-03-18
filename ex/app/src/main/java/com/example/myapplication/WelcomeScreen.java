package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WelcomeScreen extends Fragment {
    private TextView welcomeText;
    private EditText editText;
    private Button buttonF;

    public void setWelcomeText(String text) {
        this.welcomeText.setText(text);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.welcome_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        welcomeText = view.findViewById(R.id.welcomeText);
        editText = view.findViewById(R.id.editTextF);
        buttonF = view.findViewById(R.id.buttonF);

        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Interf1)getActivity()).onButtonClick(editText.getText().toString());
            }
        });
    }
}

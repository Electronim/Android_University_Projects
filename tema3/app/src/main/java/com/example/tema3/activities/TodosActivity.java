package com.example.tema3.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tema3.R;
import com.example.tema3.fragments.AlarmFragment;
import com.example.tema3.models.Todo;

public class TodosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);
    }

    public void addAlarmFragment(Todo todo) {
        AlarmFragment fragment = new AlarmFragment(todo);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment, "alarm_fragment");
        fragmentTransaction.commit();
    }

    public void removeAlarmFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("alarm_fragment");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }
}

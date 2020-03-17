package com.example.tema1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Activity2 extends AppCompatActivity {
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        frameLayout = findViewById(R.id.frameActivity2);
        addNewFragment(new Fragment1A2());
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void addNewFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        String tag = "";
        if (fragment instanceof  Fragment1A2) {
            tag = "F1A2";
        } else if (fragment instanceof Fragment2A2) {
            tag = "F2A2";
        } else if (fragment instanceof Fragment3A2){
            tag = "F3A2";
        }

        fragmentTransaction.add(R.id.frameActivity2, fragment, tag);
        fragmentTransaction.commit();
    }

    public void deleteFragment(Fragment fragment) {
        if (fragment == null) {
            return ;
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }
}

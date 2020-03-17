package com.example.tema1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2A2 extends Fragment {
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2_a_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() == null || getActivity() == null) {
                    return ;
                }

                Fragment fragment1 = getFragmentManager().findFragmentByTag("F1A2");
                Fragment fragment3 = new Fragment3A2();

                ((Activity2)getActivity()).deleteFragment(fragment1);
                ((Activity2)getActivity()).addNewFragment(fragment3);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() == null || getActivity() == null) {
                    return ;
                }

                Fragment fragment = getFragmentManager().findFragmentByTag("F1A2");
                ((Activity2)getActivity()).deleteFragment(fragment);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() == null) {
                    return ;
                }

                getActivity().finish();
            }
        });
    }
}

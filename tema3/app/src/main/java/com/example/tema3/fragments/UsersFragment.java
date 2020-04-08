package com.example.tema3.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tema3.R;
import com.example.tema3.activities.TodosActivity;
import com.example.tema3.adapters.UserAdapter;
import com.example.tema3.models.User;
import com.example.tema3.util.SingletonUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class UsersFragment extends Fragment implements UserAdapter.OnUserListener {
    private static final String TAG = "UsersFragment";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.recycler_view_users);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        getUsers();
        mAdapter = new UserAdapter(new ArrayList<User>(), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getUsers() {
        String url = "https://my-json-server.typicode.com/MoldovanG/JsonServer/users?fbclid=IwAR3Qa9EuHQyPVuYJqXILFaogE-BqKMmwQSuB5dVyr05X7BeebhAl3fRfe2Q";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<User> users = new ArrayList<>();
                for (int i = 0; i < response.length(); ++i) {
                    try {
                        User user = new User().fromJSON(response.getJSONObject(i));
                        users.add(user);

                        List<User> prev = ((UserAdapter) mAdapter).getmUsers();
                        prev.clear();
                        prev.addAll(users);

                        mAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Volley error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        SingletonUtil.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);
    }

    @Override
    public void onUserClick(int userId) {
        Intent intent = new Intent(getActivity(), TodosActivity.class);
        Log.d(TAG, "onUserClick: putExtra userId = " + userId);
        intent.putExtra("user_id", userId);
        startActivity(intent);
    }
}

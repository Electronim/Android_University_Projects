package com.example.tema3.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tema3.R;
import com.example.tema3.activities.TodosActivity;
import com.example.tema3.adapters.TodoAdapter;
import com.example.tema3.models.Todo;
import com.example.tema3.util.SingletonUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TodosFragment extends Fragment implements TodoAdapter.OnTodoListener {
    private static final String TAG = "TodosFragment";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int mUserId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.recycler_view_todos);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        mUserId = intent.getIntExtra("user_id", -1);

        Log.d(TAG, "onViewCreated: mUserId = " + mUserId);

        getTodos();
        mAdapter = new TodoAdapter(new ArrayList<Todo>(), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getTodos() {
        String url = "https://jsonplaceholder.typicode.com/todos?userId=" + mUserId;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Todo> todos = new ArrayList<>();
                for (int i = 0; i < response.length(); ++i) {
                    try {
                        Todo todo = new Todo().fromJSON(response.getJSONObject(i));
                        todos.add(todo);

                        List<Todo> prev = ((TodoAdapter) mAdapter).getmTodos();
                        prev.clear();
                        prev.addAll(todos);

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onTodoClick(final int todoId) {
        List<Todo> todos = ((TodoAdapter) mAdapter).getmTodos();

        Todo foundTodo = todos.stream().filter(t -> t.getId() == todoId).findFirst().orElse(null);

        ((TodosActivity) Objects.requireNonNull(getActivity())).addAlarmFragment(foundTodo);
    }
}

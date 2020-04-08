package com.example.tema3.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema3.R;
import com.example.tema3.models.Todo;
import com.example.tema3.models.User;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private ArrayList<Todo> mTodos;
    private OnTodoListener mOnTodoListener;

    public ArrayList<Todo> getmTodos() {
        return mTodos;
    }

    public TodoAdapter(ArrayList<Todo> todos, OnTodoListener onTodoListener) {
        this.mTodos = todos;
        this.mOnTodoListener = onTodoListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);
        return new ViewHolder(view, mOnTodoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleView.setText(mTodos.get(position).getTitle());
        holder.statusView.setText(mTodos.get(position).getCompleted() ? "Completed" : "ToDo");
    }

    @Override
    public int getItemCount() {
        return mTodos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleView;
        TextView statusView;
        OnTodoListener onTodoListener;

        public ViewHolder(@NonNull View itemView, OnTodoListener onTodoListener) {
            super(itemView);

            titleView = itemView.findViewById(R.id.title);
            statusView = itemView.findViewById(R.id.status);

            this.onTodoListener = onTodoListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int todoId = mTodos.get(getAdapterPosition()).getId();
            onTodoListener.onTodoClick(todoId);
        }
    }

    public interface OnTodoListener {
        void onTodoClick(int todoId);
    }
}

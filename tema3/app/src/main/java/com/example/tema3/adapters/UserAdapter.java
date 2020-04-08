package com.example.tema3.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema3.R;
import com.example.tema3.models.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private ArrayList<User> mUsers;
    private OnUserListener mOnUserListener;

    public ArrayList<User> getmUsers() {
        return mUsers;
    }

    public UserAdapter(ArrayList<User> users, OnUserListener onUserListener) {
        this.mUsers = users;
        this.mOnUserListener = onUserListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view, mOnUserListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameView.setText(mUsers.get(position).getName());
        holder.usernameView.setText(mUsers.get(position).getUsername());
        holder.emailView.setText(mUsers.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameView;
        TextView usernameView;
        TextView emailView;
        OnUserListener onUserListener;

        public ViewHolder(@NonNull View itemView, OnUserListener onUserListener) {
            super(itemView);

            nameView = itemView.findViewById(R.id.name);
            usernameView = itemView.findViewById(R.id.username);
            emailView = itemView.findViewById(R.id.email);

            this.onUserListener = onUserListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int userId = mUsers.get(getAdapterPosition()).getId();
            onUserListener.onUserClick(userId);
        }
    }

    public interface OnUserListener {
        void onUserClick(int userId);
    }
}

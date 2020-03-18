package com.example.tema2.ApplicationUtilities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema2.DatabaseUtilities.Student;
import com.example.tema2.R;

import java.util.ArrayList;

class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private ArrayList<Student> students;

    public StudentAdapter(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameView.setText(students.get(position).getName());
        holder.markView.setText(String.valueOf(students.get(position).getMark()));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView markView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.name_view);
            markView = itemView.findViewById(R.id.mark_view);
        }
    }
}

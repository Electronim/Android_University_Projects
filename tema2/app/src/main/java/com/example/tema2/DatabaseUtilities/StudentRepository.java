package com.example.tema2.DatabaseUtilities;

import android.content.Context;
import android.os.AsyncTask;

import com.example.tema2.ApplicationUtilities.ApplicationController;

import java.util.List;

public class StudentRepository {
    private AppDatabase mAppDatabase;

    public StudentRepository(Context context) {
        mAppDatabase = ApplicationController.getAppDatabase();
    }

    public void getAllTask(final OnUserRepositoryActionListener listener) {
        new GetAllTask(listener).execute();
    }

    public void insertTask(final Student student, final OnUserRepositoryActionListener listener) {
        new InsertTask(listener).execute(student);
    }

    public void deleteTask(final String name, final OnUserRepositoryActionListener listener) {
        new DeleteTask(listener).execute(name);
    }

    private class GetAllTask extends AsyncTask<Void, Void, List<Student>> {
        OnUserRepositoryActionListener listener;

        GetAllTask(OnUserRepositoryActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<Student> doInBackground(Void... voids) {
            List<Student> students = mAppDatabase.studentDao().getAll();
            return students;
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
            listener.updateData(students);
            listener.actionSuccess();
        }
    }

    private class InsertTask extends AsyncTask<Student, Void, List<Student>> {
        OnUserRepositoryActionListener listener;

        InsertTask(OnUserRepositoryActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<Student> doInBackground(Student... students) {
            mAppDatabase.studentDao().insertAll(students[0]);

            List<Student> studentList = mAppDatabase.studentDao().getAll();
            return studentList;
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
            listener.updateData(students);
            listener.actionSuccess();
        }
    }

    private class DeleteTask extends AsyncTask<String, Void, List<Student>> {
        OnUserRepositoryActionListener listener;

        DeleteTask(OnUserRepositoryActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<Student> doInBackground(String... names) {
            Student student = mAppDatabase.studentDao().findByName(names[0]);

            if (student != null) {
                mAppDatabase.studentDao().delete(student);
            }

            List<Student> studentList = mAppDatabase.studentDao().getAll();
            return studentList;
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
            listener.updateData(students);
            listener.actionSuccess();
        }
    }
}

package com.example.tema2.ApplicationUtilities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tema2.DatabaseUtilities.OnUserRepositoryActionListener;
import com.example.tema2.DatabaseUtilities.Student;
import com.example.tema2.DatabaseUtilities.StudentRepository;
import com.example.tema2.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnUserRepositoryActionListener {
    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private EditText mEditName;
    private EditText mEditMark;
    private Button mAddStudent;
    private Button mRemoveStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditName = findViewById(R.id.name);
        mEditMark = findViewById(R.id.mark);
        mAddStudent = findViewById(R.id.addStudent);
        mRemoveStudent = findViewById(R.id.removeStudent);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new StudentAdapter(new ArrayList<Student>());

        // get all students from room database using student repository
        final StudentRepository studentRepository = new StudentRepository(getApplicationContext());
        studentRepository.getAllTask(this);

        mRecyclerView.setAdapter( mAdapter);


        mAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: add: name = " + mEditName.getText().toString() + "; mark = " + mEditMark.getText().toString());

                try {
                    String name = mEditName.getText().toString();
                    Integer mark = Integer.parseInt(mEditMark.getText().toString());

                    if (name.length() == 0) {
                        Toast.makeText(getApplicationContext(), "Name is invalid", Toast.LENGTH_SHORT).show();
                    } else if (mark >= 1 && mark <= 10) {
                        Student student = new Student(name, mark);
                        studentRepository.insertTask(student, MainActivity.this);
                    } else {
                        Toast.makeText(getApplicationContext(), "Mark is invalid", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    actionFailed();
                }
            }
        });

        mRemoveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: remove: name = " + mEditName.getText().toString());

                String name = mEditName.getText().toString();

                if (name.equals("") || name.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Name is invalid", Toast.LENGTH_SHORT).show();
                } else {
                    List <Student> students = ((StudentAdapter) mAdapter).getStudents();
                    boolean exists = false;

                    for (int i = 0; i < students.size(); i++) {
                        exists |= students.get(i).getName().equals(name);
                    }

                    if (exists) {
                        studentRepository.deleteTask(name, MainActivity.this);
                    } else {
                        Toast.makeText(getApplicationContext(), "This name does not exist!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void updateData(List<Student> students) {
        List<Student> studentList = ((StudentAdapter) mAdapter).getStudents();
        studentList.clear();
        studentList.addAll(students);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void actionSuccess() {
        Toast.makeText(getApplicationContext(), "Succes!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void actionFailed() {
        Toast.makeText(getApplicationContext(), "Error...", Toast.LENGTH_SHORT).show();
    }
}

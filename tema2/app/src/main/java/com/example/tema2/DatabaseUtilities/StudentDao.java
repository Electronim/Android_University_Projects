package com.example.tema2.DatabaseUtilities;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student")
    List<Student> getAll();

    @Query("SELECT * FROM student WHERE name LIKE :name LIMIT 1")
    Student findByName(String name);

    @Insert
    void insertAll(Student... students);

    @Delete
    void delete(Student student);
}

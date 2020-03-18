package com.example.tema2.DatabaseUtilities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "mark")
    private Integer mark;

    public Student(String name, Integer mark) {
        this.name = name;
        this.mark = mark;
    }

    public void setId(int id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMark() {
        return mark;
    }

}

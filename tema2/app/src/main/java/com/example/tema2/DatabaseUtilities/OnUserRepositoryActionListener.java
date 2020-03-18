package com.example.tema2.DatabaseUtilities;

import java.util.List;

public interface OnUserRepositoryActionListener {
    void updateData(List<Student> students);
    void actionSuccess();
    void actionFailed();
}

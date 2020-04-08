package com.example.tema3.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Todo {
    private int userId;
    private int id;
    private String title;
    private Boolean completed;

    public Todo() { }

    public Todo(int userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Todo fromJSON(JSONObject jsonObject) {
        try {
            userId = jsonObject.getInt("userId");
            id = jsonObject.getInt("id");
            title = jsonObject.getString("title");
            completed = jsonObject.getBoolean("completed");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;
    }
}

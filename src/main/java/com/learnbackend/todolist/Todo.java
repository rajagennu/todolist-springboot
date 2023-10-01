package com.learnbackend.todolist;

public class Todo {
    private Integer id;
    private String name;

    public Todo() {

    }

    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

package com.learnbackend.todolist;

import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();
    Todo getById(Integer id);
    void save(Todo todo);
    void deleteByID(Integer id);
}

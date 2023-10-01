package com.learnbackend.todolist;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ToDoServiceImpl implements TodoService{

    List<Todo> todos = new ArrayList<>();
    @Override
    public List<Todo> getAllTodos() {
        return todos;
    }

    @Override
    public Todo getById(Integer id) {
        for (Todo todo: todos) {
            if (Objects.equals(todo.getId(), id)) {
                return todo;
            }
        }
        // what to do if no todos ?
        return new Todo();
    }

    @Override
    public void save(Todo todo) {
        // if todo object is null throw runtime exception
        if (Objects.isNull(todo)) {
            throw new RuntimeException();
        }
        System.out.println("Savind a todo " + todo.getName() + "ID : "+ todo.getId());

        // if todo id is null but todo.name not empty, get its id by size.
        if (Objects.isNull(todo.getId())) {

            todo.setId(todos.size()+1);
            System.out.println("ID added : "+ todo.getId());
            todos.add(todo);
            return;
        }
        // if id is also not empty, that means we have to update the existing todo
        // we need to find the todo in todos list with same id
        Optional<Todo> todoFound = todos.stream().filter((t) -> Objects.equals(t.getId(), todo.getId())).findFirst();
        if (todoFound.isEmpty()) {
            throw new RuntimeException();
        }


        // if found is valid, then simply update its name
        todoFound.get().setName(todo.getName());


    }

    @Override
    public void deleteByID(Integer id) {
        for (Todo todo: todos) {
            if (Objects.equals(todo.getId(), id)) {
                todos.remove(todo);
            }
        }
    }
}

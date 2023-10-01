package com.learnbackend.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        System.out.println("All todos " + todoService.getAllTodos());
        model.addAttribute("alltodolist", todoService.getAllTodos());
        System.out.println("Testing");
        return "index";
    }

    @GetMapping("/new")
    public String addNewTodo(Model model) {
        Todo todo = new Todo();
        model.addAttribute("newTodo", todo);
        return "newtodo";
    }

    @PostMapping("/new")
    public String addNewTodoFrom(@ModelAttribute Todo todo,  Model model) {

        model.addAttribute("newTodo", todo);
        System.out.println("New todo received " + todo.getName());
        todoService.save(todo);
        return "redirect:/";
    }

    @GetMapping("/showUpdate/{id}")
    public String getUpdatePage(@PathVariable(value = "id") int id, Model model) {
        System.out.println("Found the item " + todoService.getById(id));
        model.addAttribute("editedTodo", todoService.getById(id));
        return "update";
    }

    @PostMapping("/todoUpdate/{id}")
    public String updateTodo(@PathVariable(value = "id") int id , @ModelAttribute Todo todo, Model model) {
        model.addAttribute("editedTodo", todo);
        System.out.println("Edited todo " + todo.getName());
        todo.setId(id);
        todoService.save(todo);
        return "redirect:/";
    }

    @GetMapping("/deleteTodo/{id}")
    public String deleteTodo(@PathVariable(value = "id") int id) {
        todoService.deleteByID(id);
        return "redirect:/";
    }


}

package com.example.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.todolist.entity.Todo;
import com.example.todolist.service.TodoService;

@Controller
@ResponseBody
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("/")
    public List<Todo> getTodoList() {
        return todoService.findAll();
    }

    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable("id") int id) {
        return todoService.findById(id);
    }

    @PostMapping("/")
    public Todo postTodo(@RequestBody Todo todo) {
        if (todoService.create(todo)) {
            return todo;
        }
        return null;
    }

    @GetMapping("/delete/{id}")
    public boolean deleteTodo(@PathVariable("id") int id) {
        Todo todo = todoService.findById(id);
        if (todo != null && todoService.delete(todo)) {
            return true;
        }
        return false;
    }

    @PostMapping("/update/{id}")
    public Todo updateTodo(@PathVariable("id") int id, @RequestBody Todo todo) {
        Todo foundTodo = todoService.findById(id);
        if (foundTodo != null) {
            foundTodo.setDescription(todo.getDescription());
            foundTodo.setDone(todo.isDone());
            foundTodo.setPostDate(todo.getPostDate());
            foundTodo.setTitle(todo.getTitle());
            if (todoService.update(foundTodo)) {
                return foundTodo;
            }
        }
        return null;
    }

    @GetMapping("/switch/{id}")
    public Todo switchTodo(@PathVariable("id") int id) {
        Todo foundTodo = todoService.findById(id);
        if (foundTodo != null) {
            foundTodo.setDone(!foundTodo.isDone());
            if (todoService.update(foundTodo)) {
                return foundTodo;
            }
        }
        return null;
    }

    @GetMapping("/done")
    public List<Todo> getDoneList() {
        return todoService.findDone();
    }

    @GetMapping("/notDone")
    public List<Todo> getNotDoneList() {
        return todoService.findNotDone();
    }
}

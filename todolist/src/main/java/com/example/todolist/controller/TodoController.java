package com.example.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class TodoController {

    @GetMapping("/")
    public String home() {
        return "Come in, free todoList! That is if you can find the right URL!";
    }
}

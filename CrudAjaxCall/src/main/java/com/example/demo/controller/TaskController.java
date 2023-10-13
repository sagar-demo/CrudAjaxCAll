package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
//important model packages
import org.springframework.ui.Model;

@Controller
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String listTasks(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "task/list";
    }

    @GetMapping("/add")
    public String addTask(Task task) {
        return "task/add";
    }

    @PostMapping("/save")
    public String saveTask(Task task) {
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
        model.addAttribute("task", task);
        return "task/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/";
    }
}

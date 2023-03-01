package com.github.udanton.tasktracker.api;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import com.github.udanton.tasktracker.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/done")
    public List<TaskDto> getDoneTasks() {
        return taskService.getByStatus(true);
    }

    @GetMapping("/not-done")
    public List<TaskDto> getNotDoneTasks() {
        return taskService.getByStatus(false);
    }

    @GetMapping("/{name}")
    public List<TaskDto> getTaskByName(@PathVariable String name) {
        return taskService.getByName(name);
    }

    @PostMapping
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        taskDto.setId(UUID.randomUUID().toString());
        return taskService.createTask(taskDto);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable String id, @RequestBody TaskDto taskDto) {
        return taskService.updateTask(id, taskDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }

}

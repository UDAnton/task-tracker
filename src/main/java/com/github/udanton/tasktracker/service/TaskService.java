package com.github.udanton.tasktracker.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.github.udanton.tasktracker.api.TaskDto;
import com.github.udanton.tasktracker.persistance.Task;
import com.github.udanton.tasktracker.persistance.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskDto> getByStatus(boolean isDone) {
        return isDone ? mapToDtoList(taskRepository.findByIsDoneTrue()) : mapToDtoList(taskRepository.findByIsDoneFalse());
    }

    public List<TaskDto> getByName(String name) {
        return mapToDtoList(taskRepository.findByName(name));
    }

    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public TaskDto getTaskById(String id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return mapToDto(task);
    }

    public TaskDto createTask(TaskDto taskDto) {
        Task task = mapToEntity(taskDto);
        task = taskRepository.save(task);
        return mapToDto(task);
    }

    public TaskDto updateTask(String id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        ;
        existingTask.setName(taskDto.getName());
        existingTask.setDone(taskDto.isDone());
        existingTask = taskRepository.save(existingTask);
        return mapToDto(existingTask);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    private List<TaskDto> mapToDtoList(List<Task> tasks) {
        if (tasks != null && !tasks.isEmpty()) {
            return tasks.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private TaskDto mapToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDone(task.isDone());
        return taskDto;
    }

    private Task mapToEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDone(taskDto.isDone());
        return task;
    }

}

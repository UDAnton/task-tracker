package com.github.udanton.tasktracker.persistance;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final TaskRepository taskRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 100; i++) {
            Task task = new Task();
            task.setId(UUID.randomUUID().toString());
            task.setName("Task" + i);
            task.setDone(Math.random() < 0.5);
            taskRepository.save(task);
        }
    }

}

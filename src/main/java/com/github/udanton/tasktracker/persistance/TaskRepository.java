package com.github.udanton.tasktracker.persistance;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByName(String name);
    List<Task> findByIsDoneTrue();
    List<Task> findByIsDoneFalse();
}

package com.github.udanton.tasktracker.api;

import lombok.Data;

@Data
public class TaskDto {
    private String id;
    private String name;
    private boolean isDone;
}

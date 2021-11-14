package ru.ibs.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ibs.security.model.Task;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private static final List<Task> TASKS = Arrays.asList(
            new Task(1, "Create app", "Need new application"),
            new Task(2, "Update properties", "Update properties of db in dev stand")
    );

    @GetMapping("{id}")
    public Task getTask(@PathVariable("id") Integer taskId) {
        return TASKS.stream()
                .filter(task -> taskId.equals(task.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Task " + taskId + "not found"
                ));
    }

    @PreAuthorize("hasAnyRole('MANAGER','SCRUM_MASTER','EMPLOYEE')")
    @PutMapping("{id}")
    public void updateTask(@PathVariable("id") Integer taskId) {
        System.out.println("Task updated");
    }

}
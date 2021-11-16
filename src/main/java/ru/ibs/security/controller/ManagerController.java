package ru.ibs.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ibs.security.entities.UserEntity;
import ru.ibs.security.model.Employee;
import ru.ibs.security.model.Task;
import ru.ibs.security.service.UserService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/manager/api", consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class ManagerController {

    @Autowired
    UserService userService;

    private static final List<Employee> EMPLOYEES = Arrays.asList(
            new Employee(1, "Mr. Smith"),
            new Employee(2, "Mr. Proper"),
            new Employee(3, "Mr. Bean")
    );

    private static final List<Task> TASKS = Arrays.asList(
            new Task(1, "Create app", "Need new application"),
            new Task(2, "Update properties", "Update properties of db in dev stand")
    );

    @PreAuthorize("hasAnyRole('MANAGER','SCRUM_MASTER')")
    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") Integer employeeId) {
        return EMPLOYEES.stream()
                .filter(employee -> employeeId.equals(employee.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Employee " + employeeId + "not found"
                ));
    }

    @PreAuthorize("hasAnyRole('MANAGER','SCRUM_MASTER')")
    @GetMapping("/task/{id}")
    public Task getTask(@PathVariable("id") Integer taskId) {
        return TASKS.stream()
                .filter(task -> taskId.equals(task.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Task " + taskId + "not found"
                ));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/employee/{id}")
    public void fireEmployee(@PathVariable("id") Integer employeeId) {
        System.out.println("Employee " + employeeId + "is fired");
    }

    @PreAuthorize("hasAnyRole('MANAGER','SCRUM_MASTER')")
    @PostMapping("/task/{id}")
    public void createTask(@PathVariable("id") String taskId, @RequestBody Task task) {
        System.out.println("Created new task" + task);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public void registerNewEmployee(@RequestBody UserEntity userEntity){
        userService.createUser(userEntity.getUsername(), userEntity.getPassword(), userEntity.getRole());
    }
}

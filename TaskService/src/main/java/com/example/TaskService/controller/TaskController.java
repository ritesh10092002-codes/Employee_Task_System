package com.example.TaskService.controller;

import com.example.TaskService.entity.Task;
import com.example.TaskService.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/admin")
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        return  ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/employee/{employe_id}")
    public List<Task> getTaskForEmployee(@PathVariable Long employe_id){
          return taskService.getTaskForEmployee(employe_id);
    }

    @PutMapping("employee/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTaskStatus(id, task);
    }

    @GetMapping("/admin")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
    @DeleteMapping("/admin/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

}

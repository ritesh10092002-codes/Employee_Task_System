package com.example.TaskService.service;


import com.example.TaskService.DTO.Employee;
import com.example.TaskService.RestClient.EmployeeClient;
import com.example.TaskService.entity.Task;
import com.example.TaskService.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeClient employeeClient;

    public Task createTask(Task task){
        Employee employee=employeeClient.getEmployeeById(task.getEmployeeId());

        if(employee==null){
            throw new RuntimeException("Employee not found with id: "+task.getEmployeeId());
        }

       return taskRepository.save(task);
    }

    public List<Task> getTaskForEmployee(Long employeeID) {

        return taskRepository.findByEmployeeId(employeeID);
    }

    public Task updateTaskStatus(Long id, Task task) {

        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        existing.setStatus(task.getStatus());
        return taskRepository.save(existing);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }
}

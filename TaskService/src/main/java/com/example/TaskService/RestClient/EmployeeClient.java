package com.example.TaskService.RestClient;


import com.example.TaskService.DTO.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeClient {

    private final RestTemplate restTemplate;
    private final String employeeServiceUrl;

    public EmployeeClient(RestTemplate restTemplate,
                          @Value("${employee.service.url}") String employeeServiceUrl) {
        this.restTemplate = restTemplate;
        this.employeeServiceUrl = employeeServiceUrl;
    }

    public Employee getEmployeeById(Long employeeId) {
        String url = employeeServiceUrl + "/employee/{id}";
        return restTemplate.getForObject(url, Employee.class, employeeId);
    }


}

package com.example.TaskService.entity;


import com.example.TaskService.Taskstatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Taskstatus status;


//    @ManyToOne(fetch = FetchType.LAZY)  // Many tasks can belong to one employee
    //@ManyToOne is a JPA annotation, which works only when:
//
//Both entities (Task and Employee) are in the same service (same codebase).
//
//Both tables (tasks, employees) exist in the same database.
//    //Combine data at the service layer, not the DB layer.
   @Column(name = "employe_id", nullable = false)
    private Long employeeId;


}

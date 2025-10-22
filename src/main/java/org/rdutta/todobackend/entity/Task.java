package org.rdutta.todobackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;
    @Column(name = "title", nullable = false)
    private String title;
    @CreationTimestamp
    @Column(name = "task_created_at", updatable = false)
    private Instant taskCreatedAt;
    @Column(name = "description", nullable = false)
    private String description;
    @JsonIgnore
    @ManyToOne
    private TaskGroup taskGroup;
}

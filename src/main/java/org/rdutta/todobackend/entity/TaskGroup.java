package org.rdutta.todobackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups",
        uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskGroupId;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "taskGroup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Task> taskList  = new ArrayList<>();

    @PreRemove
    public void preventDeletion() {
        if ("My Tasks".equals(this.name)) {
            log.warn("Default task group cannot be deleted.");
        }
    }
}

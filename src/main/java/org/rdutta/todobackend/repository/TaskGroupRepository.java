package org.rdutta.todobackend.repository;

import org.rdutta.todobackend.entity.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskGroupRepository extends JpaRepository<TaskGroup, Integer> {
    Optional<TaskGroup> findByName(String name);
}

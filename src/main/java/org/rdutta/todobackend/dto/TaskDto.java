package org.rdutta.todobackend.dto;

public record TaskDto(
        String title,
        String description,
        String taskGroupName
) {
}

package ru.red.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import ru.red.dto.task.TaskDTO;
import ru.red.dto.task.TaskPostDTO;

import java.util.UUID;

public interface ITodoService {
    Uni<TaskDTO> create(TaskPostDTO taskPostDTO);

    Uni<TaskDTO> findById(UUID id);

    Multi<TaskDTO> findAll();

    Uni<TaskDTO> toggleStatus(UUID id);

    Uni<TaskDTO> setStatus(UUID id, Boolean status);

    Uni<Boolean> deleteById(UUID id);
}

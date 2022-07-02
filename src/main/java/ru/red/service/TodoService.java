package ru.red.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import ru.red.dto.task.TaskDTO;
import ru.red.dto.task.TaskPostDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class TodoService implements ITodoService {
    @Override
    public Uni<TaskDTO> create(TaskPostDTO taskPostDTO) {
        return null;
    }

    @Override
    public Uni<TaskDTO> findById(UUID id) {
        return null;
    }

    @Override
    public Multi<TaskDTO> findAll() {
        return null;
    }

    @Override
    public Uni<TaskDTO> toggleStatus(UUID id) {
        return null;
    }

    @Override
    public Uni<TaskDTO> setStatus(UUID id, Boolean status) {
        return null;
    }

    @Override
    public Uni<Boolean> deleteById(UUID id) {
        return null;
    }
}

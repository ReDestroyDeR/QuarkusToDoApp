package ru.red.rest.task;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import ru.red.api.ITodoRestApi;
import ru.red.dto.task.TaskDTO;
import ru.red.dto.task.TaskPostDTO;

import java.util.UUID;

public class TodoRestApi implements ITodoRestApi {
    @Override
    public Uni<TaskDTO> getTask(UUID taskId) {
        return null;
    }

    @Override
    public Multi<TaskDTO> getAllTasks() {
        return null;
    }

    @Override
    public Uni<TaskDTO> addTask(TaskPostDTO taskPostDTO) {
        return null;
    }

    @Override
    public Uni<TaskDTO> setTaskStatus(UUID taskId, Boolean status) {
        return null;
    }

    @Override
    public Uni<Boolean> deleteTask(UUID taskId) {
        return null;
    }
}

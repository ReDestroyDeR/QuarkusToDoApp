package ru.red.api;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import ru.red.dto.task.TaskDTO;
import ru.red.dto.task.TaskPostDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/api/v1/todo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ITodoRestApi {
    /**
     * Create new task
     * @param taskPostDTO task post data transfer object
     * @return persisted task with corresponding id
     */
    @POST
    Uni<TaskDTO> addTask(TaskPostDTO taskPostDTO);

    /**
     * Retrieve present task
     * @param taskId target task id
     * @return task
     * @throws javax.ws.rs.NotFoundException if task doesn't exist
     */
    @Path("/{id}")
    @GET
    Uni<TaskDTO> getTask(@PathParam("id") UUID taskId);

    /**
     * Retrieve all tasks
     * @return Collection of tasks
     */
    @GET
    Multi<TaskDTO> getAllTasks();

    /**
     * Update task status
     * @param taskId target task id
     * @param status (optional) if not specified, present status will be switched
     * @return updated task
     */
    @PATCH
    Uni<TaskDTO> setTaskStatus(@QueryParam("id") UUID taskId,
                               @QueryParam("status") Boolean status);

    /**
     * Delete task by id
     * @param taskId target task id
     * @return boolean indicating successful delete
     */
    @DELETE
    Uni<Boolean> deleteTask(@QueryParam("id") UUID taskId);
}

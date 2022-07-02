package ru.red.dto.task;

import java.util.Objects;
import java.util.UUID;

public final class TaskDTO {
    private final UUID id;
    private final String title;
    private final Boolean status;

    public TaskDTO(UUID id, String title, Boolean status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public UUID id() {
        return id;
    }

    public String title() {
        return title;
    }

    public Boolean status() {
        return status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (TaskDTO) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.title, that.title) &&
                Objects.equals(this.status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, status);
    }

    @Override
    public String toString() {
        return "TaskDTO[" +
                "id=" + id + ", " +
                "title=" + title + ", " +
                "status=" + status + ']';
    }

}

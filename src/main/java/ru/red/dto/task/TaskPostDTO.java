package ru.red.dto.task;

import java.util.Objects;

public final class TaskPostDTO {
    private final String title;

    public TaskPostDTO(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (TaskPostDTO) obj;
        return Objects.equals(this.title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "TaskPostDTO[" +
                "title=" + title + ']';
    }

}

package ru.red.domain.task;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(
        name = "tasks",
        indexes = {
                @Index(name = "status-idx", columnList = "completed")
        }
)
public class Task {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "completed", nullable = false)
    private boolean completed;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isCompleted() == task.isCompleted()
                && getUuid().equals(task.getUuid())
                && getTitle().equals(task.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getTitle(), isCompleted());
    }

    @Override
    public String toString() {
        return "Task{" +
                "uuid=" + uuid +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}

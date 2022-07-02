package ru.red.service;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.helpers.test.AssertSubscriber;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.red.dto.task.TaskDTO;
import ru.red.dto.task.TaskPostDTO;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TodoServiceTest {
    @Inject
    ITodoService service;

    @Inject
    EasyRandom easyRandom;

    @Test
    void createTest() {
        TaskPostDTO taskPostDTO = easyRandom.nextObject(TaskPostDTO.class);
        TaskDTO dto = service.create(taskPostDTO).await().atMost(Duration.ofSeconds(5));
        assertAll(
                () -> assertNotNull(dto.id()),
                () -> assertEquals(taskPostDTO.title(), dto.title()),
                () -> assertEquals(false, dto.status())
        );
    }

    @Test
    void findByIdTest() {
        TaskPostDTO taskPostDTO = easyRandom.nextObject(TaskPostDTO.class);
        TaskDTO dto = service.create(taskPostDTO).await().atMost(Duration.ofSeconds(5));

        TaskDTO found = service.findById(dto.id()).await().atMost(Duration.ofSeconds(5));
        assertEquals(dto, found);
    }

    @Test
    void findAllTest() {
        List<TaskDTO> dtoList = Stream.generate(() -> {
            TaskPostDTO taskPostDTO = easyRandom.nextObject(TaskPostDTO.class);
            return service.create(taskPostDTO).await().atMost(Duration.ofSeconds(5));
        }).limit(10).toList();

        service.findAll()
                .filter(e -> !dtoList.contains(e))
                .subscribe()
                .withSubscriber(AssertSubscriber.create())
                .assertHasNotReceivedAnyItem()
                .awaitCompletion(Duration.ofSeconds(5));
    }

    @Test
    void toggleStatusTest() {
        TaskPostDTO taskPostDTO = easyRandom.nextObject(TaskPostDTO.class);
        TaskDTO dto = service.create(taskPostDTO).await().atMost(Duration.ofSeconds(5));

        TaskDTO toggled = service.toggleStatus(dto.id()).await().atMost(Duration.ofSeconds(5));
        assertNotEquals(dto.status(), toggled.status());
    }

    @Test
    void setStatusTest() {
        TaskPostDTO taskPostDTO = easyRandom.nextObject(TaskPostDTO.class);
        TaskDTO dto = service.create(taskPostDTO).await().atMost(Duration.ofSeconds(5));

        TaskDTO toggled = service.setStatus(dto.id(), !dto.status()).await().atMost(Duration.ofSeconds(5));
        assertNotEquals(dto.status(), toggled.status());
    }

    @Test
    void deleteByIdTest() {
        TaskPostDTO taskPostDTO = easyRandom.nextObject(TaskPostDTO.class);
        TaskDTO dto = service.create(taskPostDTO).await().atMost(Duration.ofSeconds(5));

        boolean deleted = service.deleteById(dto.id()).await().atMost(Duration.ofSeconds(5));
        assertTrue(deleted);
        service.findById(dto.id())
                .subscribe()
                .withSubscriber(UniAssertSubscriber.create())
                .assertFailedWith(NotFoundException.class);
    }
}

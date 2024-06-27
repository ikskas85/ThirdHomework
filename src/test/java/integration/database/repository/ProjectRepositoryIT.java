package integration.database.repository;

import integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spring.entities.Project;
import spring.repository.ProjectRepository;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
class ProjectRepositoryIT extends IntegrationTestBase {
    private final ProjectRepository projectRepository;
    Project project;

    @BeforeEach
    public void setUp() {
        project = Project.builder().name("Test Project").startDate(LocalDate.of(2000, 1, 1)).build();
    }

    @Test
    void create() {
        AtomicLong counter = new AtomicLong(projectRepository.findAll().size());
        projectRepository.saveAndFlush(project);
        assertEquals(counter.incrementAndGet(), projectRepository.findAll().size());
    }

    @Test
    void findByName() {
        assertNotNull(projectRepository.findById("Test Project"));
    }

    @Test
    void update() {
        projectRepository.save(project);
        assertNotNull(projectRepository.findById("Test Project"));
    }

    @Test
    void delete() {
        projectRepository.save(project);
        int count = projectRepository.findAll().size();
        assertNotNull(projectRepository.findById("Test Project"));
        projectRepository.delete(project);
        assertEquals(count - 1, projectRepository.findAll().size());
    }
}
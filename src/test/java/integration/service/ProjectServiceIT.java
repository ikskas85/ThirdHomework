package integration.service;

import integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spring.dto.CompanyDto;
import spring.dto.ProjectDto;
import spring.service.ProjectService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class ProjectServiceIT extends IntegrationTestBase {
    private final ProjectService projectService;
    private ProjectDto projectDto;
    private Integer countOfEntities = 10;


    @BeforeEach
    public void setUp() {
        projectDto = new ProjectDto("test", LocalDate.now());
    }

    @Test
    void findAll() {
        List<ProjectDto> projectDtos = projectService.readAll();
        assertFalse(projectDtos.isEmpty());
        assertEquals(projectDtos.size(), countOfEntities);
    }

    @Test
    void findById() {
        Optional<ProjectDto> maybeProject = projectService.readByName("STARWOOD PROPERTY TRUST, INC.");
        assertTrue(maybeProject.isPresent());
        maybeProject.ifPresent(company -> assertEquals(LocalDate.of(2021, 4, 6), company.date()));
    }

    @Test
    void create() {
        int size = projectService.readAll().size();
        projectService.save(projectDto);
        assertTrue(projectService.readAll().size() > size);
    }

    @Test
    void update() {
        projectService.save(projectDto);
        projectService.update(
                new ProjectDto(
                        projectDto.name(),
                        LocalDate.of(2000, 1, 1))
        );
        assertEquals(LocalDate.of(2000, 1, 1).toString(),
                projectService.readByName(projectDto.name()).get().date().toString());
    }

    @Test
    void delete() {
        projectService.save(projectDto);
        int size = projectService.readAll().size();
        projectService.deleteById(projectDto.name());
        assertTrue(size > projectService.readAll().size());
    }
}

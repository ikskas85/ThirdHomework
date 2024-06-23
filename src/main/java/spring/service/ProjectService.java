package spring.service;

import lombok.RequiredArgsConstructor;
import spring.dto.CompanyDto;
import spring.dto.EmployeeDto;
import spring.dto.ProjectDto;
import spring.entities.Company;
import spring.entities.Project;
import spring.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public void save(ProjectDto projectDto) {

        projectRepository.saveAndFlush(createProject(projectDto));
    }

    public Optional<Project> readById(String name) {
        return projectRepository.findById(name);
    }

    public List<Project> readAll() {
        return projectRepository.findAll();
    }

    public void deleteById(ProjectDto projectDto) {
        projectRepository.delete(createProject(projectDto));
    }

    public void update(ProjectDto projectDto) {
        projectRepository.saveAndFlush(createProject(projectDto));
    }


    private Project createProject(ProjectDto projectDto) {
        return Project.builder()
                .name(projectDto.name())
                .startDate(projectDto.date())
                .build();
    }
}

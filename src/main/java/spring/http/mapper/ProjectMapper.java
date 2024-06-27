package spring.http.mapper;

import spring.dto.ProjectDto;
import spring.entities.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectMapper {
    static ProjectDto mappedToDto(Project project) {
        return new ProjectDto(project.getName(), project.getStartDate());
    }

    static Optional<ProjectDto> optionalMappedToDto(Optional<Project> project) {
        return project.stream()
                .map(ProjectMapper::mappedToDto)
                .findFirst();
    }

    static Project mappedDtoToProject(ProjectDto projectDto) {
        return Project.builder()
                .name(projectDto.name())
                .startDate(projectDto.date())
                .build();
    }

    static List<ProjectDto> mappedToDtoList(List<Project> projects) {
        return projects.stream()
                .map(ProjectMapper::mappedToDto)
                .toList();
    }
}
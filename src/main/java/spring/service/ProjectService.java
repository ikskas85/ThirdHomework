package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import spring.dto.ProjectDto;
import spring.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static spring.http.mapper.ProjectMapper.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Transactional
    public void save(ProjectDto projectDto) {
        projectRepository.saveAndFlush(mappedDtoToProject(projectDto));
    }

    public Optional<ProjectDto> readByName(String name) {
        return optionalMappedToDto(projectRepository.findById(name));
    }

    public List<ProjectDto> readAll() {
        return mappedToDtoList(projectRepository.findAll());
    }

    @Transactional
    public boolean deleteById(String name) {
        return projectRepository.findById(name).map(entity -> {
            projectRepository.delete(entity);
            return true;
        }).orElse(false);
    }

    @Transactional
    public void update(ProjectDto projectDto) {
        projectRepository.saveAndFlush(mappedDtoToProject(projectDto));
    }


}

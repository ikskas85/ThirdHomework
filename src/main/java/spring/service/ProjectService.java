package spring.service;

import lombok.RequiredArgsConstructor;
import spring.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;


}

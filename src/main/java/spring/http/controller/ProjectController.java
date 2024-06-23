package spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import spring.service.ProjectService;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;


}

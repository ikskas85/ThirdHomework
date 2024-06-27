package spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import spring.dto.ProjectDto;
import spring.service.ProjectService;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/{name}")
    public String findById(@PathVariable("name") String name) {
        return projectService.readByName(name)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND))
                .toString();
    }

    @GetMapping
    public String findAll() {
        return projectService.readAll().toString();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public String create(ProjectDto projectDto,
                         Model model) {
        projectService.save(projectDto);
        model.addAttribute(projectDto.name());
        return "redirect:/projects/" + projectDto.name();
    }

    @PutMapping
    public String update(ProjectDto projectDto,
                         Model model) {
        projectService.update(projectDto);
        model.addAttribute(projectDto.name());
        return "redirect:/projects/" + projectDto.name();
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(NO_CONTENT)
    public String delete(@PathVariable("name") String name) {
        if (!projectService.deleteById(name)) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        return "redirect:projects";
    }
}

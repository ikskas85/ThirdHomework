package spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView create(ProjectDto projectDto,
                               ModelAndView modelAndView) {
        projectService.save(projectDto);
        modelAndView.setViewName("redirect:/companies/" + projectDto.name());
        return modelAndView;
    }


    @PutMapping
    public ModelAndView update(ProjectDto projectDto,
                               ModelAndView model) {
        projectService.update(projectDto);
        model.setViewName("redirect:/companies");
        return model;
    }


    @DeleteMapping("/{name}")
    @ResponseStatus(NO_CONTENT)
    public ModelAndView delete(ModelAndView modelAndView,
                               @PathVariable("name") String name) {
        if (!projectService.deleteById(name)) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        modelAndView.setViewName("redirect:companies");
        return modelAndView;
    }

}

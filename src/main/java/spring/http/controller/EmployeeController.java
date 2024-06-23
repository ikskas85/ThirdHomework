package spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import spring.dto.EmployeeDto;
import spring.service.EmployeeService;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/{uuid}")
    public String findById(@PathVariable("uuid") UUID uuid) {
        return employeeService.readById(uuid)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND))
                .toString();
    }

    @GetMapping
    public String findAll() {
        return employeeService.readAll().toString();
    }

    @PostMapping("")
    @Transactional
    @ResponseStatus(CREATED)
    public ModelAndView create(EmployeeDto employeeDto,
                               ModelAndView modelAndView) {
        employeeService.save(employeeDto);
        modelAndView.setViewName("redirect:/employees/" + employeeDto.id());
        return modelAndView;
    }


    @PutMapping
    public ModelAndView update(EmployeeDto employeeDto,
                               ModelAndView model) {
        employeeService.update(employeeDto);
        model.setViewName("redirect:/employees/" + employeeDto.id());
        return model;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ModelAndView delete(ModelAndView modelAndView,
                               @PathVariable("id") UUID uuid) {
        if (!employeeService.deleteById(uuid)) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        modelAndView.setViewName("redirect:/employees");
        return modelAndView;
    }
}

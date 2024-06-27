package spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
    public String create(EmployeeDto employeeDto,
                         Model model) {
        employeeService.save(employeeDto);
        model.addAttribute(employeeDto.id());
        return "redirect:/employees/" + employeeDto.id();
    }

    @PutMapping
    public String update(EmployeeDto employeeDto,
                         Model model) {
        employeeService.update(employeeDto);
        model.addAttribute(employeeDto.id());
        return "redirect:/employees/" + employeeDto.id();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public String delete(@PathVariable("id") UUID uuid) {
        if (!employeeService.deleteById(uuid)) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        return "redirect:/employees";
    }
}
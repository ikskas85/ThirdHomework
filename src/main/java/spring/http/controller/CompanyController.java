package spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import spring.dto.CompanyDto;
import spring.service.CompanyService;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id) {
        return companyService.readById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND))
                .toString();
    }

    @GetMapping
    public String findAll() {
        return companyService.readAll().toString();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public String create(CompanyDto companyDto,
                         Model model) {
        companyService.save(companyDto);
        model.addAttribute(companyDto.id());
        return "redirect:/companies/" + companyDto.id();
    }

    @PutMapping
    public String update(CompanyDto companyDto,
                         Model model) {
        companyService.update(companyDto);
        model.addAttribute(companyDto.id());
        return "redirect:/companies/" + companyDto.id();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public String delete(@PathVariable("id") Integer id) {
        if (!companyService.deleteById(id)) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        return "redirect:/companies";
    }
}
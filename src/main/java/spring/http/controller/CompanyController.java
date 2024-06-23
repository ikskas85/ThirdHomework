package spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView create(CompanyDto companyDto,
                               ModelAndView modelAndView) {
        companyService.save(companyDto);
        modelAndView.setViewName("redirect:/companies/" + companyDto.id());
        return modelAndView;
    }


    @PutMapping
    public ModelAndView update(CompanyDto companyDto,
                               ModelAndView model) {
        companyService.update(companyDto);
        model.setViewName("redirect:/companies/" + companyDto.id());
        return model;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ModelAndView delete(ModelAndView modelAndView,
                               @PathVariable("id") Integer id) {
        if (!companyService.deleteById(id)) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        modelAndView.setViewName("redirect:/companies/" + id);
        return modelAndView;
    }
}

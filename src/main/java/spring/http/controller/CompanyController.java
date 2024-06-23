package spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.dto.CompanyDto;
import spring.service.CompanyService;

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("company/{id}")
    public String companyById(@PathVariable("id") Integer id) {
        return companyService.readById(id).isPresent() ?
                companyService.readById(id).get().toString() : "Unknown Company";
    }

    @GetMapping("company/all")
    public String companyAll() {
        return companyService.readAll().toString();
    }

    @PutMapping("/update-company")
    public ModelAndView updateCompany(@RequestParam String name,
                                      @RequestParam Integer id,
                                      ModelAndView model) {
        companyService.update(create(id, name));
        model.addObject(create(id, name));
        model.setViewName("redirect:/company/" + id);
        return model;
    }

    private CompanyDto create(Integer id, String name) {
        return new CompanyDto(id, name);
    }

    @DeleteMapping("/delete-company")
    public ModelAndView deleteCompany(ModelAndView modelAndView,
                                      @RequestParam("id") int id,
                                      @RequestParam(value = "name", required = false) String name) {
        companyService.deleteById(create(id, name));
        modelAndView.addObject(create(id, name));
        modelAndView.setViewName("redirect:/company/" + id);
        return modelAndView;
    }
}

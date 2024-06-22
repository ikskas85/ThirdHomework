package spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.service.CompanyService;

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("company/{id}")
    public ModelAndView companyById(ModelAndView modelAndView,
                                    @PathVariable("id") Integer id) {
        modelAndView.addObject(companyService.readById(id).toString());
        modelAndView.setViewName("read-company");
        return modelAndView;
    }

    @PutMapping("/update-company")
    public ModelAndView updateCompany(@RequestParam String name,
                                      @RequestParam Integer id,
                                      ModelAndView modelAndView) {
        modelAndView.setViewName("update-company");
        return modelAndView;
    }

    @DeleteMapping("/delete-company")
    public ModelAndView deleteCompany(ModelAndView modelAndView,
                                      @RequestParam("id") int id) {
        return modelAndView;
    }
}

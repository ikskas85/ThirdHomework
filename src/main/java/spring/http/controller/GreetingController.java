package spring.http.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GreetingController {

    //TODO cool GET
    @GetMapping("/index")
    public ModelAndView hello(ModelAndView mav) {
        mav.setViewName("index");
        return mav;
    }

    //TODO cool POST with dynamic attribute
    @PostMapping("/greeting")
    public ModelAndView greeting(ModelAndView mav,
                                 @RequestParam("name") String name) {
        mav.addObject("name", name);
        mav.setViewName("greeting");
        return mav;
    }
}

package com.arun.sample.springbootdemo;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {

    @RequestMapping(name = "/moviesForMe")
    public String getMovies(@RequestParam(name = "name" ,required = true, defaultValue = "Arun") String name, Model model) {
        model.addAttribute("name",name);
        return "hello";
    }
}

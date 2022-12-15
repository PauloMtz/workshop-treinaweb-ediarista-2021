package com.home.ediaristas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
    
    @GetMapping
    public ModelAndView index() {
        // local onde está o arquivo lista
        var mv = new ModelAndView("admin/diaristas/index");
        return mv;
    }
}

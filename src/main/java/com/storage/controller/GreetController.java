package com.storage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GreetController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ModelAndView greet() {

        ModelAndView model = new ModelAndView("index.jsp");
        model.addObject("bannerHeader1", "кирилица");
        return model;
    }


}
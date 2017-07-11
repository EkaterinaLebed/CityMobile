package com.lea.mobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @RequestMapping(value = {"","/console"},method = RequestMethod.GET)
    public ModelAndView showManagerConsole() {
        return new ModelAndView("manager/console");
    }
}

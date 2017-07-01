package com.lea.mobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/billing")
public class BillingController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView  showBillingPage(){
        return new ModelAndView("pages/performBilling");
    }
}

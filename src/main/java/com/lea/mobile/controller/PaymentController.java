package com.lea.mobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showPaymentPage(){
        return new ModelAndView("pages/payment");
    }
}

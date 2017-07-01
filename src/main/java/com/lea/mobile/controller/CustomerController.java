package com.lea.mobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/abonent")
public class CustomerController {
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    ModelAndView showAbonentCreatePage(){
        return new ModelAndView("pages/createAbonent");
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    ModelAndView showAbonentFindPage(){
        return new ModelAndView("pages/findAbonent");
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    ModelAndView showAbonentInfoPage(){
        return new ModelAndView("pages/abonentInfo");
    }

    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    ModelAndView showAbonentCabinetPage(){
        return new ModelAndView("pages/abonentCabinet");
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    String doSearchAbonent(@RequestParam String name){
        //@ResponseBody + @Controller = @RestController
        return name;
    }
}

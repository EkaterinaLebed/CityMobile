package com.lea.mobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/abonent")
public class CustomerController {
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    ModelAndView showAbonentCreatePage(){
        return new ModelAndView("/abonent/createAbonent");
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    ModelAndView showAbonentFindPage(){
        return new ModelAndView("/abonent/findAbonent");
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    ModelAndView showAbonentInfoPage(){
        return new ModelAndView("/abonent/abonentInfo");
    }

    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    ModelAndView showAbonentCabinetPage(){
        return new ModelAndView("/abonent/abonentCabinet");
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    String search(@RequestParam String name){
        //@ResponseBody + @Controller = @RestController
        return name;
    }

    @ResponseBody
    @RequestMapping(value = "/create/do", method = RequestMethod.POST)
    public String create(HttpServletRequest request){
        String login = request.getParameter("name");
        String pwd = request.getParameter("address");
        return "";
    }
}

package com.lea.mobile.controller;

import com.lea.mobile.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;

@Controller
public class HomeController extends HttpServlet {
    @Autowired
    ProductService productService;

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public ModelAndView showHomePage() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("serviceList", productService.selectAll());
        return model;
    }
}

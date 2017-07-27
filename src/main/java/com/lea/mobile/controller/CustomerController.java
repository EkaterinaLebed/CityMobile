package com.lea.mobile.controller;

import com.lea.mobile.entity.Customer;
import com.lea.mobile.service.CustomerService;
import com.lea.mobile.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    ModelAndView showAbonentCreatePage(){
        ModelAndView model = new ModelAndView("/abonent/createAbonent");
        model.addObject("serviceList",productService.selectAll());
        return model;
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
    @RequestMapping(value = "/create/do", method = RequestMethod.POST,
    produces = "text/xml; charset=utf-8")
    public String create(HttpServletRequest request){
        Customer customer = new Customer();
        customer.setName(request.getParameter("name"));
        customer.setBillingАddress(request.getParameter("address"));

        customerService.create(customer);

        StringBuilder sb = new StringBuilder();
        sb.append("<customer>")
            .append("<id>").append(customer.getId()).append("</id>")
            .append("<name>").append(customer.getName()).append("</name>")
        .append("</customer>");
        return sb.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/add/service/do", method = RequestMethod.POST,
    produces = "text/xml; charset=utf-8")
    public String addService(HttpServletRequest request){

        int id = Integer.parseInt(request.getParameter("abonentId"));
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        return "<error>0<error>";

//        Customer customer = customerService.selectById(id);
//        Product product = productService.selectById(serviceId);
//        customerService.addProduct(customer,product);

//        StringBuilder sb = new StringBuilder();
//        sb.append("<product>")
//            .append("<name>").append(product.getName()).append("</name>")
//            .append("<activated_date>").append(new Date()).append("</activated_date>")
//            .append("<payment>").append(product.getPayment()).append("</payment>")
//        .append("</product>");
//
//        return sb.toString();
    }
}

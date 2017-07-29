package com.lea.mobile.controller;

import com.lea.mobile.entity.Customer;
import com.lea.mobile.entity.CustomerProduct;
import com.lea.mobile.entity.Product;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    @RequestMapping(value = "/search", method = RequestMethod.GET,
    produces = "text/xml; charset=utf-8")
    public String search(@RequestParam(value = "text", required = true) String text){
        //@ResponseBody + @Controller = @RestController
        List<Customer> customers = customerService.search(text);
        StringBuilder sb = new StringBuilder();

        for (Customer customer:customers){
            sb.append("<customer>")
                .append("<id>").append(customer.getId()).append("</id>")
                .append("<name>").append(customer.getId()).append("</name>")
            .append("</customer>");
        }

        return "<customers>" + sb.toString() + "</customers>";
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

        Customer customer = customerService.selectById(id);
        Product product = productService.selectById(serviceId);

        if(customer==null || product==null) {
            return "<error>1</error>";
        }

        CustomerProduct customerProduct = new CustomerProduct();
        customerProduct.setCustomer(customer);
        customerProduct.setProduct(product);
        customerProduct.setDateActivated(new Date());
        customerService.addProduct(customer,customerProduct);

        SimpleDateFormat dateFormat= new SimpleDateFormat("dd.MM.yyyy");
        String dateActivated = " ";
        String dateDeactivated = " ";

        if(customerProduct.getDateActivated()!=null){
            dateActivated = dateFormat.format(customerProduct.getDateActivated());
        }
        if(customerProduct.getDateDeactivated()!=null){
            dateDeactivated = dateFormat.format(customerProduct.getDateDeactivated());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<product>")
            .append("<name>").append(customerProduct.getName()).append("</name>")
            .append("<activated_date>").append(dateActivated).append("</activated_date>")
            .append("<deactivated_date>").append(dateDeactivated).append("</deactivated_date>")
            .append("<payment>").append(customerProduct.getPayment()).append("</payment>")
        .append("</product>");

        return sb.toString();
    }
}

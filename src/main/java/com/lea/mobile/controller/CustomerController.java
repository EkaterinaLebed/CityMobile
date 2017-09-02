package com.lea.mobile.controller;

import com.lea.mobile.entity.Customer;
import com.lea.mobile.entity.CustomerProduct;
import com.lea.mobile.entity.Product;
import com.lea.mobile.entity.User;
import com.lea.mobile.service.CustomerProductService;
import com.lea.mobile.service.CustomerService;
import com.lea.mobile.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/abonent")
public class CustomerController {
    private static final Logger logger = Logger.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerProductService customerProductService;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    ModelAndView showCreatePage(){
        ModelAndView model = new ModelAndView("/abonent/createAbonent");
        model.addObject("serviceList",productService.selectAll());
        return model;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    ModelAndView showFindPage(){
        return new ModelAndView("/abonent/findAbonent");
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    ModelAndView showInfoPage(@RequestParam int id){
        Customer customer = null;
        try {
            customer = customerService.selectById(id);
        } catch (Exception e) {
            logger.error("Customer not found, id=" + id+", "+e.toString());
        }

        ModelAndView model = new ModelAndView("/abonent/abonentInfo");
        model.addObject("customer",customer);
        model.addObject("serviceList",productService.selectAll());
        return model;
    }

    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    ModelAndView showCabinetPage(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            User user = (User)session.getAttribute("user");
            if(user!=null){
                ModelAndView model = new ModelAndView("/abonent/abonentCabinet");
                model.addObject("customer",customerService.selectById(user.getCustomerId()));
                model.addObject("serviceList",productService.selectAll());
                return model;
            }
        }

        return new ModelAndView();
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET,produces = "text/xml; charset=utf-8")
    public String search(@RequestParam(value = "text", required = true) String text){
        //@ResponseBody + @Controller = @RestController
        logger.debug("Invoked");

        List<Customer> customers = null;
        try {
            customers = customerService.search(text);
        } catch (Exception e) {
            logger.error("FAILED. '"+e.toString());
        }

        if(customers==null){
            logger.debug("Not found, request='"+text+"'");
            return "<customers></customers>";
        }

        StringBuilder sb = new StringBuilder();
        for (Customer customer:customers){
            sb.append("<customer>")
                .append("<id>").append(customer.getId()).append("</id>")
                .append("<name>").append(customer.getName()).append("</name>")
            .append("</customer>");
        }

        return "<customers>" + sb.toString() + "</customers>";
    }

    @ResponseBody
    @RequestMapping(value = "/create/do", method = RequestMethod.POST,produces = "text/xml; charset=utf-8")
    public String create(HttpServletRequest request){
        logger.debug("Invoked");

        Customer customer = new Customer();
        customer.setName(request.getParameter("name"));
        customer.setBillingАddress(request.getParameter("address"));
        customer.setActivationDate(new Date());
        customer.setActivated(true);

        try {
            customerService.create(customer);
        } catch (Exception e) {
            logger.error("Not successful: "+e.toString());
        }

        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder();
        sb.append("<customer>")
            .append("<id>").append(customer.getId()).append("</id>")
            .append("<name>").append(customer.getName()).append("</name>")
        .append("</customer>");
        return sb.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/add/service/do", method = RequestMethod.POST,produces = "text/xml; charset=utf-8")
    public String addService(HttpServletRequest request){
        logger.debug("Invoked");

        int id=0;
        try {
            id = Integer.parseInt(request.getParameter("abonentId"));
        } catch (NumberFormatException e) {
            logger.error("Request param 'abonentId' is not a Number");
            return null;
        }

        int serviceId=0;
        try {
            serviceId = Integer.parseInt(request.getParameter("serviceId"));
        } catch (NumberFormatException e) {
            logger.error("Request param 'serviceId' is not a Number");
            return null;
        }

        Customer customer = customerService.selectById(id);
        if(customer==null) {
            logger.error("Select NULL customer, id=" + id);
            return null;
        }

        Product product = productService.selectById(serviceId);
        if(product==null) {
            logger.error("Select NULL product, id=" + serviceId);
            return null;
        }

        CustomerProduct customerProduct = new CustomerProduct();
        customerProduct.setCustomer(customer);
        customerProduct.setProduct(product);
        customerProduct.setDateActivated(new Date());
        customerProduct.setActivated(true);

        try {
            customerProductService.create(customerProduct);
        } catch (Exception e) {
            logger.error("FAILED: " + e.toString());
            return null;
        }

        SimpleDateFormat dateFormat= new SimpleDateFormat("dd.MM.yyyy");

        String dateActivated = " ";
        if(customerProduct.getDateActivated()!=null){
            dateActivated = dateFormat.format(customerProduct.getDateActivated());
        }

        String dateDeactivated =" ";
        if(customerProduct.getDateDeactivated()!=null){
            dateDeactivated = dateFormat.format(customerProduct.getDateDeactivated());
        }

        @SuppressWarnings("StringBufferReplaceableByString")
        StringBuilder sb = new StringBuilder();
        sb.append("<products><product>")
            .append("<id>").append(customerProduct.getId()).append("</id>")
            .append("<name>").append(customerProduct.getName()).append("</name>")
            .append("<activated_date>").append(dateActivated).append("</activated_date>")
            .append("<deactivated_date>").append(dateDeactivated).append("</deactivated_date>")
            .append("<payment>").append(customerProduct.getPayment()).append("</payment>")
        .append("</product></products>");

        return sb.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/activate/service/do", method = RequestMethod.POST,produces = "text/xml; charset=utf-8")
    public ResponseEntity<Void> activateService(@RequestParam int id){
        logger.debug("Invoked");

        CustomerProduct customerProduct = customerProductService.selectById(id);
        if(customerProduct==null) {
            logger.error("Select NULL customer product, id=" + id);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        customerProduct.setActivated(true);
        customerProduct.setDateDeactivated(null);

        customerProductService.update(customerProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/deactivate/service/do", method = RequestMethod.POST,produces = "text/xml; charset=utf-8")
    public ResponseEntity<Void> deactivateService(@RequestParam int id){
        logger.debug("Invoked");

        CustomerProduct customerProduct = customerProductService.selectById(id);
        if(customerProduct==null) {
            logger.error("Select NULL customer product, id=" + id);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        customerProduct.setActivated(false);
        customerProduct.setDateDeactivated(new Date());
        customerProductService.update(customerProduct);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/activate", method = RequestMethod.GET)
    public ResponseEntity<Void> activate(@RequestParam("id") int id){
        Customer customer;
        try {
            customer = customerService.selectById(id);
        } catch (Exception e) {
            logger.error("Customer not found, id=" + id+", "+e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(customer!=null){
            customerService.activated(customer,true);
            logger.debug("Customer activated, id=" + id);
        }else {
            logger.error("Customer not found, id=" + id);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/deactivate", method = RequestMethod.GET)
    public ResponseEntity<Void> deactivate(@RequestParam("id") int id){
        Customer customer;
        try {
            customer = customerService.selectById(id);
        } catch (Exception e) {
            logger.error("Customer not found, id=" + id+", "+e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(customer!=null){
            customerService.activated(customer,false);
            logger.debug("Customer deactivated, id=" + id);
        }else {
            logger.error("Customer not found, id=" + id);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.lea.mobile.controller;

import com.lea.mobile.entity.Product;
import com.lea.mobile.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET,
    produces = "text/xml; charset=utf-8")
    public String search(@RequestParam(value = "text", required = true) String text){
        List<Product> products = productService.search(text);
        StringBuilder sb = new StringBuilder();

        for (Product product:products){
            sb.append("<product>")
                .append("<id>").append(product.getId()).append("</id>")
                .append("<name>").append(product.getName()).append("</name>")
                .append("<price>").append(product.getPayment()).append("</price>")
            .append("</product>");
        }

        return "<products>" + sb.toString() + "</products>";
    }
}

package com.lea.mobile.controller;

import com.lea.mobile.entity.User;
import com.lea.mobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping()
public class RegisterController {
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/register","/registration"}, method = RequestMethod.GET)
    public ModelAndView RegisterPage() {
        return new ModelAndView("user/register");
    }

    @RequestMapping(value = "/register/do",method = RequestMethod.POST)
    public String doRegistration(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        if (session.getAttribute("user") == null) {
            String login = request.getParameter("login");
            if (userService.selectByLogin(login)==null){
                User myUser = new User();
                myUser.setName(request.getParameter("name"));
                myUser.setUserLogin(request.getParameter("login"));
                myUser.setPassword(request.getParameter("pwd").getBytes());
                userService.create(myUser);
                session.setAttribute("user", myUser);
            }
        }

        return "redirect:" + request.getParameter("from");
    }
}

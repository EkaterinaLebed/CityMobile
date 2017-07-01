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
public class SecurityController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(){
        return new ModelAndView("user/login");
    }

    @RequestMapping(value = "/login/do", method = RequestMethod.POST)
    public String login(HttpServletRequest request){
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");

        User user = userService.selectByLogin(login);

        if (user!=null && new String(user.getPassword()).compareTo(pwd)==0){
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
        }
        else {
            HttpSession session = request.getSession();
            if(session!=null){
                session.setAttribute("user", user);
            }
        }

        return "redirect:" + request.getParameter("from");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:"+ request.getHeader("Referer");
    }
}

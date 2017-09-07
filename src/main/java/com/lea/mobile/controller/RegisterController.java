package com.lea.mobile.controller;

import com.lea.mobile.entity.User;
import com.lea.mobile.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@SuppressWarnings("unused")
public class RegisterController {
    private static final Logger logger = Logger.getLogger(RegisterController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/register","/registration"}, method = RequestMethod.GET)
    public ModelAndView RegisterPage() {
        return new ModelAndView("user/register");
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/register/do",method = RequestMethod.POST)
    public String doRegistration(HttpServletRequest request) {
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");

        if (userService.selectByLogin(login)==null){
            User myUser = new User();
            myUser.setName(login);

            try {
                userService.makePassword(myUser,pwd);
                userService.makeRole(myUser,"USER");
                userService.create(myUser);
            } catch (Exception e) {
                logger.error("ERROR: can't create user '"+myUser.getName()+"': "+e.toString());
            }
        }

        return "redirect:/";
    }
}

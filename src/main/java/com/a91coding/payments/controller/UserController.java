package com.a91coding.payments.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.a91coding.payments.model.User;
import com.a91coding.payments.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @RequestMapping("/showUser")
    public String toIndex(HttpServletRequest request,Model model){
        System.out.println("ddddddddddddddddddddd");
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        model.addAttribute("user", user);
        return "showUser";
    }
}
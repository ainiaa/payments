package com.a91coding.payments.controller;

import com.a91coding.payments.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, Model model, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        logger.info("login username => " + username);
        logger.info("login password => " + password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        String msg = null;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            logger.info("login UnknownAccountException => " + e.getMessage());
            e.printStackTrace();
            msg = e.getMessage();
        } catch (IncorrectCredentialsException e) {
            logger.info("login IncorrectCredentialsException => " + e.getMessage());
            e.printStackTrace();
            msg = "username and password not matched!";
        } catch (ExcessiveAttemptsException e) {
            logger.info("login ExcessiveAttemptsException => " + e.getMessage());
            msg = "wrong times too more, please retry after 10 min";
            e.printStackTrace();
        } catch (AuthenticationException e) {
            logger.info("login AuthenticationException => " + e.getMessage());
            e.printStackTrace();
            msg = e.getMessage();
        }
        if (msg == null) {
            String referer =  WebUtils.getSavedRequest(request).getRequestUrl();
            String path = request.getContextPath();
            logger.info("referer:" + referer );
            if (null == referer) {
                return "redirect:/admin/user/listUser";
            } else {
                if (referer.startsWith(path)) {
                    referer = referer.replace(path, "");
                }
                return "redirect:" + referer;
            }

        }
        model.addAttribute("msg", msg);
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg", "您已经退出登录");
        return "login";
    }

    @RequestMapping(value = "/unAuthorization")
    public String unAuthorization() {
        return "unAuthorization";
    }

}
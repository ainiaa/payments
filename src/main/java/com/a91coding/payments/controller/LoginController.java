package com.a91coding.payments.controller;

import com.a91coding.payments.constant.ConstantVar;
import com.a91coding.payments.exception.CaptchaMissingException;
import com.a91coding.payments.exception.CaptchaNotMatchedException;
import com.a91coding.payments.model.User;
import com.a91coding.payments.vendor.captcha.Captcha;
import com.a91coding.payments.vendor.captcha.GifCaptcha;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController extends BaseBackendController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {
            boolean jcaptchaEbabled = (boolean)request.getAttribute("jcaptchaEbabled");
            String jcaptchaParam = (String)request.getAttribute("jcaptchaParam");
            model.addAttribute("jcaptchaEbabled", jcaptchaEbabled);
            model.addAttribute("jcaptchaParam", jcaptchaParam);
            return "login";
        }
        Object principal = subject.getPrincipal();
        logger.info("login getPrincipal:" + principal);
        return "redirect:/admin/user/list";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request) {
        String failureKeyAttribute = (String)request.getAttribute("failureKeyAttribute");
        Object failureMsgObj = request.getAttribute(failureKeyAttribute);
        String msg = null;
        if (null != failureMsgObj) {
            String failureMsg = failureMsgObj.toString();
            if ("jCaptcha.missing".equals(failureMsg)) {
                msg = "Captcha missing";
            } else if ("jCaptcha.error".equals(failureMsg)) {
                msg = "Captcha not matched";
            } else {
                msg = failureMsg;
            }
        } else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            logger.info("login username => " + username);
            logger.info("login password => " + password);
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            boolean rememberMe = Boolean.valueOf(request.getParameter("rememberMe"));
            token.setRememberMe(rememberMe);
            Subject subject = SecurityUtils.getSubject();

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
            } catch (CaptchaMissingException e) {
                logger.info("login CaptchaMissingException => " + e.getMessage());
                msg = "captcha missing!";
                e.printStackTrace();
            } catch (CaptchaNotMatchedException e) {
                logger.info("login CaptchaNotMatchedException => " + e.getMessage());
                msg = "captcha not matched!";
                e.printStackTrace();
            }catch (AuthenticationException e) {
                logger.info("login AuthenticationException => " + e.getMessage());
                e.printStackTrace();
                msg = e.getMessage();
            }
//        登录成功之后 会在filterChainDefinitions配置的对应的filter里面自动跳转掉 如果登录成功之后需要有特殊的处理可以重新配置/login=anon 然后用下面的代码，或者重写
//        Filter的issueSuccessRedirect方法
//        if (msg == null) {
//            String referer =  WebUtils.getSavedRequest(request).getRequestUrl();
//            String path = request.getContextPath();
//            logger.info("referer:" + referer );
//            if (null == referer) {
//                return "redirect:/admin/user/listUser";
//            } else {
//                if (referer.startsWith(path)) {
//                    referer = referer.replace(path, "3");
//                }
//                return "redirect:" + referer;
//            }
//        }
        }

        model.addAttribute("msg", msg);
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg", "您已经退出登录");
        return display(model, "login");
    }

    @RequestMapping(value = "/unAuthorization")
    public String unAuthorization(Model model) {
        return display(model, "unAuthorization");
    }

    @ResponseBody
    @RequestMapping(value = "/getGifCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            //存入Shiro会话session
            HttpSession session = request.getSession(true);
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146, 42, 4);
            //输出
            ServletOutputStream out = response.getOutputStream();
            captcha.out(out);
            out.flush();

            //存入Session
            session.setAttribute(ConstantVar.VERYFY_CODE_KEY, captcha.text().toLowerCase());
            logger.debug("captcha:" + captcha.text().toLowerCase());
        } catch (Exception e) {
            logger.error("获取验证码异常：" +  e.getMessage());
        }
    }

}
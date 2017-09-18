package com.a91coding.payments.controller;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseBackendController {

    public String display(Model model, String ftl, String layout) {
        if (null == ftl) {
            ftl = "default.ftl";
        } else if (!ftl.contains(".")) {
            ftl = ftl + ".ftl";
        }
        model.addAttribute("main_content", ftl);
        return layout;
    }

    protected Model processMsg(Model model,HttpServletRequest request) {
        Object msg = request.getSession().getAttribute("msg");
        if (msg != null) {
            String msgType ="success";
            String msgStyle = "alerts";
            if (msg instanceof String) {
                Map newMsg = new HashMap();
                newMsg.put("title", "Alert");
                newMsg.put("msg", msg);
                model.addAttribute("msg", newMsg);
            } else if (msg instanceof HashMap){
                Map newMsg = (HashMap) msg;
                model.addAttribute("msg", msg);
                if (newMsg.containsKey("msgType")) {
                    msgType = (String)newMsg.get("msgType");
                }
                if (newMsg.containsKey("msgStyle")) {
                    msgStyle = (String)newMsg.get("msgStyle");
                }
            }

            model.addAttribute("showMsg", true);
            model.addAttribute("extJsFtl","common/listExtJs.ftl");
            model.addAttribute("msg_box", msgStyle + "/"+msgType+".ftl");
            request.getSession().removeAttribute("msg");
        }
        return model;
    }

    public String display(Model model, String ftl) {
        return display(model, ftl, "layout");
    }
}

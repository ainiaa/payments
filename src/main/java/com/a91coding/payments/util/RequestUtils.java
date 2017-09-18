package com.a91coding.payments.util;

import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;

public class RequestUtils {
    public static String getPathWithinApplication(ServletRequest request) {
        return WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
    }
}

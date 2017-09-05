package com.a91coding.payments.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginAuthenticationFilter extends FormAuthenticationFilter {
    private String errorUrl;
    private static final Logger logger = LoggerFactory.getLogger(LoginAuthenticationFilter.class);
}

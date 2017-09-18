package com.a91coding.payments.filter;

import com.a91coding.payments.constant.ConstantVar;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JCaptchaValidateFilter extends FormAuthenticationFilter {

    /**
     * 是否开启验证码验证   默认true
     */
    private boolean jcaptchaEbabled = true;

    /**
     * 前台提交的验证码参数名
     */
    private String jcaptchaParam = "jcaptchaCode";

    /**
     * 验证失败后存储到的属性名
     */
    private String failureKeyAttribute = "shiroLoginFailure";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o){
        //1、设置验证码是否开启属性，页面可以根据该属性来决定是否显示验证码
        request.setAttribute("jcaptchaEbabled", jcaptchaEbabled);
        request.setAttribute("jcaptchaParam", jcaptchaParam);
        request.setAttribute("failureKeyAttribute", failureKeyAttribute);

        HttpServletRequest httpRequest = WebUtils.toHttp(request);

        //2、判断验证码是否禁用 或不是表单提交（允许访问）
        if (jcaptchaEbabled == false || !"post".equalsIgnoreCase(httpRequest.getMethod())) {
            return true;
        }

        //表单提交，校验验证码的正确性
        String storedCode = getSubject(request, response).getSession().getAttribute(ConstantVar.VERYFY_CODE_KEY).toString();
        String currentCode = httpRequest.getParameter(jcaptchaParam);
        if (null == currentCode || currentCode.isEmpty()) {
            request.setAttribute(failureKeyAttribute, "jCaptcha.missing");
            return false;
        }

        if (!StringUtils.equalsIgnoreCase(storedCode, currentCode)) {
            request.setAttribute(failureKeyAttribute, "jCaptcha.error");
            return false;
        }

        return true;
    }
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse servletResponse) throws Exception {
        return true;
    }
    public String getFailureKeyAttribute() {
        return failureKeyAttribute;
    }
    public void setFailureKeyAttribute(String failureKeyAttribute) {
        this.failureKeyAttribute = failureKeyAttribute;
    }
    public String getJcaptchaParam() {
        return jcaptchaParam;
    }
    public void setJcaptchaParam(String jcaptchaParam) {
        this.jcaptchaParam = jcaptchaParam;
    }
    public boolean isJcaptchaEbabled() {
        return jcaptchaEbabled;
    }
    public void setJcaptchaEbabled(boolean jcaptchaEbabled) {
        this.jcaptchaEbabled = jcaptchaEbabled;
    }
}
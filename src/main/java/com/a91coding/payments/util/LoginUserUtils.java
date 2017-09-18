package com.a91coding.payments.util;

import com.a91coding.payments.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class LoginUserUtils {

    public static Integer getUserId() {
        User user = getUser();
        if (user != null) {
            return user.getId();
        }
        return 0;
    }
    public static User getUser() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        return user;
    }
}

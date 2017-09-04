package com.a91coding.payments.realm;

import com.a91coding.payments.model.Resource;
import com.a91coding.payments.model.User;
import com.a91coding.payments.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.debug("--- MyRealm doGetAuthorizationInfo ---");

        // 获得经过认证的主体信息
        User user = getUser(principalCollection);
        Integer userId = user.getId();
        List<Resource> resourceList = userService.listAllResources(userId);
        List<String> roleSnList = userService.listRoleSnByUser(userId);

        List<String> resStrList = new ArrayList<>();
        for (Resource resource : resourceList) {
            resStrList.add(resource.getUrl());
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(new HashSet<>(roleSnList));
        info.setStringPermissions(new HashSet<>(resStrList));

        // 以上完成了动态地对用户授权
        logger.debug("role => " + roleSnList);
        logger.debug("permission => " + resStrList);

        return info;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("--- MyRealm doGetAuthenticationInfo ---");

        // 这里应该使用 load 方法，比对用户名的密码的环节应该交给 Shiro 这个框架去完成
        User user = getUser(authenticationToken);

        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;
        String password = String.valueOf(upToken.getPassword());
        logger.info("--- MyRealm doGetAuthenticationInfo password:" + password);
        logger.info("--- MyRealm doGetAuthenticationInfo else user:" + user.toString());
        // 第 1 个参数可以传一个实体对象，然后在认证的环节可以取出 --PS todo 这个很重要 它就是PrincipalCollection里面存储的内容
        // 第 2 个参数应该传递在数据库中“正确”的数据，然后和 token 中的数据进行匹配
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());//
        // 设置盐值
        info.setCredentialsSalt(ByteSource.Util.bytes(user.getCredentialsSalt()));
        this.setSession("currentUser", user);
        return info;
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            logger.info("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }

    public User getUser(AuthenticationToken token) {
        String username = token.getPrincipal().toString();

        // 这里应该使用 load 方法，比对用户名的密码的环节应该交给 Shiro 这个框架去完成
        User user = userService.selectByUsername(username);
        if (null == user) {
            logger.info("--- MyRealm doGetAuthenticationInfo user is null");
            throw new UnknownAccountException("username:" + username + " not exists!");
        }
        return user;
    }

    public User getUser(PrincipalCollection principals) {
        // 获得经过认证的主体信息
        User user;
        try {
            user = (User) principals.getPrimaryPrincipal();
        } catch (ClassCastException e) {
            String username = (String) principals.getPrimaryPrincipal();
            user = userService.selectByUsername(username);
            if (null == user) {
                logger.debug("--- MyRealm doGetAuthorizationInfo " + username + " not exists!");
                throw new UnknownAccountException(username + " not exists!");
            }
        }
        return user;

    }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        Cache c = getAuthenticationCache();
        logger.info("清除【认证】缓存之前");
        for (Object o : c.keys()) {
            logger.info(o + " , " + c.get(o));
        }
        super.clearCachedAuthenticationInfo(principals);
        logger.info("调用父类清除【认证】缓存之后");
        for (Object o : c.keys()) {
            logger.info(o + " , " + c.get(o));
        }

        // 添加下面的代码清空【认证】的缓存
        User user = getUser(principals);
        SimplePrincipalCollection spc = new SimplePrincipalCollection(user.getUsername(), getName());
        super.clearCachedAuthenticationInfo(spc);
        logger.info("添加了代码清除【认证】缓存之后");
        int cacheSize = c.keys().size();
        logger.info("【认证】缓存的大小:" + c.keys().size());
        if (cacheSize == 0) {
            logger.info("说明【认证】缓存被清空了。");
        }
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        logger.info("清除【授权】缓存之前");
        Cache c = getAuthorizationCache();
        for (Object o : c.keys()) {
            logger.info(o + " , " + c.get(o));
        }
        super.clearCachedAuthorizationInfo(principals);
        logger.info("清除【授权】缓存之后");
        int cacheSize = c.keys().size();
        logger.info("【授权】缓存的大小:" + cacheSize);

        for (Object o : c.keys()) {
            logger.info(o + " , " + c.get(o));
        }
        if (cacheSize == 0) {
            logger.info("说明【授权】缓存被清空了。");
        }

    }
}
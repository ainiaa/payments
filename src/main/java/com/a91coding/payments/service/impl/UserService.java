package com.a91coding.payments.service.impl;

import com.a91coding.payments.dao.IRoleDao;
import com.a91coding.payments.dao.IUserDao;
import com.a91coding.payments.kit.ShiroKit;
import com.a91coding.payments.model.Resource;
import com.a91coding.payments.model.Role;
import com.a91coding.payments.model.User;
import com.a91coding.payments.service.IUserService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserService implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;

    /**
     * 返回新插入用户数据的主键
     * @param user
     * @return
     */
    @Override
    public Integer insert(User user) {
        // 使用用户名作为盐值，MD5 算法加密
        user.setPassword(ShiroKit.md5(user.getPassword(),user.getUsername()));
        userDao.insert(user);
        Integer userId = user.getId();
        return userId;
    }

    /**
     * 为单个用户设置多个角色
     * @param user
     * @param rids
     */
    @Override
    public void insert(User user, List<Integer> rids) {
        Integer userId = this.insert(user);
        roleDao.addUserRoles(userId,rids);
    }

    /**
     * 根据 user_id 删除用户数据
     * @param id
     */
    @Override
    public void deleteByPrimaryKey(int id) {
        userDao.deleteByPrimaryKey(id);
    }

    /**
     * 更新用户数据
     * 1、更新用户基本信息
     * 2、更新用户所属角色
     *    （1）先删除所有的角色
     *    （2）再添加绑定的角色
     * @param user
     * @param rids
     */
    @Override
    @Transactional
    public User updateByPrimaryKey(User user, List<Integer> rids) {
        Integer userId = user.getId();
        roleDao.deleteUserRoles(userId);
        roleDao.addUserRoles(userId,rids);
        this.updateByPrimaryKey(user);
        return user;
    }

    /**
     * 更新单个用户信息
     * @param user
     * @return
     */
    @Override
    public User updateByPrimaryKey(User user) {
        String password = user.getPassword();
        if(password!=null){
            user.setPassword(ShiroKit.md5(user.getPassword(),user.getUsername()));
        }
        userDao.updateByPrimaryKey(user);
        return user;
    }

    /**
     * 根据主键 id 加载用户对象
     * @param id
     * @return
     */
    @Override
    public User selectByPrimaryKey(int id) {
        return userDao.selectByPrimaryKey(id);
    }

    /**
     * 根据用户名加载用户对象（用于登录使用）
     * @param username
     * @return
     */
    @Override
    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    /**
     * 登录逻辑
     * 1、先根据用户名查询用户对象
     * 2、如果有用户对象，则继续匹配密码
     * 如果没有用户对象，则抛出异常
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        User user = userDao.selectByUsername(username);
        if(user == null){
            // 抛出对象不存在异常
            // TODO: 2016/9/18  应该使用 Shiro 框架的登录方式，暂时先这样
            logger.debug("用户名不存在");
            throw new UnknownAccountException("用户名和密码不匹配");
        }else if(false){
            // !user.getPassword().equals(password)
            logger.debug("密码错误");
            // 抛出密码不匹配异常
            throw new IncorrectCredentialsException("用户名和密码不匹配");
        }else if(user.getStatus() == 0){
            throw new LockedAccountException("用户已经被锁定，请联系管理员启动");
        }
        return user;
    }

    /**
     * 查询所有的用户对象列表
     * @return
     */
    @Override
    public List<User> listUser() {
        return userDao.listUser();
    }

    /**
     * 根据角色 id 查询是这个角色的所有用户
     * @param id
     * @return
     */
    @Override
    public List<User> listByRole(int id) {
        return userDao.listByRole(id);
    }

    /**
     * 查询指定用户 id 所拥有的权限
     * @param uid
     * @return
     */
    @Override
    public List<Resource> listAllResources(int uid) {
        return userDao.listAllResources(uid);
    }

    /**
     * 查询指定用户所指定的角色字符串列表
     * @param uid
     * @return
     */
    @Override
    public List<String> listRoleSnByUser(int uid) {
        return userDao.listRoleSnByUser(uid);
    }

    /**
     * 查询指定用户所绑定的角色列表
     * @param uid
     * @return
     */
    @Override
    public List<Role> listUserRole(int uid) {
        return userDao.listUserRole(uid);
    }

    @Override
    @Transactional
    public void deleteUserAndRole(List<Integer> ids) {
        if(ids.contains(1)){
            throw new RuntimeException("不能删除管理员用户");
        }
        // 删除用户列表
        userDao.batchDelete(ids);
        // 依次删除这些用户所绑定的角色
        for(Integer userId:ids){
            roleDao.deleteUserRoles(userId);
        }
    }

}
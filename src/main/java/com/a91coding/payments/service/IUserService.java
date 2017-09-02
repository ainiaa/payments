package com.a91coding.payments.service;

import com.a91coding.payments.model.Resource;
import com.a91coding.payments.model.Role;
import com.a91coding.payments.model.User;

import java.util.List;

public interface IUserService {

    /**
     * 添加单个用户
     * @param user
     */
    Integer insert(User user);

    /**
     * 批量添加用户角色关联表数据
     * @param user
     * @param rids
     */
    void insert(User user,List<Integer> rids);

    /**
     * 根据 user_id 删除用户数据
     * @param id
     */
    void deleteByPrimaryKey(int id);

    /**
     * 更新用户数据
     * 1、更新用户基本信息
     * 2、更新用户所属角色
     *    （1）先删除所有的角色
     *    （2）再添加绑定的角色
     * @param user
     * @param rids
     */
    User updateByPrimaryKey(User user,List<Integer> rids);

    /**
     * 更新单个用户信息
     * @param user
     * @return
     */
    User updateByPrimaryKey(User user);

    /**
     * 根据主键 id 加载用户对象
     * @param id
     * @return
     */
    User selectByPrimaryKey(int id);

    /**
     * 根据用户名加载用户对象（用于登录使用）
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 登录逻辑
     * 1、先根据用户名查询用户对象
     * 2、如果有用户对象，则继续匹配密码
     * 如果没有用户对象，则抛出异常
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);

    /**
     * 查询所有的用户对象列表
     * @return
     */
    List<User> listUser();

    /**
     * 根据角色 id 查询是这个角色的所有用户
     * @param id
     * @return
     */
    List<User> listByRole(int id);

    /**
     * 查询指定用户 id 所拥有的权限
     * @param uid
     * @return
     */
    List<Resource> listAllResources(int uid);

    /**
     * 查询指定用户所指定的角色字符串列表
     * @param uid
     * @return
     */
    List<String> listRoleSnByUser(int uid);

    /**
     * 查询指定用户所绑定的角色列表
     * @param uid
     * @return
     */
    List<Role> listUserRole(int uid);

    /**
     * 删除用户和用户绑定的角色信息
     * @param ids
     */
    void deleteUserAndRole(List<Integer> ids);

}
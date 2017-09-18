package com.a91coding.payments.service.impl;

import com.a91coding.payments.dao.IRoleDao;
import com.a91coding.payments.model.Resource;
import com.a91coding.payments.model.Role;
import com.a91coding.payments.model.RoleResource;
import com.a91coding.payments.model.UserRole;
import com.a91coding.payments.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
public class RoleService implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public Integer insert(Role role) {
        return roleDao.insert(role);
    }

    @Override
    public Integer deleteByPrimaryKey(int id) {
        return roleDao.deleteByPrimaryKey(id);
    }

    @Transactional
    @Override
    public Integer deleteRoleAndResource(List<Integer> ids) {
        roleDao.batchDelete(ids);
        return roleDao.batchDeleteRoleResource(ids);
    }

    @Override
    public Role selectByPrimaryKey(int id) {
        return roleDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> list() {
        return roleDao.listRole();
    }

    @Override
    public Integer updateByPrimaryKey(Role role) {
        return roleDao.updateByPrimaryKey(role);
    }

    @Override
    public List<Role> listRole() {
        return roleDao.listRole();
    }

    @Override
    public UserRole selectUserRole(int uid, int roleId) {
        return roleDao.selectUserRole(uid, roleId);
    }

    @Override
    public Integer addUserRole(int uid, int roleId) {
        return roleDao.addUserRole(uid, roleId);
    }

    @Override
    public Integer deleteUserRole(int uid, int roleId) {
        return roleDao.deleteUserRole(uid, roleId);
    }

    @Override
    public Integer deleteUserRoles(int uid) {
        return roleDao.deleteUserRoles(uid);
    }

    @Override
    public List<Resource> listRoleResource(int roleId) {
        return roleDao.listRoleResource(roleId);
    }

    @Override
    public Integer addRoleResource(int roleId, int resId) {
        return roleDao.addRoleResource(roleId, resId);
    }

    @Override
    public Integer deleteRoleResource(int roleId, int resId) {
        return roleDao.deleteRoleResource(roleId, resId);
    }

    @Override
    public RoleResource selectResourceRole(int roleId, int resId) {
        return roleDao.selectResourceRole(roleId, resId);
    }

    @Override
    public Integer deleteRoleAndUser(List<Integer> ids) {
        return roleDao.deleteRoleAndUser(ids);
    }
}

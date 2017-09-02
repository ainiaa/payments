package com.a91coding.payments.dao;

import com.a91coding.payments.model.Resource;
import com.a91coding.payments.model.Role;
import com.a91coding.payments.model.RoleResource;
import com.a91coding.payments.model.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Role record);

    Integer batchDelete(@Param("ids") List<Integer> ids);

    List<Role> listRole();

    UserRole selectUserRole(@Param("userId") int userId, @Param("roleId") int roleId);

    /**
     * 为单个用户设置单个角色
     * @param userId
     * @param roleId
     * @return
     */
    Integer addUserRole(@Param("userId") int userId,@Param("roleId") int roleId);

    /**
     * 为单个用户设置多个角色
     * @param userId
     * @param roleIds
     * @return
     */
    Integer addUserRoles(@Param("userId") int userId,@Param("roleIds")List<Integer> roleIds);

    Integer deleteUserRole(@Param("userId") int userId,@Param("roleId") int roleId);

    /**
     * 删除某个用户的所有角色
     * @param uid
     */
    Integer deleteUserRoles(int uid);

    Integer batchDeleteRoleResource(@Param("roleIds") List<Integer> roleIds);

    /**
     * 根据角色id获取可以访问的所有资源
     * @param roleId
     * @return
     */
    List<Resource> listRoleResource(int roleId);

    Integer addRoleResource(@Param("roleId") int roleId,@Param("resourceId") int resourceId);

    Integer deleteRoleResource(@Param("roleId")int roleId,@Param("resourceId")int resorceId);

    RoleResource selectResourceRole(@Param("roleId")int roleId, @Param("resourceId") int resorceId);

    Integer deleteRoleAndUser(@Param("ids") List<Integer> ids);
}
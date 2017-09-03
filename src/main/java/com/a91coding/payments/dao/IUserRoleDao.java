package com.a91coding.payments.dao;

import com.a91coding.payments.model.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRoleDao {
    int deleteByPrimaryKey(Byte id);

    int insert(UserRole record);

    UserRole selectByPrimaryKey(Byte id);

    int updateByPrimaryKey(UserRole record);
}
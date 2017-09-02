package com.a91coding.payments.dao;

import com.a91coding.payments.model.RoleResource;

public interface IRoleResourceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleResource record);

    RoleResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(RoleResource record);
}
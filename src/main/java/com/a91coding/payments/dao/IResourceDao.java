package com.a91coding.payments.dao;

import com.a91coding.payments.model.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IResourceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Resource record);

    List<Resource> listResource();
}
package com.a91coding.payments.service;


import com.a91coding.payments.model.Resource;

import java.util.List;

/**
 * Created by Liwei on 2016/9/19.
 */
public interface IResourceService {
    Integer insert(Resource res);

    Integer updateByPrimaryKey(Resource res);

    Integer deleteByPrimaryKey(int id);

    Resource selectByPrimaryKey(int id);

    List<Resource> listResource();
}

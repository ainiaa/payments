package com.a91coding.payments.service.impl;

import com.a91coding.payments.dao.IResourceDao;
import com.a91coding.payments.model.Resource;
import com.a91coding.payments.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Liwei on 2016/9/19.
 */
@Service("resourceService")
public class ResourceService implements IResourceService {
    @Autowired
    private IResourceDao resourceDao;

    @Override
    public Integer insert(Resource res) {
        return resourceDao.insert(res);
    }

    @Override
    public Integer updateByPrimaryKey(Resource res) {
        return resourceDao.updateByPrimaryKey(res);
    }

    @Override
    public Integer deleteByPrimaryKey(int id) {
        return resourceDao.deleteByPrimaryKey(id);
    }

    @Override
    public Resource selectByPrimaryKey(int id) {
        return resourceDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Resource> listResource() {
        return resourceDao.listResource();
    }
}

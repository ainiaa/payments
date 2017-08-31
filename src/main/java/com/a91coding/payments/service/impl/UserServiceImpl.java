package com.a91coding.payments.service.impl;

import com.a91coding.payments.dao.IUserDao;
import com.a91coding.payments.model.User;
import com.a91coding.payments.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDao userDao;

    public User getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }
    public List<User> findAllUser() {
        return userDao.selectAll();
    }
}

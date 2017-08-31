package com.a91coding.payments.service;

import com.a91coding.payments.model.User;

import java.util.List;

public interface IUserService {
    public User getUserById(int userId);

    public List<User> findAllUser();
}

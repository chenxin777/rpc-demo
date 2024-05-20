package com.chenxin.example.common.service;

import com.chenxin.example.common.model.User;

/**
 * @author fangchenxin
 * @description 用户服务
 * @date
 * @modify
 */
public interface UserService {

    /**
     * @description: 获取用户
     * @param user
     * @return: User
     * @author: fangchenxin
     * @date: 2024/3/21 00:26
     * @modify:
     */
    User getUser(User user);

    /**
     * @description 获取数字
     * @author fangchenxin
     * @date 2024/3/29 09:44
     * @return short
     */
    default short getNumber() {
        return 1;
    }

    default String getName() {
        return "fcx";
    }
}

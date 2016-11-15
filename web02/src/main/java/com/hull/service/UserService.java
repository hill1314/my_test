package com.hull.service;

import com.hull.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
public interface UserService {
    int deleteByPrimaryKey(String userId);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAll(User user);
}

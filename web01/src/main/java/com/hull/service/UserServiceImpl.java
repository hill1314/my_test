package com.hull.service;

import com.hull.dao.UserMapper;
import com.hull.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/11/11.
 */

@Service("userService")
public class UserServiceImpl implements UserService{

//    @Resource
//    UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(String userId) {
//        return userMapper.deleteByPrimaryKey(userId);
        return 0;
    }

    @Override
    public int insert(User record) {
//        return userMapper.insert(record);
        return 0;
    }

    @Override
    public int insertSelective(User record) {
//        return userMapper.insertSelective(record);
        return 0;
    }

    @Override
    public User selectByPrimaryKey(String userId) {
//        return userMapper.selectByPrimaryKey(userId);
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
//        return userMapper.updateByPrimaryKeySelective(record);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) {
//        return userMapper.updateByPrimaryKey(record);
        return 0;
    }
}

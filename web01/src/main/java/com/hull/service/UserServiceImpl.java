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

    @Resource
    UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(String userId) {
        try {
            return userMapper.deleteByPrimaryKey(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insert(User record) {
        try {
            return userMapper.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insertSelective(User record) {
        try {
            return userMapper.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User selectByPrimaryKey(String userId) {
        try {
            return userMapper.selectByPrimaryKey(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        try {
            return userMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        try {
            return userMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

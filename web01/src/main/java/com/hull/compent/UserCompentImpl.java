package com.hull.compent;

import com.hull.compent.UserCompent;
import com.hull.dao.UserMapper;
import com.hull.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/11/13.
 */
@Component("userCompent")
public class UserCompentImpl implements UserCompent{

    @Resource
    UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(String userId) throws Exception {
        return 0;
    }

    @Override
    public int insert(User record) throws Exception {
        return 0;
    }

    @Override
    public int insertSelective(User record) throws Exception {
        return 0;
    }

    @Override
    public User selectByPrimaryKey(String userId) throws Exception {
        User user = new User();
        user.setUserId("001");
        user.setUserName("wwww");
        userMapper.selectByPrimaryKey("001");
        return user;
    }

    @Override
    public int updateByPrimaryKeySelective(User record) throws Exception {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) throws Exception {
        return 0;
    }
}

package com.hull.compent;

import com.hull.entity.User;

/**
 * Created by Administrator on 2016/11/13.
 */

public interface UserCompent {

    int deleteByPrimaryKey(String userId) throws Exception;

    int insert(User record) throws Exception;

    int insertSelective(User record) throws Exception;

    User selectByPrimaryKey(String userId) throws Exception;

    int updateByPrimaryKeySelective(User record) throws Exception;

    int updateByPrimaryKey(User record) throws Exception;
}

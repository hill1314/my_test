package com.hull.dao;

import com.hull.entity.MasterOrder;

//@
public interface MasterOrderMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(MasterOrder record);

    int insertSelective(MasterOrder record);

    MasterOrder selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(MasterOrder record);

    int updateByPrimaryKey(MasterOrder record);
}
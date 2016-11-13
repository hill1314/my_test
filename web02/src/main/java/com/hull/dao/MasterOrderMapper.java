package com.hull.dao;

import com.hull.entity.MasterOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterOrderMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(MasterOrder record);

    int insertSelective(MasterOrder record);

    MasterOrder selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(MasterOrder record);

    int updateByPrimaryKey(MasterOrder record);
}
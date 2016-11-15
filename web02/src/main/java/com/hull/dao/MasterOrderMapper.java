package com.hull.dao;

import com.hull.entity.MasterOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterOrderMapper {
    int deleteByPrimaryKey(String orderNo) throws Exception;

    int insert(MasterOrder record) throws Exception;

    int insertSelective(MasterOrder record) throws Exception;

    MasterOrder selectByPrimaryKey(String orderNo) throws Exception;

    int updateByPrimaryKeySelective(MasterOrder record) throws Exception;

    int updateByPrimaryKey(MasterOrder record) throws Exception;
}
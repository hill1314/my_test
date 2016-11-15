package com.hull.dao;

import com.hull.entity.PayInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface PayInfoMapper {
    int deleteByPrimaryKey(String payId) throws Exception;

    int insert(PayInfo record) throws Exception;

    int insertSelective(PayInfo record) throws Exception;

    PayInfo selectByPrimaryKey(String payId) throws Exception;

    int updateByPrimaryKeySelective(PayInfo record) throws Exception;

    int updateByPrimaryKey(PayInfo record) throws Exception;
}
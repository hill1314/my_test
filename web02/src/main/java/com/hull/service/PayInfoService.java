package com.hull.service;

import com.hull.entity.PayInfo;

/**
 * Created by Administrator on 2016/11/13.
 */
public interface PayInfoService {
    int deleteByPrimaryKey(String payId);

    int insert(PayInfo record);

    int insertSelective(PayInfo record);

    PayInfo selectByPrimaryKey(String payId);

    int updateByPrimaryKeySelective(PayInfo record);

    int updateByPrimaryKey(PayInfo record);
}

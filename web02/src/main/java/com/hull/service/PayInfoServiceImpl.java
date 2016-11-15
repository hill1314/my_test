package com.hull.service;

import com.hull.entity.PayInfo;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/11/15.
 */
@Service
public class PayInfoServiceImpl implements PayInfoService{
    @Override
    public int deleteByPrimaryKey(String payId) {
        return 0;
    }

    @Override
    public int insert(PayInfo record) {
        return 0;
    }

    @Override
    public int insertSelective(PayInfo record) {
        return 0;
    }

    @Override
    public PayInfo selectByPrimaryKey(String payId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(PayInfo record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PayInfo record) {
        return 0;
    }
}

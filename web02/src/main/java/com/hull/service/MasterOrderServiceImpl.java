package com.hull.service;

import com.hull.dao.MasterOrderMapper;
import com.hull.entity.MasterOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/11/15.
 */
@Service
public class MasterOrderServiceImpl implements MasterOrderService{
    @Resource
    MasterOrderMapper masterOrderMapper;

    @Override
    public int deleteByPrimaryKey(String orderNo) {
        try {
            return masterOrderMapper.deleteByPrimaryKey(orderNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public int insertSelective(MasterOrder record) {
        try {
            return masterOrderMapper.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public MasterOrder selectByPrimaryKey(String orderNo) {
        try {
            return masterOrderMapper.selectByPrimaryKey(orderNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(MasterOrder record) {
        try {
            return masterOrderMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateByPrimaryKey(MasterOrder record) {
        try {
            return masterOrderMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

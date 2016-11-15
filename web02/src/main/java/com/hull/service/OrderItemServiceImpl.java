package com.hull.service;

import com.hull.entity.OrderItem;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/11/15.
 */
@Service
public class OrderItemServiceImpl implements OrderItemService{
    @Override
    public int deleteByPrimaryKey(String itemId) {
        return 0;
    }

    @Override
    public int insert(OrderItem record) {
        return 0;
    }

    @Override
    public int insertSelective(OrderItem record) {
        return 0;
    }

    @Override
    public OrderItem selectByPrimaryKey(String itemId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(OrderItem record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(OrderItem record) {
        return 0;
    }
}

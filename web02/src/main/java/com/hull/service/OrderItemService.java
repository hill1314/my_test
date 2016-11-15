package com.hull.service;

import com.hull.entity.OrderItem;

/**
 * Created by Administrator on 2016/11/13.
 */
public interface OrderItemService {
    int deleteByPrimaryKey(String itemId);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(String itemId);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}

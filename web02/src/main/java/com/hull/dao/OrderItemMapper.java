package com.hull.dao;

import com.hull.entity.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemMapper {
    int deleteByPrimaryKey(String itemId) throws Exception;

    int insert(OrderItem record) throws Exception;

    int insertSelective(OrderItem record) throws Exception;

    OrderItem selectByPrimaryKey(String itemId) throws Exception;

    int updateByPrimaryKeySelective(OrderItem record) throws Exception;

    int updateByPrimaryKey(OrderItem record) throws Exception;
}
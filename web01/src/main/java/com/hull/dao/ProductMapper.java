package com.hull.dao;

import com.hull.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}
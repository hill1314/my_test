package com.hull.dao;

import com.hull.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(String prodId) throws Exception;

    int insert(Product record) throws Exception;

    int insertSelective(Product record) throws Exception;

    Product selectByPrimaryKey(String prodId) throws Exception;

    int updateByPrimaryKeySelective(Product record) throws Exception;

    int updateByPrimaryKey(Product record) throws Exception;
}
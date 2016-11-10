package com.hull.dao;

import com.hull.entity.ProductType;

public interface ProductTypeMapper {
    int deleteByPrimaryKey(String prodTypeId);

    int insert(ProductType record);

    int insertSelective(ProductType record);

    ProductType selectByPrimaryKey(String prodTypeId);

    int updateByPrimaryKeySelective(ProductType record);

    int updateByPrimaryKey(ProductType record);
}
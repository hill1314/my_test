package com.hull.dao;

import com.hull.entity.ProductType;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeMapper {
    int deleteByPrimaryKey(String prodTypeId) throws Exception;

    int insert(ProductType record) throws Exception;

    int insertSelective(ProductType record) throws Exception;

    ProductType selectByPrimaryKey(String prodTypeId) throws Exception;

    int updateByPrimaryKeySelective(ProductType record) throws Exception;

    int updateByPrimaryKey(ProductType record) throws Exception;
}
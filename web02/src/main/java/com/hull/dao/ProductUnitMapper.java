package com.hull.dao;

import com.hull.entity.ProductUnit;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductUnitMapper {
    int deleteByPrimaryKey(String unitId) throws Exception;

    int insert(ProductUnit record) throws Exception;

    int insertSelective(ProductUnit record) throws Exception;

    ProductUnit selectByPrimaryKey(String unitId) throws Exception;

    int updateByPrimaryKeySelective(ProductUnit record) throws Exception;

    int updateByPrimaryKey(ProductUnit record) throws Exception;
}
package com.hull.dao;

import com.hull.entity.ProductUnit;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductUnitMapper {
    int deleteByPrimaryKey(String unitId);

    int insert(ProductUnit record);

    int insertSelective(ProductUnit record);

    ProductUnit selectByPrimaryKey(String unitId);

    int updateByPrimaryKeySelective(ProductUnit record);

    int updateByPrimaryKey(ProductUnit record);
}
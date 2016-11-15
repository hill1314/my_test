package com.hull.service;

import com.hull.entity.ProductType;

/**
 * Created by Administrator on 2016/11/13.
 */
public interface ProductTypeService {
    int deleteByPrimaryKey(String prodTypeId);

    int insert(ProductType record);

    int insertSelective(ProductType record);

    ProductType selectByPrimaryKey(String prodTypeId);

    int updateByPrimaryKeySelective(ProductType record);

    int updateByPrimaryKey(ProductType record);
}

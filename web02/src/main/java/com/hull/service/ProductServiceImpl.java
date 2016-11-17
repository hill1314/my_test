package com.hull.service;

import com.hull.entity.Product;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/11/15.
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Override
    public int deleteByPrimaryKey(String prodId) {
        return 0;
    }

    @Override
    public int insert(Product record) {
        return 0;
    }

    @Override
    public int insertSelective(Product record) {
        return 0;
    }

    @Override
    public Product selectByPrimaryKey(String prodId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Product record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Product record) {
        return 0;
    }
}

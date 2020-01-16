package com.bison.inventory.mapper.jpa;

import com.bison.inventory.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ClassName ProductMapper
 * @Description 数据访问接口
 * @Author zhangshuai
 * @Date 19-3-18 下午5:32
 * @Version 1.0
 **/
public interface ProductMapper extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {




}

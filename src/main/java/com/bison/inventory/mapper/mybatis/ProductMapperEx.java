package com.bison.inventory.mapper.mybatis;




public interface ProductMapperEx {


    Integer findProductIdBySku(String sku);

    Integer findSkuIdBySku(String sku);

    Integer findAsinIdByAsin(String asin);
}

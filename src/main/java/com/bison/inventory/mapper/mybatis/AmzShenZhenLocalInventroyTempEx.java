package com.bison.inventory.mapper.mybatis;


import com.bison.inventory.pojo.AmzShenZhenLocalInventroyTemp;
import com.bison.inventory.pojo.ShenZhenLocalOutInBoundInventroy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AmzShenZhenLocalInventroyTempEx {


    void insert(AmzShenZhenLocalInventroyTemp amzShenZhenLocalInventroyTemp);

    void upcInsertOrUpdateByProductIdList(@Param("productIdList") List<Integer> productIdList);

    void fnskuInsertOrUpdateByProductIdList(@Param("productIdList")List<Integer> productIdList);


}

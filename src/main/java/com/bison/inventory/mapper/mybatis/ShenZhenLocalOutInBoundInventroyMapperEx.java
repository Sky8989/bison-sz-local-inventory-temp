package com.bison.inventory.mapper.mybatis;


import com.bison.inventory.pojo.AmzShenZhenLocalInventroyTemp;
import com.bison.inventory.pojo.ShenZhenLocalOutInBoundInventroy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShenZhenLocalOutInBoundInventroyMapperEx {


    void insert(ShenZhenLocalOutInBoundInventroy shenZhenLocalOutInBoundInventroy);

    List<AmzShenZhenLocalInventroyTemp> findShenZhenLocalINventoryTempList();

    List<Integer> findProductIdList();

    void batchInsert(@Param("inventoryList") List<ShenZhenLocalOutInBoundInventroy> inventoryList);
}

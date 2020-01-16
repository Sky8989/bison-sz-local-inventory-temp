package com.bison.inventory.constant;

/**
 *  深圳本地仓 凭证类型
 */
public interface ShenZhenLocalInventoryCertificateType {

    String IN_BOUND_ORDER = "入库单";
    String OHTER_IN_BOUND_ORDER = "其他入库单";
    String OUT_BOUND = "出库";
    String OUT_BOUND_ORDER = "出库单";
    String OHTER_OUT_BOUND_ORDER = "其他出库单";
    String MOVE_INVENTORY_ORDER = "移仓单";
    String ADJUSTMENTINVENTORY_ORDER = "调仓单";
    String RETURN_OUT_BOUND_ORDER = "退货出库单";
    String BALANCE_IN_BOUND = "结余入库";
}

package com.bison.inventory.pojo;

import java.util.Date;

/**
 * AMZ 深圳仓库 临时表
 */
public class AmzShenZhenLocalInventroyTemp {
    /**
     * 产品id
     */
    private Integer productId;

    /**
     * upc库存
     */
    private Integer upcQuantity;

    /**
     * fnsku库存
     */
    private Integer fnskuQuantity;

    private Date updateTime;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUpcQuantity() {
        return upcQuantity;
    }

    public void setUpcQuantity(Integer upcQuantity) {
        this.upcQuantity = upcQuantity;
    }

    public Integer getFnskuQuantity() {
        return fnskuQuantity;
    }

    public void setFnskuQuantity(Integer fnskuQuantity) {
        this.fnskuQuantity = fnskuQuantity;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AmzShenZhenLocalInventroyTemp{");
        sb.append("productId=").append(productId);
        sb.append(", upcQuantity=").append(upcQuantity);
        sb.append(", fnskuQuantity=").append(fnskuQuantity);
        sb.append('}');
        return sb.toString();
    }
}

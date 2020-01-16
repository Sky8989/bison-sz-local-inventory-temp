package com.bison.inventory.pojo;

/**
 * 深圳仓库Sku
 */
public class ShenZhenLocalInventroySku {
    /**
     * 产品id
     */
    private Integer productId;


    private String sku;

    /**
     * 供应商
     */
    private String supplier;
    /**
     * 国别
     */
    private String countryOrTraingType;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getCountryOrTraingType() {
        return countryOrTraingType;
    }

    public void setCountryOrTraingType(String countryOrTraingType) {
        this.countryOrTraingType = countryOrTraingType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShenZhenLocalInventroySku{");
        sb.append("productId=").append(productId);
        sb.append(", sku='").append(sku).append('\'');
        sb.append(", supplier='").append(supplier).append('\'');
        sb.append(", countryOrTraingType='").append(countryOrTraingType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

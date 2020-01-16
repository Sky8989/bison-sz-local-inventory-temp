package com.bison.inventory.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName Product
 * @Description TODO
 * @Author peter
 * @Date 19-4-9 下午4:22
 * @Version 1.0
 **/
@Entity
public class Product {
    private int productId;
    private String productModelNumber;
    private int businessUnitId;
    private int brandId;
    private int productCategoryId;
    private BigDecimal productLong;
    private BigDecimal productWidth;
    private BigDecimal productHeight;
    private BigDecimal productVolume;
    private BigDecimal productNetweight;
    private BigDecimal productGrossweight;
    private String productMaterial;
    private String productPackageContains;
    private BigDecimal productOrderDefect;
    private String productU8Code;
    private int status;
    private String productCertification;
    private String productDescriptionChs;
    private String productDescriptionEn;
    private int userId;
    private Timestamp cTime;
    private String productImgUrl;

    //库存相关字段

    /**
     * 总安全库存天数
     */

    private Integer totalSafetyDay;
    /**
     * 亚马逊安全库存天数
     */
    private Integer amzSafetyDay;
    /**
     * 加权预测日均比率
     */
    private BigDecimal estUnitsAvgDayRatio;
    /**
     * 加权历史日均比率
     */
    private BigDecimal lastUnitsAvgDayRatio;

    /**
     * 库存设置提醒 大于方向 历史日均
     */
    private Integer bigLastUnitsAvgDay;

    /**
     * 库存设置提醒 大于方向 预测日均比率
     */
    private BigDecimal bigEstUnitisAvgDayRatio;

    /**
     *库存设置提醒 大于方向 对应 item_key_id
     */
    private Integer bigItemKeyId;

    /**
     *库存设置提醒 小于方向 对应 历史日均天数
     */
    private Integer samllLastUnitsAvgDay;

    /**
     *库存设置提醒 小于方向 对应 预测日均比率
     */
    private BigDecimal samllEstUnitsAvgDayRatio;

    /**
     *库存设置提醒 小于方向 对应 item_key_id
     */
    private Integer samllItemKeyId;

    /**
     * 运营备注
     */
    private String purchaseRemark;

    /**
     * 销售备注
     */
    private String salesRemark;


    @Id
    @Column(name = "product_id", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "product_model_number", nullable = false, length = 45)
    public String getProductModelNumber() {
        return productModelNumber;
    }

    public void setProductModelNumber(String productModelNumber) {
        this.productModelNumber = productModelNumber;
    }

    @Basic
    @Column(name = "business_unit_id", nullable = false)
    public int getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(int businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    @Basic
    @Column(name = "brand_id", nullable = false)
    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    @Basic
    @Column(name = "product_category_id", nullable = false)
    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    @Basic
    @Column(name = "product_long", nullable = false, precision = 2)
    public BigDecimal getProductLong() {
        return productLong;
    }

    public void setProductLong(BigDecimal productLong) {
        this.productLong = productLong;
    }

    @Basic
    @Column(name = "product_width", nullable = false, precision = 2)
    public BigDecimal getProductWidth() {
        return productWidth;
    }

    public void setProductWidth(BigDecimal productWidth) {
        this.productWidth = productWidth;
    }

    @Basic
    @Column(name = "product_height", nullable = false, precision = 2)
    public BigDecimal getProductHeight() {
        return productHeight;
    }

    public void setProductHeight(BigDecimal productHeight) {
        this.productHeight = productHeight;
    }

    @Basic
    @Column(name = "product_volume", nullable = false, precision = 2)
    public BigDecimal getProductVolume() {
        return productVolume;
    }

    public void setProductVolume(BigDecimal productVolume) {
        this.productVolume = productVolume;
    }

    @Basic
    @Column(name = "product_netweight", nullable = false, precision = 2)
    public BigDecimal getProductNetweight() {
        return productNetweight;
    }

    public void setProductNetweight(BigDecimal productNetweight) {
        this.productNetweight = productNetweight;
    }

    @Basic
    @Column(name = "product_grossweight", nullable = false, precision = 2)
    public BigDecimal getProductGrossweight() {
        return productGrossweight;
    }

    public void setProductGrossweight(BigDecimal productGrossweight) {
        this.productGrossweight = productGrossweight;
    }

    @Basic
    @Column(name = "product_material", nullable = true, length = 255)
    public String getProductMaterial() {
        return productMaterial;
    }

    public void setProductMaterial(String productMaterial) {
        this.productMaterial = productMaterial;
    }

    @Basic
    @Column(name = "product_package_contains", nullable = true, length = 255)
    public String getProductPackageContains() {
        return productPackageContains;
    }

    public void setProductPackageContains(String productPackageContains) {
        this.productPackageContains = productPackageContains;
    }

    @Basic
    @Column(name = "product_order_defect", nullable = false, precision = 2)
    public BigDecimal getProductOrderDefect() {
        return productOrderDefect;
    }

    public void setProductOrderDefect(BigDecimal productOrderDefect) {
        this.productOrderDefect = productOrderDefect;
    }

    @Basic
    @Column(name = "product_u8_code", nullable = false, length = 45)
    public String getProductU8Code() {
        return productU8Code;
    }

    public void setProductU8Code(String productU8Code) {
        this.productU8Code = productU8Code;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "product_certification", nullable = true, length = 45)
    public String getProductCertification() {
        return productCertification;
    }

    public void setProductCertification(String productCertification) {
        this.productCertification = productCertification;
    }

    @Basic
    @Column(name = "product_description_chs", nullable = true, length = -1)
    public String getProductDescriptionChs() {
        return productDescriptionChs;
    }

    public void setProductDescriptionChs(String productDescriptionChs) {
        this.productDescriptionChs = productDescriptionChs;
    }

    @Basic
    @Column(name = "product_description_en", nullable = true, length = -1)
    public String getProductDescriptionEn() {
        return productDescriptionEn;
    }

    public void setProductDescriptionEn(String productDescriptionEn) {
        this.productDescriptionEn = productDescriptionEn;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "c_time", nullable = false)
    public Timestamp getcTime() {
        return cTime;
    }

    public void setcTime(Timestamp cTime) {
        this.cTime = cTime;
    }

    @Basic
    @Column(name = "product_img_url", nullable = true, length = 255)
    public String getProductImgUrl() {
        return productImgUrl;
    }

    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }

    @Basic
    @Column(name = "total_safety_day")
    public Integer getTotalSafetyDay() {
        return totalSafetyDay;
    }

    public void setTotalSafetyDay(Integer totalSafetyDay) {
        this.totalSafetyDay = totalSafetyDay;
    }

    @Basic
    @Column(name = "amz_safety_day")
    public Integer getAmzSafetyDay() {
        return amzSafetyDay;
    }

    public void setAmzSafetyDay(Integer amzSafetyDay) {
        this.amzSafetyDay = amzSafetyDay;
    }

    @Basic
    @Column(name = "est_units_avg_day_ratio", precision = 2)
    public BigDecimal getEstUnitsAvgDayRatio() {
        return estUnitsAvgDayRatio;
    }

    public void setEstUnitsAvgDayRatio(BigDecimal estUnitsAvgDayRatio) {
        this.estUnitsAvgDayRatio = estUnitsAvgDayRatio;
    }

    @Basic
    @Column(name = "last_units_avg_day_ratio", precision = 2)
    public BigDecimal getLastUnitsAvgDayRatio() {
        return lastUnitsAvgDayRatio;
    }

    public void setLastUnitsAvgDayRatio(BigDecimal lastUnitsAvgDayRatio) {
        this.lastUnitsAvgDayRatio = lastUnitsAvgDayRatio;
    }

    @Basic
    @Column(name = "big_last_units_avg_day")
    public Integer getBigLastUnitsAvgDay() {
        return bigLastUnitsAvgDay;
    }

    public void setBigLastUnitsAvgDay(Integer bigLastUnitsAvgDay) {
        this.bigLastUnitsAvgDay = bigLastUnitsAvgDay;
    }

    @Basic
    @Column(name = "big_est_unitis_avg_day_ratio", precision = 2)
    public BigDecimal getBigEstUnitisAvgDayRatio() {
        return bigEstUnitisAvgDayRatio;
    }

    public void setBigEstUnitisAvgDayRatio(BigDecimal bigEstUnitisAvgDayRatio) {
        this.bigEstUnitisAvgDayRatio = bigEstUnitisAvgDayRatio;
    }

    @Basic
    @Column(name = "big_item_key_id")
    public Integer getBigItemKeyId() {
        return bigItemKeyId;
    }

    public void setBigItemKeyId(Integer bigItemKeyId) {
        this.bigItemKeyId = bigItemKeyId;
    }

    @Basic
    @Column(name = "samll_last_units_avg_day")
    public Integer getSamllLastUnitsAvgDay() {
        return samllLastUnitsAvgDay;
    }

    public void setSamllLastUnitsAvgDay(Integer samllLastUnitsAvgDay) {
        this.samllLastUnitsAvgDay = samllLastUnitsAvgDay;
    }

    @Basic
    @Column(name = "samll_est_units_avg_day_ratio", precision = 2)
    public BigDecimal getSamllEstUnitsAvgDayRatio() {
        return samllEstUnitsAvgDayRatio;
    }

    public void setSamllEstUnitsAvgDayRatio(BigDecimal samllEstUnitsAvgDayRatio) {
        this.samllEstUnitsAvgDayRatio = samllEstUnitsAvgDayRatio;
    }

    @Basic
    @Column(name = "samll_item_key_id")
    public Integer getSamllItemKeyId() {
        return samllItemKeyId;
    }

    public void setSamllItemKeyId(Integer samllItemKeyId) {
        this.samllItemKeyId = samllItemKeyId;
    }

    public String getpurchaseRemark() {
        return purchaseRemark;
    }

    public void setpurchaseRemark(String purchaseRemark) {
        this.purchaseRemark = purchaseRemark;
    }

    public String getSalesRemark() {
        return salesRemark;
    }

    public void setSalesRemark(String saleRemark) {
        this.salesRemark = saleRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId &&
                businessUnitId == product.businessUnitId &&
                brandId == product.brandId &&
                productCategoryId == product.productCategoryId &&
                status == product.status &&
                userId == product.userId &&
                Objects.equals(productModelNumber, product.productModelNumber) &&
                Objects.equals(productLong, product.productLong) &&
                Objects.equals(productWidth, product.productWidth) &&
                Objects.equals(productHeight, product.productHeight) &&
                Objects.equals(productVolume, product.productVolume) &&
                Objects.equals(productNetweight, product.productNetweight) &&
                Objects.equals(productGrossweight, product.productGrossweight) &&
                Objects.equals(productMaterial, product.productMaterial) &&
                Objects.equals(productPackageContains, product.productPackageContains) &&
                Objects.equals(productOrderDefect, product.productOrderDefect) &&
                Objects.equals(productU8Code, product.productU8Code) &&
                Objects.equals(productCertification, product.productCertification) &&
                Objects.equals(productDescriptionChs, product.productDescriptionChs) &&
                Objects.equals(productDescriptionEn, product.productDescriptionEn) &&
                Objects.equals(cTime, product.cTime) &&
                Objects.equals(productImgUrl, product.productImgUrl) &&
                Objects.equals(totalSafetyDay, product.totalSafetyDay) &&
                Objects.equals(amzSafetyDay, product.amzSafetyDay) &&
                Objects.equals(estUnitsAvgDayRatio, product.estUnitsAvgDayRatio) &&
                Objects.equals(lastUnitsAvgDayRatio, product.lastUnitsAvgDayRatio) &&
                Objects.equals(bigLastUnitsAvgDay, product.bigLastUnitsAvgDay) &&
                Objects.equals(bigEstUnitisAvgDayRatio, product.bigEstUnitisAvgDayRatio) &&
                Objects.equals(bigItemKeyId, product.bigItemKeyId) &&
                Objects.equals(samllLastUnitsAvgDay, product.samllLastUnitsAvgDay) &&
                Objects.equals(samllEstUnitsAvgDayRatio, product.samllEstUnitsAvgDayRatio) &&
                Objects.equals(samllItemKeyId, product.samllItemKeyId) &&
                Objects.equals(purchaseRemark, product.purchaseRemark) &&
                Objects.equals(salesRemark, product.salesRemark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productModelNumber, businessUnitId, brandId, productCategoryId, productLong, productWidth, productHeight, productVolume, productNetweight, productGrossweight, productMaterial, productPackageContains, productOrderDefect, productU8Code, status, productCertification, productDescriptionChs, productDescriptionEn, userId, cTime, productImgUrl, totalSafetyDay, amzSafetyDay, estUnitsAvgDayRatio, lastUnitsAvgDayRatio, bigLastUnitsAvgDay, bigEstUnitisAvgDayRatio, bigItemKeyId, samllLastUnitsAvgDay, samllEstUnitsAvgDayRatio, samllItemKeyId, purchaseRemark, salesRemark);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productModelNumber='" + productModelNumber + '\'' +
                ", businessUnitId=" + businessUnitId +
                ", brandId=" + brandId +
                ", productCategoryId=" + productCategoryId +
                ", productLong=" + productLong +
                ", productWidth=" + productWidth +
                ", productHeight=" + productHeight +
                ", productVolume=" + productVolume +
                ", productNetweight=" + productNetweight +
                ", productGrossweight=" + productGrossweight +
                ", productMaterial='" + productMaterial + '\'' +
                ", productPackageContains='" + productPackageContains + '\'' +
                ", productOrderDefect=" + productOrderDefect +
                ", productU8Code='" + productU8Code + '\'' +
                ", status=" + status +
                ", productCertification='" + productCertification + '\'' +
                ", productDescriptionChs='" + productDescriptionChs + '\'' +
                ", productDescriptionEn='" + productDescriptionEn + '\'' +
                ", userId=" + userId +
                ", cTime=" + cTime +
                ", productImgUrl='" + productImgUrl + '\'' +
                ", totalSafetyDay=" + totalSafetyDay +
                ", amzSafetyDay=" + amzSafetyDay +
                ", estUnitsAvgDayRatio=" + estUnitsAvgDayRatio +
                ", lastUnitsAvgDayRatio=" + lastUnitsAvgDayRatio +
                ", bigLastUnitsAvgDay=" + bigLastUnitsAvgDay +
                ", bigEstUnitisAvgDayRatio=" + bigEstUnitisAvgDayRatio +
                ", bigItemKeyId=" + bigItemKeyId +
                ", samllLastUnitsAvgDay=" + samllLastUnitsAvgDay +
                ", samllEstUnitsAvgDayRatio=" + samllEstUnitsAvgDayRatio +
                ", samllItemKeyId=" + samllItemKeyId +
                ", purchaseRemark='" + purchaseRemark + '\'' +
                ", salesRemark='" + salesRemark + '\'' +
                '}';
    }
}

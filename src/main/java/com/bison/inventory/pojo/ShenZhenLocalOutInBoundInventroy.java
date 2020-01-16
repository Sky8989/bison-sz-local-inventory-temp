package com.bison.inventory.pojo;


import java.util.Date;
import java.util.Objects;

/**
 * 深圳仓库出入库明细录入表格
 */
public class ShenZhenLocalOutInBoundInventroy {
    private Integer autoId;
    /**
     * 交易类型 1:人民币交易 2:美元交易
     */
    private Integer transactionType;
    /**
     * 出入库日期
     */
    private Date outInBoundDate;
    /**
     * 出入库类型 1;入库 2:出库 3:调仓
     */
    private Integer outInBoundTypeId;
    /**
     * 凭证类型 1:入库单 2:其他入库 3:出库单  4: 其他出库 5: 移仓单 6:调仓单 7: 退货出库单
     */
    private Integer certificateTypeId;
    /**
     * sku编排规则：型号+供应商+国别（部分是拼音首个字母）
     */
    private String sku;
    /**
     * 收货时间
     */
    private Date receiptDate;
    /**
     * 送货单号
     */
    private String deliveryNoteNumber;
    /**
     * 送检单号
     */
    private String sendCheckNumber;
    /**
     *
     */
    private String certificateNumber;
    /**
     * 产品id
     */
    private Integer productId;
    /**
     * 供应商 缩写
     */
    private String supplierAbbreviation;
    /**
     * 向别
     */
    private String inventoryType;
    /**
     * 入库数量
     */
    private Integer inBoundQuantity;
    /**
     * 出库数量
     */
    private Integer outBoundQuantity;
    /**
     * 移仓数量（入）
     */
    private Integer moveInBoundQuantity;
    /**
     * 移仓数量（出）
     */
    private Integer moveOutBoundQuantity;
    /**
     * 备注
     */
    private String remarks;

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public Date getOutInBoundDate() {
        return outInBoundDate;
    }

    public void setOutInBoundDate(Date outInBoundDate) {
        this.outInBoundDate = outInBoundDate;
    }

    public Integer getOutInBoundTypeId() {
        return outInBoundTypeId;
    }

    public void setOutInBoundTypeId(Integer outInBoundTypeId) {
        this.outInBoundTypeId = outInBoundTypeId;
    }

    public Integer getCertificateTypeId() {
        return certificateTypeId;
    }

    public void setCertificateTypeId(Integer certificateTypeId) {
        this.certificateTypeId = certificateTypeId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getDeliveryNoteNumber() {
        return deliveryNoteNumber;
    }

    public void setDeliveryNoteNumber(String deliveryNoteNumber) {
        this.deliveryNoteNumber = deliveryNoteNumber;
    }

    public String getSendCheckNumber() {
        return sendCheckNumber;
    }

    public void setSendCheckNumber(String sendCheckNumber) {
        this.sendCheckNumber = sendCheckNumber;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getSupplierAbbreviation() {
        return supplierAbbreviation;
    }

    public void setSupplierAbbreviation(String supplierAbbreviation) {
        this.supplierAbbreviation = supplierAbbreviation;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public Integer getInBoundQuantity() {
        return inBoundQuantity;
    }

    public void setInBoundQuantity(Integer inBoundQuantity) {
        this.inBoundQuantity = inBoundQuantity;
    }

    public Integer getOutBoundQuantity() {
        return outBoundQuantity;
    }

    public void setOutBoundQuantity(Integer outBoundQuantity) {
        this.outBoundQuantity = outBoundQuantity;
    }

    public Integer getMoveInBoundQuantity() {
        return moveInBoundQuantity;
    }

    public void setMoveInBoundQuantity(Integer moveInBoundQuantity) {
        this.moveInBoundQuantity = moveInBoundQuantity;
    }

    public Integer getMoveOutBoundQuantity() {
        return moveOutBoundQuantity;
    }

    public void setMoveOutBoundQuantity(Integer moveOutBoundQuantity) {
        this.moveOutBoundQuantity = moveOutBoundQuantity;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShenZhenLocalOutInBoundInventroy that = (ShenZhenLocalOutInBoundInventroy) o;
        return Objects.equals(autoId, that.autoId) &&
                Objects.equals(transactionType, that.transactionType) &&
                Objects.equals(outInBoundDate, that.outInBoundDate) &&
                Objects.equals(outInBoundTypeId, that.outInBoundTypeId) &&
                Objects.equals(certificateTypeId, that.certificateTypeId) &&
                Objects.equals(sku, that.sku) &&
                Objects.equals(receiptDate, that.receiptDate) &&
                Objects.equals(deliveryNoteNumber, that.deliveryNoteNumber) &&
                Objects.equals(sendCheckNumber, that.sendCheckNumber) &&
                Objects.equals(certificateNumber, that.certificateNumber) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(supplierAbbreviation, that.supplierAbbreviation) &&
                Objects.equals(inventoryType, that.inventoryType) &&
                Objects.equals(inBoundQuantity, that.inBoundQuantity) &&
                Objects.equals(outBoundQuantity, that.outBoundQuantity) &&
                Objects.equals(moveInBoundQuantity, that.moveInBoundQuantity) &&
                Objects.equals(moveOutBoundQuantity, that.moveOutBoundQuantity) &&
                Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autoId, transactionType, outInBoundDate, outInBoundTypeId, certificateTypeId, sku, receiptDate, deliveryNoteNumber, sendCheckNumber, certificateNumber, productId, supplierAbbreviation, inventoryType, inBoundQuantity, outBoundQuantity, moveInBoundQuantity, moveOutBoundQuantity, remarks);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShenZhenLocalOutInBoundInventroy{");
        sb.append("autoId=").append(autoId);
        sb.append(", transactionType='").append(transactionType).append('\'');
        sb.append(", outInBoundDate=").append(outInBoundDate);
        sb.append(", outInBoundTypeId=").append(outInBoundTypeId);
        sb.append(", certificateTypeId=").append(certificateTypeId);
        sb.append(", sku='").append(sku).append('\'');
        sb.append(", receiptDate='").append(receiptDate).append('\'');
        sb.append(", deliveryNoteNumber='").append(deliveryNoteNumber).append('\'');
        sb.append(", sendCheckNumber='").append(sendCheckNumber).append('\'');
        sb.append(", certificateNumber='").append(certificateNumber).append('\'');
        sb.append(", productId=").append(productId);
        sb.append(", supplierAbbreviation='").append(supplierAbbreviation).append('\'');
        sb.append(", inventoryType='").append(inventoryType).append('\'');
        sb.append(", inBoundQuantity=").append(inBoundQuantity);
        sb.append(", outBoundQuantity=").append(outBoundQuantity);
        sb.append(", moveInBoundQuantity=").append(moveInBoundQuantity);
        sb.append(", moveOutBoundQuantity=").append(moveOutBoundQuantity);
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

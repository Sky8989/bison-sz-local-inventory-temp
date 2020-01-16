package com.bison.inventory.pojo.ad;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;

/**
 * The primary key class for the amz_report_advertising database table.
 * 
 */
@Embeddable
public class AmzReportAdvertisingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="record_date")
	private java.util.Date recordDate;

	@Column(name="adgroup_id")
	private int adgroupId;

	@Column(name="seller_id")
	private int sellerId;

	@Column(name="seller_sku_id")
	private int sellerSkuId;

	public AmzReportAdvertisingPK() {
	}
	public java.util.Date getRecordDate() {
		return this.recordDate;
	}
	public void setRecordDate(java.util.Date recordDate) {
		this.recordDate = recordDate;
	}
	public int getAdgroupId() {
		return this.adgroupId;
	}
	public void setAdgroupId(int adgroupId) {
		this.adgroupId = adgroupId;
	}
	public int getSellerId() {
		return this.sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public int getSellerSkuId() {
		return this.sellerSkuId;
	}
	public void setSellerSkuId(int sellerSkuId) {
		this.sellerSkuId = sellerSkuId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AmzReportAdvertisingPK)) {
			return false;
		}
		AmzReportAdvertisingPK castOther = (AmzReportAdvertisingPK)other;
		return 
			this.recordDate.equals(castOther.recordDate)
			&& (this.adgroupId == castOther.adgroupId)
			&& (this.sellerId == castOther.sellerId)
			&& (this.sellerSkuId == castOther.sellerSkuId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.recordDate.hashCode();
		hash = hash * prime + this.adgroupId;
		hash = hash * prime + this.sellerId;
		hash = hash * prime + this.sellerSkuId;
		
		return hash;
	}
}
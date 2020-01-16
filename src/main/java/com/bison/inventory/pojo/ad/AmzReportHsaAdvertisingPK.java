package com.bison.inventory.pojo.ad;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;

/**
 * The primary key class for the amz_report_hsa_advertising database table.
 * 
 */
@Embeddable
public class AmzReportHsaAdvertisingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="record_date")
	private java.util.Date recordDate;

	@Column(name="seller_id")
	private int sellerId;

	@Column(name="campaign_id")
	private int campaignId;

	@Column(name="targeting_id")
	private int targetingId;

	@Column(name="match_type_id")
	private int matchTypeId;

	public AmzReportHsaAdvertisingPK() {
	}
	public java.util.Date getRecordDate() {
		return this.recordDate;
	}
	public void setRecordDate(java.util.Date recordDate) {
		this.recordDate = recordDate;
	}
	public int getSellerId() {
		return this.sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public int getCampaignId() {
		return this.campaignId;
	}
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	public int getTargetingId() {
		return this.targetingId;
	}
	public void setTargetingId(int targetingId) {
		this.targetingId = targetingId;
	}
	public int getMatchTypeId() {
		return this.matchTypeId;
	}
	public void setMatchTypeId(int matchTypeId) {
		this.matchTypeId = matchTypeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AmzReportHsaAdvertisingPK)) {
			return false;
		}
		AmzReportHsaAdvertisingPK castOther = (AmzReportHsaAdvertisingPK)other;
		return 
			this.recordDate.equals(castOther.recordDate)
			&& (this.sellerId == castOther.sellerId)
			&& (this.campaignId == castOther.campaignId)
			&& (this.targetingId == castOther.targetingId)
			&& (this.matchTypeId == castOther.matchTypeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.recordDate.hashCode();
		hash = hash * prime + this.sellerId;
		hash = hash * prime + this.campaignId;
		hash = hash * prime + this.targetingId;
		hash = hash * prime + this.matchTypeId;
		
		return hash;
	}
}
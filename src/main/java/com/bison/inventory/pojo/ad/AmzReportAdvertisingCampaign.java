package com.bison.inventory.pojo.ad;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the amz_report_advertising_campaign database table.
 * 
 */
@Entity
@Table(name="amz_report_advertising_campaign")
@NamedQuery(name="AmzReportAdvertisingCampaign.findAll", query="SELECT a FROM AmzReportAdvertisingCampaign a")
public class AmzReportAdvertisingCampaign implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="campaign_id")
	private int campaignId;

	@Column(name="campaign_name")
	private String campaignName;

	@Column(name="country_id")
	private int countryId;

	@Column(name="seller_id")
	private int sellerId;

	public AmzReportAdvertisingCampaign() {
	}

	public int getCampaignId() {
		return this.campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public String getCampaignName() {
		return this.campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public int getCountryId() {
		return this.countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

}
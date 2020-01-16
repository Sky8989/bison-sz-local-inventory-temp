package com.bison.inventory.pojo.ad;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the amz_report_advertising_adgroup database table.
 * 
 */
@Entity
@Table(name="amz_report_advertising_adgroup")
@NamedQuery(name="AmzReportAdvertisingAdgroup.findAll", query="SELECT a FROM AmzReportAdvertisingAdgroup a")
public class AmzReportAdvertisingAdgroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="adgroup_id")
	private int adgroupId;

	@Column(name="adgroup_name")
	private String adgroupName;

	@Column(name="campaign_id")
	private int campaignId;

	public AmzReportAdvertisingAdgroup() {
	}

	public int getAdgroupId() {
		return this.adgroupId;
	}

	public void setAdgroupId(int adgroupId) {
		this.adgroupId = adgroupId;
	}

	public String getAdgroupName() {
		return this.adgroupName;
	}

	public void setAdgroupName(String adgroupName) {
		this.adgroupName = adgroupName;
	}

	public int getCampaignId() {
		return this.campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

}
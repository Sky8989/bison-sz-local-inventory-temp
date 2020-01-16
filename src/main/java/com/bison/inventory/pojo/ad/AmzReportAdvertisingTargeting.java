package com.bison.inventory.pojo.ad;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the amz_report_advertising_targeting database table.
 * 
 */
@Entity
@Table(name="amz_report_advertising_targeting")
@NamedQuery(name="AmzReportAdvertisingTargeting.findAll", query="SELECT a FROM AmzReportAdvertisingTargeting a")
public class AmzReportAdvertisingTargeting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="targeting_id")
	private int targetingId;

	@Column(name="country_id")
	private int countryId;

	@Column(name="seller_id")
	private int sellerId;

	@Column(name="targeting_name")
	private String targetingName;

	public AmzReportAdvertisingTargeting() {
	}

	public int getTargetingId() {
		return this.targetingId;
	}

	public void setTargetingId(int targetingId) {
		this.targetingId = targetingId;
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

	public String getTargetingName() {
		return this.targetingName;
	}

	public void setTargetingName(String targetingName) {
		this.targetingName = targetingName;
	}

}
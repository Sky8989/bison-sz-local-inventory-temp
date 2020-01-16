package com.bison.inventory.pojo.ad;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * The persistent class for the amz_report_hsa_advertising database table.
 *
 */
@Entity
@Table(name="amz_report_sponsored_brands_search_term")
@NamedQuery(name="AmzReportSponsoredBrandsSearchTerm.findAll", query="SELECT a FROM AmzReportSponsoredBrandsSearchTerm a")
public  class AmzReportSponsoredBrandsSearchTerm implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="auto_id")
	private int autoId;

	@Temporal(TemporalType.DATE)
	@Column(name="record_date")
	private Date recordDate;

	//	@Column(name="seller_id")
	//	private int sellerId;

	//	@Column(name="campaign_id")
	//	private int campaignId;

	@Column(name="targeting_id")
	private int targetingId;

	@Column(name="adgroup_id")
	private int adgroupId;

	@Column(name="match_type_id")
	private int matchTypeId;



	@Column(name="customer_search_term")
	private String customerSearchTerm;

	@Column(name="impressions")
	private int impressions;

	@Column(name="clicks")
	private int clicks;

	@Column(name="click_thru_rate")
	private BigDecimal clickThruRate;

	@Column(name="cost_per_click")
	private BigDecimal costPerClick;

	@Column(name="spend")
	private BigDecimal spend;

	@Column(name="sales_14day")
	private BigDecimal sales14day;

	@Column(name="cost_of_sales")
	private BigDecimal costOfSales;



	@Column(name="return_on_advertising_spend")
	private BigDecimal returnOnAdvertisingSpend;


	@Column(name="order_14day")
	private int order14day;

	@Column(name="order_units_14day")
	private int orderUnits14day;

	@Column(name="conversion_rate_14day")
	private BigDecimal conversionRate14day;



	//	@Column(name="advertised_sku_units_14day")
	//	private BigDecimal advertisedSkuUnits14day;

	//	@Column(name="other_sku_units_14day")
	//	private BigDecimal otherSkuUnits14day;

	//	@Column(name="advertised_sku_sales_14day")
	//	private BigDecimal advertisedSkuSales14day;

	//	@Column(name="other_sku_sales_14day")
	//	private int other_sku_sales_14day;


	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public int getTargetingId() {
		return targetingId;
	}

	public void setTargetingId(int targetingId) {
		this.targetingId = targetingId;
	}

	public int getAdgroupId() {
		return adgroupId;
	}

	public void setAdgroupId(int adgroupId) {
		this.adgroupId = adgroupId;
	}

	public int getMatchTypeId() {
		return matchTypeId;
	}

	public void setMatchTypeId(int matchTypeId) {
		this.matchTypeId = matchTypeId;
	}

	public String getCustomerSearchTerm() {
		return customerSearchTerm;
	}

	public void setCustomerSearchTerm(String customerSearchTerm) {
		this.customerSearchTerm = customerSearchTerm;
	}

	public int getImpressions() {
		return impressions;
	}

	public void setImpressions(int impressions) {
		this.impressions = impressions;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public BigDecimal getClickThruRate() {
		return clickThruRate;
	}

	public void setClickThruRate(BigDecimal clickThruRate) {
		this.clickThruRate = clickThruRate;
	}

	public BigDecimal getCostPerClick() {
		return costPerClick;
	}

	public void setCostPerClick(BigDecimal costPerClick) {
		this.costPerClick = costPerClick;
	}

	public BigDecimal getSpend() {
		return spend;
	}

	public void setSpend(BigDecimal spend) {
		this.spend = spend;
	}

	public BigDecimal getSales14day() {
		return sales14day;
	}

	public void setSales14day(BigDecimal sales14day) {
		this.sales14day = sales14day;
	}

	public BigDecimal getCostOfSales() {
		return costOfSales;
	}

	public void setCostOfSales(BigDecimal costOfSales) {
		this.costOfSales = costOfSales;
	}

	public BigDecimal getReturnOnAdvertisingSpend() {
		return returnOnAdvertisingSpend;
	}

	public void setReturnOnAdvertisingSpend(BigDecimal returnOnAdvertisingSpend) {
		this.returnOnAdvertisingSpend = returnOnAdvertisingSpend;
	}

	public int getOrder14day() {
		return order14day;
	}

	public void setOrder14day(int order14day) {
		this.order14day = order14day;
	}

	public int getOrderUnits14day() {
		return orderUnits14day;
	}

	public void setOrderUnits14day(int orderUnits14day) {
		this.orderUnits14day = orderUnits14day;
	}

	public BigDecimal getConversionRate14day() {
		return conversionRate14day;
	}

	public void setConversionRate14day(BigDecimal conversionRate14day) {
		this.conversionRate14day = conversionRate14day;
	}
}

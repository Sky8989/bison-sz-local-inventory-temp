package com.bison.inventory.pojo.ad;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the amz_report_advertising database table.
 * 
 */
@Entity
@Table(name="amz_report_advertising")
@NamedQuery(name="AmzReportAdvertising.findAll", query="SELECT a FROM AmzReportAdvertising a")
public class AmzReportAdvertising implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AmzReportAdvertisingPK id;

	@Column(name="advertised_sku_sales_7day")
	private BigDecimal advertisedSkuSales7day;

	@Column(name="advertised_sku_units_7day")
	private int advertisedSkuUnits7day;

	@Column(name="asin_id")
	private int asinId;

	@Column(name="click_thru_rate")
	private BigDecimal clickThruRate;

	private int clicks;

	@Column(name="conversion_rate_7day")
	private BigDecimal conversionRate7day;

	@Column(name="cost_of_sales")
	private BigDecimal costOfSales;

	@Column(name="cost_per_click")
	private BigDecimal costPerClick;

	private int impressions;

	@Column(name="order_7day")
	private int order7day;

	@Column(name="order_units_7day")
	private int orderUnits7day;

	@Column(name="other_sku_sales_7day")
	private BigDecimal otherSkuSales7day;

	@Column(name="other_sku_units_7day")
	private int otherSkuUnits7day;

	@Column(name="product_id")
	private int productId;

	@Column(name="return_on_advertising_spend")
	private BigDecimal returnOnAdvertisingSpend;

	@Column(name="sales_7day")
	private BigDecimal sales7day;

	private BigDecimal spend;

	public AmzReportAdvertising() {
	}

	public AmzReportAdvertisingPK getId() {
		return this.id;
	}

	public void setId(AmzReportAdvertisingPK id) {
		this.id = id;
	}

	public BigDecimal getAdvertisedSkuSales7day() {
		return this.advertisedSkuSales7day;
	}

	public void setAdvertisedSkuSales7day(BigDecimal advertisedSkuSales7day) {
		this.advertisedSkuSales7day = advertisedSkuSales7day;
	}

	public int getAdvertisedSkuUnits7day() {
		return this.advertisedSkuUnits7day;
	}

	public void setAdvertisedSkuUnits7day(int advertisedSkuUnits7day) {
		this.advertisedSkuUnits7day = advertisedSkuUnits7day;
	}

	public int getAsinId() {
		return this.asinId;
	}

	public void setAsinId(int asinId) {
		this.asinId = asinId;
	}

	public BigDecimal getClickThruRate() {
		return this.clickThruRate;
	}

	public void setClickThruRate(BigDecimal clickThruRate) {
		this.clickThruRate = clickThruRate;
	}

	public int getClicks() {
		return this.clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public BigDecimal getConversionRate7day() {
		return this.conversionRate7day;
	}

	public void setConversionRate7day(BigDecimal conversionRate7day) {
		this.conversionRate7day = conversionRate7day;
	}

	public BigDecimal getCostOfSales() {
		return this.costOfSales;
	}

	public void setCostOfSales(BigDecimal costOfSales) {
		this.costOfSales = costOfSales;
	}

	public BigDecimal getCostPerClick() {
		return this.costPerClick;
	}

	public void setCostPerClick(BigDecimal costPerClick) {
		this.costPerClick = costPerClick;
	}

	public int getImpressions() {
		return this.impressions;
	}

	public void setImpressions(int impressions) {
		this.impressions = impressions;
	}

	public int getOrder7day() {
		return this.order7day;
	}

	public void setOrder7day(int order7day) {
		this.order7day = order7day;
	}

	public int getOrderUnits7day() {
		return this.orderUnits7day;
	}

	public void setOrderUnits7day(int orderUnits7day) {
		this.orderUnits7day = orderUnits7day;
	}

	public BigDecimal getOtherSkuSales7day() {
		return this.otherSkuSales7day;
	}

	public void setOtherSkuSales7day(BigDecimal otherSkuSales7day) {
		this.otherSkuSales7day = otherSkuSales7day;
	}

	public int getOtherSkuUnits7day() {
		return this.otherSkuUnits7day;
	}

	public void setOtherSkuUnits7day(int otherSkuUnits7day) {
		this.otherSkuUnits7day = otherSkuUnits7day;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public BigDecimal getReturnOnAdvertisingSpend() {
		return this.returnOnAdvertisingSpend;
	}

	public void setReturnOnAdvertisingSpend(BigDecimal returnOnAdvertisingSpend) {
		this.returnOnAdvertisingSpend = returnOnAdvertisingSpend;
	}

	public BigDecimal getSales7day() {
		return this.sales7day;
	}

	public void setSales7day(BigDecimal sales7day) {
		this.sales7day = sales7day;
	}

	public BigDecimal getSpend() {
		return this.spend;
	}

	public void setSpend(BigDecimal spend) {
		this.spend = spend;
	}

}
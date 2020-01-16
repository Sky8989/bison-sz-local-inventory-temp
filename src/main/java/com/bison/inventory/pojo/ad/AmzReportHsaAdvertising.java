package com.bison.inventory.pojo.ad;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the amz_report_hsa_advertising database table.
 * 
 */
@Entity
@Table(name="amz_report_hsa_advertising")
@NamedQuery(name="AmzReportHsaAdvertising.findAll", query="SELECT a FROM AmzReportHsaAdvertising a")
public class AmzReportHsaAdvertising implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AmzReportHsaAdvertisingPK id;

	@Column(name="click_thru_rate")
	private BigDecimal clickThruRate;

	private int clicks;

	@Column(name="conversion_rate_14day")
	private BigDecimal conversionRate14day;

	@Column(name="cost_of_sales")
	private BigDecimal costOfSales;

	@Column(name="cost_per_click")
	private BigDecimal costPerClick;

	private int impressions;

	@Column(name="new_to_brand_order_14day")
	private BigDecimal newToBrandOrder14day;

	@Column(name="new_to_brand_order_percentage_14day")
	private BigDecimal newToBrandOrderPercentage14day;

	@Column(name="new_to_brand_order_rate_14day")
	private BigDecimal newToBrandOrderRate14day;

	@Column(name="new_to_brand_order_units_14day")
	private int newToBrandOrderUnits14day;

	@Column(name="new_to_brand_order_units_percentage_14day")
	private BigDecimal newToBrandOrderUnitsPercentage14day;

	@Column(name="new_to_brand_sales_14day")
	private BigDecimal newToBrandSales14day;

	@Column(name="new_to_brand_sales_percentage_14day")
	private BigDecimal newToBrandSalesPercentage14day;

	@Column(name="order_14day")
	private int order14day;

	@Column(name="order_units_14day")
	private int orderUnits14day;

	@Column(name="return_on_advertising_spend")
	private BigDecimal returnOnAdvertisingSpend;

	@Column(name="sales_14day")
	private BigDecimal sales14day;

	private BigDecimal spend;

	public AmzReportHsaAdvertising() {
	}

	public AmzReportHsaAdvertisingPK getId() {
		return this.id;
	}

	public void setId(AmzReportHsaAdvertisingPK id) {
		this.id = id;
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

	public BigDecimal getConversionRate14day() {
		return this.conversionRate14day;
	}

	public void setConversionRate14day(BigDecimal conversionRate14day) {
		this.conversionRate14day = conversionRate14day;
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

	public BigDecimal getNewToBrandOrder14day() {
		return this.newToBrandOrder14day;
	}

	public void setNewToBrandOrder14day(BigDecimal newToBrandOrder14day) {
		this.newToBrandOrder14day = newToBrandOrder14day;
	}

	public BigDecimal getNewToBrandOrderPercentage14day() {
		return this.newToBrandOrderPercentage14day;
	}

	public void setNewToBrandOrderPercentage14day(BigDecimal newToBrandOrderPercentage14day) {
		this.newToBrandOrderPercentage14day = newToBrandOrderPercentage14day;
	}

	public BigDecimal getNewToBrandOrderRate14day() {
		return this.newToBrandOrderRate14day;
	}

	public void setNewToBrandOrderRate14day(BigDecimal newToBrandOrderRate14day) {
		this.newToBrandOrderRate14day = newToBrandOrderRate14day;
	}

	public int getNewToBrandOrderUnits14day() {
		return this.newToBrandOrderUnits14day;
	}

	public void setNewToBrandOrderUnits14day(int newToBrandOrderUnits14day) {
		this.newToBrandOrderUnits14day = newToBrandOrderUnits14day;
	}

	public BigDecimal getNewToBrandOrderUnitsPercentage14day() {
		return this.newToBrandOrderUnitsPercentage14day;
	}

	public void setNewToBrandOrderUnitsPercentage14day(BigDecimal newToBrandOrderUnitsPercentage14day) {
		this.newToBrandOrderUnitsPercentage14day = newToBrandOrderUnitsPercentage14day;
	}

	public BigDecimal getNewToBrandSales14day() {
		return this.newToBrandSales14day;
	}

	public void setNewToBrandSales14day(BigDecimal newToBrandSales14day) {
		this.newToBrandSales14day = newToBrandSales14day;
	}

	public BigDecimal getNewToBrandSalesPercentage14day() {
		return this.newToBrandSalesPercentage14day;
	}

	public void setNewToBrandSalesPercentage14day(BigDecimal newToBrandSalesPercentage14day) {
		this.newToBrandSalesPercentage14day = newToBrandSalesPercentage14day;
	}

	public int getOrder14day() {
		return this.order14day;
	}

	public void setOrder14day(int order14day) {
		this.order14day = order14day;
	}

	public int getOrderUnits14day() {
		return this.orderUnits14day;
	}

	public void setOrderUnits14day(int orderUnits14day) {
		this.orderUnits14day = orderUnits14day;
	}

	public BigDecimal getReturnOnAdvertisingSpend() {
		return this.returnOnAdvertisingSpend;
	}

	public void setReturnOnAdvertisingSpend(BigDecimal returnOnAdvertisingSpend) {
		this.returnOnAdvertisingSpend = returnOnAdvertisingSpend;
	}

	public BigDecimal getSales14day() {
		return this.sales14day;
	}

	public void setSales14day(BigDecimal sales14day) {
		this.sales14day = sales14day;
	}

	public BigDecimal getSpend() {
		return this.spend;
	}

	public void setSpend(BigDecimal spend) {
		this.spend = spend;
	}

}
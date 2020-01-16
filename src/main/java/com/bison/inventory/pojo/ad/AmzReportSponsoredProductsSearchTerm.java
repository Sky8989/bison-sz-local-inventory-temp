package com.bison.inventory.pojo.ad;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;


/**
 * The persistent class for the amz_report_hsa_advertising database table.
 * 
 */
@Entity
//@IdClass(AmzReportSponsoredProductsSearchTermPK.class)
@Table(name="amz_report_sponsored_products_search_term")
@NamedQuery(name="AmzReportSponsoredProductsSearchTerm.findAll", query="SELECT a FROM AmzReportSponsoredProductsSearchTerm a")
public class AmzReportSponsoredProductsSearchTerm implements Serializable {

	private static final long serialVersionUID = 1L;
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	@Column(name="auto_id")
//	private int autoId;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="record_date")
//	private Date recordDate;

//	@Column(name="seller_id")
//	private int sellerId;

//	@Column(name="campaign_id")
//	private int campaignId;



	@EmbeddedId
	private AmzReportSponsoredProductsSearchTermPK id;



	@Column(name="targeting_id")
	private int targetingId;

//	@Column(name="adgroup_id")
//	private int adgroupId;

	@Column(name="match_type_id")
	private int matchTypeId;



//	@Column(name="customer_search_term")
//	private String customerSearchTerm;

	@Column(name="impressions")
	private int impressions;

	@Column(name="clicks")
	private int clicks;

//	@Column(name="click_thru_rate")
//	private BigDecimal clickThruRate;

	@Column(name="cost_per_click")
	private BigDecimal costPerClick;

//	@Column(name="spend")
//	private BigDecimal spend;
//
//	@Column(name="sales_7day")
//	private BigDecimal sales7day;

	@Column(name="cost_of_sales")
	private BigDecimal costOfSales;



	@Column(name="return_on_advertising_spend")
	private BigDecimal returnOnAdvertisingSpend;


	@Column(name="order_7day")
	private int order7day;

	@Column(name="order_units_7day")
	private int orderUnits7day;


	@Column(name="conversion_rate_7day")
	private BigDecimal conversionRate7day;



//	@Column(name="advertised_sku_units_7day")
//	private BigDecimal advertisedSkuUnits7day;

//	@Column(name="other_sku_units_7day")
//	private BigDecimal otherSkuUnits7day;

//	@Column(name="advertised_sku_sales_7day")
//	private BigDecimal advertisedSkuSales7day;

//	@Column(name="other_sku_sales_7day")
//	private int other_sku_sales_7day;




	public int getTargetingId() {
		return targetingId;
	}

	public void setTargetingId(int targetingId) {
		this.targetingId = targetingId;
	}


	public int getMatchTypeId() {
		return matchTypeId;
	}

	public void setMatchTypeId(int matchTypeId) {
		this.matchTypeId = matchTypeId;
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



	public BigDecimal getCostPerClick() {
		return costPerClick;
	}

	public void setCostPerClick(BigDecimal costPerClick) {
		this.costPerClick = costPerClick;
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

	public int getOrder7day() {
		return order7day;
	}

	public void setOrder7day(int order7day) {
		this.order7day = order7day;
	}

	public int getOrderUnits7day() {
		return orderUnits7day;
	}

	public void setOrderUnits7day(int orderUnits7day) {
		this.orderUnits7day = orderUnits7day;
	}

	public BigDecimal getConversionRate7day() {
		return conversionRate7day;
	}

	public void setConversionRate7day(BigDecimal conversionRate7day) {
		this.conversionRate7day = conversionRate7day;
	}

	public AmzReportSponsoredProductsSearchTermPK getId() {
		return id;
	}

	public void setId(AmzReportSponsoredProductsSearchTermPK id) {
		this.id = id;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AmzReportSponsoredProductsSearchTerm that = (AmzReportSponsoredProductsSearchTerm) o;

		if (targetingId != that.targetingId) return false;
		if (matchTypeId != that.matchTypeId) return false;
		if (impressions != that.impressions) return false;
		if (clicks != that.clicks) return false;
		if (order7day != that.order7day) return false;
		if (orderUnits7day != that.orderUnits7day) return false;
		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (costPerClick != null ? !costPerClick.equals(that.costPerClick) : that.costPerClick != null) return false;
		if (costOfSales != null ? !costOfSales.equals(that.costOfSales) : that.costOfSales != null) return false;
		if (returnOnAdvertisingSpend != null ? !returnOnAdvertisingSpend.equals(that.returnOnAdvertisingSpend) : that.returnOnAdvertisingSpend != null)
			return false;
		return conversionRate7day != null ? conversionRate7day.equals(that.conversionRate7day) : that.conversionRate7day == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + targetingId;
		result = 31 * result + matchTypeId;
		result = 31 * result + impressions;
		result = 31 * result + clicks;
		result = 31 * result + (costPerClick != null ? costPerClick.hashCode() : 0);
		result = 31 * result + (costOfSales != null ? costOfSales.hashCode() : 0);
		result = 31 * result + (returnOnAdvertisingSpend != null ? returnOnAdvertisingSpend.hashCode() : 0);
		result = 31 * result + order7day;
		result = 31 * result + orderUnits7day;
		result = 31 * result + (conversionRate7day != null ? conversionRate7day.hashCode() : 0);
		return result;
	}
}
package com.bison.inventory.pojo.ad;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the amz_report_hsa_advertising database table.
 *
 */
@Entity
@Table(name="amz_report_sponsored_products_purchased_product")
@NamedQuery(name="AmzReportSponsoredProductPurchasedProduct.findAll", query="SELECT a FROM AmzReportSponsoredProductPurchasedProduct a")
public class AmzReportSponsoredProductPurchasedProduct implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	@Column(name="auto_id")
	private int autoId;

	@Temporal(TemporalType.DATE)
	@Column(name="record_date")
	private Date recordDate;



	@Column(name="targeting_id")
	private int targetingId;

	@Column(name="adgroup_id")
	private int adgroupId;


	@Column(name="product_id")
	private int productId;

	@Column(name="seller_sku_id")
	private int sellerSkuid;

	@Column(name="advertised_asin_id")
	private int advertisedAsinId;

	@Column(name="purchased_asin_id")
	private int purchasedAsinId;


	@Column(name="match_type_id")
	private int matchTypeId;


	@Column(name="ohter_sku_order_7day")
	private int ohterSkuOrder7day;

	@Column(name="other_sku_units_7day")
	private int otherSkuUnits7day;


	@Column(name="other_sku_sales_7day")
	private BigDecimal otherSkuSales7day;


	public BigDecimal getOtherSkuSales7day() {
		return otherSkuSales7day;
	}

	public void setOtherSkuSales7day(BigDecimal otherSkuSales7day) {
		this.otherSkuSales7day = otherSkuSales7day;
	}

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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSellerSkuid() {
		return sellerSkuid;
	}

	public void setSellerSkuid(int sellerSkuid) {
		this.sellerSkuid = sellerSkuid;
	}

	public int getAdvertisedAsinId() {
		return advertisedAsinId;
	}

	public void setAdvertisedAsinId(int advertisedAsinId) {
		this.advertisedAsinId = advertisedAsinId;
	}

	public int getPurchasedAsinId() {
		return purchasedAsinId;
	}

	public void setPurchasedAsinId(int purchasedAsinId) {
		this.purchasedAsinId = purchasedAsinId;
	}

	public int getMatchTypeId() {
		return matchTypeId;
	}

	public void setMatchTypeId(int matchTypeId) {
		this.matchTypeId = matchTypeId;
	}

	public int getOhterSkuOrder7day() {
		return ohterSkuOrder7day;
	}

	public void setOhterSkuOrder7day(int ohterSkuOrder7day) {
		this.ohterSkuOrder7day = ohterSkuOrder7day;
	}

	public int getOtherSkuUnits7day() {
		return otherSkuUnits7day;
	}

	public void setOtherSkuUnits7day(int otherSkuUnits7day) {
		this.otherSkuUnits7day = otherSkuUnits7day;
	}



}
package com.bison.inventory.pojo.ad;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * The primary key class for the amz_report_sponsored_products_search_term database table.
 * 
 */
@Embeddable
public class AmzReportSponsoredProductsSearchTermPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="record_date")
	private Date recordDate;

	@Column(name="adgroup_id")
	private int adgroupId;

	@Column(name="customer_search_term")
	private String customerSearchTerm;

	@Column(name="click_thru_rate")
	private BigDecimal clickThruRate;

	@Column(name="spend")
	private BigDecimal spend;

	@Column(name="sales_7day")
	private BigDecimal sales7day;

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public int getAdgroupId() {
		return adgroupId;
	}

	public void setAdgroupId(int adgroupId) {
		this.adgroupId = adgroupId;
	}

	public String getCustomerSearchTerm() {
		return customerSearchTerm;
	}

	public void setCustomerSearchTerm(String customerSearchTerm) {
		this.customerSearchTerm = customerSearchTerm;
	}

	public BigDecimal getClickThruRate() {
		return clickThruRate;
	}

	public void setClickThruRate(BigDecimal clickThruRate) {
		this.clickThruRate = clickThruRate;
	}

	public BigDecimal getSpend() {
		return spend;
	}

	public void setSpend(BigDecimal spend) {
		this.spend = spend;
	}

	public BigDecimal getSales7day() {
		return sales7day;
	}

	public void setSales7day(BigDecimal sales14day) {
		this.sales7day = sales14day;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AmzReportSponsoredProductsSearchTermPK that = (AmzReportSponsoredProductsSearchTermPK) o;

		if (adgroupId != that.adgroupId) return false;
		if (recordDate != null ? !recordDate.equals(that.recordDate) : that.recordDate != null) return false;
		if (customerSearchTerm != null ? !customerSearchTerm.equals(that.customerSearchTerm) : that.customerSearchTerm != null)
			return false;
		if (clickThruRate != null ? !clickThruRate.equals(that.clickThruRate) : that.clickThruRate != null)
			return false;
		if (spend != null ? !spend.equals(that.spend) : that.spend != null) return false;
		return sales7day != null ? sales7day.equals(that.sales7day) : that.sales7day == null;

	}

	@Override
	public int hashCode() {
		int result = recordDate != null ? recordDate.hashCode() : 0;
		result = 31 * result + adgroupId;
		result = 31 * result + (customerSearchTerm != null ? customerSearchTerm.hashCode() : 0);
		result = 31 * result + (clickThruRate != null ? clickThruRate.hashCode() : 0);
		result = 31 * result + (spend != null ? spend.hashCode() : 0);
		result = 31 * result + (sales7day != null ? sales7day.hashCode() : 0);
		return result;
	}
}
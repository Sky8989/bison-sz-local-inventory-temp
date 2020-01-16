package com.bison.inventory.pojo.ad;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the amz_report_advertising_match_type database table.
 * 
 */
@Entity
@Table(name="amz_report_advertising_match_type")
@NamedQuery(name="AmzReportAdvertisingMatchType.findAll", query="SELECT a FROM AmzReportAdvertisingMatchType a")
public class AmzReportAdvertisingMatchType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="match_type_id")
	private int matchTypeId;

	@Column(name="match_type_name")
	private String matchTypeName;

	public AmzReportAdvertisingMatchType() {
	}

	public int getMatchTypeId() {
		return this.matchTypeId;
	}

	public void setMatchTypeId(int matchTypeId) {
		this.matchTypeId = matchTypeId;
	}

	public String getMatchTypeName() {
		return this.matchTypeName;
	}

	public void setMatchTypeName(String matchTypeName) {
		this.matchTypeName = matchTypeName;
	}

}
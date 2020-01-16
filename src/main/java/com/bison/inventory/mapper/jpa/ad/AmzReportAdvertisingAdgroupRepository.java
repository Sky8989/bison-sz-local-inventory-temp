package com.bison.inventory.mapper.jpa.ad;

import com.bison.inventory.pojo.ad.AmzReportAdvertisingAdgroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmzReportAdvertisingAdgroupRepository extends JpaRepository<AmzReportAdvertisingAdgroup, Integer> {
	public List<AmzReportAdvertisingAdgroup> findByAdgroupNameAndCampaignId(String adgroupName, int campaignId);
}

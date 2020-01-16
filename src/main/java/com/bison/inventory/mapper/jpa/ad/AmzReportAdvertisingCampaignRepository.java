package com.bison.inventory.mapper.jpa.ad;

import com.bison.inventory.pojo.ad.AmzReportAdvertisingCampaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmzReportAdvertisingCampaignRepository extends JpaRepository<AmzReportAdvertisingCampaign, Integer> {

	public List<AmzReportAdvertisingCampaign> findByCampaignNameAndSellerIdAndCountryId(String campaignName, int sellerId, int countryId);
}

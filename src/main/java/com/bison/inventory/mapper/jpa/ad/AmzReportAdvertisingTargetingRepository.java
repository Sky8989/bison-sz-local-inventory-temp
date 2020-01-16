package com.bison.inventory.mapper.jpa.ad;

import com.bison.inventory.pojo.ad.AmzReportAdvertisingTargeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmzReportAdvertisingTargetingRepository extends JpaRepository<AmzReportAdvertisingTargeting, Integer> {

	List<AmzReportAdvertisingTargeting> findByTargetingNameAndSellerIdAndCountryId(String targetingName, int sellerId, int countryId);

}

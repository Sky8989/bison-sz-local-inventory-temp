package com.bison.inventory.mapper.jpa.ad;

import com.bison.inventory.pojo.ad.AmzReportAdvertisingMatchType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmzReportAdvertisingMatchTypeRepository extends JpaRepository<AmzReportAdvertisingMatchType, Integer> {

	List<AmzReportAdvertisingMatchType> findByMatchTypeName(String matchTypeName);

}

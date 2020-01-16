package com.bison.inventory.handler;


import com.alibaba.fastjson.JSON;
import com.bison.inventory.mapper.jpa.ProductMapper;
import com.bison.inventory.mapper.jpa.ad.*;
import com.bison.inventory.mapper.mybatis.AmzShenZhenLocalInventroyTempEx;
import com.bison.inventory.mapper.mybatis.ProductMapperEx;
import com.bison.inventory.mapper.mybatis.ShenZhenLocalInventroyMapperSkuEx;
import com.bison.inventory.mapper.mybatis.ShenZhenLocalOutInBoundInventroyMapperEx;
import com.bison.inventory.pojo.ad.*;
import com.bison.inventory.util.excel.ImportExcelUtil;
import com.bison.inventory.util.excel.entity.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


/**
 * @author Lee
 * @date 2019-10-27
 */
//@Api(description = "测试广告", tags = "ad")
//@RestController
//@RequestMapping("/ad")
public class TestAdvertitsingHandler {


    @Autowired
    ProductMapper productMapper;

    @Autowired
    ShenZhenLocalOutInBoundInventroyMapperEx shenZhenLocalOutInBoundInventroyMapperEx;

    @Autowired
    ShenZhenLocalInventroyMapperSkuEx shenZhenLocalInventroyMapperSkuEx;

    @Autowired
    AmzShenZhenLocalInventroyTempEx amzShenZhenLocalInventroyTempEx;


    @Autowired
    AmzReportAdvertisingCampaignRepository amzReportAdvertisingCampaignRepository;

    @Autowired
    AmzReportAdvertisingAdgroupRepository amzReportAdvertisingAdgroupRepository;

    @Autowired
    AmzReportAdvertisingMatchTypeRepository amzReportAdvertisingMatchTypeRepository;

    @Autowired
    com.bison.inventory.mapper.jpa.ad.AmzReportAdvertisingTargetingRepository amzReportAdvertisingTargetingRepository;

    @Autowired
    AmzReportSponsoredBrandsSearchTermRepository amzReportSponsoredBrandsSearchTermRepository;

    @Autowired
    AmzReportSponsoredProductsSearchTermRepository amzReportSponsoredProductsSearchTermRepository;

    @Autowired
    AmzReportSponsoredProductPurchasedProductRepository amzReportSponsoredProductPurchasedProductRepository;


    @Autowired
    ProductMapperEx productMapperEx;



    private static final Logger log = LoggerFactory.getLogger(TestAdvertitsingHandler.class);


    @ApiOperation(value = "test")
    @GetMapping(value = "test")
    public String test(){

        log.info("info log log4j2");
        log.warn("info warn log4j2");
        log.error("info error log4j2");

        return "hello-log";
    }


    @ApiOperation(value = "上传广告文件")
    @RequestMapping(value = "/sponsored_brands_search_term", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean updateByFile(@RequestParam(value = "file")
                                           MultipartFile file) throws IOException {
        ResultBean resultBean = new ResultBean();
        try {
            if (null != file) {
                System.out.println("=== file ==" + file);
            }

            ImportExcelUtil.checkFile(file);
            //解析 excel

            Workbook workbook = ImportExcelUtil.getWorkBook(file);

            //数据的行数
            int numberOfSheets = workbook.getNumberOfSheets();
            System.out.println("numberOfSheets = " + numberOfSheets);

            Sheet sheet0 = workbook.getSheetAt(0);


            resultBean = saveTableHead(sheet0);
            //保存第一页数据
//            resultBean = saveSheet0(sheet0);

        } catch (Exception e) {
            e.printStackTrace();
            resultBean.setMsg("Update failed！");
            log.error(e.getMessage());
        }
        return resultBean;
    }

    private ResultBean saveTableHead(Sheet sheet0) {
        ResultBean resultBean = new ResultBean();

        Row row = sheet0.getRow(0);

        Iterator<Cell> cellIterator =  row.cellIterator();

        List<String> list = new ArrayList<>();
        while (cellIterator.hasNext()){
            Cell cell  = cellIterator.next();
            System.out.println(cell.getStringCellValue());
            list.add(cell.getStringCellValue());
        }



        String json = JSON.toJSONString(list);
        System.out.println("json list ="+ json);
        System.out.println(" list ="+ JSON.parseObject(json,ArrayList.class));

        resultBean.setData(JSON.parseObject(json,ArrayList.class));

        return resultBean;

    }


    @ApiOperation(value = "上传广告文件")
    @RequestMapping(value = "/sponsoredProduct_search_term", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean updateReport(@RequestParam(value = "file")
                                           MultipartFile file) throws IOException {
        ResultBean resultBean = new ResultBean();
        try {
            if (null != file) {
                System.out.println("=== file ==" + file);
            }

            ImportExcelUtil.checkFile(file);
            //解析 excel

            Workbook workbook = ImportExcelUtil.getWorkBook(file);

            //数据的行数
            int numberOfSheets = workbook.getNumberOfSheets();
            System.out.println("numberOfSheets = " + numberOfSheets);

            Sheet sheet0 = workbook.getSheetAt(0);


            //保存第一页数据
            resultBean = saveSheet1(sheet0);

        } catch (Exception e) {
            e.printStackTrace();
            resultBean.setMsg("Update failed！");
            log.error(e.getMessage());
        }
        return resultBean;
    }


    @Transactional
    public ResultBean saveSheet0(Sheet sheet0) {
        ResultBean resultBean = new ResultBean();

        Iterator<Row> rowIterator = sheet0.iterator();

        int i = 1;
        try {


            int countryId = 0;
            int sellerId = 3;

            while (rowIterator.hasNext()) {
                //  System.out.println("=====================");
                Row row = rowIterator.next();

                if (StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(0)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(1)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(2)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(3)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(4)))

                ) {
                    break;
                }


//                int num = 1;
//              List<AmzReportSponsoredBrandsSearchTerm> searchTermList =   amzReportSponsoredBrandsSearchTermRepository.findAll();
//              if(!searchTermList.isEmpty()){
//                  int id = searchTermList.get(searchTermList.size()-1).getAutoId();
//                  i = id;
//                  num = id;
//
//              }


                if (i > 1) {
                    System.out.println("=========== i " + i);
                    AmzReportSponsoredBrandsSearchTerm searchTerm = new AmzReportSponsoredBrandsSearchTerm();
                   // AmzReportAdvertisingAdgroup ad = new AmzReportAdvertisingAdgroup();


                    Cell cell = null;
                    //列0 - Date
                    cell = row.getCell(0);
                    Date recordDate = cell.getDateCellValue();
                    //              String recordDate =   ImportExcelUtil.getCellValue(cell) ;
//                    System.out.println(" Date = " +recordDate);
                    searchTerm.setRecordDate(recordDate);

                    //						//列1 - Portfolio name
                    cell = row.getCell(1);
                    String portfolio_name = cell.getStringCellValue();
                    System.out.println(" portfolio_name = " + portfolio_name);
//						//列2 - Currency
                    cell = row.getCell(2);
                    String currency = cell.getStringCellValue();

                    if ("CAD".equals(currency)) {
                        countryId = 8;
                    } else if ("USD".equals(currency)) {
                        countryId = 7;
                    } else if ("EUR".equals(currency)) {
                        countryId = 1;
                        sellerId = 5;
                    }

                    System.out.println(" currency = " + currency);

                    //列3 - Campaign Name
                    cell = row.getCell(3);
                    String adGcampaignName = cell.getStringCellValue();
                    System.out.println(" adGcampaignName = " + adGcampaignName);
//                        int campaignId = 0;
//                        List<AmzReportAdvertisingCampaign> advertisingCampaignList =  amzReportAdvertisingCampaignRepository.findByCampaignNameAndSellerIdAndCountryId(adGcampaignName,sellerId,countryId);
//                    if(StringUtils.isNotBlank(adGcampaignName)){
//                        AmzReportAdvertisingCampaign campaign = new AmzReportAdvertisingCampaign();
//                        if(!advertisingCampaignList.isEmpty()){
//                            //拿id
//                            campaignId = amzReportAdvertisingCampaignRepository.findByCampaignNameAndSellerIdAndCountryId(adGcampaignName,sellerId,countryId).get(0).getCampaignId();
//
//                        }else{
//
//                            campaign.setCampaignName(adGcampaignName);
//                            campaign.setSellerId(sellerId);
//                            campaign.setCountryId(countryId);
//
//                            //insert
//                            amzReportAdvertisingCampaignRepository.saveAndFlush(campaign);
//                            campaignId = campaign.getCampaignId();
//                        }
//
//                        ad.setCampaignId(campaignId);
//                    }

//						//列4 - Ad Group Name
                    cell = row.getCell(4);
                    String adGroupName = cell.getStringCellValue();
                    System.out.println(" adGroupName = " + adGroupName);

//                        if(StringUtils.isNotBlank(adGcampaignName)){
//                            System.out.println("campaignId  = " + campaignId);
//                            int adGroupId = 0;
//                            List<AmzReportAdvertisingAdgroup> adgroupList =   amzReportAdvertisingAdgroupRepository.findByAdgroupNameAndCampaignId(adGroupName,ad.getCampaignId());
//
//                            if(adgroupList.isEmpty()){
//                                //insert
//                                ad.setAdgroupName(adGroupName);
//                                amzReportAdvertisingAdgroupRepository.saveAndFlush(ad);
//                                adGroupId = ad.getAdgroupId();
//
//
//                            }else{
//                                //get
//                                adGroupId = adgroupList.get(0).getAdgroupId();
//                            }
//
//                            searchTerm.setAdgroupId(adGroupId);
//                        }

                    //						//列5 - Targeting
                    cell = row.getCell(5);
                    String targeting = cell.getStringCellValue();
                    System.out.println(" targeting = " + targeting);


                    if (StringUtils.isNotBlank(targeting)) {
                        int targetingId = 0;
                        List<AmzReportAdvertisingTargeting> targetingList = amzReportAdvertisingTargetingRepository.findByTargetingNameAndSellerIdAndCountryId(targeting, sellerId, countryId);
                        if (targetingList.size() <= 0) {
                            //insert
                            AmzReportAdvertisingTargeting advertisingTargeting = new AmzReportAdvertisingTargeting();
                            advertisingTargeting.setCountryId(countryId);
                            advertisingTargeting.setSellerId(sellerId);
                            advertisingTargeting.setTargetingName(targeting);

                            amzReportAdvertisingTargetingRepository.saveAndFlush(advertisingTargeting);

                            targetingId = advertisingTargeting.getTargetingId();

                        } else {
                            //get
                            targetingId = targetingList.get(0).getTargetingId();
                        }
                        searchTerm.setTargetingId(targetingId);
                    }


                    //列6 - Match Type
                    cell = row.getCell(6);
                    String matchTypeStr = cell.getStringCellValue();
//                    System.out.println(" match_type_str = " + match_type_str);
                    if (StringUtils.isNotBlank(matchTypeStr)) {
                        int matchTypeStrId = 0;
                        List<AmzReportAdvertisingMatchType> matchTypeList = amzReportAdvertisingMatchTypeRepository.findByMatchTypeName(matchTypeStr);
                        if (matchTypeList.size() <= 0) {
                            //insert
                            AmzReportAdvertisingMatchType matchType = new AmzReportAdvertisingMatchType();
                            matchType.setMatchTypeName(matchTypeStr);
                            amzReportAdvertisingMatchTypeRepository.saveAndFlush(matchType);

                            matchTypeStrId = matchType.getMatchTypeId();
                        } else {
                            //get
                            matchTypeStrId = matchTypeList.get(0).getMatchTypeId();

                        }
                        searchTerm.setMatchTypeId(matchTypeStrId);
                    }


                    //列7 - Customer Search Term
                    cell = row.getCell(7);
                    String customer_search_term = cell.getStringCellValue();
                    System.out.println(" customer_search_term = " + customer_search_term);

                    searchTerm.setCustomerSearchTerm(customer_search_term);


                    //列8 - Impressions
                    cell = row.getCell(8);
//                        System.out.println(" impressions = " + cell.getStringCellValue());
                    int impressions = Integer.parseInt(ImportExcelUtil.getCellValue(cell));
                    System.out.println(" impressions = " + impressions);

                    searchTerm.setImpressions(impressions);

                    //列9 - Clicks
                    cell = row.getCell(9);
                    int clicks = Integer.parseInt(ImportExcelUtil.getCellValue(cell));
                    System.out.println(" clicks = " + clicks);
                    searchTerm.setClicks(clicks);

                    //列10 - Click-Thru Rate (CTR)
                    cell = row.getCell(10);
                    BigDecimal click_thru_rate =
                            StringUtils.isNotBlank(ImportExcelUtil.getCellValue(cell)) ?
                                    BigDecimal.valueOf(Double.parseDouble(ImportExcelUtil.getCellValue(cell))) : BigDecimal.ZERO;
                    System.out.println(" click_thru_rate = " + click_thru_rate);
                    searchTerm.setClickThruRate(click_thru_rate);

                    //列11 - Cost Per Click (CPC)
                    cell = row.getCell(11);
                    BigDecimal cost_per_click = StringUtils.isNotBlank(ImportExcelUtil.getCellValue(cell)) ?
                            BigDecimal.valueOf(Double.parseDouble(ImportExcelUtil.getCellValue(cell))) : BigDecimal.ZERO;
                    System.out.println(" cost_per_click = " + cost_per_click);
                    searchTerm.setCostPerClick(cost_per_click);

                    //列12 - Spend
                    cell = row.getCell(12);
                    BigDecimal spend = StringUtils.isNotBlank(ImportExcelUtil.getCellValue(cell)) ?
                            BigDecimal.valueOf(Double.parseDouble(ImportExcelUtil.getCellValue(cell))) : BigDecimal.ZERO;
                    System.out.println(" spend = " + spend);
                    searchTerm.setSpend(spend);

                    //列13 - 14 Day Total Sales
                    cell = row.getCell(13);
                    BigDecimal sales_14day_total = StringUtils.isNotBlank(ImportExcelUtil.getCellValue(cell)) ?
                            BigDecimal.valueOf(Double.parseDouble(ImportExcelUtil.getCellValue(cell))) : BigDecimal.ZERO;
                    System.out.println(" sales_14day_total = " + sales_14day_total);
                    searchTerm.setSales14day(sales_14day_total);

                    //列14 - Total Advertising Cost of Sales (ACoS)
                    cell = row.getCell(14);
                    BigDecimal cost_of_sales = StringUtils.isNotBlank(ImportExcelUtil.getCellValue(cell)) ?
                            BigDecimal.valueOf(Double.parseDouble(ImportExcelUtil.getCellValue(cell))) : BigDecimal.ZERO;
                    System.out.println(" cost_of_sales = " + cost_of_sales);
                    searchTerm.setCostOfSales(cost_of_sales);

                    //列15 - Total Return on Advertising Spend (RoAS)
                    cell = row.getCell(15);
                    BigDecimal return_on_advertising_spend = StringUtils.isNotBlank(ImportExcelUtil.getCellValue(cell)) ?
                            BigDecimal.valueOf(Double.parseDouble(ImportExcelUtil.getCellValue(cell))) : BigDecimal.ZERO;
                    System.out.println(" return_on_advertising_spend = " + return_on_advertising_spend);
                    searchTerm.setReturnOnAdvertisingSpend(return_on_advertising_spend);


                    //列14 - 14 Day Total Orders (#)
                    cell = row.getCell(16);
                    Integer order_14day = Integer.parseInt(ImportExcelUtil.getCellValue(cell));
                    System.out.println(" order_14day = " + order_14day);
                    searchTerm.setOrder14day(order_14day);

                    //列15 - 14 Day Total Units (#)
                    cell = row.getCell(17);
                    Integer order_units_14day = Integer.parseInt(ImportExcelUtil.getCellValue(cell));
                    System.out.println(" order_units_14day = " + order_units_14day);
                    searchTerm.setOrderUnits14day(order_units_14day);

                    //列16 - 14 Day Conversion Rate
                    cell = row.getCell(18);
                    BigDecimal conversion_rate_14day = StringUtils.isNotBlank(ImportExcelUtil.getCellValue(cell)) ?
                            BigDecimal.valueOf(Double.parseDouble(ImportExcelUtil.getCellValue(cell))) : BigDecimal.ZERO;
                    System.out.println(" conversion_rate_14day = " + conversion_rate_14day);
                    searchTerm.setConversionRate14day(conversion_rate_14day);

                    //break;

                    int adgroupId = 0;
                    List<AmzReportAdvertisingCampaign> campaign_list = amzReportAdvertisingCampaignRepository.findByCampaignNameAndSellerIdAndCountryId(adGcampaignName, sellerId, countryId);
                    if (campaign_list.size() > 0) {
                        //存在Campaign
                        AmzReportAdvertisingCampaign campaign = campaign_list.get(0);
                        List<AmzReportAdvertisingAdgroup> adGroup_list = amzReportAdvertisingAdgroupRepository.findByAdgroupNameAndCampaignId(adGroupName, campaign.getCampaignId());
                        if (adGroup_list.size() > 0) {
                            adgroupId = adGroup_list.get(0).getAdgroupId();
                        } else {
                            //创建AdGroup
                            AmzReportAdvertisingAdgroup adGroup = new AmzReportAdvertisingAdgroup();
                            System.out.println("--- CampaignId = " + campaign.getCampaignId());
                            adGroup.setCampaignId(campaign.getCampaignId());
                            adGroup.setAdgroupName(adGroupName);
                            amzReportAdvertisingAdgroupRepository.saveAndFlush(adGroup);

                            adgroupId = adGroup.getAdgroupId();
                        }
                    } else {
                        //创建campaign
                        AmzReportAdvertisingCampaign campaign = new AmzReportAdvertisingCampaign();
                        campaign.setCampaignName(adGcampaignName);
                        campaign.setCountryId(countryId);
                        campaign.setSellerId(sellerId);
                        amzReportAdvertisingCampaignRepository.saveAndFlush(campaign);

                        //创建AdGroup
                        AmzReportAdvertisingAdgroup adGroup = new AmzReportAdvertisingAdgroup();
                        System.out.println("--- CampaignId = " + campaign.getCampaignId());
                        adGroup.setCampaignId(campaign.getCampaignId());
                        adGroup.setAdgroupName(adGroupName);
                        amzReportAdvertisingAdgroupRepository.saveAndFlush(adGroup);

                        adgroupId = adGroup.getAdgroupId();
                    }

                    searchTerm.setAdgroupId(adgroupId);

//                    searchTerm.setAutoId(i);
                    amzReportSponsoredBrandsSearchTermRepository.saveAndFlush(searchTerm);
                    System.out.println("======== autoId " + searchTerm.getAutoId());
                }

                i++;
            }

            System.out.println("插入 成功 ");
            resultBean.setMsg("成功");
        } catch (Exception e) {
            resultBean.setMsg("失败");
            System.out.println("出现错误");
            resultBean.setCode(500);
            e.printStackTrace();
        }

        return resultBean;

    }


    @Transactional
    public ResultBean saveSheet1(Sheet sheet0) {
        ResultBean resultBean = new ResultBean();

        Iterator<Row> rowIterator = sheet0.iterator();

        int i = 1;
        try {


            int countryId = 0;
            int sellerId = 3;

            while (rowIterator.hasNext()) {
                //  System.out.println("=====================");
                Row row = rowIterator.next();

                if (StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(0)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(1)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(2)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(3)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(4)))

                ) {
                    break;
                }

                if (i > 1) {
//                if (i > 48433) {
                    System.out.println("=========== i " + i);

                   // AmzReportAdvertisingAdgroup ad = new AmzReportAdvertisingAdgroup();


                    Cell cell = null;
                    //列0 - Date
                    cell = row.getCell(0);
                    Date recordDate = cell.getDateCellValue();
                    //              String recordDate =   ImportExcelUtil.getCellValue(cell) ;
//                    System.out.println(" Date = " +recordDate);
//                    searchTerm.setRecordDate(recordDate);

                    //						//列1 - Portfolio name
                    cell = row.getCell(1);
                    String portfolio_name = cell.getStringCellValue();
                    System.out.println(" portfolio_name = " + portfolio_name);
//						//列2 - Currency
                    cell = row.getCell(2);
                    String currency = cell.getStringCellValue();

                    if ("CAD".equals(currency)) {
                        countryId = 8;
                    } else if ("USD".equals(currency)) {
                        countryId = 7;
                    } else if ("EUR".equals(currency)) {
                        countryId = 1;
                        sellerId = 5;
                    }

                    System.out.println(" currency = " + currency);

                    //列3 - Campaign Name
                    cell = row.getCell(3);
                    String adGcampaignName = cell.getStringCellValue();
                    System.out.println(" adGcampaignName = " + adGcampaignName);
//                        int campaignId = 0;
//                        List<AmzReportAdvertisingCampaign> advertisingCampaignList =  amzReportAdvertisingCampaignRepository.findByCampaignNameAndSellerIdAndCountryId(adGcampaignName,sellerId,countryId);
//                    if(StringUtils.isNotBlank(adGcampaignName)){
//                        AmzReportAdvertisingCampaign campaign = new AmzReportAdvertisingCampaign();
//                        if(!advertisingCampaignList.isEmpty()){
//                            //拿id
//                            campaignId = amzReportAdvertisingCampaignRepository.findByCampaignNameAndSellerIdAndCountryId(adGcampaignName,sellerId,countryId).get(0).getCampaignId();
//
//                        }else{
//
//                            campaign.setCampaignName(adGcampaignName);
//                            campaign.setSellerId(sellerId);
//                            campaign.setCountryId(countryId);
//
//                            //insert
//                            amzReportAdvertisingCampaignRepository.saveAndFlush(campaign);
//                            campaignId = campaign.getCampaignId();
//                        }
//
//                        ad.setCampaignId(campaignId);
//                    }

//						//列4 - Ad Group Name
                    cell = row.getCell(4);
                    String adGroupName = cell.getStringCellValue();
                    System.out.println(" adGroupName = " + adGroupName);

//                        if(StringUtils.isNotBlank(adGcampaignName)){
//                            System.out.println("campaignId  = " + campaignId);
//                            int adGroupId = 0;
//                            List<AmzReportAdvertisingAdgroup> adgroupList =   amzReportAdvertisingAdgroupRepository.findByAdgroupNameAndCampaignId(adGroupName,ad.getCampaignId());
//
//                            if(adgroupList.isEmpty()){
//                                //insert
//                                ad.setAdgroupName(adGroupName);
//                                amzReportAdvertisingAdgroupRepository.saveAndFlush(ad);
//                                adGroupId = ad.getAdgroupId();
//
//
//                            }else{
//                                //get
//                                adGroupId = adgroupList.get(0).getAdgroupId();
//                            }
//
//                            searchTerm.setAdgroupId(adGroupId);
//                        }

                    //						//列5 - Targeting
                    cell = row.getCell(5);
                    String targeting = cell.getStringCellValue();
                    System.out.println(" targeting = " + targeting);


                    int targetingId = 0;
                    if (StringUtils.isNotBlank(targeting)) {
                        List<AmzReportAdvertisingTargeting> targetingList = amzReportAdvertisingTargetingRepository.findByTargetingNameAndSellerIdAndCountryId(targeting, sellerId, countryId);
                        if (targetingList.size() <= 0) {
                            //insert
                            AmzReportAdvertisingTargeting advertisingTargeting = new AmzReportAdvertisingTargeting();
                            System.out.println(" countryId == " + countryId + " ---  seller_id" + sellerId);
                            advertisingTargeting.setCountryId(countryId);
                            advertisingTargeting.setSellerId(sellerId);
                            advertisingTargeting.setTargetingName(targeting);

                            amzReportAdvertisingTargetingRepository.saveAndFlush(advertisingTargeting);

                            targetingId = advertisingTargeting.getTargetingId();

                        } else {
                            //get
                            targetingId = targetingList.get(0).getTargetingId();
                        }
                       // searchTerm.setTargetingId(targetingId);
                    }


                    //列6 - Match Type
                    cell = row.getCell(6);
                    String matchTypeStr = cell.getStringCellValue();
//                    System.out.println(" match_type_str = " + match_type_str);
                    int matchTypeStrId = 0;
                    if (StringUtils.isNotBlank(matchTypeStr)) {

                        List<AmzReportAdvertisingMatchType> matchTypeList = amzReportAdvertisingMatchTypeRepository.findByMatchTypeName(matchTypeStr);
                        if (matchTypeList.isEmpty()) {
                            //insert
                            AmzReportAdvertisingMatchType matchType = new AmzReportAdvertisingMatchType();
                            matchType.setMatchTypeName(matchTypeStr);
                            amzReportAdvertisingMatchTypeRepository.saveAndFlush(matchType);

                            matchTypeStrId = matchType.getMatchTypeId();
                        } else {
                            //get
                            matchTypeStrId = matchTypeList.get(0).getMatchTypeId();

                        }
//                        searchTerm.setMatchTypeId(matchTypeStrId);
                    }


                    //列7 - Customer Search Term
                    cell = row.getCell(7);
                    String customer_search_term = cell.getStringCellValue();
//                    System.out.println(" customer_search_term = " + customer_search_term);

//                    searchTerm.setCustomerSearchTerm(customer_search_term);


                    //列8 - Impressions
                    cell = row.getCell(8);
//                        System.out.println(" impressions = " + cell.getStringCellValue());
                    int impressions = Integer.parseInt(ImportExcelUtil.getCellValue(cell));
//                    System.out.println(" impressions = " + impressions);

                   // searchTerm.setImpressions(impressions);

                    //列9 - Clicks
                    cell = row.getCell(9);
                    int clicks = Integer.parseInt(ImportExcelUtil.getCellValue(cell));
//                    System.out.println(" clicks = " + clicks);
                  //  searchTerm.setClicks(clicks);

                    //列10 - Click-Thru Rate (CTR)
                    cell = row.getCell(10);
                    BigDecimal click_thru_rate =
                            StringUtils.isNotBlank(ImportExcelUtil.getCellValue(cell)) ?
                                    BigDecimal.valueOf(Double.parseDouble(ImportExcelUtil.getCellValue(cell))) : BigDecimal.ZERO;
//                    System.out.println(" click_thru_rate = " + click_thru_rate);
                  //  searchTerm.setClickThruRate(click_thru_rate);

                    //列11 - Cost Per Click (CPC)
                    cell = row.getCell(11);
                    BigDecimal cost_per_click = StringUtils.isNotBlank(ImportExcelUtil.getCellValue(cell)) ?
                            BigDecimal.valueOf(Double.parseDouble(ImportExcelUtil.getCellValue(cell))) : BigDecimal.ZERO;
//                    System.out.println(" cost_per_click = " + cost_per_click);
//                    searchTerm.setCostPerClick(cost_per_click);

                    //列12 - Spend
                    cell = row.getCell(12);
                    BigDecimal spend = StringUtils.isNotBlank(ImportExcelUtil.getCellValue(cell)) ?
                            BigDecimal.valueOf(Double.parseDouble(ImportExcelUtil.getCellValue(cell))) : BigDecimal.ZERO;
//                    System.out.println(" spend = " + spend);
//                    searchTerm.setSpend(spend);

                    //列13 - 7 Day Total Sales
                    cell = row.getCell(13);
                    BigDecimal sales_7day_total = StringUtils.isNotBlank(ImportExcelUtil.getCellValue(cell)) ?
                            BigDecimal.valueOf(Double.parseDouble(ImportExcelUtil.getCellValue(cell))) : BigDecimal.ZERO;
//                    System.out.println(" sales_7day_total = " + sales_7day_total);
//                    searchTerm.setSales7day(sales_7day_total);

                    //列7 - Total Advertising Cost of Sales (ACoS)
                    cell = row.getCell(14);
                    BigDecimal cost_of_sales = StringUtils.isNotBlank(ImportExcelUtil.getCellValue(cell)) ?
                            BigDecimal.valueOf(Double.parseDouble(ImportExcelUtil.getCellValue(cell))) : BigDecimal.ZERO;
//                    System.out.println(" cost_of_sales = " + cost_of_sales);
//                    searchTerm.setCostOfSales(cost_of_sales);

                    //列15 - Total Return on Advertising Spend (RoAS)
                    cell = row.getCell(15);
                    BigDecimal return_on_advertising_spend = StringUtils.isNotBlank(ImportExcelUtil.getCellValue(cell)) ?
                            BigDecimal.valueOf(Double.parseDouble(ImportExcelUtil.getCellValue(cell))) : BigDecimal.ZERO;
//                    System.out.println(" return_on_advertising_spend = " + return_on_advertising_spend);
//                    searchTerm.setReturnOnAdvertisingSpend(return_on_advertising_spend);


                    //列7 - 7 Day Total Orders (#)
                    cell = row.getCell(16);
                    Integer order_7day = Integer.parseInt(ImportExcelUtil.getCellValue(cell));
//                    System.out.println(" order_7day = " + order_7day);
//                    searchTerm.setOrder7day(order_7day);
//
                    //列15 - 7 Day Total Units (#)
                    cell = row.getCell(17);
                    Integer order_units_7day = Integer.parseInt(ImportExcelUtil.getCellValue(cell));
//                    System.out.println(" order_units_7day = " + order_units_7day);
//                    searchTerm.setOrderUnits7day(order_units_7day);

                    //列16 - 7 Day Conversion Rate
                    cell = row.getCell(18);
                    BigDecimal conversion_rate_7day = StringUtils.isNotBlank(ImportExcelUtil.getCellValue(cell)) ?
                            BigDecimal.valueOf(Double.parseDouble(ImportExcelUtil.getCellValue(cell))) : BigDecimal.ZERO;
//                    System.out.println(" conversion_rate_7day = " + conversion_rate_7day);
//                    searchTerm.setConversionRate7day(conversion_rate_7day);

                    //break;

                    int adgroupId = 0;
                    List<AmzReportAdvertisingCampaign> campaign_list = amzReportAdvertisingCampaignRepository.findByCampaignNameAndSellerIdAndCountryId(adGcampaignName, sellerId, countryId);
                    if (campaign_list.size() > 0) {
                        //存在Campaign
                        AmzReportAdvertisingCampaign campaign = campaign_list.get(0);
                        List<AmzReportAdvertisingAdgroup> adGroup_list = amzReportAdvertisingAdgroupRepository.findByAdgroupNameAndCampaignId(adGroupName, campaign.getCampaignId());
                        if (adGroup_list.size() > 0) {
                            adgroupId = adGroup_list.get(0).getAdgroupId();
                        } else {
                            //创建AdGroup
                            AmzReportAdvertisingAdgroup adGroup = new AmzReportAdvertisingAdgroup();
                            System.out.println("--- CampaignId = " + campaign.getCampaignId());
                            adGroup.setCampaignId(campaign.getCampaignId());
                            adGroup.setAdgroupName(adGroupName);
                            amzReportAdvertisingAdgroupRepository.saveAndFlush(adGroup);

                            adgroupId = adGroup.getAdgroupId();
                        }
                    } else {
                        //创建campaign
                        AmzReportAdvertisingCampaign campaign = new AmzReportAdvertisingCampaign();
                        campaign.setCampaignName(adGcampaignName);
                        campaign.setCountryId(countryId);
                        campaign.setSellerId(sellerId);
                        amzReportAdvertisingCampaignRepository.saveAndFlush(campaign);

                        //创建AdGroup
                        AmzReportAdvertisingAdgroup adGroup = new AmzReportAdvertisingAdgroup();
                        System.out.println("--- CampaignId = " + campaign.getCampaignId());
                        adGroup.setCampaignId(campaign.getCampaignId());
                        adGroup.setAdgroupName(adGroupName);
                        amzReportAdvertisingAdgroupRepository.saveAndFlush(adGroup);

                        adgroupId = adGroup.getAdgroupId();
                    }


                    AmzReportSponsoredProductsSearchTerm sponsoredProductsSearchTerm =new AmzReportSponsoredProductsSearchTerm();
                    AmzReportSponsoredProductsSearchTermPK sponsoredProductsSearchTermPK=new AmzReportSponsoredProductsSearchTermPK();

                    sponsoredProductsSearchTermPK.setRecordDate(recordDate);
                    sponsoredProductsSearchTermPK.setAdgroupId(adgroupId);
                    sponsoredProductsSearchTermPK.setCustomerSearchTerm(customer_search_term);
                    sponsoredProductsSearchTermPK.setClickThruRate(click_thru_rate);
                    sponsoredProductsSearchTermPK.setSpend(spend);
                    sponsoredProductsSearchTermPK.setSales7day(sales_7day_total);

                    sponsoredProductsSearchTerm.setId(sponsoredProductsSearchTermPK);
                    sponsoredProductsSearchTerm.setTargetingId(targetingId);
                    sponsoredProductsSearchTerm.setMatchTypeId(matchTypeStrId);
                    sponsoredProductsSearchTerm.setImpressions(impressions);
                    sponsoredProductsSearchTerm.setClicks(clicks);
                    sponsoredProductsSearchTerm.setCostPerClick(cost_per_click);
                    sponsoredProductsSearchTerm.setCostOfSales(cost_of_sales);
                    sponsoredProductsSearchTerm.setReturnOnAdvertisingSpend(return_on_advertising_spend);
                    sponsoredProductsSearchTerm.setOrder7day(order_7day);
                    sponsoredProductsSearchTerm.setOrderUnits7day(order_units_7day);
                    sponsoredProductsSearchTerm.setConversionRate7day(conversion_rate_7day);

//                    searchTerm.setAutoId(i);
                    amzReportSponsoredProductsSearchTermRepository.save(sponsoredProductsSearchTerm);
//                    System.out.println("=== autoId = " + searchTerm.getAutoId());
                }

                i++;
            }

            System.out.println("插入 成功 ");
            resultBean.setMsg("成功");
        } catch (Exception e) {
            resultBean.setMsg(e.getLocalizedMessage());
            System.out.println("出现错误");
            resultBean.setCode(500);
            e.printStackTrace();
        }

        return resultBean;

    }

    @ApiOperation(value = "上传广告文件")
    @RequestMapping(value = "/PurchasedProduct", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean updateReportPurchasedProduct(@RequestParam(value = "file")
                                                           MultipartFile file) throws IOException {
        ResultBean resultBean = new ResultBean();

        log.info("============= ");
        try {

            if (null != file) {
                System.out.println("=== file ==" + file);
            }

            ImportExcelUtil.checkFile(file);
            //解析 excel

            Workbook workbook = ImportExcelUtil.getWorkBook(file);

            //数据的行数
            int numberOfSheets = workbook.getNumberOfSheets();
            System.out.println("numberOfSheets = " + numberOfSheets);

            Sheet sheet0 = workbook.getSheetAt(0);


            //保存第一页数据
              resultBean = savePurchasedProduct(sheet0);

        } catch (Exception e) {
            e.printStackTrace();
            resultBean.setMsg("Update failed！");
            log.info(e.getMessage());

        }
        return resultBean;
    }

    @Transactional
    public ResultBean savePurchasedProduct(Sheet sheet0) {
        ResultBean resultBean = new ResultBean();

        Iterator<Row> rowIterator =  sheet0.iterator();

        int i = 1;
        try {


            int countryId = 0;
            int sellerId = 3;

            while (rowIterator.hasNext()){
                //  System.out.println("=====================");
                Row row = rowIterator.next();

                if(StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(0)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(1)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(2)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(3)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(4)))

                ){
                    break;
                }

                if(i > 1){
                    System.out.println("=========== i " + i);
                    AmzReportSponsoredProductPurchasedProduct purchasedProduct = new AmzReportSponsoredProductPurchasedProduct();
                    AmzReportAdvertisingAdgroup ad = new AmzReportAdvertisingAdgroup();


                    Cell cell = null;
                    //列0 - Date
                    cell = row.getCell(0);
                    Date recordDate = cell.getDateCellValue();
                    //              String recordDate =   ImportExcelUtil.getCellValue(cell) ;
                    System.out.println(" Date = " +recordDate);
                    purchasedProduct.setRecordDate(recordDate);

                    //						//列1 - Portfolio name
                    cell = row.getCell(1);
                    String portfolio_name = cell.getStringCellValue();
                    System.out.println(" portfolio_name = " + portfolio_name);
//						//列2 - Currency
                    cell = row.getCell(2);
                    String currency = cell.getStringCellValue();

                    if("CAD".equals(currency)){
                        countryId = 8;
                    }else if("USD".equals(currency)){
                        countryId = 7;
                    }else if("EUR".equals(currency)){
                        countryId = 1;
                        sellerId = 5;
                    }

                    System.out.println(" currency = " + currency);

                    //列3 - Campaign Name
                    cell = row.getCell(3);
                    String adGcampaignName = cell.getStringCellValue();
                    System.out.println(" adGcampaignName = " + adGcampaignName);

                    //列4 - Ad Group Name
                    cell = row.getCell(4);
                    String adGroupName = cell.getStringCellValue();
                    System.out.println(" adGroupName = " + adGroupName);

                    //列5 - Advertised SKU
                    cell = row.getCell(5);
                    String advertised_sku = cell.getStringCellValue();
                    System.out.println(" advertised_sku = " + advertised_sku);
                    if(StringUtils.isNotBlank(advertised_sku)){
                       Integer productId =  productMapperEx.findProductIdBySku(advertised_sku);
                        productId  = (productId == null ? 0 : productId);

                        Integer skuId = productMapperEx.findSkuIdBySku(advertised_sku);
                        skuId  = (skuId == null ? 0 : skuId);
                       purchasedProduct.setProductId(productId);
                       purchasedProduct.setSellerSkuid(skuId);
                    }

                    //列6 - Advertised ASIN
                    cell = row.getCell(6);
                    String advertised_asin = cell.getStringCellValue();
                    System.out.println(" advertised_asin = " + advertised_asin);
                    if(StringUtils.isNotBlank(advertised_sku)){

                        Integer asinId = productMapperEx.findAsinIdByAsin(advertised_asin);
                        asinId = (asinId == null ? 0 : asinId);

                        purchasedProduct.setAdvertisedAsinId(asinId);
                    }



                    //列7 - Targeting
                    cell = row.getCell(7);
                    String targeting = cell.getStringCellValue();
                    System.out.println(" targeting = " + targeting);





                    //列8 - Match Type
                    cell = row.getCell(8);
                    String matchTypeStr = cell.getStringCellValue();
//                    System.out.println(" match_type_str = " + match_type_str);
                    if(StringUtils.isNotBlank(matchTypeStr)){
                        int matchTypeStrId = 0;
                        List<AmzReportAdvertisingMatchType>  matchTypeList = amzReportAdvertisingMatchTypeRepository.findByMatchTypeName(matchTypeStr);
                        if(matchTypeList.isEmpty()){
                            //insert
                            AmzReportAdvertisingMatchType matchType = new AmzReportAdvertisingMatchType();
                            matchType.setMatchTypeName(matchTypeStr);
                            amzReportAdvertisingMatchTypeRepository.saveAndFlush(matchType);

                            matchTypeStrId = matchType.getMatchTypeId();
                        }else {
                            //get
                            matchTypeStrId = matchTypeList.get(0).getMatchTypeId();

                        }
                        purchasedProduct.setMatchTypeId(matchTypeStrId);
                    }


                    //列9 - Purchased ASIN
                    cell = row.getCell(9);
                    String purchased_asin = cell.getStringCellValue();
                    System.out.println(" purchased_asin = " + purchased_asin);
                    if(StringUtils.isNotBlank(advertised_sku)){

                        Integer asinId = productMapperEx.findAsinIdByAsin(purchased_asin);
                        asinId = (asinId == null ? 0 : asinId);

                        purchasedProduct.setPurchasedAsinId(asinId);
                    }


                    //列10 - 7 Day Other SKU Units (#)
                    cell = row.getCell(10);
                    Integer other_order_units_7day =  Integer.parseInt(ImportExcelUtil.getCellValue(cell));
                    System.out.println(" other_order_units_7day = " + other_order_units_7day);
                    purchasedProduct.setOtherSkuUnits7day(other_order_units_7day);

                    //列11 - 7 Day Other SKU Orders (#)
                    cell = row.getCell(11);
                    Integer other_order_sku_7day = Integer.parseInt(ImportExcelUtil.getCellValue(cell));
                    System.out.println(" other_order_sku_7day = " + other_order_sku_7day);
                    purchasedProduct.setOhterSkuOrder7day(other_order_sku_7day);



                    //列16 - 7 Day Other SKU Sales
                    cell = row.getCell(12);
                    BigDecimal other_order_sku_7day_sales =StringUtils.isNotBlank(ImportExcelUtil.getCellValue(cell)) ?
                            BigDecimal.valueOf(Double.parseDouble(ImportExcelUtil.getCellValue(cell))) : BigDecimal.ZERO;
//                    System.out.println(" conversion_rate_7day = " + conversion_rate_7day);
                    purchasedProduct.setOtherSkuSales7day(other_order_sku_7day_sales);

                    //break;

                    int adgroupId=0;
                    List<AmzReportAdvertisingCampaign> campaign_list = amzReportAdvertisingCampaignRepository.findByCampaignNameAndSellerIdAndCountryId(adGcampaignName, sellerId, countryId);
                    if (campaign_list.size()>0) {
                        //存在Campaign
                        AmzReportAdvertisingCampaign campaign = campaign_list.get(0);
                        List<AmzReportAdvertisingAdgroup> adGroup_list = amzReportAdvertisingAdgroupRepository.findByAdgroupNameAndCampaignId(adGroupName, campaign.getCampaignId());
                        if (adGroup_list.size()>0) {
                            adgroupId=adGroup_list.get(0).getAdgroupId();
                        }else {
                            //创建AdGroup
                            AmzReportAdvertisingAdgroup adGroup=new AmzReportAdvertisingAdgroup();
                            System.out.println("--- CampaignId = " + campaign.getCampaignId());
                            adGroup.setCampaignId(campaign.getCampaignId());
                            adGroup.setAdgroupName(adGroupName);
                            amzReportAdvertisingAdgroupRepository.saveAndFlush(adGroup);

                            adgroupId=adGroup.getAdgroupId();
                        }
                    }else {
                        //创建campaign
                        AmzReportAdvertisingCampaign campaign = new AmzReportAdvertisingCampaign();
                        campaign.setCampaignName(adGcampaignName);
                        campaign.setCountryId(countryId);
                        campaign.setSellerId(sellerId);
                        amzReportAdvertisingCampaignRepository.saveAndFlush(campaign);

                        //创建AdGroup
                        AmzReportAdvertisingAdgroup adGroup=new AmzReportAdvertisingAdgroup();
                        System.out.println("--- CampaignId = " + campaign.getCampaignId());
                        adGroup.setCampaignId(campaign.getCampaignId());
                        adGroup.setAdgroupName(adGroupName);
                        amzReportAdvertisingAdgroupRepository.saveAndFlush(adGroup);

                        adgroupId = adGroup.getAdgroupId();
                    }

                    purchasedProduct.setAdgroupId(adgroupId);

                    //purchasedProduct.setAutoId(i);
                    amzReportSponsoredProductPurchasedProductRepository.saveAndFlush(purchasedProduct);

                    System.out.println("======= autoId = " + purchasedProduct.getAutoId());
                }

                i++;
            }

            System.out.println("插入 成功 ");
            resultBean.setMsg("成功");
        }catch (Exception e){
            resultBean.setMsg("失败");
            System.out.println("出现错误");
            resultBean.setCode(500);
            e.printStackTrace();
        }

        return resultBean;

    }

}

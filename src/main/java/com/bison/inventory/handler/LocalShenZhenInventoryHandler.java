package com.bison.inventory.handler;


import com.bison.inventory.constant.ShenZhenLocalInventoryCertificateType;
import com.bison.inventory.constant.ShenZhenLocalInventoryOutInBoundType;
import com.bison.inventory.mapper.jpa.ProductMapper;
import com.bison.inventory.mapper.mybatis.AmzShenZhenLocalInventroyTempEx;
import com.bison.inventory.mapper.mybatis.ShenZhenLocalInventroyMapperSkuEx;
import com.bison.inventory.mapper.mybatis.ShenZhenLocalOutInBoundInventroyMapperEx;
import com.bison.inventory.pojo.AmzShenZhenLocalInventroyTemp;
import com.bison.inventory.pojo.Product;
import com.bison.inventory.pojo.ShenZhenLocalInventroySku;
import com.bison.inventory.pojo.ShenZhenLocalOutInBoundInventroy;
import com.bison.inventory.util.excel.ImportExcelUtil;
import com.bison.inventory.util.excel.entity.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author Lee
 * @date 2019-10-27
 */
@Api(description = "本地深圳库存", tags = "LocalShenZhenInventoryHandler")
@RestController
@RequestMapping("/localShenZhenInventoryHandler")
public class LocalShenZhenInventoryHandler {


    @Autowired
    ProductMapper productMapper;

    @Autowired
    ShenZhenLocalOutInBoundInventroyMapperEx shenZhenLocalOutInBoundInventroyMapperEx;

    @Autowired
    ShenZhenLocalInventroyMapperSkuEx shenZhenLocalInventroyMapperSkuEx;

    @Autowired
    AmzShenZhenLocalInventroyTempEx amzShenZhenLocalInventroyTempEx;

    List<ShenZhenLocalOutInBoundInventroy> localInventoryList;

    Map<String,Integer> map;


    @ApiOperation(value = "上传深圳本地仓库存文件")
    @RequestMapping(value = "/uploadShenZhenLocalInventoryFile", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResultBean updateByFile(@RequestParam(value = "file")
                                           MultipartFile file) throws IOException {
        ResultBean resultBean = new ResultBean();
        try {
            if(null != file) {
                System.out.println("=== file ==" + file);
            }

            //初始化 产品 map
            map =  getProductMap();

            Long time=System.currentTimeMillis();

            ImportExcelUtil.checkFile(file);
            //解析 excel
            ImportExcelUtil example = new ImportExcelUtil();



//        example.processOneSheet("/home/leaderment/桌面/inventory/深圳仓库出入库明细录入表格01月11日.xlsx");
//            example.processOneSheet("c:/Users/31719/Desktop/深圳仓库出入库明细录入表格01月14日.xlsx");
            example.processOneSheet(file.getOriginalFilename());
            ;

            Long endtime=System.currentTimeMillis();
            LinkedHashMap<String, String>  map=example.getRowContents();
            Iterator<Map.Entry<String, String>> it= map.entrySet().iterator();

            //初始化本地仓库存列表
            localInventoryList = new ArrayList<>(map.size());

            int count=0;
            String prePos="";
            while (it.hasNext()){
                Map.Entry<String, String> entry=(Map.Entry<String, String>)it.next();
                String pos=entry.getKey();
                if(!pos.substring(1).equals(prePos)){
                    prePos=pos.substring(1);
                    count++;
                }
                System.out.println(pos+";"+entry.getValue());

                boolean flag = saveDB(pos,entry.getValue());

                if(flag){
                    break;
                }
            }
            System.out.println("解析数据"+count+"条;耗时"+(endtime-time)/1000+"秒");

            System.out.println(" localInventoryList   size === " +  localInventoryList.size());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean.setMsg(e.getMessage());
            resultBean.setCode(500);
            return resultBean;
        }


        return resultBean;
    }




    private boolean saveDB(String pos, String value) throws ParseException {

        /**
         * 一般为 A B C D E F G H 等
         */
        String col = pos.substring(0,1);

        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        ShenZhenLocalOutInBoundInventroy shenZhenLocalOutInBoundInventroy = new ShenZhenLocalOutInBoundInventroy();

        switch (col){
            case "A" :
                //收货日期
//                Date date = new Date(Long.parseLong(value));
//                System.out.println("date = " + date);
                shenZhenLocalOutInBoundInventroy.setReceiptDate(simpleFormat.parse(value));
                break;

            case "B" :
                //出入库日期

//                Date date = new Date(Long.parseLong(value));
//                System.out.println("date = " + date);
                shenZhenLocalOutInBoundInventroy.setOutInBoundDate(simpleFormat.parse(value));

                break;

            case "C" :
                //送货单号
//                Date date = new Date(Long.parseLong(value));
//                System.out.println("date = " + date);
                shenZhenLocalOutInBoundInventroy.setDeliveryNoteNumber(value);

                break;

            case "D" :
                //送检单号
//                Date date = new Date(Long.parseLong(value));
                shenZhenLocalOutInBoundInventroy.setSendCheckNumber(value);

                break;

            case "E" :
                //出入库类型
                /**
                 * 出入库类型
                 */
                String outInBoundType =  value;

                int num = 0;
                switch (outInBoundType){
                    case ShenZhenLocalInventoryOutInBoundType
                            .IN_BOUND:
                        num = 1;
                        break;
                    case ShenZhenLocalInventoryOutInBoundType
                            .OUT_BOUND:
                        num = 2;
                        break;
                    case ShenZhenLocalInventoryOutInBoundType
                            .ADJUSTMENTINVENTORY_WAREHOUSE:
                        num = 3;
                        break;
                }
                shenZhenLocalOutInBoundInventroy.setOutInBoundTypeId(num);

                break;

            case "F" :
                //凭证类型
                String certificateType =  value;
                num = 0;
                switch (certificateType){

                    case ShenZhenLocalInventoryCertificateType.IN_BOUND_ORDER:
                        num = 1;
                        break;

                    case ShenZhenLocalInventoryCertificateType.OHTER_IN_BOUND_ORDER:
                        num = 2;
                        break;

                    case ShenZhenLocalInventoryCertificateType.OUT_BOUND:
                        num = 3;
                        break;

                    case ShenZhenLocalInventoryCertificateType.OUT_BOUND_ORDER:
                        num = 4;
                        break;
                    case ShenZhenLocalInventoryCertificateType.OHTER_OUT_BOUND_ORDER:
                        num = 5;
                        break;
                    case ShenZhenLocalInventoryCertificateType.MOVE_INVENTORY_ORDER:
                        num = 6;
                        break;
                    case ShenZhenLocalInventoryCertificateType.ADJUSTMENTINVENTORY_ORDER:
                        num = 7;
                        break;
                    case ShenZhenLocalInventoryCertificateType.RETURN_OUT_BOUND_ORDER:
                        num = 8;
                        break;
                    case ShenZhenLocalInventoryCertificateType.BALANCE_IN_BOUND:
                        num = 9;
                        break;
                }
                shenZhenLocalOutInBoundInventroy.setCertificateTypeId(num);


                break;

            case "G" :
                //凭证号
                shenZhenLocalOutInBoundInventroy.setCertificateNumber(value);

                break;

            case "H" :
                //sku
                shenZhenLocalOutInBoundInventroy.setSku(value);

                break;

            case "I" :
                //型号
                String modelNumber = value;
                Integer productId = map.get(modelNumber);
                shenZhenLocalOutInBoundInventroy.setProductId(productId);
                break;

            case "J" :
                //供应商
                shenZhenLocalOutInBoundInventroy.setSupplierAbbreviation(value);
                break;

            case "k" :
                //向别
                shenZhenLocalOutInBoundInventroy.setInventoryType(value);

                break;

            case "L" :
                //入库数量
                shenZhenLocalOutInBoundInventroy.setInBoundQuantity(Integer.parseInt(value));

                break;

            case "M" :
                //移仓数量（入）
                shenZhenLocalOutInBoundInventroy.setMoveInBoundQuantity(Integer.parseInt(value));
                break;

            case "N" :
                //出库数量
                shenZhenLocalOutInBoundInventroy.setOutBoundQuantity(Integer.parseInt(value));
                break;

            case "O" :
                //移仓数量（出）
                shenZhenLocalOutInBoundInventroy.setMoveOutBoundQuantity(Integer.parseInt(value));
                break;

            case "P" :
                //备注
                shenZhenLocalOutInBoundInventroy.setRemarks(value);

                break;
        }



        int row = Integer.parseInt(pos.substring(1,pos.length()));

        //判断是否结束
        String endStr = "#N/A";
        if(endStr.equalsIgnoreCase(value)){
            return true;
        }


        return false;
    }


    private Map<String, Integer> getProductMap() {

        List<Product> productList = productMapper.findAll();
        Map<String,Integer> map = new HashMap<String,Integer>(productList.size());

        for(Product product : productList){
            map.put(product.getProductModelNumber(),product.getProductId());
        }


        return map;
    }

    private ResultBean saveSheet4(Sheet sheet4, Map<String, Integer> map) {

        ResultBean resultBean = new ResultBean();

        Iterator<Row> rowIterator =  sheet4.iterator();



        int i = 1;
        try {
            while (rowIterator.hasNext()){
                //  System.out.println("=====================");


                Row row = rowIterator.next();

                // System.out.println("row 0 " + row.getCell(1).getStringCellValue());

                if (StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(0))) ||
                        StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(1))) ||
                        StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(2))) ||
                        StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(4)))
                ) {

                    break;
                }
                if(i > 1){
                    ShenZhenLocalInventroySku shenZhenLocalInventroySku = new ShenZhenLocalInventroySku();
                    System.out.println("111111111=====================" + i);

                    Integer productId = map.get(ImportExcelUtil.getCellValue(row.getCell(0)));
                    shenZhenLocalInventroySku.setProductId(productId);

                    /**
                     *  供应商
                     */
                    shenZhenLocalInventroySku.setSupplier(ImportExcelUtil.getCellValue(row.getCell(1)));
                    /**
                     * 向别
                     */
                    shenZhenLocalInventroySku.setCountryOrTraingType(ImportExcelUtil.getCellValue(row.getCell(2)));

                    /**
                     * sku
                     */
                    shenZhenLocalInventroySku.setSku(ImportExcelUtil.getCellValue(row.getCell(4)));


                    System.out.println("insert before = " + shenZhenLocalInventroySku);

                    shenZhenLocalInventroyMapperSkuEx.insert(shenZhenLocalInventroySku);
                }

                i++;
            }
            System.out.println("插入 成功 ");
            resultBean.setMsg("成功");
        }catch (Exception e){
            resultBean.setMsg("失败");
            System.out.println(" 出现错误 ");
            resultBean.setCode(500);
            e.printStackTrace();
        }

        return resultBean;
    }

 //   @Transactional
    public ResultBean saveSheet1(Sheet sheet1, Map<String, Integer> map) {

        ResultBean resultBean = new ResultBean();

        Iterator<Row> rowIterator =  sheet1.iterator();



        int i = 1;
        try {
            while (rowIterator.hasNext()){
                //  System.out.println("=====================");


                Row row = rowIterator.next();

                // System.out.println("row 0 " + row.getCell(1).getStringCellValue());

                if(StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(1)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(4)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(5)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(6)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(7)))

                ){
                    break;
                }

                if(i > 1){
                    ShenZhenLocalOutInBoundInventroy shenZhenLocalOutInBoundInventroy = new ShenZhenLocalOutInBoundInventroy();
                    System.out.println("111111111=====================" + i);

                    shenZhenLocalOutInBoundInventroy.setAutoId(i);
                    shenZhenLocalOutInBoundInventroy.setTransactionType(2);
                    /**
                     * 收货日期
                     */
                    shenZhenLocalOutInBoundInventroy.setReceiptDate(row.getCell(0) != null  ? row.getCell(0).getDateCellValue() : null);
                    /**
                     * 出
                     *
                     */
//                    Date date = row.getCell(1).getDateCellValue();
                    String dateStr = ImportExcelUtil.getCellValue(row.getCell(1));
                    Date date = StringUtils.isNotBlank(dateStr) ? HSSFDateUtil.getJavaDate(Double.parseDouble(dateStr)) : null;
                    System.out.println("出入库日期 = " + date );
                    shenZhenLocalOutInBoundInventroy.setOutInBoundDate(date);
                    /**
                     * 送货单号
                     */

                    shenZhenLocalOutInBoundInventroy.setDeliveryNoteNumber(ImportExcelUtil.getCellValue(row.getCell(2)));
                    /**
                     * 送检单号
                     */
                    shenZhenLocalOutInBoundInventroy.setSendCheckNumber(ImportExcelUtil.getCellValue(row.getCell(2)));


                    /**
                     * 出入库类型
                     */
                    String outInBoundType =  row.getCell(4).getStringCellValue();

                    int num = 0;
                    switch (outInBoundType){
                        case ShenZhenLocalInventoryOutInBoundType
                                .IN_BOUND:
                            num = 1;
                            break;
                        case ShenZhenLocalInventoryOutInBoundType
                                .OUT_BOUND:
                            num = 2;
                            break;
                        case ShenZhenLocalInventoryOutInBoundType
                                .ADJUSTMENTINVENTORY_WAREHOUSE:
                            num = 3;
                            break;
                    }
                    shenZhenLocalOutInBoundInventroy.setOutInBoundTypeId(num);


                    /**
                     * 凭证类型
                     */
                    String certificateType =  row.getCell(5).getStringCellValue();
                    num = 0;
                    switch (certificateType){

                        case ShenZhenLocalInventoryCertificateType.IN_BOUND_ORDER:
                            num = 1;
                            break;

                        case ShenZhenLocalInventoryCertificateType.OHTER_IN_BOUND_ORDER:
                            num = 2;
                            break;

                        case ShenZhenLocalInventoryCertificateType.OUT_BOUND:
                            num = 3;
                            break;

                        case ShenZhenLocalInventoryCertificateType.OUT_BOUND_ORDER:
                            num = 4;
                            break;
                        case ShenZhenLocalInventoryCertificateType.OHTER_OUT_BOUND_ORDER:
                            num = 5;
                            break;
                        case ShenZhenLocalInventoryCertificateType.MOVE_INVENTORY_ORDER:
                            num = 6;
                            break;
                        case ShenZhenLocalInventoryCertificateType.ADJUSTMENTINVENTORY_ORDER:
                            num = 7;
                            break;
                        case ShenZhenLocalInventoryCertificateType.RETURN_OUT_BOUND_ORDER:
                            num = 8;
                            break;
                        case ShenZhenLocalInventoryCertificateType.BALANCE_IN_BOUND:
                            num = 9;
                            break;
                    }
                    shenZhenLocalOutInBoundInventroy.setCertificateTypeId(num);

                    /**
                     * 凭证号
                     */
                    shenZhenLocalOutInBoundInventroy.setCertificateNumber(new String(ImportExcelUtil.getCellValue(row.getCell(6))));

                    shenZhenLocalOutInBoundInventroy.setSku(row.getCell(7).getStringCellValue());


                    Integer productId = map.get(row.getCell(8).getStringCellValue());
                    shenZhenLocalOutInBoundInventroy.setProductId(productId);

                    /**
                     *  供应商
                     */
                    shenZhenLocalOutInBoundInventroy.setSupplierAbbreviation(row.getCell(9).getStringCellValue());
                    /**
                     * 向别
                     */
                    shenZhenLocalOutInBoundInventroy.setInventoryType(row.getCell(10).getStringCellValue());
                    /**
                     * 入库数量
                     */
                    String  inBoundInventroy = ImportExcelUtil.getCellValue(row.getCell(11));
                    shenZhenLocalOutInBoundInventroy.setInBoundQuantity(StringUtils.isNotBlank(inBoundInventroy) ? Integer.parseInt(inBoundInventroy) : 0);
                    /**
                     * //移仓数量（入）
                     */
                    String  moveOutBoundInventroy = ImportExcelUtil.getCellValue(row.getCell(12));
                    shenZhenLocalOutInBoundInventroy.setMoveInBoundQuantity(StringUtils.isNotBlank(moveOutBoundInventroy) ? Integer.parseInt(moveOutBoundInventroy) :  0);
                    /**
                     * 出库数量
                     */
                    String  outBoundInventroy = ImportExcelUtil.getCellValue(row.getCell(13));
                    shenZhenLocalOutInBoundInventroy.setOutBoundQuantity(StringUtils.isNotBlank(outBoundInventroy) ?  Integer.parseInt(outBoundInventroy) : 0);
                    /**
                     * //移仓数量（出）
                     */
                    String moveOutBoundQuantity = ImportExcelUtil.getCellValue(row.getCell(14));
                    shenZhenLocalOutInBoundInventroy.setMoveOutBoundQuantity(StringUtils.isNotBlank(moveOutBoundQuantity) ? Integer.parseInt(moveOutBoundQuantity) : 0);
                    /**
                     * 备注
                     */
                    shenZhenLocalOutInBoundInventroy.setRemarks(row.getCell(15).getStringCellValue());

                    //System.out.println("insert before = " + shenZhenLocalOutInBoundInventroy);

                    shenZhenLocalOutInBoundInventroyMapperEx.insert(shenZhenLocalOutInBoundInventroy);
                }

                i++;
            }
            System.out.println("插入 成功 ");
            resultBean.setMsg("成功");
        }catch (Exception e){
            System.out.println("出现错误");
            resultBean.setMsg("失败");
            resultBean.setCode(500);
            e.printStackTrace();
            return resultBean;
        }

        return resultBean;
    }

   // @Transactional
    public ResultBean saveSheet0(Sheet sheet0, Map<String, Integer> map) {
        ResultBean resultBean = new ResultBean();

        Iterator<Row> rowIterator =  sheet0.iterator();

        int i = 1;
        try {
            while (rowIterator.hasNext()){
              //  System.out.println("=====================");


                Row row = rowIterator.next();

                if(StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(1)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(4)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(5)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(6)))
                        && StringUtils.isBlank(ImportExcelUtil.getCellValue(row.getCell(7)))

                ){
                    break;
                }

                if(i > 1){
                    ShenZhenLocalOutInBoundInventroy shenZhenLocalOutInBoundInventroy = new ShenZhenLocalOutInBoundInventroy();
                    System.out.println("111111111=====================" + i);

                    shenZhenLocalOutInBoundInventroy.setAutoId(i);
                    shenZhenLocalOutInBoundInventroy.setTransactionType(1);
                    /**
                     * 收货日期
                     */
                    shenZhenLocalOutInBoundInventroy.setReceiptDate(row.getCell(0) != null  ? row.getCell(0).getDateCellValue() : null);
                    /**
                     * 出入库日期
                     *
                     */
//                    Date date = row.getCell(1).getDateCellValue();
                    String dateStr = ImportExcelUtil.getCellValue(row.getCell(1));
                    Date date = StringUtils.isNotBlank(dateStr) ? HSSFDateUtil.getJavaDate(Double.parseDouble(dateStr)) : null;
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    System.out.println("出入库日期 = " + date );
//                    shenZhenLocalOutInBoundInventroy.setOutInBoundDate(sdf.parse(date));
                    shenZhenLocalOutInBoundInventroy.setOutInBoundDate(date);
                    /**
                     * 送货单号
                     */

                    shenZhenLocalOutInBoundInventroy.setDeliveryNoteNumber(ImportExcelUtil.getCellValue(row.getCell(2)));
                    /**
                     * 送检单号
                     */
                    shenZhenLocalOutInBoundInventroy.setSendCheckNumber(ImportExcelUtil.getCellValue(row.getCell(2)));


                    /**
                     * 出入库类型
                     */
                    String outInBoundType =  row.getCell(4).getStringCellValue();

                    int num = 0;
                    switch (outInBoundType){
                        case ShenZhenLocalInventoryOutInBoundType
                                .IN_BOUND:
                            num = 1;
                            break;
                        case ShenZhenLocalInventoryOutInBoundType
                                .OUT_BOUND:
                            num = 2;
                            break;
                        case ShenZhenLocalInventoryOutInBoundType
                                .ADJUSTMENTINVENTORY_WAREHOUSE:
                            num = 3;
                            break;
                    }
                    shenZhenLocalOutInBoundInventroy.setOutInBoundTypeId(num);


                    /**
                     * 凭证类型
                     */
                    String certificateType =  row.getCell(5).getStringCellValue();
                    num = 0;
                    switch (certificateType){

                        case ShenZhenLocalInventoryCertificateType.IN_BOUND_ORDER:
                            num = 1;
                            break;

                        case ShenZhenLocalInventoryCertificateType.OHTER_IN_BOUND_ORDER:
                            num = 2;
                            break;

                        case ShenZhenLocalInventoryCertificateType.OUT_BOUND:
                            num = 3;
                            break;

                        case ShenZhenLocalInventoryCertificateType.OUT_BOUND_ORDER:
                            num = 4;
                            break;
                        case ShenZhenLocalInventoryCertificateType.OHTER_OUT_BOUND_ORDER:
                            num = 5;
                            break;
                        case ShenZhenLocalInventoryCertificateType.MOVE_INVENTORY_ORDER:
                            num = 6;
                            break;
                        case ShenZhenLocalInventoryCertificateType.ADJUSTMENTINVENTORY_ORDER:
                            num = 7;
                            break;
                        case ShenZhenLocalInventoryCertificateType.RETURN_OUT_BOUND_ORDER:
                            num = 8;
                            break;
                        case ShenZhenLocalInventoryCertificateType.BALANCE_IN_BOUND:
                            num = 9;
                            break;
                    }
                    shenZhenLocalOutInBoundInventroy.setCertificateTypeId(num);

                    /**
                     * 凭证号
                     */
                    shenZhenLocalOutInBoundInventroy.setCertificateNumber(new String(ImportExcelUtil.getCellValue(row.getCell(6))));

                    shenZhenLocalOutInBoundInventroy.setSku(row.getCell(7).getStringCellValue());


                    Integer productId = map.get(row.getCell(8).getStringCellValue());
                    shenZhenLocalOutInBoundInventroy.setProductId(productId);

                    /**
                     *  供应商
                     */
                    shenZhenLocalOutInBoundInventroy.setSupplierAbbreviation(row.getCell(9).getStringCellValue());
                    /**
                     * 向别
                     */
                    shenZhenLocalOutInBoundInventroy.setInventoryType(row.getCell(10).getStringCellValue());
                    /**
                     * 入库数量
                     */
                    String  inBoundInventroy = ImportExcelUtil.getCellValue(row.getCell(11));
                    shenZhenLocalOutInBoundInventroy.setInBoundQuantity(StringUtils.isNotBlank(inBoundInventroy) ? Integer.parseInt(inBoundInventroy) : 0);
                    /**
                     * //移仓数量（入）
                     */
                    String  moveOutBoundInventroy = ImportExcelUtil.getCellValue(row.getCell(12));
                    shenZhenLocalOutInBoundInventroy.setMoveInBoundQuantity(StringUtils.isNotBlank(moveOutBoundInventroy) ? Integer.parseInt(moveOutBoundInventroy) :  0);
                    /**
                     * 出库数量
                     */
                    String  outBoundInventroy = ImportExcelUtil.getCellValue(row.getCell(13));
                    shenZhenLocalOutInBoundInventroy.setOutBoundQuantity(StringUtils.isNotBlank(outBoundInventroy) ?  Integer.parseInt(outBoundInventroy) : 0);
                    /**
                     * //移仓数量（出）
                     */
                    String moveOutBoundQuantity = ImportExcelUtil.getCellValue(row.getCell(14));
                    shenZhenLocalOutInBoundInventroy.setMoveOutBoundQuantity(StringUtils.isNotBlank(moveOutBoundQuantity) ? Integer.parseInt(moveOutBoundQuantity) : 0);
                    /**
                     * 备注
                     */
                    shenZhenLocalOutInBoundInventroy.setRemarks(row.getCell(15).getStringCellValue());

                    //System.out.println("insert before = " + shenZhenLocalOutInBoundInventroy);

                    shenZhenLocalOutInBoundInventroyMapperEx.insert(shenZhenLocalOutInBoundInventroy);
                }




//                if(i == 25310){
//                    break;
//                }
                i++;
            }
            System.out.println("插入 成功 ");
            resultBean.setMsg("成功");
        }catch (Exception e){
            resultBean.setMsg("失败");
            System.out.println("出现错误");
            resultBean.setCode(500);
            e.printStackTrace();
            return resultBean;
        }

        return resultBean;

    }


    @ApiOperation(value = "生成本地深圳仓临时表数据")
    @RequestMapping(value = "/generateShenZhenLocalInventoryTempTable", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean generateShenZhenLocalInventoryTempTable() throws IOException {

        ResultBean resultBean = new ResultBean();
        //1: 生成深圳本地仓临时表

        /**
         * 1:查询 sz_local_out_in_bound_inventroy 表数据 productId列表
         */
        List<Integer> productIdList  = shenZhenLocalOutInBoundInventroyMapperEx.findProductIdList();

        /**
         * 2:通过productId 更新对应productId 总UPC 和总FNSKU库存
         */
        try{
            //2.1 UPC


            amzShenZhenLocalInventroyTempEx.upcInsertOrUpdateByProductIdList(productIdList);

            //2.2 FNSKU
            amzShenZhenLocalInventroyTempEx.fnskuInsertOrUpdateByProductIdList(productIdList);


            resultBean.setMsg("生产临时表成功");
        }catch (Exception e){
            resultBean.setMsg("生产临时表失败");
            resultBean.setCode(500);
            e.printStackTrace();
        }






        return resultBean;

    }


}

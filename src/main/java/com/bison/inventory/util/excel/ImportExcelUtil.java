package com.bison.inventory.util.excel;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import com.bison.inventory.constant.ShenZhenLocalInventoryCertificateType;
import com.bison.inventory.constant.ShenZhenLocalInventoryOutInBoundType;
import com.bison.inventory.pojo.Product;
import com.bison.inventory.pojo.ShenZhenLocalOutInBoundInventroy;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.crypto.Data;


public class ImportExcelUtil {
    private static final Logger logger = LoggerFactory.getLogger(ImportExcelUtil.class);

    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    /**
     * 读入excel文件，解析后返回
     * @param file
     * @throws IOException
     */
    public static List<String[]> readExcel(MultipartFile file) throws IOException {
        //检查文件
        checkFile(file);
        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<String[]>();
        if(workbook != null){
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum  = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if(row == null){
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    //循环当前行
                    for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
            }
            // workbook.close();
        }
        return list;
    }
    public static void checkFile(MultipartFile file) throws IOException{
        //判断文件是否存在
        if(null == file){
            logger.info("文件不存在！");
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){
            logger.info(fileName + "不是excel文件");
            throw new IOException(fileName + "不是excel文件");
        }
    }
    public static Workbook getWorkBook(MultipartFile file) {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(fileName.endsWith(xls)){
                //2003
                workbook = new HSSFWorkbook(is);
            }else if(fileName.endsWith(xlsx)){
                //2007 及2007以上
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return workbook;
    }
    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
//        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
//            cell.setCellType(Cell.CELL_TYPE_STRING);
//            cell.setCellValue((int)cell.getNumericCellValue() == cell.getNumericCellValue()  ? (int)cell.getNumericCellValue() : cell.getNumericCellValue());
//
//        }
        //判断数据的类型
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date tempValue = cell.getDateCellValue();
                    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    cellValue = simpleFormat.format(tempValue);
                }else {

                    //double
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    //只有int
                    if((int)cell.getNumericCellValue() == cell.getNumericCellValue()){
                        cellValue =  String.valueOf((int)cell.getNumericCellValue());
                    }



                }
                break;
            case Cell.CELL_TYPE_STRING: //字符串

                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }



    public static List<String> getTableHeadLsit(Sheet sheet){
        if(sheet != null){

            Row row = sheet.getRow(0);

            Iterator<Cell> cellIterator =  row.cellIterator();

            List<String> list = new ArrayList<>();
            while (cellIterator.hasNext()){
                Cell cell  = cellIterator.next();
                list.add(cell.getStringCellValue());
            }




//                  resultBean.setData(JSON.parseObject(json,ArrayList.class));
            return list;
        }

        return null;
    }

    public static void main(String[] args) {
        List<String> listA = new ArrayList<String>();
        List<String> listB = new ArrayList<String>();
        listA.add("A");
        listA.add("B");

        listB.add("B");
        listB.add("C");
     //   listA.retainAll(listB);
        System.out.println("A"+listA);
        System.out.println("B"+listB);


        List<String> copyA = new ArrayList<>();
        copyA.addAll(listA);

        listA.removeAll(listB);


        listB.removeAll(copyA);
        //listA.addAll(listB);
        System.out.println("A和B的差:" + listA);
        System.out.println("B和A的差"+ listB);
    }



    private LinkedHashMap<String, String> rowContents=new LinkedHashMap<String, String>();
    private  SheetHandler sheetHandler;

    public LinkedHashMap<String, String> getRowContents() {
        return rowContents;
    }
    public void setRowContents(LinkedHashMap<String, String> rowContents) {
        this.rowContents = rowContents;
    }

    public SheetHandler getSheetHandler() {
        return sheetHandler;
    }
    public void setSheetHandler(SheetHandler sheetHandler) {
        this.sheetHandler = sheetHandler;
    }

    public XMLReader fetchSheetParser(SharedStringsTable sst) throws Exception {
        XMLReader parser =
                XMLReaderFactory.createXMLReader(
                        "com.sun.org.apache.xerces.internal.parsers.SAXParser"
                );
        setSheetHandler(new SheetHandler(sst));
        ContentHandler handler = (ContentHandler) sheetHandler;
        parser.setContentHandler(handler);
        return parser;
    }


    //处理一个sheet
    public void processOneSheet(String filename) throws Exception {
        InputStream sheet2=null;
        OPCPackage pkg =null;
        try {
            pkg = OPCPackage.open(filename);
            XSSFReader r = new XSSFReader(pkg);
            SharedStringsTable sst = r.getSharedStringsTable();
            XMLReader parser = fetchSheetParser(sst);
            sheet2 = r.getSheet("rId1");
            InputSource sheetSource = new InputSource(sheet2);
            parser.parse(sheetSource);
            setRowContents(sheetHandler.getRowContents());
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally{
            if(pkg!=null){
                pkg.close();
            }
            if(sheet2!=null){
                sheet2.close();
            }
        }
    }
    //处理多个sheet
    public void processAllSheets(String filename) throws Exception {
        OPCPackage pkg =null;
        InputStream sheet=null;
        try{
            pkg=OPCPackage.open(filename);
            XSSFReader r = new XSSFReader( pkg );
            SharedStringsTable sst = r.getSharedStringsTable();
            XMLReader parser = fetchSheetParser(sst);
            Iterator<InputStream> sheets = r.getSheetsData();
            while(sheets.hasNext()) {
                System.out.println("Processing new sheet:\n");
                sheet = sheets.next();
                InputSource sheetSource = new InputSource(sheet);
                parser.parse(sheetSource);
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally{
            if(pkg!=null){
                pkg.close();
            }
            if(sheet!=null){
                sheet.close();
            }
        }
    }

    /**
     * 指定页数
     * @param filename
     * @param pageSize
     * @throws Exception
     */
    public void processSheetsByPageSize(String filename, int pageSize) throws Exception {

        OPCPackage pkg =null;
        InputStream sheet2=null;

        try{
            pkg=OPCPackage.open(filename);
            XSSFReader r = new XSSFReader( pkg );
            SharedStringsTable sst = r.getSharedStringsTable();
            XMLReader parser = fetchSheetParser(sst);
            sheet2 = r.getSheet("rId"+pageSize);
            InputSource sheetSource = new InputSource(sheet2);
            parser.parse(sheetSource);
            setRowContents(sheetHandler.getRowContents());
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally{
            if(pkg!=null){
                pkg.close();
            }
            if(sheet2!=null){
                sheet2.close();
            }
        }
    }


    //测试
//    @Test
//    public  void test ()throws Exception {
//
//
//
//        Long time=System.currentTimeMillis();
//        ImportExcelUtil example = new ImportExcelUtil();
//
////        example.processOneSheet("/home/leaderment/桌面/inventory/深圳仓库出入库明细录入表格01月11日.xlsx");
//        example.processOneSheet("c:/Users/31719/Desktop/深圳仓库出入库明细录入表格01月14日.xlsx");
//        Long endtime=System.currentTimeMillis();
//        LinkedHashMap<String, String>  map=example.getRowContents();
//        Iterator<Entry<String, String>> it= map.entrySet().iterator();
//        int count=0;
//        String prePos="";
//        while (it.hasNext()){
//            Map.Entry<String, String> entry=(Map.Entry<String, String>)it.next();
//            String pos=entry.getKey();
//            if(!pos.substring(1).equals(prePos)){
//                prePos=pos.substring(1);
//                count++;
//            }
//            System.out.println(pos+";"+entry.getValue());
//
//            boolean flag = saveDB(pos,entry.getValue());
//
//            if(flag){
//                break;
//            }
//        }
//        System.out.println("解析数据"+count+"条;耗时"+(endtime-time)/1000+"秒");
//    }






}

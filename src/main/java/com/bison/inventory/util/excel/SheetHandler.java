package com.bison.inventory.util.excel;



/**
 SheetHandler  类中处理从excle获取的数据，官方文档中 SheetHandler以内部类形式，为保证更新代码减少内部类class文件忘记打包，改为一般java类
 */
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.xssf.model.SharedStringsTable;
        import org.apache.poi.xssf.usermodel.XSSFRichTextString;
        import org.xml.sax.Attributes;
        import org.xml.sax.SAXException;
        import org.xml.sax.helpers.DefaultHandler;

public class SheetHandler  extends DefaultHandler{

    private SharedStringsTable sst;

    /**
     * 存储cell标签下v标签包裹的字符文本内容
     * 在v标签开始后，解析器自动调用characters()保存到 lastContents
     * 【但】当cell标签的属性 s是 t时, 表示取到的lastContents是 SharedStringsTable 的index值
     * 需要在v标签结束时根据 index(lastContents)获取一次真正的值
     */

    private String lastContents;
    private boolean nextIsString;
    private String  cellPosition;
    private  LinkedHashMap<String, String>rowContents=new LinkedHashMap<String, String>();

    //行数据保存
    private List<String> currentRow;

    //根据dimension得出每行的数据长度
    private int longest;

    //上个有内容的单元格id，判断空单元格
    private String lastCellid;

    //上一行id, 判断空行
    private String lastRowid;

    //解析结果保存
    private List<List<String>> container;

    //单元格内容是SST 的索引
    private boolean isSSTIndex = false;

    public SheetHandler(SharedStringsTable sst, List<List<String>> container) {
        this.sst = sst;
        this.container = container;
    }


    public LinkedHashMap<String, String> getRowContents() {
        return rowContents;
    }

    public void setRowContents(LinkedHashMap<String, String> rowContents) {
        this.rowContents = rowContents;
    }

    public SheetHandler(SharedStringsTable sst) {
        this.sst = sst;
    }

    @Override
    public void startElement(String uri, String localName, String name,
                             Attributes attributes) throws SAXException {
        if(name.equals("c")) {
            //   System.out.print(attributes.getValue("r") + " - ");
            cellPosition=attributes.getValue("r");
            String cellType = attributes.getValue("t");
            String sCellType = attributes.getValue("s");

            if(cellType != null && cellType.equals("s")) {
                nextIsString = true;
            } else {
                nextIsString = false;
            }
        }
        // 清楚缓存内容
        lastContents = "";
    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        if(nextIsString) {
            int idx = Integer.parseInt(lastContents);
            lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
            nextIsString = false;
        }

        if(name.equals("v")) {
//            System.out.println("lastContents:"+cellPosition+";"+lastContents);
            //数据读取结束后，将单元格坐标,内容存入map中
            if(!(cellPosition.length()==2)||(cellPosition.length()==2&&!"1".equals(cellPosition.substring(1)))){//不保存第一行数据
                rowContents.put(cellPosition, lastContents);
            }
        }
    }

    // 判断单元格cell的c标签下是否有v，否则可能数据错位
   // private boolean hasV = false;


//    @Override
//    public void endElement(String uri, String localName, String qName) throws SAXException {
////        System.out.println("endElement:"+qName);
//
//        //行结束,存储一行数据
//        if (qName.equals("row")) {
//
//            //判断最后一个单元格是否在最后，补齐列数
//            //【注意】有的单元格只修改单元格格式，而没有内容，会出现c标签下没有v标签，导致currentRow少
//            if (covertRowIdtoInt(lastCellid) < longest) {
//                int min = Math.min(currentRow.size(), covertRowIdtoInt(lastCellid));
//                for (int i = 0; i < longest - min; i++) {
//                    currentRow.add("");
//                }
//            }
//
//            container.add(currentRow);
//            lastCellid = null;
//        }
//
//        //单元格结束，没有v时需要补位
//        if (qName.equals("c")){
//            if (!hasV) currentRow.add("");
//            hasV = false;
//        }
//
//        //单元格内容标签结束，characters方法会被调用处理内容
//        if (qName.equals("v")) {
//            hasV = true;
//            //单元格的值是SST 的索引
//            if (isSSTIndex) {
//                String sstIndex = lastContents.toString();
//                try {
//                    int idx = Integer.parseInt(sstIndex);
//                    XSSFRichTextString rtss = new XSSFRichTextString(
//                            sst.getEntryAt(idx));
//                    lastContents = rtss.toString();
//                    currentRow.add(lastContents);
//                } catch (NumberFormatException ex) {
//                    System.out.println(lastContents);
//                }
//            } else {
//                currentRow.add(lastContents);
//            }
//
//        }
//
//    }

    /**
     * 列号转数字   AB7-->28 第28列
     *
     * @param cellId 单元格定位id，行列号，AB7
     * @return
     */
    public static int covertRowIdtoInt(String cellId) {
        StringBuilder sb = new StringBuilder();
        String column = "";
        //从cellId中提取列号
        for(char c:cellId.toCharArray()){
            if (Character.isAlphabetic(c)){
                sb.append(c);
            }else{
                column = sb.toString();
            }
        }
        //列号字符转数字
        int result = 0;
        for (char c : column.toCharArray()) {
            result = result * 26 + (c - 'A') + 1;
        }
        return result;
    }


    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        lastContents += new String(ch, start, length);
    }
}


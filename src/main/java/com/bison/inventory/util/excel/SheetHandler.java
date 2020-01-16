package com.bison.inventory.util.excel;



/**
 SheetHandler  类中处理从excle获取的数据，官方文档中 SheetHandler以内部类形式，为保证更新代码减少内部类class文件忘记打包，改为一般java类
 */
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.ss.usermodel.DataFormatter;
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
//            String value = this.getDataValue(lastContents.trim(), "");
//            lastContents = value;
//            System.out.println("lastContents:"+cellPosition+";"+lastContents);
            //数据读取结束后，将单元格坐标,内容存入map中
            if(!(cellPosition.length()==2)||(cellPosition.length()==2&&!"1".equals(cellPosition.substring(1)))){//不保存第一行数据
                //列名
                String pre = cellPosition.substring(0,1);

                if("A".equals(pre) || "B".equals(pre)){
                    Calendar calendar = new GregorianCalendar(1900,0,-1);
                    calendar.add(Calendar.DAY_OF_YEAR,Integer.parseInt(lastContents));
                    //Date d = calendar.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    lastContents =  sdf.format(calendar.getTime());

                }
                rowContents.put(cellPosition, lastContents);
            }
        }
    }




    //用一个enum表示单元格可能的数据类型
    enum CellDataType{
        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER, DATE, NULL
    }

    private CellDataType nextDataType = CellDataType.SSTINDEX;

    private String formatString;

    private final DataFormatter formatter = new DataFormatter();

    private short formatIndex;


    /**
     * 根据数据类型获取数据
     * @param value
     * @param thisStr
     * @return
     */
    public String getDataValue(String value, String thisStr)

    {
        switch (nextDataType)
        {
            //这几个的顺序不能随便交换，交换了很可能会导致数据错误
            case BOOL:
                char first = value.charAt(0);
                thisStr = first == '0' ? "FALSE" : "TRUE";
                break;
            case ERROR:
                thisStr = "\"ERROR:" + value.toString() + '"';
                break;
            case FORMULA:
                thisStr = '"' + value.toString() + '"';
                break;
            case INLINESTR:
                XSSFRichTextString rtsi = new XSSFRichTextString(value.toString());
                thisStr = rtsi.toString();
                rtsi = null;
                break;
            case SSTINDEX:
                String sstIndex = value.toString();
                thisStr = value.toString();
                break;
            case NUMBER:
                if (formatString != null){
                    thisStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString).trim();
                }else{
                    thisStr = value;
                }
                thisStr = thisStr.replace("_", "").trim();
                break;
            case DATE:
                try{
                    thisStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString);
                }catch(NumberFormatException ex){
                    thisStr = value.toString();
                }
                thisStr = thisStr.replace(" ", "");
                break;
            default:
                thisStr = "";
                break;
        }
        return thisStr;
    }




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
        StringBuilder sb = new StringBuilder();
//        for(int i  =0 ; i < ch.length; i++){
//            sb.append(ch[i]);
//        }
//        System.out.println(" str === " + sb.toString() );
        lastContents += new String(ch, start, length);
    }
}


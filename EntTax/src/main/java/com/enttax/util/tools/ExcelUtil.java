package com.enttax.util.tools;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lcyanxi on 17-3-26.
 */
public class ExcelUtil {
    private  static final Logger logger=Logger.getLogger(ExcelUtil.class);

    private static int type;
    private static  Map<Object, Object> map = new HashMap<Object, Object>();

    /**
     * @throws Exception
     *
     * @功能:Excel存放数据
     */
//    public static List<ExcelModel> getExcelModel() throws Exception{
//
//        List<ExcelModel> list= new ArrayList<ExcelModel>();
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
//
//        ExcelModel user1= new ExcelModel(1,"one",16,df.parse("2015-12-1"));
//        ExcelModel user2= new ExcelModel(1,"two",16,df.parse("2015-12-2"));
//        ExcelModel user3= new ExcelModel(1,"three",16,df.parse("2015-12-3"));
//
//        list.add(user1);
//        list.add(user2);
//        list.add(user3);
//
//        return list;
//    }
    /**
     * 创建Excel模板
     *
     * @return
     * @throws Exception
     */
    public static void   CreateExcel(String filePath) throws Exception {
        //1.创建一个workbook,对应一个Excel文件
        HSSFWorkbook wb= new HSSFWorkbook();
        //2.在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet=wb.createSheet("进销项数据表");
        //3.在sheet中添加表头第0行
        HSSFRow row= sheet.createRow((int)0);
        //4.创建单元格,并且设置表头,设置表头居中
        HSSFCellStyle style=wb.createCellStyle();
        //4.1格式居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //4.2单元格信息设置
        HSSFCell cell=row.createCell((short)0);
        cell.setCellValue("项目名称");
        cell.setCellStyle(style);
        cell=row.createCell((short)1);
        cell.setCellValue("类型");
        cell.setCellStyle(style);
        cell = row.createCell((short)2);
        cell.setCellValue("数量");
        cell.setCellStyle(style);
        cell = row.createCell((short)3);
        cell.setCellValue("金额");
        cell.setCellStyle(style);
        cell = row.createCell((short)4);
        cell.setCellValue("月份");
        cell.setCellStyle(style);



        //将文件保存到指定位置
        FileOutputStream fous= new FileOutputStream(filePath);
        //将设置的内容写入到Excel文件中
        wb.write(fous);
        //关闭输出流
        fous.close();

        //5.写入实体数据
//        List<ExcelModel>list= ExcelUtil.getExcelModel();
//
//        for(int i=0;i<list.size();i++){
//
//            row=sheet.createRow((int)i+1);
//
//            ExcelModel stu=(ExcelModel)list.get(i);
//
//            //4.3单元格信息值填写
//            row.createCell((short)0).setCellValue((double)stu.getId());
//            row.createCell((short)1).setCellValue(stu.getName());
//            row.createCell((short)2).setCellValue((double)stu.getAge());
//            cell=row.createCell((short)3);
//            cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(stu.getBirth()));
//        }
    }


    /**
     * 读取excel的数据
     * @param sheetAt
     * @param fileAddress
     * @return
     */
    public static  Map readExcelFile(int sheetAt,String fileAddress){
        long begain = System.currentTimeMillis();
        try {
            if(fileAddress == null || "".equals(fileAddress)){
                return map;
            }
            fileAddress.replace("/", File.separator);
            fileAddress.replace("\\", File.separator);
            File file = new File(fileAddress);
            Workbook wb = WorkbookFactory.create(new FileInputStream(file));
            Sheet sheet = wb.getSheetAt(sheetAt);
            int numRows = sheet.getLastRowNum();
            System.out.println("行："+numRows);
            for(int i = 0; i<numRows;i++){
                //当前行的集合
                List rowList = new ArrayList();
                //获取当前行
                Row row = sheet.getRow(i);
                //获取当前行的单元格数量
                int rowCount = row.getLastCellNum();
                for(int j = 0; j < rowCount;j++){
                    //获取元素
                    Cell cell = row.getCell(j);
                    if(cell==null){
                        continue;
                    }
                    type = cell.getCellType();
                    Object result = null;
                    switch (type) {
                        case Cell.CELL_TYPE_NUMERIC:
                            result = cell.getNumericCellValue();
                            //自定义日期格式
                            if(HSSFDateUtil.isCellDateFormatted(cell)){
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd");
                                Date date = cell.getDateCellValue();
                                result = simpleDateFormat.format(date);
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            result = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_BLANK: //空
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            result = cell.getBooleanCellValue();
                            break;
                        case Cell.CELL_TYPE_ERROR:
                            result = cell.getErrorCellValue();
                            break;
                        case Cell.CELL_TYPE_FORMULA: //公式
                            result = cell.getCellFormula();
                            break;
                    }
                    rowList.add(result);
                }
                map.put(i+1, rowList);

            }

        } catch (Exception e) {
            System.out.println("readExcelFile执行异常："+e);
            logger.info("ExcelUtil的readExcelFile执行异常："+e);
        }
        long end = System.currentTimeMillis();
        System.out.println("use time :"+(end-begain));
        System.out.println(map);
        return map;
    }

}

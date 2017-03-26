package com.enttax.util.tools;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import java.io.FileOutputStream;

/**
 * Created by lcyanxi on 17-3-26.
 */
public class ExcelUtil {
    private  static final Logger logger=Logger.getLogger(ExcelUtil.class);
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
     * 创建Excel
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
}

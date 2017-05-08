package com.enttax.util;

import com.enttax.util.tools.ExcelUtil;
import com.enttax.util.tools.ToolString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;

/**
 * Created by lcyanxi on 17-3-26.
 */
public class ExcelTest {
    public static void main(String[] args) throws Exception {
//        //调用创建Excel的方法
//        HSSFWorkbook wb = ExcelUtil.CreateExcel();
//        //将文件保存到指定位置
//        FileOutputStream fous= new FileOutputStream("/home/lcyanxi/excel/进销项数据表.xls");
//        //将设置的内容写入到Excel文件中
//        wb.write(fous);
//        //关闭输出流
//        fous.close();
    }


    @Test
    public  void getExcelFileTest() {
        String address = "/home/lcyanxi/excel/进销项数据表.xls";
         System.out.println(ExcelUtil.readExcelFile(0,address));
    }

    @Test
    public void regExpValiTest(){
        Double bb=25.1;
       System.out.println(ToolString.regExpVali(""+bb+"",ToolString.regExp_float_1));
    }

}

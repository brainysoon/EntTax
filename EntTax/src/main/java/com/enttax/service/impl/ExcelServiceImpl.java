package com.enttax.service.impl;

import com.enttax.dao.LogMapper;
import com.enttax.model.Bill;
import com.enttax.service.BillService;
import com.enttax.service.ExcelService;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.ToolDates;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by brainy on 17-5-17.
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private RedisTemplate<String, List<Bill>> template;

    @Resource(name = "redisTemplate")
    private ListOperations<String, List<Bill>> listOps;

    @Autowired
    private BillService billService;

    @Autowired
    private LogMapper logMapper;

    private int outputNumber;
    private int inputNumber;
    @Override
    public List<Bill> readExcelFromInputStream(int bMark, int sheetAt, InputStream is, String extName) throws Exception {

        Workbook wb;

        //判断excel的类型（xls、xlsx、或者其他）
        if (ConstantStr.EXCELTYPEXLS.equals(extName)) {
            wb = new HSSFWorkbook(is);
        } else if (ConstantStr.EXCELTYPEXLSX.equals(extName)) {
            wb = new XSSFWorkbook(is);
        } else {
            throw new Exception("当前文件不是excel文件");
        }

        Sheet sheet = wb.getSheetAt(sheetAt);
        int numRows = sheet.getLastRowNum();
        System.out.println("行：" + numRows);

        //记录记录数
        if (bMark==1){
            outputNumber=numRows;
        }else {
            inputNumber=numRows;
        }

        List<Bill> bills = new ArrayList<>();

        for (int i = 1; i <= numRows; i++) {
            //当前行的集合
            List rowList = new ArrayList();
            //获取当前行
            Row row = sheet.getRow(i);
            //获取当前行的单元格数量
            int cellCount = row.getLastCellNum();
            Bill bill = new Bill();
            for (int j = 0; j < cellCount; j++) {
                //获取元素
                Cell cell = row.getCell(j);
                if (cell == null) {
                    System.out.println("-----------表格中有为空的数据！！--------");
                    return null;
                }

                switch (j) {

                    case 0:
                        bill.setBId(cell.getStringCellValue());
                        break;
                    case 1:
                        bill.setBName(cell.getStringCellValue());
                        break;
                    case 2:
                        bill.setBMonth((int) cell.getNumericCellValue());
                        break;
                    case 3:
                        bill.setBPrice(cell.getNumericCellValue());
                        break;
                }
            }

            bill.setBMark(bMark);
            bill.setBType(bMark == 1 ? TAX_OUT : TAX_IN);
            bill.setBUpdateTime(new Date());

            //为了模拟数据，折算月份成年份
            ToolDates.fixDateBaseMonth(bill);

            bills.add(bill);
        }

        return bills;
    }

    @Override
    public long pushExcelToCache(String key, List<Bill> bills) {

        return listOps.leftPush(key, bills);
    }

    @Override
    public List<Bill> readExcelFromRedis(String key) {

        return listOps.rightPopAndLeftPush(key, key);
    }

    @Transactional
    @Override
    public int moveCacheToDataBase(String key ,Session session) {

        //先从缓存取出
        List<Bill> bills = listOps.leftPop(key);

        if (bills == null || bills.size() == 0) {

            return -1;
        }

        //生成系统日志
        String message=":导入进项数据"+inputNumber+"条,销项数据"+outputNumber+"条";
        logMapper.insert(CommonLog.createLogMessage(message,session));

        //存入MySql数据库
        return billService.insertAll(bills);
    }

    @Override
    public List<Bill> showData() {
        return billService.selectAll();
    }
}


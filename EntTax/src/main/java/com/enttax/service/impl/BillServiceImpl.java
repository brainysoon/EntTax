package com.enttax.service.impl;

import com.enttax.dao.BillMapper;
import com.enttax.model.Bill;
import com.enttax.service.BillService;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.ToolDates;
import com.enttax.util.tools.ToolString;
import com.enttax.vo.BillInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lcyanxi on 17-4-7.
 */
@Service
public class BillServiceImpl implements BillService {

    private  static final String  INPUTDATA="inputdata";
    private  static final String  OUTPUTDATA="outputdata";
    private  static final String  YEAR="year";

    @Autowired
    private BillMapper billMapper;

    public List<Bill> select(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Bill> bills = billMapper.selectAll();
        return bills;
    }


    public boolean insertExcelData(Map<Object, Object> map) {
        for (Map.Entry<Object, Object> entry : map.entrySet()) {

            Bill bill = new Bill();
            bill.setBId(ToolDates.getDate15Num());
            bill.setBUpdateTime(new Date());
            List<String> list = (List<String>) entry.getValue();

            for (int i = 0; i < list.size(); i++) {
                switch (i) {
                    case 0:
                        bill.setBName(list.get(i));
                        break;
                    case 1:
                        bill.setBType(list.get(i));
                        break;
                    case 2:  //将得到的数据先转换成String类项再匹配是否是数字
                        if (ToolString.regExpVali(ToolString.toString(list.get(i)), ToolString.regExp_float_1)) {
//                            bill.setBnum(ToolString.toInteger(list.get(i)));
                        } else {
                            System.out.println("-------" + list.get(i) + "数据类型有错-------");
                            return false;
                        }
                        break;
                    case 3:
                        if (ToolString.regExpVali(ToolString.toString(list.get(i)), ToolString.regExp_float_1)) {
                            bill.setBPrice(ToolString.toDouble(list.get(i)));
                        } else {
                            System.out.println("-------" + list.get(i) + "数据类型有错-------");
                            return false;
                        }
                        break;
                    case 4:
//                        bill.setBmonth(ToolString.toString(list.get(i)));
                        break;
                }

            }
            try {
                billMapper.insert(bill);
            } catch (Exception e) {
                System.out.println("---------数据添加失败----------");
                return false;
            }

            System.out.println("---------数据添加成功----------");
        }
        return true;
    }

    @Override
    public List<Bill> selectAll() {
        return billMapper.selectAll();
    }

    @Override
    public List<Bill> selectByBMark(Integer bMark) {
        return billMapper.selectByBMark(bMark);
    }

    @Override
    public List<Bill> selectByBName(String bName) {
        return billMapper.selectByBName(bName);
    }

    @Override
    public int insertAll(List<Bill> bills) {

        return billMapper.insertAll(bills);
    }

    @Override
    public int deleteBillById(String bId) {
        Bill bill = billMapper.selectByPrimaryKey(bId);
        //假删除，将Mark置为-1
        bill.setBMark(-1);
        return billMapper.updateByPrimaryKey(bill);
    }

    @Override
    public int updateBill(Bill bill) {
        bill.setBUpdateTime(new Date());
        bill.setBMark(0);
        return billMapper.updateByPrimaryKey(bill);
    }

    @Override
    public Map showMonthBill(String year) {

        List<BillInfo> billInfos = billMapper.selectMonthBill(year);
        List inputList = new ArrayList();
        List outputList = new ArrayList();
        Map map = new HashMap<>();
        Map<Integer, Double> inputMap = new HashMap();
        Map<Integer, Double> outputMap = new HashMap();

        int inputindex = 1;
        int outputindex = 1;

        for (BillInfo billInfo : billInfos) {
            //将查出的进销项数据分开
            if (billInfo.getbType().equals(ConstantStr.INPUTDATA)) {
                inputMap.put(billInfo.getbMonth(), billInfo.getTotalPrice());

            } else {
                outputMap.put(billInfo.getbMonth(), billInfo.getTotalPrice());
            }

        }

        //将进项list填充月份的金额值，没有月份的填充0
        for (Integer key : inputMap.keySet()) {
            if (key == inputindex) {
                inputList.add(inputMap.get(key));
                inputindex++;
            } else {
                inputList.add(0);
                inputindex++;
            }
        }
        //将进项数据遍历完的其他月份填充0
        for (; inputindex <= 12; inputindex++) {
            inputList.add(0);
        }

        //将进项list填充月份的金额值，没有月份的填充0
        for (Integer key : outputMap.keySet()) {
            if (key == outputindex) {
                outputList.add(outputMap.get(key));
                outputindex++;
            } else {
                outputList.add(0);
                outputindex++;
            }
        }
        //将销项数据遍历完的其他月份填充0
        for (; outputindex <= 12; outputindex++) {
            outputList.add(0);
        }

        map.put(INPUTDATA, inputList);
        map.put(OUTPUTDATA, outputList);

        return map;
    }


    @Override
    public Map showYearBill() {

        List<BillInfo> billInfos = billMapper.selectYearBill();
        Map map=new HashMap();
        List inputList=new ArrayList();
        List outputList=new ArrayList();
        List year=new ArrayList();
        for (BillInfo billInfo:billInfos){
            if (billInfo.getbType().equals(ConstantStr.INPUTDATA)){
                inputList.add(billInfo.getTotalPrice());
                year.add(billInfo.getbYear());
            }else {
                outputList.add(billInfo.getTotalPrice());
            }
        }
        map.put(YEAR,year);
        map.put(INPUTDATA,inputList);
        map.put(OUTPUTDATA,outputList);

        return map;
    }
}

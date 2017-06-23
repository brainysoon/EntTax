package com.enttax.service.impl;

import com.enttax.dao.BillMapper;
import com.enttax.dao.LogMapper;
import com.enttax.model.Bill;
import com.enttax.service.BillService;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.ToolDates;
import com.enttax.util.tools.ToolString;
import com.enttax.vo.BillInfo;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by lcyanxi on 17-4-7.
 */
@Service
public class BillServiceImpl implements BillService {

    private static final String INPUTDATA = "inputdata";
    private static final String OUTPUTDATA = "outputdata";
    private static final String INPUTNAMES = "inputnames";
    private static final String OUTPUTNAMES = "outputnames";
    private static final String YEAR = "year";

    @Autowired
    private BillMapper billMapper;

    @Autowired
    private LogMapper logMapper;

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

    @Transactional
    @Override
    public int deleteBillById(String bId, Session session) {
        Bill bill = billMapper.selectByPrimaryKey(bId);
        //假删除，将Mark置为-1
        bill.setBMark(-1);

        //生成系统日志
        String message = ":将项目编号为" + bId + "的数据删除";
        logMapper.insert(CommonLog.createLogMessage(message, session));
        return billMapper.updateByPrimaryKey(bill);
    }

    @Transactional
    @Override
    public int updateBill(Bill bill, Session session) {
        Bill preBill = billMapper.selectByPrimaryKey(bill.getBId());

        //标记是否有改变
        boolean flag = false;

        String message = ":将项目编号为" + bill.getBId() + "的";

        if (!preBill.getBName().equals(bill.getBName())) {
            message += "项目名称更改为" + bill.getBName() + ",";
            flag = true;
        }
        if (!preBill.getBMonth().equals(bill.getBMonth())) {
            message += "月份改为" + bill.getBMonth() + ",";
            flag = true;
        }
        if (!preBill.getBPrice().equals(bill.getBPrice())) {
            message += "金额改为" + bill.getBPrice() + ",";
            flag = true;
        }
        if (!preBill.getBType().equals(bill.getBType())) {
            message += "项目类型改为" + bill.getBType() + ",";
            flag = true;
        }

        int result = 0;

        if (flag) {

            //更新日期
            bill.setBUpdateTime(new Date());
            bill.setBMark(1);

            try {
                result = billMapper.updateByPrimaryKey(bill);

            } catch (Exception ex) {
                ex.printStackTrace();

                result = -1;
            }
        }

        if (result > 0) {
            //生成系统日志
            logMapper.insert(CommonLog.createLogMessage(message, session));
        }

        return result;
    }

    @Override
    public Map showMonthBill(String year) {

        List<BillInfo> billInfos = billMapper.selectMonthBill(year);

        Map map = new HashMap<>();
        Map<Integer, Double> inputMap = new HashMap();
        Map<Integer, Double> outputMap = new HashMap();


        for (BillInfo billInfo : billInfos) {
            //将查出的进销项数据分开
            if (billInfo.getbType().equals(ConstantStr.INPUTDATA)) {
                inputMap.put(billInfo.getbMonth(), billInfo.getTotalPrice());

            } else {
                outputMap.put(billInfo.getbMonth(), billInfo.getTotalPrice());
            }

        }

        List inputList = dataConver(inputMap);
        List outputList = dataConver(outputMap);


        map.put(INPUTDATA, inputList);
        map.put(OUTPUTDATA, outputList);

        return map;
    }


    @Override
    public Map showYearBill() {

        List<BillInfo> billInfos = billMapper.selectYearBill();
        Map map = new HashMap();
        List inputList = new ArrayList();
        List outputList = new ArrayList();
        List year = new ArrayList();
        for (BillInfo billInfo : billInfos) {
            if (billInfo.getbType().equals(ConstantStr.INPUTDATA)) {
                inputList.add(billInfo.getTotalPrice());
                year.add(billInfo.getbYear());
            } else {
                outputList.add(billInfo.getTotalPrice());
            }
        }
        map.put(YEAR, year);
        map.put(INPUTDATA, inputList);
        map.put(OUTPUTDATA, outputList);

        return map;
    }

    @Override
    public Map showCategoryName() {

        List inputlist = billMapper.selectAllbName(ConstantStr.INPUTDATA);
        List outputlist = billMapper.selectAllbName(ConstantStr.OUTPUTDATA);
        Map map = new HashMap();
        map.put(INPUTNAMES, inputlist);
        map.put(OUTPUTNAMES, outputlist);
        return map;
    }

    @Override
    public Map showCategoryBill(String year, String inputbName, String outputbName) {


        List<BillInfo> inputlist = billMapper.selectCategoryBill(year, inputbName, ConstantStr.INPUTDATA);
        List<BillInfo> outputlist = billMapper.selectCategoryBill(year, outputbName, ConstantStr.OUTPUTDATA);

        List transfInput = dataTransf(inputlist);
        List transfOutput = dataTransf(outputlist);
        Map map = new HashMap();
        map.put(INPUTDATA, transfInput);
        map.put(OUTPUTDATA, transfOutput);
        return map;
    }

    @Override
    public Map showRateCountBill(String year) {

        List<BillInfo> inputlist = billMapper.selectRateCountBill(year, ConstantStr.INPUTDATA);
        List<BillInfo> outputlist = billMapper.selectRateCountBill(year, ConstantStr.OUTPUTDATA);

        Map map = new HashMap();
        map.put(INPUTDATA, dataToList(inputlist));
        map.put(OUTPUTDATA, dataToList(outputlist));
        return map;
    }


    /**
     * 转换为list对值
     *
     * @param billInfos
     * @return
     */
    private List dataToList(List<BillInfo> billInfos) {
        List list2 = new ArrayList();
        for (BillInfo billInfo : billInfos) {
            List list1 = new ArrayList();
            list1.add(billInfo.getbName());
            list1.add(billInfo.getTotalPrice());
            list2.add(list1);
        }
        return list2;
    }

    /**
     * 转换成页面需要格式的数据————categorycountbill
     *
     * @param list
     * @return
     */
    private List dataTransf(List<BillInfo> list) {

        List arrayList = new ArrayList();
        int index = 1;

        for (BillInfo billInfo : list) {
            //保留小数点2位
            BigDecimal bg = new BigDecimal(billInfo.getTotalPrice());
            double result = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            //将月份的金额数据填充到list集合里
            if (billInfo.getbMonth() == index) {
                arrayList.add(result);
                index++;
            } else {
                //没有月份的数据填充0
                while (index != billInfo.getbMonth()) {
                    arrayList.add(0);
                    index++;
                }
                arrayList.add(result);
                index++;
            }
        }

        for (; index <= 12; index++) {
            arrayList.add(0);
        }
        return arrayList;
    }

    /**
     * 转换成页面需要的格式数据————monthcountbill
     *
     * @param map
     * @return
     */
    private List dataConver(Map<Integer, Double> map) {
        List arrayList = new ArrayList();
        int index = 1;
        //将进项list填充月份的金额值，没有月份的填充0
        for (Integer key : map.keySet()) {
            if (key == index) {
                //保留小数点两位
                arrayList.add(map.get(key));
            } else {
                arrayList.add(0);
            }
            index++;
        }
        //将进项数据遍历完的其他月份填充0
        for (; index <= 12; index++) {
            arrayList.add(0);
        }
        return arrayList;
    }

    @Override
    public List<Bill> getBillByYearAndMonth(String year, String month) {

        return billMapper.selectYearMonthAndBMarkBill(year, month, 1);
    }
}

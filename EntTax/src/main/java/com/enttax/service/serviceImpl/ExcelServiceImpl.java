package com.enttax.service.serviceImpl;

import com.enttax.dao.BillMapper;
import com.enttax.model.Bill;
import com.enttax.service.ExcelService;
import com.enttax.util.tools.ToolDates;
import com.enttax.util.tools.ToolString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lcyanxi on 17-3-28.
 */
@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private BillMapper billMapper;


    public boolean insertExcelData(Map<Object, Object> map) {
        for (Map.Entry<Object, Object> entry : map.entrySet()) {

            Bill bill=new Bill();
            bill.setBid(ToolDates.getDate15Num());
            bill.setBdate(new Date());
            List<String> list=(List<String>) entry.getValue();

            for (int i=0;i<list.size();i++){
                switch (i) {
                    case 0:
                        bill.setBtitle(list.get(i));
                    break;
                    case 1:
                        bill.setBtype(list.get(i));
                        break;
                    case 2:  //将得到的数据先转换成String类项再匹配是否是数字
                        if (ToolString.regExpVali(ToolString.toString(list.get(i)),ToolString.regExp_float_1)){
                            bill.setBnum(ToolString.toInteger(list.get(i)));
                        }else {
                            System.out.println("-------"+list.get(i)+"数据类型有错-------");
                            return false;
                        }
                        break;
                    case 3:
                        if (ToolString.regExpVali(ToolString.toString(list.get(i)),ToolString.regExp_float_1)){
                            bill.setBprice(ToolString.toDouble(list.get(i)));
                        }else {
                            System.out.println("-------"+list.get(i)+"数据类型有错-------");
                            return false;
                        }
                        break;
                    case 4:
                        bill.setBmonth(ToolString.toString(list.get(i)));
                        break;
                }

            }
           try {
               billMapper.insert(bill);
           }catch (Exception e){
               System.out.println("---------数据添加失败----------");
               return false;
           }

            System.out.println("---------数据添加成功----------");
        }
        return true;
    }

}

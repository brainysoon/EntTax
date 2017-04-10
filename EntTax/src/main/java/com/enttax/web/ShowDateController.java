package com.enttax.web;

import com.enttax.model.Bill;
import com.enttax.service.ShowDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by lcyanxi on 17-4-7.
 */
@Controller
public class ShowDateController extends BaseController {
    @Autowired
    private ShowDateService showDateService;

    @RequestMapping(value = "/showDate")
    public Map  showDate(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){

        List<Bill> bills=showDateService.select(pageNum,pageSize);

        System.out.println(bills.size());
        for (Bill bill:bills){
            System.out.println(bill.getBtitle());
        }

        return null;
    }


}

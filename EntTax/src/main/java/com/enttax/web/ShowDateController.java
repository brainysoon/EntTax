package com.enttax.web;

import com.enttax.model.Bill;
import com.enttax.service.ShowDateService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by lcyanxi on 17-4-7.
 */
@Controller
public class ShowDateController extends BaseController {
    @Autowired
    private ShowDateService showDateService;

//    @RequestMapping(value = "/showDate")
//    @ResponseBody
    public Map  showDate(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){

        List<Bill> bills=showDateService.select(pageNum,pageSize);
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("csv",bills);
        System.out.println(map);
        return map;
    }



    @RequestMapping(value = "/showDate")
    @ResponseBody
    public Map  showDate2(){
        System.out.println("aaaaaaaaaaaaaa");
        Map<String, Object> map = new HashMap<String, Object>();
        List list= new ArrayList();
        list.add("aaaaa");
        list.add("bbbbb'");
        list.add("ccccc");
        map.put("csv",list);
        System.out.println(map);
        return map;
    }



}

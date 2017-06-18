package com.enttax.web;

import com.enttax.model.Bill;
import com.enttax.model.Staff;
import com.enttax.service.BillService;
import com.enttax.service.ExcelService;
import com.enttax.util.constant.ConstantCode;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.ToolRandoms;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcyanxi on 17-3-27.
 */
@Controller
@RequestMapping("/bill")
public class BillController extends BaseController {

    private static final Logger logger = Logger.getLogger(BillController.class);

    @Autowired
    private ExcelService excelService;

    @Autowired
    private BillService billService;

    /**
     * excel模板下载
     */
    @RequestMapping(value = "/downloadExcelModel")
    public void downloadExcelModel() {
//        String filePath = session.getServletContext().getRealPath(ConstantStr.EXCELRESOURCEPATH) + ConstantStr.EXCELMODELNAME;
//        try {
//            if (!FiledownUtil.checkExist(filePath)) {
//                ExcelUtil.CreateExcel(filePath);
//            }
//            FiledownUtil.download(filePath, request, response);
//
//        } catch (Exception e) {
//            logger.info("-----" + ExcelController.class.getName() + "downloadExcelModel出现错误------");
//
//        }
    }

    /**
     * excel数据导入
     *
     * @param excelFile
     */
    @RequestMapping(value = "/uploadexcel", method = RequestMethod.POST)
    @ResponseBody
    public Map uploadExcelData(@RequestParam(value = "excelFile") MultipartFile excelFile,
                               @RequestParam(value = "bmark") Integer[] bmark) {

        Map map = new HashMap();

        // 判断文件是否为空
        if (!excelFile.isEmpty()) {

            try {

                //获取文件的后缀名
                String fileName = excelFile.getOriginalFilename();
                int lastIndex = fileName.lastIndexOf(".");
                String extName = fileName.substring(lastIndex);

                // 从文件流中读取
                List<Bill> bills = new ArrayList<>();
                for (int i = 0; i < bmark.length; i++) {

                    bills.addAll(excelService.readExcelFromInputStream(bmark[i], bmark[i], excelFile.getInputStream(), extName));
                }

                //随机生成一个id
                String key = ToolRandoms.randomId20();

                //放入 redis 缓存
                excelService.pushExcelToCache(key, bills);

                //放入model 方便渲染
                map.put(ConstantStr.REDIS_CACHE_KEY, key);
                map.put(ConstantStr.STATUS, ConstantCode.CODE_SUCCESSED);

            } catch (Exception e) {
                e.printStackTrace();
                map.put(ConstantStr.STATUS, ConstantCode.CODE_FAILED);
                map.put(ConstantStr.MESSAGE, e.getMessage());
            }
        }

        return map;
    }

    @RequestMapping(value = "/uploadexcel", method = RequestMethod.GET)
    public  String toUploadExcel(Model model,
                                 @RequestParam(value = "key", required = false) String key) {

        //用户登录信息
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO, staff);

        //查找缓存
        if (!(key == null || key.equals("null"))) {

            List<Bill> bills = excelService.readExcelFromRedis(key);

            model.addAttribute("bills", bills);
            model.addAttribute("key", key);
        }

        return "bill/uploadexcel";
    }

    @RequestMapping(value = "/confirmupload/{key}", method = RequestMethod.GET)
    public String confirmUpload(Model model,
                                 @PathVariable(value = "key") String key) {

        //用户登录信息
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO, staff);

        //查找缓存 并且存入MySql 数据库
        if (!(key == null || key.equals("null"))) {

            try {
                int result = excelService.moveCacheToDataBase(key);
                model.addAttribute(ConstantStr.STATUS, result > 0 ? ConstantCode.CODE_SUCCESSED : ConstantCode.CODE_FAILED);
                model.addAttribute(ConstantStr.MESSAGE,
                        result > 0 ? Constant.UPLOAD_EXCEL_AND_CONFIRM_SUCCESSED : Constant.UPLOAD_EXCEL_AND_CONFIRM_FAILED);
            } catch (Exception ex) {

                model.addAttribute(ConstantStr.STATUS, ConstantCode.CODE_FAILED);
                model.addAttribute(ConstantStr.MESSAGE, ex.getLocalizedMessage());
            }


        }

        return "bill/uploadexcel";
    }

    /**
     * 显示进销项数据列表
     *
     * @return
     */
    @RequestMapping(value = "/managedata", method = RequestMethod.GET)
    public String toManageData(Model model) {
        List<Bill> dataList=excelService.showData();
        System.out.println(dataList);
        model.addAttribute(ConstantStr.STAFFINFO, session.getAttribute(ConstantStr.STAFFINFO));
        model.addAttribute(ConstantStr.DATALIST,dataList);
        return "bill/managedata";
    }

    /**
     * 删除bill数据
     * @param bId
     * @return
     */
    @RequestMapping(value = "/deletebill", method = RequestMethod.GET)
    @ResponseBody
    public Map deleteBill(@RequestParam(value = "bId") String bId){
        Map map=new HashMap();
        System.out.println(bId);

        if (bId == null || bId == ""){
            map.put(ConstantStr.STATUS,ConstantStr.str_zero);
            map.put(ConstantStr.MESSAGE,"对不起你输入的员工序号为空！");
            return map;
        }

        if (billService.deleteBillById(bId)>0){
            map.put(ConstantStr.STATUS,ConstantStr.str_one);
            map.put(ConstantStr.MESSAGE,"恭喜你，操作成功！");
        }else {
            map.put(ConstantStr.STATUS,ConstantStr.str_zero);
            map.put(ConstantStr.MESSAGE,"对不起，操作失败！");
        }

        return map;
    }

    /**
     * 更新bill数据
     * @param bill
     * @return
     */
    @RequestMapping(value = "/updatebill",method = RequestMethod.POST)
    @ResponseBody
    public Map updateBill(Bill bill){
        System.out.println(bill);
        Map map=new HashMap();

        if (bill.getBId() == null || bill.getBId() == ""){
            map.put(ConstantStr.MESSAGE,"对不起项目序号不能为空！");
            return map;
        }
       if (billService.updateBill(bill)>0){
            map.put(ConstantStr.STATUS,ConstantStr.str_one);
            map.put(ConstantStr.MESSAGE,"恭喜你，操作成功！");
        }else {
            map.put(ConstantStr.MESSAGE,"对不起，操作失败！");
        }
        return map;

    }

    /**
     * 跳转到年度统计页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/yearcount",method = RequestMethod.GET)
    public String yearCount(Model model){
        model.addAttribute(ConstantStr.STAFFINFO,session.getAttribute(ConstantStr.STAFFINFO));
        return "bill/yearcount";
    }

    /**
     * 跳转到月度统计页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/monthcount",method = RequestMethod.GET)
    public String monthCount(Model model){
        model.addAttribute(ConstantStr.STAFFINFO,session.getAttribute(ConstantStr.STAFFINFO));
        return "bill/monthcount";
    }

    /**
     * 跳转到分类统计页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/categorycount",method = RequestMethod.GET)
    public String categoryCount(Model model){
        model.addAttribute(ConstantStr.STAFFINFO,session.getAttribute(ConstantStr.STAFFINFO));
        return "bill/categorycount";
    }

    /**
     * 跳转到比率统计页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/ratecount",method = RequestMethod.GET)
    public String rateCount(Model model){
        model.addAttribute(ConstantStr.STAFFINFO,session.getAttribute(ConstantStr.STAFFINFO));
        return "bill/ratecount";
    }


    /**
     * 显示月度统计数据
     * @param year
     * @return
     */
    @RequestMapping(value = "/showmonthbill",method = RequestMethod.GET)
    @ResponseBody
    public Map showMonthBill(@RequestParam(value = "year") String year){

        Map map =billService.showMonthBill(year);

        return  map;
    }

    /**
     * 显示年度统计数据
     * @return
     */
    @RequestMapping(value = "/showyearbill",method = RequestMethod.GET)
    @ResponseBody
    public Map showYearBill(){
        return billService.showYearBill();
    }

    /**
     * 显示进销项名称下拉列表
     * @return
     */
    @RequestMapping(value = "/showbnames",method = RequestMethod.GET)
    @ResponseBody
    public Map showCategoryName(){
        return billService.showCategoryName();
    }

    /**
     * 分类统计数据展示
     * @param year
     * @param inputbName
     * @param outputbName
     * @return
     */
    @RequestMapping(value = "/showcategorybill",method = RequestMethod.POST)
    @ResponseBody
    public Map showCategoryBill(@RequestParam(value = "year") String year,
                                @RequestParam(value = "inputbName") String inputbName,
                                @RequestParam(value = "outputbName") String outputbName){

        System.out.println("---------"+year+inputbName+outputbName);
        Map map=billService.showCategoryBill(year,inputbName,outputbName);
        System.out.println(map);
        return map;
    }

    @RequestMapping(value = "/showratebill",method = RequestMethod.GET)
    @ResponseBody
    public Map showRateCountBill(@RequestParam("year") String year){

        System.out.println(year+"---------------------");
        Map map=billService.showRateCountBill(year);
        System.out.println(map);
        return map;
    }

}

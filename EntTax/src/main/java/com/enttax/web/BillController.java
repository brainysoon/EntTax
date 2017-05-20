package com.enttax.web;

import com.enttax.model.Bill;
import com.enttax.model.Staff;
import com.enttax.service.ExcelService;
import com.enttax.util.constant.ConstantCode;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.ToolRandoms;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    private String toUploadExcel(Model model,
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
    private String confirmUpload(Model model,
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
        model.addAttribute(ConstantStr.STAFFINFO, session.getAttribute(ConstantStr.STAFFINFO));
        return "bill/managedata";
    }

}

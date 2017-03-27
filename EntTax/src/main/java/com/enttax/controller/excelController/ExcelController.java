package com.enttax.controller.excelController;

import com.enttax.controller.permissionController.BaseController;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.ExcelUtil;
import com.enttax.util.tools.FileUploadUtil;
import com.enttax.util.tools.FiledownUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static com.enttax.util.tools.FileUploadUtil.rename;

/**
 * Created by lcyanxi on 17-3-27.
 */
@Controller
public class ExcelController extends BaseController {
   private  static final Logger logger=Logger.getLogger(ExcelController.class);
    @RequestMapping(value = "/downloadExcelModel")
    public void downloadExcelModel(){
        String filePath =session.getServletContext().getRealPath(ConstantStr.EXCELRESOURCEPATH)+ConstantStr.EXCELMODELNAME;
        try {
            if (!FiledownUtil.checkExist(filePath)){
                ExcelUtil.CreateExcel(filePath);
            }
            FiledownUtil.download(filePath, request,response);

        }catch (Exception e){

        }
    }
    @RequestMapping(value = "/uploadExcelDate" ,method = RequestMethod.POST)
    public void uploadExcelData(@RequestParam(value = "excelFile") MultipartFile excelFile){

        // 判断文件是否为空
        if (!excelFile.isEmpty()) {
            if (excelFile.getContentType().contains(ConstantStr.EXCELTYPE)) {
                try {
                    // 文件保存路径
                    String filePath = session.getServletContext().getRealPath("/") + ConstantStr.EXCELRESOURCEPATH;
                    String fileName = rename(excelFile.getOriginalFilename());
                    // 转存文件
                    File dir = new File(filePath);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File file = new File(dir, fileName);
                    excelFile.transferTo(file);
                    ExcelUtil.readExcelFile(0, filePath + fileName);
                    System.out.println("-------文件上传成功！！-----------");
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.info("-------ExcelController的uploadExcelData方法文件上传出错！！---------");
                }
            }
        }
        System.out.println("error");
    }
}

package com.enttax.web;

import com.enttax.service.ExcelService;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.ExcelUtil;
import com.enttax.util.tools.FiledownUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

import static com.enttax.util.tools.FileUploadUtil.rename;

/**
 * Created by lcyanxi on 17-3-27.
 */
@Controller
public class ExcelController extends BaseController {

    private  static final Logger logger=Logger.getLogger(ExcelController.class);
    @Autowired
    private ExcelService excelService;

    /**
     * excel模板下载
     */
    @RequestMapping(value = "/downloadExcelModel")
    public void downloadExcelModel(){
        String filePath =session.getServletContext().getRealPath(ConstantStr.EXCELRESOURCEPATH)+ConstantStr.EXCELMODELNAME;
        try {
            if (!FiledownUtil.checkExist(filePath)){
                ExcelUtil.CreateExcel(filePath);
            }
            FiledownUtil.download(filePath, request,response);

        }catch (Exception e){
            logger.info("-----"+ExcelController.class.getName()+"downloadExcelModel出现错误------");

        }
    }

    /**
     * excel数据导入
     * @param excelFile
     */
    @RequestMapping(value = "/uploadExcelDate" ,method = RequestMethod.POST)
    public void uploadExcelData(@RequestParam(value = "excelFile") MultipartFile excelFile){

        // 判断文件是否为空
        if (!excelFile.isEmpty()) {
            System.out.println("excelFile类型："+excelFile.getContentType());
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

                    Map<Object,Object> map=ExcelUtil.readExcelFile(0, filePath + fileName);
                   if (map!=null){
                       //将上传的数据保存到数据库
                       if (excelService.insertExcelData(map)){
                           System.out.println("-------数据保存成功！！-----------");
                       }else {
                           System.out.println("-------数据保存失败！！-----------");
                       }
                       System.out.println("-------文件上传成功！！-----------");
                   }else {
                       System.out.println("-------文件上传失败！！-----------");
                   }

                } catch (Exception e) {
                    e.printStackTrace();
                    logger.info("-------ExcelController的uploadExcelData方法文件上传出错！！---------");
                }
            }
    }

    @RequestMapping(value = "/downExcelDate")
    @ResponseBody
    public void downExcelDate(){

    }
}

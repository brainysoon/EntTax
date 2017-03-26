package com.enttax.controller.commController;

import com.enttax.controller.permissionController.BaseController;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.ExcelUtil;
import com.enttax.util.tools.FiledownUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lcyanxi on 17-3-26.
 */
@Controller
public class DownloadExcelModel extends BaseController{
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

    public void uploadExcelDate(){

    }
}

package com.enttax.util.tools;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by lcyanxi on 17-3-26.
 */
public class FiledownUtil {
    private static  final Logger logger=Logger.getLogger(FiledownUtil.class);
    /**
     * 文件下载
     * @param path
     * @param request
     * @param response
     */
    public static void download(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header

            String userAgent = request.getHeader("User-Agent");
           //针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE")||userAgent.contains("Trident")) {
                filename = java.net.URLEncoder.encode(filename, "UTF-8");
            } else {
            //非IE浏览器的处理：
                filename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
            }

            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.info("FiledownUtil的download方法报错"+ex);
        }
    }

    /**
     * 判断文件是否存在
     * @param filepath
     * @return
     * @throws Exception
     */
    public static boolean checkExist(String filepath) throws Exception{

        File file=new File(filepath);

        if (file.exists()) {//判断文件目录的存在
            System.out.println("文件夹存在！");
            if(file.isDirectory()){//判断文件的存在性
                System.out.println("该文件路径合法，找到这样的文件！！");
                return true;
            }else{
                System.out.println("创建一个新文件！！");
                file.createNewFile();//创建文件
                return false;
            }
        }else {
            System.out.println("创建一个新的文件夹及文件！！");
            File file2=new File(file.getParent());
            file2.mkdirs();
            return false;
        }

    }





}

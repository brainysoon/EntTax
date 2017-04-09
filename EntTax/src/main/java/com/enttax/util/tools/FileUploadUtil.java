package com.enttax.util.tools;

import com.enttax.util.constant.ConstantStr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by lcyanxi on 17-3-15.
 */
public class FileUploadUtil {

    public static final List<String> ALLOW_TYPES = Arrays.asList(
            "image/jpg","image/jpeg","image/png","image/gif"
    );

    /**
     *
     * @param realPath
     * @param x
     * @param y
     * @param h
     * @param w
     * @param imageFile
     * @return
     * @throws IOException
     */
    public static String uploadHeadImage(String realPath,String x,String y,
                                         String h,String w,MultipartFile imageFile) throws IOException{


        if(imageFile!=null) {
            if (FileUploadUtil.allowUpload(imageFile.getContentType())) {
                String fileName = rename(imageFile.getOriginalFilename());
                int end = fileName.lastIndexOf(".");
                String saveName = fileName.substring(0, end);
                File dir = new File(realPath + ConstantStr.RESOURCEPATH);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, saveName + "_src.jpg");
                imageFile.transferTo(file);
                String srcImagePath = realPath + ConstantStr.RESOURCEPATH + saveName;
                System.out.println("图片存储路劲：" + srcImagePath);
                int imageX = Integer.parseInt(x);
                int imageY = Integer.parseInt(y);
                int imageH = Integer.parseInt(h);
                int imageW = Integer.parseInt(w);
                //这里开始截取操作
                System.out.println("==========imageCutStart=============");
                FileUploadUtil.imgCut(srcImagePath, imageX, imageY, imageW, imageH);
                System.out.println("==========imageCutEnd=============");
//                request.getSession().setAttribute("imgSrc",resourcePath + saveName+"_src.jpg");//成功之后显示用
//                request.getSession().setAttribute("imgCut",resourcePath + saveName+"_cut.jpg");//成功之后显示用
                System.out.println( ConstantStr.RESOURCEPATH  + saveName+"_src.jpg");
                return ConstantStr.RESOURCEPATH  + saveName+"_src.jpg";
            }


        }
        return null;
    }


        /**
         * 文件重命名
         */
    public  static  String rename(String fileName){
        int i = fileName.lastIndexOf(".");
        String str = fileName.substring(i);
        return new Date().getTime()+""+ new Random().nextInt(99999999) +str;
    }
    /**
     * 校验图片类型
     * @param postfix
     * @return
     */
    private static boolean allowUpload(String postfix){
        return ALLOW_TYPES.contains(postfix);
    }
    /**
     * 截取图片
     * @param srcImageFile  原图片地址
     * @param x    截取时的x坐标
     * @param y    截取时的y坐标
     * @param desWidth   截取的宽度
     * @param desHeight   截取的高度
     */
    private static void imgCut(String srcImageFile, int x, int y, int desWidth,
                              int desHeight) {
        try {
            Image img;
            ImageFilter cropFilter;
            BufferedImage bi = ImageIO.read(new File(srcImageFile+"_src.jpg"));
            int srcWidth = bi.getWidth();
            int srcHeight = bi.getHeight();
            if (srcWidth >= desWidth && srcHeight >= desHeight) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight,Image.SCALE_DEFAULT);
                cropFilter = new CropImageFilter(x, y, desWidth, desHeight);
                img = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(desWidth, desHeight,
                        BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null);
                g.dispose();
                //输出文件
                ImageIO.write(tag, "JPEG", new File(srcImageFile+"_cut.jpg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

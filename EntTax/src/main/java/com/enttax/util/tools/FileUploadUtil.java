package com.enttax.util.tools;

import com.enttax.util.constant.ConstantStr;
import org.springframework.web.multipart.MultipartFile;

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
            "image/jpg", "image/jpeg", "image/png", "image/gif"
    );

    /**
     * @param realPath
     * @param imageFile
     * @return
     * @throws IOException
     */
    public static String uploadHeadImage(String realPath, MultipartFile imageFile) throws IOException {


        if (imageFile != null) {
            if (FileUploadUtil.allowUpload(imageFile.getContentType())) {
                String fileName = rename(imageFile.getOriginalFilename());
                int end = fileName.lastIndexOf(".");
                String saveName = fileName.substring(0, end);
                File dir = new File(realPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, saveName + "_src.png");

                imageFile.transferTo(file);

                //给权限
                file.setReadable(true, false);

                return ConstantStr.IMAGE_UPLOAD_DOMAIN + saveName + "_src.png";
            }


        }
        return null;
    }


    /**
     * 文件重命名
     */
    public static String rename(String fileName) {
        int i = fileName.lastIndexOf(".");
        String str = fileName.substring(i);
        return new Date().getTime() + "" + new Random().nextInt(99999999) + str;
    }

    /**
     * 校验图片类型
     *
     * @param postfix
     * @return
     */
    private static boolean allowUpload(String postfix) {
        return ALLOW_TYPES.contains(postfix);
    }


}

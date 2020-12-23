package com.secondhand.xiaoyi.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

/**
 * @ClassName ImgHandlerUtil
 * @Description 使用hutool封装,为用户上传头像和商品图片设计
 * @Author Gaosl
 * @Date 2020/12/6 16:11
 * @Version 1.0
 */
@Slf4j
public class ImgHandlerUtil {

    public static String IMGDIR="F:\\ideaproject\\xiaoyi\\src\\main\\resources\\static";
    /**
     * @author Gaosl
     * @description //写入图像
     * @date 21:01 2020/12/11
     * @param base64ImgStr, imgPartName
     * @return void
     **/
    public static String imgHandlerWrite(String base64ImgStr,String imgPartName){
        String type= getImgType(base64ImgStr);
        String imgName=imgPartName+"."+type;
        base64ImgStr = base64ImgStr.replaceAll("data:image/"+type+";base64,", "");
        BufferedOutputStream out = FileUtil.getOutputStream(IMGDIR+imgName);
        byte[] bytes= Base64.decodeBase64(base64ImgStr);
        IoUtil.write(out,true,bytes);
        return imgName;
    }

    /**
     * @author Gaosl
     * @description //读出图像
     * @date 21:01 2020/12/11
     * @param imgPartName
     * @return void
     **/
    public static String imgHandlerRead(String imgPartName){
        BufferedInputStream in = FileUtil.getInputStream(IMGDIR+imgPartName);
        byte[] bytes = IoUtil.readBytes(in,true);
        String type="jpg";
        for (int i = imgPartName.length()-1; i > 0; i--) {
            if (imgPartName.charAt(i)=='.') {
                type=imgPartName.substring(++i);
                break;
            }
        }
        String headType="data:image/"+type+";base64,";
        String base64ImgStr= headType+Base64.encodeBase64String(bytes);
        return base64ImgStr;
    }

    /**
     * @author Gaosl
     * @description //由base64字符串得到图片格式
     * @date 19:45 2020/12/13
     * @param base64ImgStr
     * @return java.lang.String
     **/
    public static String getImgType(String base64ImgStr){
        int l=0;
        int r=0;
        for (int i = 0; i < base64ImgStr.toCharArray().length; i++) {
            if (base64ImgStr.charAt(i)=='/') {
                l=i+1;
            }
            if (base64ImgStr.charAt(i)==';') {
                r=i;
            }
            if (l>0&&r>0) {
                break;
            }
        }
        return base64ImgStr.substring(l,r);
    }


}

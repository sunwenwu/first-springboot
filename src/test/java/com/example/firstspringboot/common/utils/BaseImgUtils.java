package com.example.firstspringboot.common.utils;

import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @Author :sunwenwu
 * @Date : 2020/1/6 15:07
 * @Description :
 */
public class BaseImgUtils {


    public static void main(String[] args) throws Exception{
        String imgSrc ="";

        generateImage(imgSrc,"D:\\test.jpg");
    }

    public static boolean generateImage(String imgStr, String imgFile) throws Exception {// 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            String imgFilePath = imgFile;// 新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            throw e;
        }

    }
}
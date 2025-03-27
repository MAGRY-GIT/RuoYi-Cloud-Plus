package com.cdzeroly.weather.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author : MGARY
 * @description :
 * @createDate : 2025/3/12 18:09
 */
public class WeatherUtils {


    /**
     *    透明处理
     * @param imagePath
     * @throws IOException
     */
    public static void transparentProcessing(String imagePath) throws IOException {
        //读取图片
        BufferedImage bi = ImageIO.read(new File(imagePath));
        //类型转换
        BufferedImage img = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.drawImage(bi, null, 0, 0);
        //透明处理
        int alpha = 0;
        for(int i=img.getMinY(); i<img.getHeight(); i++){
            for(int j=img.getMinX(); j<img.getWidth(); j++){
                int rgb = img.getRGB(j, i);
                //透明部分不需要处理
                if(rgb < 0){
                    int R = (rgb & 0xff0000) >> 16;
                    int G = (rgb & 0xff00) >> 8;
                    int B = (rgb & 0xff);
                    //将白色剔除
                    Color color = Color.white;
                    if(color.getRed() == R && color.getGreen() == G && color.getBlue() == B){
                        alpha = 0;
                    }
                    else {
                        alpha = 255;
                    }
                    rgb = (alpha << 24) | (rgb & 0x00ffffff);
                    img.setRGB(j, i, rgb);
                }
            }
        }
        //释放资源
        g.dispose();
        ImageIO.write(img, "png", new File(imagePath));
    }


}

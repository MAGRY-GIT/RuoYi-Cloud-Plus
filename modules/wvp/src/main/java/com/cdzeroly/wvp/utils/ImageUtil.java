package com.cdzeroly.wvp.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author MAGRY
 */
public class ImageUtil {

    /**
     * 将网络数据流转换为图片数据
     *
     * @param inputStream 网络数据流
     * @return BufferedImage对象
     * @throws IOException 如果读取或转换过程中发生错误
     */
    public static BufferedImage convertStreamToImage(InputStream inputStream) throws IOException {
        // 将输入流读取为字节数组
        byte[] imageBytes = inputStream.readAllBytes();

        // 将字节数组转换为BufferedImage
        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
        return ImageIO.read(bis);
    }

    /**
     * 将字节数组转换为图片数据
     *
     * @param imageBytes 字节数组
     * @return BufferedImage对象
     * @throws IOException 如果转换过程中发生错误
     */
    public static BufferedImage convertBytesToImage(byte[] imageBytes) throws IOException {
        // 将字节数组转换为BufferedImage
        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
        return ImageIO.read(bis);
    }

    /**
     * 将BufferedImage保存到本地文件
     *
     * @param image BufferedImage对象
     * @param filePath 文件保存路径
     * @param formatName 图片格式（如 "jpg", "png"）
     * @throws IOException 如果保存过程中发生错误
     */
    public static void saveImageToFile(BufferedImage image, String filePath, String formatName) throws IOException {
        File outputFile = new File(filePath);
        ImageIO.write(image, formatName, outputFile);
    }
}

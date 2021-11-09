package com.springboot.start.utils.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author FireWang
 * @date 2020/9/14 18:47
 */
public class ImageUtil {
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
    //缩略图默认长宽限制
    private static final int THUMBNAIL_DEFAULT_LIMIT = 400;

    /**
     * 根据长宽值缩放
     *
     * @author sunk
     */
    public static BufferedImage scaleByWh(BufferedImage source, int width, int height) {
        return getBufferedImageLocal(source.getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    /**
     * 根据长宽限制缩放
     * <p> 限制较大的一边
     *
     * @author sunk
     */
    public static BufferedImage scaleByWhLimit(BufferedImage source, int limit) {
        int scaleW = -1;
        int scaleH = -1;

        if (source.getWidth() > source.getHeight()) {
            scaleW = limit;
        } else {
            scaleH = limit;
        }
        return scaleByWh(source, scaleW, scaleH);
    }

    /**
     * 根据比例缩放
     *
     * @author sunk
     */
    public static BufferedImage scaleByRatio(BufferedImage source, double ratio) {

        int w = (int) (source.getWidth() * ratio);
        int h = (int) (source.getHeight() * ratio);
        return scaleByWh(source, w, h);
    }

    /**
     * 将 Image 转为 BufferedImage
     *
     * @author sunk
     */
    public static BufferedImage getBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bufImage = new BufferedImage(img.getWidth(null),
                img.getHeight(null), BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = bufImage.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return bufImage;
    }

    /**
     * 将 Image 转为 BufferedImage
     *
     * @author sunk
     */
    public static BufferedImage getBufferedImageLocal(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        BufferedImage bufImage = gc.createCompatibleImage(img.getWidth(null),
                img.getHeight(null));

        Graphics2D g2d = bufImage.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return bufImage;
    }

    /**
     * 获取缩略图
     *
     * @author sunk
     */
    public static ByteArrayOutputStream getThumbnail(BufferedImage sourceBi,
                                                     int limit) throws IOException {

        try {
            //缩放
            BufferedImage scaledBi = scaleByWhLimit(sourceBi, limit);

            //压缩
            ByteArrayOutputStream compressedOs = new ByteArrayOutputStream();
            ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
            ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
            jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            jpgWriteParam.setCompressionQuality(0.5f);

            jpgWriter.setOutput(ImageIO.createImageOutputStream(compressedOs));
            jpgWriter.write(null, new IIOImage(scaledBi, null, null), jpgWriteParam);

            return compressedOs;
        } catch (
                Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 获取缩略图
     *
     * @author sunk
     */
    public static ByteArrayOutputStream getThumbnail(BufferedImage sourceBi)
            throws IOException {
        return getThumbnail(sourceBi, THUMBNAIL_DEFAULT_LIMIT);
    }

    /**
     * 获取缩略图
     *
     * @author sunk
     */
    public static ByteArrayOutputStream getThumbnail(File sourceFile, int limit)
            throws IOException {

        return getThumbnail(ImageIO.read(sourceFile), limit);
    }

    /**
     * 获取缩略图
     *
     * @author sunk
     */
    public static ByteArrayOutputStream getThumbnail(File source)
            throws IOException {

        return getThumbnail(ImageIO.read(source));
    }
}

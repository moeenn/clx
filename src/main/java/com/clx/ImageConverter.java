package com.clx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageConverter {
    public void convert(String filepath) throws Exception {
        var inputFile = new File(filepath);
        BufferedImage originalImage = ImageIO.read(inputFile);

        BufferedImage grayImage = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY);

        Graphics g = grayImage.getGraphics();
        g.drawImage(originalImage, 0, 0, null);
        g.dispose();

        File outputFile = new File(filepath);
        ImageIO.write(grayImage, "jpg", outputFile);
    }
}

package edu.school21.printer.logic;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class BmpToPixelMapConverter {
    public static int[][] convert(InputStream is) {
        try {
            BufferedImage bi = ImageIO.read(is);
            int imgHeight = bi.getHeight();
            int imgWidth = bi.getWidth();
            int[][] imagePixelsMap = new int[imgHeight][imgWidth];
            for (int y = 0; y < imgHeight; y++) {
                for (int x = 0; x < imgWidth; x++) {
                    int color = bi.getRGB(x, y);
                    if (color == Color.WHITE.getRGB()) {
                        imagePixelsMap[y][x] = 1;
                    } else {
                        imagePixelsMap[y][x] = 0;
                    }
                }
            }
            return imagePixelsMap;
        } catch (IOException ex) {
            System.err.println(ex);
            return null;
        }
    }
 }
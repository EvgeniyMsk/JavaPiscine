package edu.school21.printer.logic;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Converter {

    private char White;
    private char Black;
    private BufferedImage image;

    public Converter(char white, char black, BufferedImage image) {

        this.Black = black;
        this.White = white;
        this.image = image;
    }

    public void Print() {

        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color color = new Color(image.getRGB(j, i));
                int red = color.getRed();
                if (red == 255) {
                    System.out.print(White);
                } else {
                    System.out.print(Black);
                }
            }
            System.out.println();
        }
    }
}

package edu.school21.printer.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Program {
	public static void main(String[] argv) throws IOException {
		char wPixel = argv[0].split("=")[1].charAt(0);
		char bPixel = argv[1].split("=")[1].charAt(0);
		String fileName = argv[2].split("=")[1];
		BufferedImage image = ImageIO.read(new File(fileName));
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++)
			{
				Color color = new Color(image.getRGB(j, i));
				int red = color.getRed();
				if (red == 255) {
					System.out.print(wPixel);
				} else {
					System.out.print(bPixel);
				}
			}
			System.out.println();
		}
	}
}